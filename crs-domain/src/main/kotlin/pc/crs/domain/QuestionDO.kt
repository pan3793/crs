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
@Table(name = "question", indexes = [
    Index(name = "course_id_index", columnList = "courseId")
])
data class QuestionDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
        @Column(nullable = false) var courseId: Long = -1,
        @Column(nullable = false) var score: BigDecimal = BigDecimal.ZERO,

        @Column(nullable = false, columnDefinition = "mediumtext") var ask: String = "{}",
        @Column(nullable = false, columnDefinition = "mediumtext") var answer: String = "{}",

        @Column(nullable = false) var checkType: String = "1",
        @Column(nullable = false) var teacherId: Long = -1,
        @Column(nullable = false) var teacherName: String = "",

        @CreatedBy @Column(nullable = false) var creator: String = "",
        @LastModifiedBy @Column(nullable = false) var modifier: String = "",
        @CreatedDate @Column(nullable = false) var createTime: LocalDateTime?,
        @LastModifiedDate @Column(nullable = false) var modifiedTime: LocalDateTime?,
        @JsonIgnore @Column(nullable = false, columnDefinition = "text") var note: String = "",
        @JsonIgnore @Version @Column(nullable = false) var version: Long?
)