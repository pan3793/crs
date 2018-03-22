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

    @GetMapping("recommended")
    fun findRecommended(): RestResult {
        return successRestResult(service.findRecommended())
    }

    @GetMapping("allWithCardName")
    fun findAllWithCardName(): RestResult {
        return successRestResult(service.findAllWithCardName())
    }

    @GetMapping("{id}/detail")
    fun findCourseDetail(@PathVariable id: Long): RestResult {
        return successRestResult(service.findCourseDetail(id))
    }

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

    @PostMapping("{id}/bindCard")
    fun bindCard(@PathVariable id: Long, @RequestParam cardId: Long): RestResult {
        service.bindCard(id, cardId)
        return successRestResult()
    }

    @PostMapping("{id}/removeCard")
    fun removeCard(@PathVariable id: Long, @RequestParam cardId: Long): RestResult {
        service.removeCard(id, cardId)
        return successRestResult()
    }
}