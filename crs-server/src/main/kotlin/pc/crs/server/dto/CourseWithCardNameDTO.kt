package pc.crs.server.dto

import java.time.LocalDateTime

data class CourseWithCardNameDTO(var id: Long? = null,
                                 var name: String = "",
                                 var categoryId: Long = -1,
                                 var categoryName: String = "",
                                 var teacherId: Long = -1,
                                 var teacherName: String = "",
                                 var description: String = "",
                                 var imageUrl: String = "",
                                 var cards: List<Card> = emptyList(),
                                 var creator: String = "",
                                 var modifier: String = "",
                                 var createTime: LocalDateTime? = null,
                                 var modifiedTime: LocalDateTime? = null,
                                 var note: String = "",
                                 var version: Long? = null) {

    data class Card(var id: Long? = null,
                    var name: String = "")
}





