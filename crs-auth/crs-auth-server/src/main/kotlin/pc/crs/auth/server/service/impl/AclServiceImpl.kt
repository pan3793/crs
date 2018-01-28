package pc.crs.auth.server.service.impl

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.util.PathMatcher
import pc.crs.auth.server.dao.AclDAO
import pc.crs.auth.server.dao.UserDAO
import pc.crs.auth.server.dao.UserRoleDAO
import pc.crs.auth.server.service.AclService
import pc.crs.auth.server.service.TokenService

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

    override fun checkAnonymous(clientId: Long, url: String): Boolean {
        aclDAO.findAllByClientId(clientId)
                .firstOrNull { pathMatcher.match(it.url, url) }
                ?.let {
                    logger.info("匹配到 acl={}", it)
                    return it.anonymous
                }
        logger.error("无匹配 acl 条目，执行默认行为 DEFAULT_ACL_UNMATCHED_ACTION= {}", DEFAULT_ACL_UNMATCHED_ACTION)
        return DEFAULT_ACL_UNMATCHED_ACTION
    }

    override fun checkPermission(clientId: Long, token: String, url: String): Boolean {
        val (tokenExist, userInfo) = tokenService.checkToken(clientId, token)
        if (!tokenExist || userInfo == null) {
            logger.error("tokenExist={}，userInfo={}", tokenExist, userInfo)
            return false
        }

        userDAO.findById(userInfo.id).orElse(null)?.let {
            logger.info("找到 user")

            val userRoleIds = userRoleDAO.findAllByUserId(userInfo.id).map { it.roleId }
            logger.info("用户拥有角色 userRoleIds={}", userRoleIds)

            val aclRoleIds = aclDAO.findAllByClientId(clientId, Sort.by("priority"))
                    .firstOrNull { pathMatcher.match(it.url, url) }
                    ?.roleIds?.split(',')?.filterNot { it.isBlank() }?.map { it.toLong() } ?: emptyList()
            logger.info("acl 要求角色 aclRoleIds={}", aclRoleIds)

            return if (aclRoleIds.any { userRoleIds.contains(it) }) {
                logger.info("权限检查通过")
                true
            } else {
                logger.info("权限检查不通过")
                false
            }
        }
        logger.error("user 不存在")
        return false
    }
}