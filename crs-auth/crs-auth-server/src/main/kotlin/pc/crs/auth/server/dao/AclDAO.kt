package pc.crs.auth.server.dao

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import pc.crs.auth.domain.AclDO
import pc.crs.common.base.dao.BaseDAO

@Repository
interface AclDAO : BaseDAO<AclDO> {

    fun findAllByClientIdAndEnabled(clientId: Long, sort: Sort = Sort.unsorted(), enabled: Boolean = true): List<AclDO>

}