package pc.crs.server.dao

import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pc.crs.common.base.dao.BaseDAO
import pc.crs.domain.CourseDO

@Repository
interface CourseDAO : BaseDAO<CourseDO> {

    @Query(value = "SELECT * FROM course ORDER BY modified_time DESC LIMIT ?1", nativeQuery = true)
    fun findAllByOrderByModifiedTimeLimit(limit: Int): List<CourseDO>

    @Query(value = "SELECT * FROM course WHERE category_id = ?1 ORDER BY modified_time DESC LIMIT ?2",
            nativeQuery = true)
    fun findAllByCategoryIdAndOrderByModifiedTimeLimit(categoryId: Long, limit: Int): List<CourseDO>
}