package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.base.controller.BaseController
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.successRestResult
import pc.crs.domain.CourseDO
import pc.crs.server.service.CourseService

@RestController
@RequestMapping("/api/course")
class CourseController(@Autowired override val service: CourseService)
    : BaseController<CourseDO, CourseDO, CourseService>() {

    @GetMapping("teacherIdNameList")
    fun getTeacherIdNameList(): RestResult {
        return successRestResult(service.fetchTeacherIdNameList())
    }

    @GetMapping("studentIdNameList")
    fun getStudentIdNameList(): RestResult {
        return successRestResult(service.fetchStudentIdNameList())
    }
}