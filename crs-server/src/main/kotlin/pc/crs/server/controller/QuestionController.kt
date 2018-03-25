package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.base.controller.BaseController
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.successRestResult
import pc.crs.domain.QuestionDO
import pc.crs.server.service.QuestionService

@RestController
@RequestMapping("/api/question")
class QuestionController(@Autowired override val service: QuestionService)
    : BaseController<QuestionDO, QuestionDO, QuestionService>() {

    @GetMapping("typeList")
    fun fetchTypeList(): RestResult {
        return successRestResult(service.fetchTypeList())
    }

    @GetMapping("checkTypeList")
    fun fetchCheckTypeList(): RestResult {
        return successRestResult(service.fetchCheckTypeList())
    }
}