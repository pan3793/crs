package pc.crs.auth.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "role_res")
data class RoleResDO(
        @Column(nullable = false) var roleId: Long = -1,
        @Column(nullable = false) var resId: Long = -1
) : BaseDO()