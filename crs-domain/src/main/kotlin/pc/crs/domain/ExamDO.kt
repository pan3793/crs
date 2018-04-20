package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import pc.crs.common.convert.LongListJsonConverter
import javax.persistence.*

@Entity
@Table(name = "exam", indexes = [
    Index(name = "teacher_id_index", columnList = "teacherId"),
    Index(name = "course_id_index", columnList = "courseId")
])
data class ExamDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false, columnDefinition = "text") var description: String = "",
        @Column(nullable = false) var teacherId: Long = -1,
        @Column(nullable = false) var teacherName: String = "",
        @Column(nullable = false) var courseId: Long = -1,
        @Column(nullable = false) var courseName: String = "",

        @Convert(converter = LongListJsonConverter::class) @Column(nullable = false)
        var questionIds: List<Long> = emptyList()
) : BaseDO()