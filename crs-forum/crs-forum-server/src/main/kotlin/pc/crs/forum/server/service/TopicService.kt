package pc.crs.forum.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.common.constant.BASE_ALLOWED_QUERY_CONDITION_LIST
import pc.crs.forum.domain.TopicDO
import pc.crs.forum.server.dao.TopicDAO

@Service
class TopicService(@Autowired override val dao: TopicDAO)
    : BaseService<TopicDO, TopicDO, TopicDAO>() {

    override val allowedQueryConditions: List<String> = BASE_ALLOWED_QUERY_CONDITION_LIST + listOf(
            "EQ_blockId", "IN_blockId",
            "LIKE_keywords",
            "LIKE_name"
    )
}