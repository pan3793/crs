package pc.crs.auth.server.service.impl

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.auth.common.dto.ResTree
import pc.crs.auth.common.dto.UserInfo
import pc.crs.auth.domain.redis.TokenDO
import pc.crs.auth.server.dao.RoleDAO
import pc.crs.auth.server.dao.RoleResDAO
import pc.crs.auth.server.dao.UserDAO
import pc.crs.auth.server.dao.UserRoleDAO
import pc.crs.auth.server.dao.redis.TokenDAO
import pc.crs.auth.server.service.TokenService
import java.util.*

@Service
class TokenServiceImpl(
        @Autowired private val tokenDAO: TokenDAO,
        @Autowired private val userDAO: UserDAO,
        @Autowired private val roleDAO: RoleDAO,
        @Autowired private val resDAO: RoleResDAO,
        @Autowired private val roleResDAO: RoleResDAO,
        @Autowired private val userRoleDAO: UserRoleDAO) : TokenService {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun checkToken(token: String): Pair<Boolean, UserInfo?> {
        tokenDAO.findById(token).orElse(null)?.let {
            logger.info("token={} 在 redis 中找到", token)

            fetchUserInfo(it.userId)?.let {
                it.token = token
                return Pair(true, it)
            }
            return Pair(false, null)
        }
        logger.info("token={} 无效", token)
        return Pair(false, null)
    }

    override fun login(loginName: String, password: String): Pair<Boolean, UserInfo?> {
        userDAO.findByLoginName(loginName)?.let {
            logger.info("user loginName={} 找到", loginName)
            it.id?.let {
                fetchUserInfo(it)?.let {
                    val token = UUID.randomUUID().toString()
                    tokenDAO.save(TokenDO(token, it.id!!).apply { logger.info("生成 token={}", it) })
                    it.token = token
                    return Pair(true, it)
                }
            }
            logger.error("user loginName={} 不存在", loginName)
            return Pair(false, null)
        }

        logger.error("loginName={} 不存在", loginName)
        return Pair(false, null)
    }

    override fun logout(token: String) {
        tokenDAO.deleteById(token)
    }

    // affirmative属性将在checkPermission中设置
    private fun fetchUserInfo(userId: Long): UserInfo? {
        userDAO.findById(userId).orElse(null)?.let {
            logger.info("找到 user={}", it)
            val roleNames = roleDAO.findAllById(userRoleDAO.findAllByUserId(userId).map { it.roleId }).map { it.name }
            return UserInfo(
                    id = it.id,
                    name = it.name,
                    loginName = it.loginName,
                    roles = roleNames,
                    // TODO resTree 构建
                    resTree = ResTree()
            )
        }
        logger.error("user id={} 不存在", userId)
        return null
    }
}
