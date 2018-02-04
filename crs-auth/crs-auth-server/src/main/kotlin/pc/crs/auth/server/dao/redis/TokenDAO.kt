package pc.crs.auth.server.dao.redis

import org.springframework.data.keyvalue.repository.KeyValueRepository
import org.springframework.stereotype.Repository
import pc.crs.auth.domain.redis.TokenDO

@Repository
interface TokenDAO : KeyValueRepository<TokenDO, String> {

    fun findByLoginName(loginName: String): TokenDO?
}