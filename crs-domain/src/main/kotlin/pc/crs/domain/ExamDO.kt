package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import java.time.Duration
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "exam", indexes = [
    Index(name = "teacher_id_index", columnList = "teacherId"),
    Index(name = "course_id_index", columnList = "courseId")
])
data class ExamDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false, columnDefinition = "text") var description: String = "",
        @Column(nullable = false) var teacherId: Long = -1,
        @Column(nullable = false) var teacherName: String = "",
        @Column(nullable = false) var courseId: Long = -1,
        @Column(nullable = false) var courseName: String = "",
        @Column(nullable = false) var startTime: LocalDateTime = LocalDateTime.now(),
        @Column(nullable = false) var endTime: LocalDateTime = LocalDateTime.now(),

        @Basic(fetch = FetchType.LAZY) @Column(nullable = false, columnDefinition = "text")
        var generateStrategy: String = "{}",

        @Column(nullable = false) var duration: Duration = Duration.ZERO
) : BaseDO()