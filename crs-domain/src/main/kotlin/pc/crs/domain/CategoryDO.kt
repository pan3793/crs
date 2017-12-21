package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "category")
data class CategoryDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var description: String = ""
) : BaseDO()