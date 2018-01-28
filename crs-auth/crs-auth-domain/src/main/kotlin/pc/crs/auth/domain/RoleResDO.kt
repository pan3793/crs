package pc.crs.auth.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.*

@Entity
@Table(name = "role_res", indexes = [
    Index(name = "role_id_index", columnList = "roleId")
])
data class RoleResDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
        @Column(nullable = false) var roleId: Long = -1,
        @Column(nullable = false) var resId: Long = -1
) : BaseDO()