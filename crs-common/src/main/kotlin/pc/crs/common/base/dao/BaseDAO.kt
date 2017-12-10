package pc.crs.common.base.dao

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseDAO<T> : JpaRepository<T, Long> {

    fun findByIdAndEnabled(id: Long, enabled: Boolean = true): T?

    fun findAllEnabled(sort: Sort = Sort.unsorted(), enabled: Boolean = true): List<T>

    fun findAllEnabled(pageable: Pageable, enabled: Boolean = true): Page<T>
}