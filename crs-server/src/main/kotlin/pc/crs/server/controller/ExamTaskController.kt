package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.base.controller.BaseController
import pc.crs.domain.ExamTaskDO
import pc.crs.server.service.ExamTaskService

@RestController
@RequestMapping("/api/examTask")
class ExamTaskController(@Autowired override val service: ExamTaskService)
    : BaseController<ExamTaskDO, ExamTaskDO, ExamTaskService>()