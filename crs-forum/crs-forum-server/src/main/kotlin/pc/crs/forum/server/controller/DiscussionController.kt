package pc.crs.forum.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.base.controller.BaseController
import pc.crs.forum.domain.DiscussionDO
import pc.crs.forum.server.service.DiscussionService

@RestController
@RequestMapping("/api/discussion")
class DiscussionController(@Autowired override val service: DiscussionService)
    : BaseController<DiscussionDO, DiscussionDO, DiscussionService>()