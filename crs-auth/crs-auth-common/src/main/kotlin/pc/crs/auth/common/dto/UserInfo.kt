package pc.crs.auth.common.dto

data class UserInfo(
        var id: Long? = null,
        var name: String = "",
        var loginName: String = "",
        var affirmative: Boolean = false,
        var token: String = "",
        val roles: List<String> = emptyList(),
        val resTree: ResTree = ResTree()
)