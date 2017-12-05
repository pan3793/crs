package pc.crs.auth.domain

import pc.crs.common.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "res")
data class ResDO(
        @Column var name: String = "",
        @Column var parentId: Long = -1,
        @Column var url: String = "",
        @Column var clientId: Long = -1
) : BaseDO()