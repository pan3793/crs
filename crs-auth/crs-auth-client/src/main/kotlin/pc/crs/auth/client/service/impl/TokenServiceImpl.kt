package pc.crs.auth.client.service.impl

import org.springframework.stereotype.Service
import pc.crs.auth.client.service.TokenService

@Service
class TokenServiceImpl : TokenService {
    override fun checkToken(token: String): Pair<Boolean, String> {
        return Pair(true, "Token is valid.")
    }
}