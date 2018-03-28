package pc.crs.forum.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(name = "discussion", indexes = [
    Index(name = "reply_id_index", columnList = "replyId")
])
data class DiscussionDO(
        @Column(nullable = false) var topicId: Long = -1,
        @Column(nullable = false) var replyId: Long = -1,
        @Column(nullable = false, columnDefinition = "mediumtext") var content: String = "{}"
) : BaseDO()