package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.base.controller.BaseController
import pc.crs.domain.CourseDO
import pc.crs.server.service.CourseService

@RestController
@RequestMapping("/api/course")
class CourseController(@Autowired override var service: CourseService) : BaseController<CourseDO, CourseService>()