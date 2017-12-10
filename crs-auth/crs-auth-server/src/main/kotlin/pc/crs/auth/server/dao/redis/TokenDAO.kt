package pc.crs.auth.server.dao.redis

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.keyvalue.repository.KeyValueRepository
import org.springframework.stereotype.Repository
import pc.crs.auth.domain.redis.TokenDO

@Repository
interface TokenDAO : KeyValueRepository<TokenDO, String> {

    fun findAllByClientId(clientId: Long): List<TokenDO>

    fun findAllByClientId(clientId: Long, pageable: Pageable): Page<TokenDO>

    fun findByLoginName(loginName: String): TokenDO?
}