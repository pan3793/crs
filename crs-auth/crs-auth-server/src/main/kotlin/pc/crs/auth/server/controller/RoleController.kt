package pc.crs.auth.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.auth.domain.RoleDO
import pc.crs.auth.server.service.RoleService
import pc.crs.common.base.controller.BaseController

@RestController
@RequestMapping("/api/role")
class RoleController(@Autowired override val service: RoleService)
    : BaseController<RoleDO, RoleDO, RoleService>()