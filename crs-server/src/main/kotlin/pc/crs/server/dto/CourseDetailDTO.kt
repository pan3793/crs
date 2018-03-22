package pc.crs.server.dto

data class CourseDetailDTO(
        var id: Long? = null,
        var name: String = "",
        var categoryId: Long = -1,
        var categoryName: String = "",
        var teacherId: Long = -1,
        var teacherName: String = "",
        var description: String = "",
        var imageUrl: String = "",
        var cards: List<Card> = emptyList()
) {
    data class Card(
            var id: Long? = null,
            var name: String = "",
            var content: String = "",
            var files: List<File> = emptyList()
    ) {
        data class File(
                var id: Long? = null,
                var name: String = "",
                var type: String = "other",
                var url: String = ""
        )
    }
}