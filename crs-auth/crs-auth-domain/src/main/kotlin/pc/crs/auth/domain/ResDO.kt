package pc.crs.auth.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.*

@Entity
@Table(name = "res")
data class ResDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var parentId: Long = -1,
        @Column(nullable = false, columnDefinition = "text") var url: String = ""
) : BaseDO()