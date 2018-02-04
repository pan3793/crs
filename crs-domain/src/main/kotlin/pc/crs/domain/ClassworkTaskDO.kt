package pc.crs.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import pc.crs.common.base.domain.BaseDO
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "classwork_task", indexes = [
    Index(name = "teacher_id_index", columnList = "teacherId"),
    Index(name = "student_id_index", columnList = "studentId")
])
data class ClassworkTaskDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
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

        @CreatedBy @Column(nullable = false) var creator: String = "",
        @LastModifiedBy @Column(nullable = false) var modifier: String = "",
        @CreatedDate @Column(nullable = false) var createTime: LocalDateTime?,
        @LastModifiedDate @Column(nullable = false) var modifiedTime: LocalDateTime?,
        @JsonIgnore @Column(nullable = false, columnDefinition = "text") var note: String = "",
        @JsonIgnore @Version @Column(nullable = false) var version: Long?
)