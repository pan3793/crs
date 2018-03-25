package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.base.controller.BaseController
import pc.crs.domain.ClassworkDO
import pc.crs.server.service.ClassworkService


@RestController
@RequestMapping("/api/classwork")
class ClassworkController(@Autowired override val service: ClassworkService)
    : BaseController<ClassworkDO, ClassworkDO, ClassworkService>()