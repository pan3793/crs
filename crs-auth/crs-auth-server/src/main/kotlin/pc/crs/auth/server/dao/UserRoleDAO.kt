package pc.crs.auth.server.dao

import org.springframework.stereotype.Repository
import pc.crs.auth.domain.UserRoleDO
import pc.crs.common.base.dao.BaseDAO

@Repository
interface UserRoleDAO : BaseDAO<UserRoleDO> {

    fun findByUserId(userId: Long): List<UserRoleDO>

    fun findByRoleId(roleId: Long): List<UserRoleDO>

    fun findByUserIdAndRoleIdIn(userId: Long, roleIds: List<Long>): List<UserRoleDO>
}