package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "question")
data class QuestionDO(
        @Column(nullable = false) var score: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false) var ask: String = "{}",
        @Column(nullable = false) var answer: String = "{}",
        @Column(nullable = false) var checkType: String = "1",
        @Column(nullable = false) var teacherId: Long = -1,
        @Column(nullable = false) var teacherName: String = ""
) : BaseDO()