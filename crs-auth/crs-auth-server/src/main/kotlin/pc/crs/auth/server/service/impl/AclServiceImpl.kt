package pc.crs.auth.server.service.impl

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.util.PathMatcher
import pc.crs.auth.common.dto.UserInfo
import pc.crs.auth.server.dao.AclDAO
import pc.crs.auth.server.dao.UserDAO
import pc.crs.auth.server.dao.UserRoleDAO
import pc.crs.auth.server.service.AclService
import pc.crs.auth.server.service.TokenService
import pc.crs.common.constant.NO_PERMISSION_CODE
import pc.crs.common.constant.SUCCESS_CODE
import pc.crs.common.constant.TOKEN_INVALID_CODE

@Service
class AclServiceImpl(@Autowired val aclDAO: AclDAO,
                     @Autowired val userDAO: UserDAO,
                     @Autowired val userRoleDAO: UserRoleDAO,
                     @Autowired val pathMatcher: PathMatcher,
                     @Autowired val tokenService: TokenService) : AclService {

    companion object {
        const val DEFAULT_ACL_UNMATCHED_ACTION = false
    }

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun checkAnonymous(url: String): Boolean {
        aclDAO.findAll()
                .firstOrNull { pathMatcher.match(it.url, url) }
                ?.let {
                    logger.info("匹配到 acl={}", it)
                    return it.anonymous
                }
        logger.error("无匹配 acl 条目，执行默认行为 DEFAULT_ACL_UNMATCHED_ACTION= {}", DEFAULT_ACL_UNMATCHED_ACTION)
        return DEFAULT_ACL_UNMATCHED_ACTION
    }

    override fun checkPermission(token: String, url: String): Triple<Int, String, UserInfo?> {
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

            val userRoleIds = userRoleDAO.findAllByUserId(userInfo.id!!).map { it.roleId }
            logger.info("用户拥有角色 userRoleIds={}", userRoleIds)

            val aclDO = aclDAO.findAll(Sort.by("priority"))
                    .firstOrNull { pathMatcher.match(it.url, url) }

            val aclRoleIds = aclDO?.roleIds?.split(',')?.filterNot { it.isBlank() }?.map { it.toLong() } ?: emptyList()
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