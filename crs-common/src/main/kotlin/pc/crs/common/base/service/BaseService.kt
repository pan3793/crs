package pc.crs.common.base.service

import org.springframework.data.jpa.repository.JpaRepository

abstract class BaseService<DO, DAO : JpaRepository<DO, Long>>(val dao: DAO) {

    fun save(bean: DO) {
        dao.save(bean)
    }

    fun remove(id: Long) {
        dao.deleteById(id)
    }

    fun remove(bean: DO) {
        dao.delete(bean)
    }

    fun findOne(id: Long): DO? {
        dao.findOne(id)
    }

    fun findFirst(bean: DO): DO? {

    }

    fun findAll()
}