package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "exam_task")
data class ExamTaskDO(
        @Column(nullable = false) var classworkId: Long = -1,
        @Column(nullable = false) var courseName: String = "",
        @Column(nullable = false) var teacherId: Long = -1,
        @Column(nullable = false) var teacherName: String = "",
        @Column(nullable = false) var studentId: Long = -1,
        @Column(nullable = false) var studentName: String = "",
        @Column(nullable = false) var releaseTime: LocalDateTime = LocalDateTime.now(),
        @Column(nullable = false) var deadlineTime: LocalDateTime = LocalDateTime.now(),
        @Column(nullable = false) var status: String = "0",
        @Column(nullable = false) var actualScore: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false) var totalScore: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false) var actualCommitTime: LocalDateTime = LocalDateTime.MAX,
        @Column(nullable = false) var checkType: String = "",
        @Column(nullable = false) var checkTime: LocalDateTime = LocalDateTime.MAX,
        @Column(nullable = false) var checkTeacherId: Long = -1,
        @Column(nullable = false) var checkTeacherName: String = "",
        @Column(nullable = false) var reviewTeacherId: Long = -1,
        @Column(nullable = false) var reviewTeacherName: String = "",
        @Column(nullable = false) var maxDuration: Duration = Duration.ZERO,
        @Column(nullable = false) var actualStartTime: LocalDateTime = LocalDateTime.MAX,
        @Column(nullable = false) var actualDuration: Duration = Duration.ZERO
) : BaseDO()