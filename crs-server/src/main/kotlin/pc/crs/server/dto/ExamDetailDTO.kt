package pc.crs.server.dto

import java.math.BigDecimal

data class ExamDetailDTO(
        var id: Long? = null,
        var name: String = "",
        var description: String = "",
        var teacherId: Long = -1,
        var teacherName: String = "",
        var courseId: Long = -1,
        var courseName: String = "",
        var questions: List<Question> = emptyList()
) {
    data class Question(
            var id: Long? = null,
            var score: BigDecimal = BigDecimal.ZERO,
            var ask: String = "",
            var answer: String = "",
            var type: String = "",
            var teacherId: Long = -1,
            var teacherName: String = ""
    )
}