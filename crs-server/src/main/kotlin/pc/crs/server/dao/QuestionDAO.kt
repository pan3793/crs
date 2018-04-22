package pc.crs.server.dao

import org.springframework.stereotype.Repository
import pc.crs.common.base.dao.BaseDAO
import pc.crs.domain.QuestionDO

@Repository
interface QuestionDAO : BaseDAO<QuestionDO> {

    fun findAllByIdIn(ids: List<Long>): List<QuestionDO>

    fun findAllByCourseId(courseId: Long): List<QuestionDO>
}