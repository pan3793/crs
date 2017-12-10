package pc.crs.auth.server.dao

import org.springframework.stereotype.Repository
import pc.crs.auth.domain.UserRoleDO
import pc.crs.common.base.dao.BaseDAO

@Repository
interface UserRoleDAO : BaseDAO<UserRoleDO> {

    fun findAllByUserIdAndEnabled(userId: Long, enabled: Boolean = true): List<UserRoleDO>
}