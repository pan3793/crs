package pc.crs.auth.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user_role")
data class UserRoleDO(
        @Column(nullable = false) var userId: Long = -1,
        @Column(nullable = false) var roleId: Long = -1
) : BaseDO()