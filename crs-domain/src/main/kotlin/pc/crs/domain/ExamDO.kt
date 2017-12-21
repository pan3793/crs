package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import java.time.Duration
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "exam")
class ExamDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var description: String = "",
        @Column(nullable = false) var teacherId: Long = -1,
        @Column(nullable = false) var teacherName: String = "",
        @Column(nullable = false) var courseId: Long = -1,
        @Column(nullable = false) var courseName: String = "",
        @Column(nullable = false) var startTime: LocalDateTime = LocalDateTime.now(),
        @Column(nullable = false) var endTime: LocalDateTime = LocalDateTime.now(),
        @Column(nullable = false) var generateStrategy: String = "{}",
        @Column(nullable = false) var duration: Duration = Duration.ZERO
) : BaseDO()