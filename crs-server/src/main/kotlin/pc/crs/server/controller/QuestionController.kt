package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pc.crs.common.base.controller.BaseController
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.successRestResult
import pc.crs.domain.QuestionDO
import pc.crs.server.dto.AskAndAnswerDTO
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

    @PostMapping("{id}/editAskAndAnswer")
    fun editAskAndAnswer(@PathVariable id: Long, askAndAnswerDTO: AskAndAnswerDTO): RestResult {
        val (ask, answer) = askAndAnswerDTO
        service.editAskAndAnswer(id, ask, answer)
        return successRestResult()
    }

    @PostMapping("{id}/editAskAndAnswer")
    fun clearAskAndAnswer(@PathVariable id: Long): RestResult {
        service.clearAskAndAnswer(id)
        return successRestResult()
    }
}