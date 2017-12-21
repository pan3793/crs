package pc.crs.forum.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "block")
data class BlockDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false, columnDefinition = "text") var description: String = ""
) : BaseDO()