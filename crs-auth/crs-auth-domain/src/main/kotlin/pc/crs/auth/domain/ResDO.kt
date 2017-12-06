package pc.crs.auth.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "res")
data class ResDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var parentId: Long = -1,
        @Column(nullable = false) var url: String = "",
        @Column(nullable = false) var clientId: Long = -1
) : BaseDO()