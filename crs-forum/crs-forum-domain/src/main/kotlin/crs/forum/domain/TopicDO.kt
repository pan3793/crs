package crs.forum.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "topic")
data class TopicDO(
        @Column(nullable = false) var blockId: Long = -1,
        @Column(nullable = false) var keywords: String = "",
        @Column(nullable = false) var title: String = "",
        @Column(nullable = false) var discussionIds: String = ""
) : BaseDO()