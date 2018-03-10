package pc.crs.auth.server.dto

import java.time.LocalDateTime

data class UserDTO(var id: Long?,
                   var name: String,
                   var loginName: String,
                   var roleIds: List<Long>,
                   var creator: String = "",
                   var modifier: String = "",
                   var createTime: LocalDateTime? = null,
                   var modifiedTime: LocalDateTime? = null,
                   var note: String = "")


