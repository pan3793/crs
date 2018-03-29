package pc.crs.forum.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pc.crs.common.base.controller.BaseController
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.successRestResult
import pc.crs.forum.domain.TopicDO
import pc.crs.forum.server.service.TopicService

@RestController
@RequestMapping("/api/topic")
class TopicController(@Autowired override val service: TopicService)
    : BaseController<TopicDO, TopicDO, TopicService>() {

    @GetMapping("{id}/detail")
    fun findTopicDetail(@PathVariable id: Long): RestResult {
        return successRestResult(service.findTopicDetail(id))
    }

    @PostMapping("{id}/bindDiscussion")
    fun bindDiscussion(@PathVariable id: Long, @RequestParam discussionId: Long): RestResult {
        service.bindDiscussion(id, discussionId)
        return successRestResult()
    }

    @PostMapping("{id}/removeDiscussion")
    fun removeCard(@PathVariable id: Long, @RequestParam discussionId: Long): RestResult {
        service.removeDiscussion(id, discussionId)
        return successRestResult()
    }
}