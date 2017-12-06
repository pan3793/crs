package pc.crs.auth.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "acl")
data class AclDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var url: String = "",
        @Column(nullable = false) var permission: String = "",
        @Column(nullable = false) var roleIds: String = "",
        @Column(nullable = false) var priority: Int = 100,
        @Column(nullable = false) var clientId: Long = -1
) : BaseDO()