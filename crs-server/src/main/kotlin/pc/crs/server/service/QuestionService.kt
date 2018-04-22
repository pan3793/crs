package pc.crs.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.common.constant.BASE_ALLOWED_QUERY_CONDITION_LIST
import pc.crs.common.constant.BASE_DTO_READ_IGNORE_FIELD_LIST
import pc.crs.common.constant.QUESTION_TYPE_LIST
import pc.crs.common.exception.RecordNotFoundException
import pc.crs.domain.QuestionDO
import pc.crs.server.dao.QuestionDAO

@Service
class QuestionService(@Autowired override val dao: QuestionDAO)
    : BaseService<QuestionDO, QuestionDO, QuestionDAO>() {

    override val allowedQueryConditions: List<String> = BASE_ALLOWED_QUERY_CONDITION_LIST + listOf(
            "EQ_courseId", "IN_courseId",
            "EQ_courseName", "LIKE_courseName",
            "EQ_teacherId", "IN_teacherId",
            "EQ_teacherName", "LIKE_teacherName",
            "EQ_type", "IN_type"
    )

    override val dtoReadOnlyIgnoreFiledList = BASE_DTO_READ_IGNORE_FIELD_LIST + listOf(
            "ask", "answer"
    )

    val typeList = QUESTION_TYPE_LIST

    fun fetchTypeList() = typeList

    fun fetchByCourseId(courseId: Long)=
        dao.findAllByCourseId(courseId)


    fun editAskAndAnswer(id: Long, ask: String, answer: String) {
        validateAskAndAnswer(ask, answer)
        val questionDO = dao.findById(id).orElseThrow {
            RecordNotFoundException("${this.javaClass.simpleName},id=${id}记录未找到")
        }.apply {
            this.ask = ask
            this.answer = answer
        }
        dao.saveAndFlush(questionDO)
    }

    fun clearAskAndAnswer(id: Long) {
        editAskAndAnswer(id, "", "")
    }

    fun validateAskAndAnswer(ask: String, answer: String) {
    }
}