package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.base.controller.BaseController
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.successRestResult
import pc.crs.domain.TeacherDO
import pc.crs.server.service.TeacherService

@RestController
@RequestMapping("/api/teacher")
class TeacherController(@Autowired override val service: TeacherService)
    : BaseController<TeacherDO, TeacherDO, TeacherService>() {

    @GetMapping("userIdNameList")
    fun getUserIdNameList(): RestResult {
        return successRestResult(service.findNotAddedUserIdNameList())
    }
}
