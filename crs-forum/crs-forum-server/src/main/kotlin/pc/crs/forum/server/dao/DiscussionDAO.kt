package pc.crs.forum.server.dao

import org.springframework.stereotype.Repository
import pc.crs.common.base.dao.BaseDAO
import pc.crs.forum.domain.DiscussionDO

@Repository
interface DiscussionDAO : BaseDAO<DiscussionDO> {
    fun findAllByIdIn(ids: List<Long>): List<DiscussionDO>
}