package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(name = "question", indexes = [
    Index(name = "course_id_index", columnList = "courseId")
])
data class QuestionDO(
        @Column(nullable = false) var courseId: Long = -1,
        @Column(nullable = false) var courseName: String = "",
        @Column(nullable = false) var score: BigDecimal = BigDecimal.ZERO,

        @Column(nullable = false, columnDefinition = "mediumtext") var ask: String = "",
        @Column(nullable = false, columnDefinition = "mediumtext") var answer: String = "",

        @Column(nullable = false) var type: String = "",
        @Column(nullable = false) var checkType: String = "1",
        @Column(nullable = false) var teacherId: Long = -1,
        @Column(nullable = false) var teacherName: String = ""
) : BaseDO()