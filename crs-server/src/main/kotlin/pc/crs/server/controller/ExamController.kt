package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.base.controller.BaseController
import pc.crs.domain.ExamDO
import pc.crs.server.service.ExamService

@RestController
@RequestMapping("/api/exam")
class ExamController(@Autowired override val service: ExamService)
    : BaseController<ExamDO, ExamDO, ExamService>()