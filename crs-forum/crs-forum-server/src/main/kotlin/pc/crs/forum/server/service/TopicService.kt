package pc.crs.forum.server.service

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.common.constant.BASE_ALLOWED_QUERY_CONDITION_LIST
import pc.crs.forum.domain.DiscussionDO
import pc.crs.forum.domain.TopicDO
import pc.crs.forum.server.dao.DiscussionDAO
import pc.crs.forum.server.dao.TopicDAO
import pc.crs.forum.server.dto.TopicDetailDTO

@Service
class TopicService(@Autowired override val dao: TopicDAO,
                   @Autowired val discussionDAO: DiscussionDAO)
    : BaseService<TopicDO, TopicDO, TopicDAO>() {

    override val allowedQueryConditions: List<String> = BASE_ALLOWED_QUERY_CONDITION_LIST + listOf(
            "EQ_blockId", "IN_blockId",
            "LIKE_keywords",
            "LIKE_name"
    )

    fun findTopicDetail(id: Long): TopicDetailDTO = findById(id).let { topicDO ->
        TopicDetailDTO().apply {
            BeanUtils.copyProperties(topicDO, this)
            this.discussions = discussionDAO.findAllByIdIn(topicDO.discussionIds).map { discussionDO ->
                DiscussionDO().apply {
                    BeanUtils.copyProperties(discussionDO, this)
                }
            }
        }
    }

    fun bindDiscussion(id: Long, discussionId: Long) {
        val topicDO = findById(id)
        if (!topicDO.discussionIds.contains(discussionId))
            topicDO.discussionIds += discussionId
        dao.saveAndFlush(topicDO)
    }

    fun removeDiscussion(id: Long, discussionId: Long) {
        val topicDO = findById(id)
        topicDO.discussionIds -= discussionId
        dao.saveAndFlush(topicDO)
    }
}