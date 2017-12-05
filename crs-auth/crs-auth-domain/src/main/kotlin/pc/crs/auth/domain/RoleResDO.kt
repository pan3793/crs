package pc.crs.auth.domain

import pc.crs.common.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "role_res")
class RoleResDO(
        @Column var roleId: Long = -1,
        @Column var resId: Long = -1
) : BaseDO()