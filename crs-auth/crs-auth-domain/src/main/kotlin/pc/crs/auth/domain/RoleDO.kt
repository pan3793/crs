package pc.crs.auth.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.*

@Entity
@Table(name = "role")
data class RoleDO(
        @Column(nullable = false) var code: String = "",
        @Column(nullable = false) var name: String = ""
) : BaseDO()