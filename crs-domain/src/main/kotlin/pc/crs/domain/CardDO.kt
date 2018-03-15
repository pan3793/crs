package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import pc.crs.common.convert.LongListJsonConverter
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "card")
data class CardDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false, columnDefinition = "text") var content: String = "",

        @Convert(converter = LongListJsonConverter::class) @Column(nullable = false)
        var fileIds: List<Long> = emptyList()
) : BaseDO()