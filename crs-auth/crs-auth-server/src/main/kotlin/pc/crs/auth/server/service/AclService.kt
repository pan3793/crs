package pc.crs.auth.server.service

import pc.crs.auth.common.dto.UserInfo

interface AclService {

    fun checkAnonymous(url: String): Boolean

    fun checkPermission(token: String, url: String): Triple<Int, String, UserInfo?>
}