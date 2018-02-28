package pc.crs.auth.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.auth.domain.UserDO
import pc.crs.auth.server.service.UserService
import pc.crs.common.base.controller.BaseController
import pc.crs.common.bean.IdNameDTO

@RestController
@RequestMapping("/api/user")
class UserController(@Autowired override val service: UserService)
    : BaseController<UserDO, UserDO, UserService>() {

    @GetMapping("idNameList")
    fun getIdNameList(): List<IdNameDTO> {
        return service.getIdNameList()
    }
}