package pc.crs.common.base.service

import org.springframework.beans.BeanUtils
import pc.crs.common.base.dao.BaseDAO
import pc.crs.common.base.domain.BaseDO
import pc.crs.common.constant.BASE_VO_READ_IGNORE_FIELD_LIST

abstract class BaseService<DO : BaseDO, DAO : BaseDAO<DO>> {

    open lateinit var dao: DAO

    fun findAll(): Iterable<DO> {
        return dao.findAll()
    }

    fun findById(id: Long): DO? {
        return dao.findById(id).orElse(null)
    }

    fun save(entity: DO) {
        entity.id?.let { id ->
            dao.findById(id).orElse(null)?.let {
                BeanUtils.copyProperties(entity, it, *BASE_VO_READ_IGNORE_FIELD_LIST)
                dao.save(it)
                return
            }
            throw RuntimeException("id=${id}记录未找到")
        }
        dao.save(entity)
    }

    fun deleteById(id: Long) {
        dao.deleteById(id)
    }
}