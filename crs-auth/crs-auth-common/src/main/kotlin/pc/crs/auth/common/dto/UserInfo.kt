package pc.crs.auth.common.dto

data class UserInfo(
        val id: Long = -1,
        val name: String = "",
        val loginName: String = "",
        var token: String = "",
        val roles: List<String> = emptyList(),
        val resTree: ResTree = ResTree()
)