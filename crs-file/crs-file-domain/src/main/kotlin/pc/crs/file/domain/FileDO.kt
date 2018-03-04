package pc.crs.file.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Table


@Table(name = "file")
data class FileDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var type: String = "",
        @Column(nullable = false, columnDefinition = "text") var url: String = ""
) : BaseDO()