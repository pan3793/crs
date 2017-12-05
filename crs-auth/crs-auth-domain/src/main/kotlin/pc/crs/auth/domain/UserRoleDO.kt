package pc.crs.auth.domain

import pc.crs.common.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user_role")
data class UserRoleDO(
        @Column var userId: Long = -1,
        @Column var roleId: Long = -1
) : BaseDO()