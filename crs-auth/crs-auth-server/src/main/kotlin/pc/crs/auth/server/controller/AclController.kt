package pc.crs.auth.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.auth.domain.AclDO
import pc.crs.auth.server.service.AclService
import pc.crs.common.base.controller.BaseController

@RestController
@RequestMapping("/api/acl")
class AclController(@Autowired override val service: AclService)
    : BaseController<AclDO, AclDO, AclService>()