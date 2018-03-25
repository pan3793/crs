package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.base.controller.BaseController
import pc.crs.domain.ClassworkTaskDO
import pc.crs.server.service.ClassworkTaskService

@RestController
@RequestMapping("/api/classworkTask")
class ClassworkTaskController(@Autowired override val service: ClassworkTaskService)
    : BaseController<ClassworkTaskDO, ClassworkTaskDO, ClassworkTaskService>()