package pc.crs.auth.server.service

import pc.crs.auth.common.dto.UserInfo

interface TokenService {

    fun checkToken(clientId: Long, token: String): Pair<Boolean, UserInfo?>

    fun login(clientId: Long, loginName: String, password: String): Pair<Boolean, UserInfo?>

    fun logout(clientId: Long, token: String)
}