package pc.crs.auth.domain

import pc.crs.common.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "acl")
data class AclDO(
        @Column var name: String,
        @Column var url: String = "",
        @Column var permission: String = "",
        @Column var roleIds: String = "",
        @Column var priority: Int = 100
) : BaseDO()