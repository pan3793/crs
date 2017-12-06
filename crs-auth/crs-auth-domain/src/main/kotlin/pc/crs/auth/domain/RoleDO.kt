package pc.crs.auth.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "role")
data class RoleDO(
        @Column(nullable = false) var code: String = "",
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var clientId: Long = -1
) : BaseDO()