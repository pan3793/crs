package pc.crs.auth.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.*

@Entity
@Table(name = "acl", indexes = [
    Index(name = "priority_index", columnList = "priority")
])
data class AclDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false, columnDefinition = "text") var url: String = "",
        @Column(nullable = false) var anonymous: Boolean = false,
        @Column(nullable = false) var permissions: String = "",
        @Column(nullable = false) var roleIds: String = "",
        @Column(nullable = false, columnDefinition = "int COMMENT '数值小优先'") var priority: Int = 100,
        @Column(nullable = false) var clientId: Long = -1
) : BaseDO()