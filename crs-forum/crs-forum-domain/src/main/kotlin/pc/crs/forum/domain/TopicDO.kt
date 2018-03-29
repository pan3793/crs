package pc.crs.forum.domain

import pc.crs.common.base.domain.BaseDO
import pc.crs.common.convert.LongListJsonConverter
import javax.persistence.*

@Entity
@Table(name = "topic", indexes = [
    Index(name = "block_id_index", columnList = "blockId")
])
data class TopicDO(
        @Column(nullable = false) var blockId: Long = -1,
        @Column(nullable = false) var blockName: String = "",
        @Column(nullable = false) var keywords: String = "",
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false, columnDefinition = "mediumtext") var content: String = "",

        @Convert(converter = LongListJsonConverter::class) @Column(nullable = false)
        var discussionIds: List<Long> = emptyList()
) : BaseDO()