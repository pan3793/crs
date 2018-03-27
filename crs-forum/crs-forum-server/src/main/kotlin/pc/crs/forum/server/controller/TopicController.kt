package pc.crs.forum.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.base.controller.BaseController
import pc.crs.forum.domain.TopicDO
import pc.crs.forum.server.service.TopicService

@RestController
@RequestMapping("/api/topic")
class TopicController(@Autowired override val service: TopicService)
    : BaseController<TopicDO, TopicDO, TopicService>()