package pc.crs.common.base.service

import org.springframework.data.jpa.repository.JpaRepository

abstract class BaseService<DO, DAO : JpaRepository<DO, Long>>(val dao: DAO)
    : JpaRepository<DO, Long> by dao