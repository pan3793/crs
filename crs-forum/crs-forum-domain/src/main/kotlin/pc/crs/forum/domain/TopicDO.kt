package pc.crs.forum.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(name = "topic", indexes = [
    Index(name = "block_id_index", columnList = "blockId")
])
data class TopicDO(
        @Column(nullable = false) var blockId: Long = -1,
        @Column(nullable = false) var keywords: String = "",
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var discussionIds: String = ""
) : BaseDO()