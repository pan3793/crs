package pc.crs.common.base.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.transaction.annotation.Transactional
import pc.crs.common.base.dao.BaseDAO
import pc.crs.common.base.domain.BaseDO
import pc.crs.common.constant.BASE_DTO_READ_IGNORE_FIELD_LIST
import pc.crs.common.exception.RecordNotFoundException
import pc.crs.common.exception.ValidateException

abstract class BaseService<DTO : Any, DO : BaseDO, out DAO : BaseDAO<DO>> {

    protected open val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    abstract val dao: DAO

    /**
     * 复写该属性以修改屏蔽字段
     */
    open val dtoReadOnlyIgnoreFiledList = BASE_DTO_READ_IGNORE_FIELD_LIST

    @Transactional
    open fun findAll(): Iterable<DTO> {
        return dao.findAll().map { convertDO2DTO(it) }
    }

    @Throws(RecordNotFoundException::class)
    @Transactional
    open fun findById(id: Long): DTO {
        return convertDO2DTO(dao.findById(id).orElseThrow {
            RecordNotFoundException("${this.javaClass.simpleName},id=${id}记录未找到")
        })
    }

    @Transactional
    @Throws(RecordNotFoundException::class)
    open fun save(dto: DTO): DTO {
        val entity = convertDTO2DO(dto)
        entity.id?.let { id ->
            dao.findById(id).orElseThrow {
                RecordNotFoundException("${this.javaClass.simpleName},id=${id}记录未找到")
            }.let {
                BeanUtils.copyProperties(entity, it, *dtoReadOnlyIgnoreFiledList)
                validateDO(it)
                dao.save(it)
                return convertDO2DTO(it)
            }
        }
        val newEntity = entity.javaClass.newInstance()
        BeanUtils.copyProperties(entity, newEntity, *dtoReadOnlyIgnoreFiledList)
        validateDO(newEntity)
        dao.save(newEntity)
        return convertDO2DTO(newEntity)
    }

    @Throws(RecordNotFoundException::class)
    open fun deleteById(id: Long) {
        try {
            dao.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw RecordNotFoundException("${this.javaClass.simpleName},id=${id}记录未找到")
        }
    }

    /**
     * 复写该方法自定义校验
     */
    @Throws(ValidateException::class)
    open fun validateDO(entity: DO) {
    }

    /**
     * 当DTO与DO类型不一致时，复写该方法
     */
    @Suppress("UNCHECKED_CAST")
    open fun convertDO2DTO(entity: DO): DTO {
        // 避免lazy属性不加载
        val dto: DTO = entity.javaClass.newInstance() as DTO
        BeanUtils.copyProperties(entity, dto)
        return dto
    }

    /**
     * 当DTO与DO类型不一致时，复写该方法
     */
    @Suppress("UNCHECKED_CAST")
    open fun convertDTO2DO(dto: DTO): DO {
        val entity: DO = dto.javaClass.newInstance() as DO
        BeanUtils.copyProperties(dto, entity, *dtoReadOnlyIgnoreFiledList)
        return entity
    }
}