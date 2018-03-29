package pc.crs.forum.server.dto

import pc.crs.forum.domain.DiscussionDO
import java.time.LocalDateTime

data class TopicDetailDTO(var id: Long? = null,
                          var blockId: Long = -1,
                          var blockName: String = "",
                          var keywords: String = "",
                          var name: String = "",
                          var content: String = "",
                          var creator: String = "",
                          var modifier: String = "",
                          var createTime: LocalDateTime? = null,
                          var modifiedTime: LocalDateTime? = null,
                          var discussions: List<DiscussionDO> = emptyList())