package pc.crs.auth.common.dto

import java.time.LocalDate

data class UserInfo(
        val id: Long = -1,
        val code: String = "",
        val name: String = "",
        val gender: String = "unknown",
        val loginName: String = "",
        val birthday: LocalDate? = null,
        val phone: String = "",
        val email: String = "",
        val description: String = "",
        var token: String = "",
        val roles: List<String> = emptyList(),
        val resTree: ResTree = ResTree()
)