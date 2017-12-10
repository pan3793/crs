package pc.crs.auth.domain.redis

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.io.Serializable
import java.util.*
import javax.persistence.Id

@RedisHash(value = "crs:auth:token", timeToLive = 30 * 60)
data class TokenDO(@Id val id: String = UUID.randomUUID().toString(),
                   @Indexed val clientId: Long,
                   @Indexed val userId: Long) : Serializable {
    constructor() : this(clientId = -1, userId = -1)
}