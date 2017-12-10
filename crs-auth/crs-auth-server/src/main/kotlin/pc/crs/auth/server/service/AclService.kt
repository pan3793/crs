package pc.crs.auth.server.service

interface AclService {

    fun checkAnonymous(clientId: Long, url: String): Boolean

    fun checkPermission(clientId: Long, token: String, url: String): Boolean
}