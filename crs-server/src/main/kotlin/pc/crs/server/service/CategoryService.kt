package pc.crs.server.service

import pc.crs.domain.CategoryDO

interface CategoryService {
    fun findAll(): Iterable<CategoryDO>

    fun findById(id: Long): CategoryDO?

    fun save(categoryDO: CategoryDO)

    fun deleteById(id: Long)
}