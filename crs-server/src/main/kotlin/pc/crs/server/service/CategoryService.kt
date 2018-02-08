package pc.crs.server.service

import org.springframework.data.domain.Page
import pc.crs.domain.CategoryDO

interface CategoryService {
    fun findAll(pageNumber: Int, pageSize: Int): Page<CategoryDO>

    fun findById(id: Long): CategoryDO?

    fun save(categoryDO: CategoryDO)

    fun deleteById(id: Long)
}