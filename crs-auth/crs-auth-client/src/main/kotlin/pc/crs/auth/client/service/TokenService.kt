package pc.crs.auth.client.service

interface TokenService {

    fun checkToken(token: String): Pair<Boolean, String>
}