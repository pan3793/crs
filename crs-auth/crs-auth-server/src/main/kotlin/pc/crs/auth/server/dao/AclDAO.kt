package pc.crs.auth.server.dao

import org.springframework.stereotype.Repository
import pc.crs.auth.domain.AclDO
import pc.crs.common.base.dao.BaseDAO

@Repository
interface AclDAO : BaseDAO<AclDO> {

    fun findAllByOrderByPriority() : List<AclDO>
}