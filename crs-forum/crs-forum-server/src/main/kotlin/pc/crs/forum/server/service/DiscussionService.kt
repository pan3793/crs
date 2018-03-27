package pc.crs.forum.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.forum.domain.DiscussionDO
import pc.crs.forum.server.dao.DiscussionDAO

@Service
class DiscussionService(@Autowired override val dao: DiscussionDAO)
    : BaseService<DiscussionDO, DiscussionDO, DiscussionDAO>()