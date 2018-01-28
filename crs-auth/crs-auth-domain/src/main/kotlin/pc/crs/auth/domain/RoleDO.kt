package pc.crs.auth.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.*

@Entity
@Table(name = "role")
data class RoleDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
        @Column(nullable = false) var code: String = "",
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var clientId: Long = -1
) : BaseDO()