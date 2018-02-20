package pc.crs.auth.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.auth.domain.RoleDO
import pc.crs.auth.server.dao.RoleDAO
import pc.crs.common.base.service.BaseService

@Service
class RoleService(@Autowired override var dao: RoleDAO)
    : BaseService<RoleDO, RoleDO, RoleDAO>()