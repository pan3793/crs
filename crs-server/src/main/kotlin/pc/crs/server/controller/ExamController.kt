package pc.crs.server.controller

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pc.crs.common.base.controller.BaseController
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.successRestResult
import pc.crs.domain.ExamDO
import pc.crs.server.service.ExamService

@RestController
@RequestMapping("/api/exam")
class ExamController(@Autowired override val service: ExamService)
    : BaseController<ExamDO, ExamDO, ExamService>() {

    @GetMapping("{id}/detail")
    fun fetchExamDetail(@PathVariable id: Long): RestResult {
        return successRestResult(service.fetchExamDetail(id))
    }

    @PostMapping("{id}/editQuestions")
    fun editQuestions(@PathVariable id: Long, @RequestBody json: String): RestResult {
        val questionIds = JSON.parseObject<List<Long>>(json, object : TypeReference<List<Long>>() {})
        service.editQuestions(id, questionIds)
        return successRestResult()
    }
}