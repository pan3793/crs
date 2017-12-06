package pc.crs.auth.domain

import pc.crs.common.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "role")
data class RoleDO(
        @Column var code: String = "",
        @Column var name: String = "",
        @Column var clientId: Long = -1
) : BaseDO()