package pc.crs.auth.common.context

import org.springframework.core.NamedInheritableThreadLocal
import pc.crs.auth.common.dto.UserInfo

object AuthContextHolder {

    private val userInfoHolder = NamedInheritableThreadLocal<UserInfo>("UserInfoHolder")

    fun getUserInfo(): UserInfo? = userInfoHolder.get()

    fun setUserInfo(userInfo: UserInfo) = userInfoHolder.set(userInfo)

    fun clean() = userInfoHolder.remove()
}