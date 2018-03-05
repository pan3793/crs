package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "student")
data class StudentDO(
        @Column(nullable = false) var userId: Long = -1,
        @Column(nullable = false) var userName: String = ""
) : BaseDO()