package pc.crs.forum.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.*

@Entity
@Table(name = "topic", indexes = [
    Index(name = "block_id_index", columnList = "blockId")
])
data class TopicDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
        @Column(nullable = false) var blockId: Long = -1,
        @Column(nullable = false) var keywords: String = "",
        @Column(nullable = false) var title: String = "",
        @Column(nullable = false) var discussionIds: String = ""
) : BaseDO()