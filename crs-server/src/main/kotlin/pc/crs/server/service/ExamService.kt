package pc.crs.server.service

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.common.constant.BASE_ALLOWED_QUERY_CONDITION_LIST
import pc.crs.common.constant.BASE_DTO_READ_IGNORE_FIELD_LIST
import pc.crs.domain.ExamDO
import pc.crs.server.dao.ExamDAO
import pc.crs.server.dao.QuestionDAO
import pc.crs.server.dto.ExamDetailDTO

@Service
class ExamService(@Autowired override val dao: ExamDAO,
                  @Autowired val questionDAO: QuestionDAO)
    : BaseService<ExamDO, ExamDO, ExamDAO>() {

    override val allowedQueryConditions: List<String> = BASE_ALLOWED_QUERY_CONDITION_LIST + listOf(
            "LIKE_name",
            "LIKE_courseName"
    )

    override val dtoReadOnlyIgnoreFiledList = BASE_DTO_READ_IGNORE_FIELD_LIST + listOf(
            "questionIds"
    )

    fun fetchExamDetail(id: Long): ExamDetailDTO = this.findById(id).let { examDO ->
        ExamDetailDTO().apply {
            BeanUtils.copyProperties(examDO, this)
            this.questions = questionDAO.findAllByIdIn(examDO.questionIds).map { questionDO ->
                ExamDetailDTO.Question().apply {
                    BeanUtils.copyProperties(questionDO, this)
                }
            }
        }
    }

    fun editQuestions(id: Long, questionIds: List<Long>) {
        val examDO = findById(id).apply { this.questionIds = questionIds }
        dao.saveAndFlush(examDO)
    }
}