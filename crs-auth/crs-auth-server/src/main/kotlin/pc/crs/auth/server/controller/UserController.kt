package pc.crs.auth.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pc.crs.auth.domain.UserDO
import pc.crs.auth.server.dto.UserDTO
import pc.crs.auth.server.service.UserService
import pc.crs.common.aop.annotation.Log
import pc.crs.common.base.controller.BaseController
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.successRestResult

@RestController
@RequestMapping("/api/user")
class UserController(@Autowired override val service: UserService)
    : BaseController<UserDTO, UserDO, UserService>() {

    @Log
    @GetMapping("idNameList")
    fun getIdNameList(): RestResult {
        return successRestResult(service.fetchIdNameList())
    }

    @Log
    @GetMapping("teacherIdNameList")
    fun getTeacherIdNameList(): RestResult {
        return successRestResult(service.fetchTeacherIdNameList())
    }

    @Log
    @GetMapping("studentIdNameList")
    fun getStudentIdNameList(): RestResult {
        return successRestResult(service.fetchStudentIdNameList())
    }

    @Log
    @PostMapping("{id}/resetPassword")
    fun resetPassword(@PathVariable id: Long, @RequestParam newPassword: String): RestResult {
        service.resetPassword(id, newPassword)
        return successRestResult()
    }
}