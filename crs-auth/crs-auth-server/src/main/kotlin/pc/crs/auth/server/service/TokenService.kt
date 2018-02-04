package pc.crs.auth.server.service

import pc.crs.auth.common.dto.UserInfo

interface TokenService {

    fun checkToken(token: String): Pair<Boolean, UserInfo?>

    fun login(loginName: String, password: String): Pair<Boolean, UserInfo?>

    fun logout(token: String)
}