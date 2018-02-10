package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(name = "solution", indexes = [
    Index(name = "question_id_index", columnList = "questionId")
])
data class SolutionDO(
        @Column(nullable = false) var questionId: Long = -1,

        @Column(nullable = false, columnDefinition = "mediumtext") var content: String = "{}",

        @Column(nullable = false) var studentId: Long = -1,
        @Column(nullable = false) var studentName: String = "",
        @Column(nullable = false) var teacherId: Long = -1,
        @Column(nullable = false) var teacherName: String = "",
        @Column(nullable = false) var actualScore: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false) var totalScore: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false) var status: String = "0"
) : BaseDO()