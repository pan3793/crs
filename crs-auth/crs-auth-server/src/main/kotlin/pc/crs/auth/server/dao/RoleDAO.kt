package pc.crs.auth.server.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pc.crs.auth.domain.RoleDO

@Repository
interface RoleDAO : JpaRepository<RoleDO, Long>