package pc.crs.auth.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.util.PathMatcher
import pc.crs.auth.common.dto.UserInfo
import pc.crs.auth.domain.AclDO
import pc.crs.auth.server.dao.AclDAO
import pc.crs.auth.server.dao.UserDAO
import pc.crs.auth.server.dao.UserRoleDAO
import pc.crs.common.base.service.BaseService
import pc.crs.common.constant.BASE_ALLOWED_QUERY_CONDITION_LIST
import pc.crs.common.constant.NO_PERMISSION_CODE
import pc.crs.common.constant.SUCCESS_CODE
import pc.crs.common.constant.TOKEN_INVALID_CODE

@Service
class AclService(@Autowired override val dao: AclDAO,
                 @Autowired val userDAO: UserDAO,
                 @Autowired val userRoleDAO: UserRoleDAO,
                 @Autowired val pathMatcher: PathMatcher,
                 @Autowired val tokenService: TokenService)
    : BaseService<AclDO, AclDO, AclDAO>() {

    companion object {
        const val DEFAULT_ACL_UNMATCHED_ACTION = false
    }

    override val allowedQueryConditions: List<String> = BASE_ALLOWED_QUERY_CONDITION_LIST + listOf(
            "EQ_name", "LIKE_name",
            "EQ_url", "LIKE_url",
            "EQ_anonymous",
            "EQ_affirmative"
    )

    override fun findAll(): Iterable<AclDO> {
        return dao.findAllByOrderByPriority().map { convertDO2DTO(it) }
    }

    fun checkAnonymous(url: String): Boolean {
        dao.findAllByOrderByPriority()
                .firstOrNull { pathMatcher.match(it.url, url) }
                ?.let {
                    logger.info("匹配到 acl={}", it)
                    return it.anonymous
                }
        logger.error("无匹配 acl 条目，执行默认行为 DEFAULT_ACL_UNMATCHED_ACTION= {}", DEFAULT_ACL_UNMATCHED_ACTION)
        return DEFAULT_ACL_UNMATCHED_ACTION
    }

    fun checkPermission(token: String, url: String): Triple<Int, String, UserInfo?> {
        val (tokenExist, userInfo) = tokenService.checkToken(token)
        if (!tokenExist) {
            logger.error("tokenExist={}，userInfo={}", tokenExist, userInfo)
            return Triple(TOKEN_INVALID_CODE, "Token无效", null)
        }
        if (userInfo == null) {
            logger.error("tokenExist={}，userInfo={}", tokenExist, userInfo)
            return Triple(TOKEN_INVALID_CODE, "用户Id不存在", null)
        }

        userDAO.findById(userInfo.id!!).orElse(null)?.let {
            logger.info("找到 user")

            val userRoleIds = userRoleDAO.findByUserId(userInfo.id!!).map { it.roleId }
            logger.info("用户拥有角色 userRoleIds={}", userRoleIds)

            val aclDO = dao.findAll(Sort.by("priority"))
                    .firstOrNull { pathMatcher.match(it.url, url) }

            val aclRoleIds = aclDO?.roleIds ?: emptyList()
            logger.info("acl 要求角色 aclRoleIds={}", aclRoleIds)

            if (aclRoleIds.any { userRoleIds.contains(it) }) {
                logger.info("权限检查通过")
                userInfo.affirmative = aclDO!!.affirmative
                return Triple(SUCCESS_CODE, "权限检查通过", userInfo)
            } else {
                logger.info("权限检查不通过")
                return Triple(NO_PERMISSION_CODE, "权限检查不通过", null)
            }
        }
        logger.error("user 不存在")
        return Triple(TOKEN_INVALID_CODE, "user 不存在", null)
    }
}