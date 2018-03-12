package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pc.crs.common.base.controller.BaseController
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.successRestResult
import pc.crs.domain.CourseDO
import pc.crs.server.service.CourseService

@RestController
@RequestMapping("/api/course")
class CourseController(@Autowired override val service: CourseService)
    : BaseController<CourseDO, CourseDO, CourseService>() {

    @PostMapping("{id}/bindImage")
    fun bindImage(@PathVariable id: Long, @RequestParam imageUrl: String): RestResult {
        service.bindImage(id, imageUrl)
        return successRestResult()
    }

    @PostMapping("{id}/removeImage")
    fun removeImage(@PathVariable id: Long): RestResult {
        service.removeImage(id)
        return successRestResult()
    }
}