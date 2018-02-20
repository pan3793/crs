package pc.crs.auth.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.auth.domain.UserDO
import pc.crs.auth.server.service.UserService
import pc.crs.common.base.controller.BaseController

@RestController
@RequestMapping("/api/user")
class UserController(@Autowired override var service: UserService)
    : BaseController<UserDO, UserDO, UserService>() {

}