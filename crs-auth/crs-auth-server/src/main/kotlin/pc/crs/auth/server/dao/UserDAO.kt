package pc.crs.auth.server.dao

import org.springframework.stereotype.Repository
import pc.crs.auth.domain.UserDO
import pc.crs.common.base.dao.BaseDAO

@Repository
interface UserDAO : BaseDAO<UserDO> {

    fun findByClientIdAndLoginNameAndEnabled(clientId: Long, loginName: String, enabled: Boolean = true): UserDO?
}