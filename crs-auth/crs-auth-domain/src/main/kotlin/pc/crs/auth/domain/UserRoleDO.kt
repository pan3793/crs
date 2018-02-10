package pc.crs.auth.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.*

@Entity
@Table(name = "user_role", indexes = [
    Index(name = "user_id_index", columnList = "userId")
])
data class UserRoleDO(
        @Column(nullable = false) var userId: Long = -1,
        @Column(nullable = false) var roleId: Long = -1
) : BaseDO()