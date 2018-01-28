package pc.crs.auth.server.dao

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import pc.crs.auth.domain.AclDO
import pc.crs.common.base.dao.BaseDAO

@Repository
interface AclDAO : BaseDAO<AclDO> {

    fun findAllByClientId(clientId: Long, sort: Sort = Sort.unsorted()): List<AclDO>

}