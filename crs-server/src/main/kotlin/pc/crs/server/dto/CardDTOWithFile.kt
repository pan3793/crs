package pc.crs.server.dto

import java.time.LocalDateTime

data class CardDTOWithFile(var id: Long? = null,
                           var name: String = "",
                           var content: String = "",
                           var files: List<File> = emptyList(),
                           var creator: String = "",
                           var modifier: String = "",
                           var createTime: LocalDateTime? = null,
                           var modifiedTime: LocalDateTime? = null,
                           var note: String = "",
                           var version: Long? = null) {

    data class File(var id: Long? = null,
                    var name: String = "",
                    var type: String = "",
                    var url: String = "")
}