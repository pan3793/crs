package pc.crs.forum.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.*

@Entity
@Table(name = "discussion", indexes = [
    Index(name = "reply_id_index", columnList = "replyId")
])
data class DiscussionDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
        @Column(nullable = false) var replyId: Long = -1,
        @Column(nullable = false) var userId: Long = -1,
        @Column(nullable = false) var userName: String = "",
        @Column(nullable = false, columnDefinition = "mediumtext") var content: String = "{}"
) : BaseDO()