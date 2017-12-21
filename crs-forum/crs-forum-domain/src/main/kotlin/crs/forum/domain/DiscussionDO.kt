package crs.forum.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "discussion")
data class DiscussionDO(
        @Column(nullable = false) var replyId: Long = -1,
        @Column(nullable = false) var userId: Long = -1,
        @Column(nullable = false) var userName: String = "",
        @Column(nullable = false) var content: String = ""
) : BaseDO()