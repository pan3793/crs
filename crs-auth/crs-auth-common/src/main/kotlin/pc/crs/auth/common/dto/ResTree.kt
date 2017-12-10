package pc.crs.auth.common.dto

data class ResTree(
        val id: Long = -1,
        val parentId: Long = -1,
        val name: String = "",
        val url: String = "",
        val children: List<ResTree> = emptyList()
)