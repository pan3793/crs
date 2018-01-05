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
import pc.crs.auth.server.dao.redis.TokenDAO
import pc.crs.auth.server.service.TokenService
import java.util.*

@Service
class TokenServiceImpl(
        @Autowired val tokenDAO: TokenDAO,
        @Autowired val userDAO: UserDAO,
        @Autowired val roleDAO: RoleDAO,
        @Autowired val resDAO: RoleResDAO,
        @Autowired val roleResDAO: RoleResDAO,
        @Autowired val userRoleResDAO: RoleResDAO) : TokenService {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun checkToken(clientId: Long, token: String): Pair<Boolean, UserInfo?> {
        (tokenDAO.findById(token) as TokenDO?)?.let {
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

    override fun login(clientId: Long, loginName: String, password: String): Pair<Boolean, UserInfo?> {
        userDAO.findByClientIdAndLoginNameAndEnabled(clientId, loginName)?.let {
            logger.info("user clientId={}，loginName={} 找到", clientId, loginName)
            it.id?.let {
                fetchUserInfo(it)?.let {
                    val token = UUID.randomUUID().toString()
                    tokenDAO.save(TokenDO(token, clientId, it.id).apply { logger.info("生成 token={}", it) })
                    it.token = token
                    return Pair(true, it)
                }
            }
            logger.error("user clientId={}，loginName={} 不存在或被禁用", clientId, loginName)
            return Pair(false, null)
        }

        logger.error("clientId={}，loginName={} 不存在或被禁用", clientId, loginName)
        return Pair(false, null)
    }

    override fun logout(clientId: Long, token: String) {
        tokenDAO.deleteById(token)
    }

    private fun fetchUserInfo(userId: Long): UserInfo? {
        userDAO.findByIdAndEnabled(userId)?.let {
            logger.info("找到 user={}", it)
            return UserInfo(
                    id = it.id ?: -1,
                    name = it.name,
                    loginName = it.loginName,
                    // TODO roles and resTree 构建
                    roles = emptyList(),
                    resTree = ResTree()
            )
        }
        logger.error("user id={} 不存在或被禁用", userId)
        return null
    }
}
