package pc.crs.common.base.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseDAO<T> : JpaRepository<T, Long>, JpaSpecificationExecutor<T>
