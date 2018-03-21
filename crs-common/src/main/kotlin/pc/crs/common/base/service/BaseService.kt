package pc.crs.common.base.service

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.transaction.annotation.Transactional
import pc.crs.common.base.dao.BaseDAO
import pc.crs.common.base.domain.BaseDO
import pc.crs.common.constant.BASE_ALLOWED_QUERY_CONDITION_LIST
import pc.crs.common.constant.BASE_DTO_READ_IGNORE_FIELD_LIST
import pc.crs.common.constant.DEFAULT_PAGE_NUM
import pc.crs.common.constant.DEFAULT_PAGE_SIZE
import pc.crs.common.exception.CriterionException
import pc.crs.common.exception.RecordNotFoundException
import pc.crs.common.exception.ValidateException
import pc.crs.common.ext.toLocalDateTime
import pc.crs.common.jpa.*
import pc.crs.common.jpa.Criterion.Operator.*

abstract class BaseService<DTO : Any, DO : BaseDO, out DAO : BaseDAO<DO>> {

    protected open val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    abstract val dao: DAO

    /**
     * 复写该属性以修改允许查询条件
     */
    open val allowedQueryConditions: List<String> = BASE_ALLOWED_QUERY_CONDITION_LIST

    /**
     * 复写该属性以修改屏蔽字段
     */
    open val dtoReadOnlyIgnoreFiledList = BASE_DTO_READ_IGNORE_FIELD_LIST

    open val defaultPageNum = DEFAULT_PAGE_NUM
    open val defaultPageSize = DEFAULT_PAGE_SIZE

    @Transactional
    open fun findAll(): Iterable<DTO> {
        return dao.findAll().map { convertDO2DTO(it) }
    }

    @Throws(CriterionException::class)
    open fun query(jsonObject: JSONObject): Page<DTO> {
        val illegalQueryConditions = jsonObject.keys - allowedQueryConditions
        if (illegalQueryConditions.isNotEmpty()) {
            logger.error("检测到非法查询参数{}", JSON.toJSONString(illegalQueryConditions))
            throw CriterionException("包含非法查询参数")
        }
        var pageNum = defaultPageNum
        var pageSize = defaultPageSize
        var pageDisable = false
        val criteria = Criteria<DO>()
        val orderAndPriorities = mutableListOf<Pair<Sort.Order, Int>>()
        allowedQueryConditions.forEach { queryString ->
            when (queryString) {
                "P_NUM" -> pageNum = jsonObject.getInteger(queryString) ?: defaultPageNum
                "P_SIZE" -> pageSize = jsonObject.getInteger(queryString) ?: defaultPageSize
                "P_DISABLE" -> pageDisable = jsonObject.getBoolean(queryString) ?: false
            }

            val queryMetas = queryString.split('_').filterNot { it.isBlank() }
            // 目前考虑到的所有查询条件至少由两部分查询元素构成
            if (queryMetas.size < 2) {
                logger.error("查询条件{}定义错误", queryString)
                throw CriterionException("查询条件${queryString}定义错误")
            }
            when (queryMetas[0]) {
                EQ.toString() -> criteria.add(eq(queryMetas[1], jsonObject[queryString]))
                NEQ.toString() -> criteria.add(neq(queryMetas[1], jsonObject[queryString]))
                LIKE.toString() -> criteria.add(like(queryMetas[1], jsonObject.getString(queryString)))
                GT.toString() -> {
                    if (queryMetas[1].contains("date", true)
                            || queryMetas[1].contains("time", true)) {
                        criteria.add(gt(queryMetas[1], jsonObject.getDate(queryString)?.toLocalDateTime()))
                    } else {
                        criteria.add(gt(queryMetas[1], jsonObject.getBigDecimal(queryString)))
                    }
                }
                LT.toString() -> {
                    if (queryMetas[1].contains("date", true)
                            || queryMetas[1].contains("time", true)) {
                        criteria.add(lt(queryMetas[1], jsonObject.getDate(queryString)?.toLocalDateTime()))
                    } else {
                        criteria.add(lt(queryMetas[1], jsonObject.getBigDecimal(queryString)))
                    }
                }
                GTE.toString() -> {
                    if (queryMetas[1].contains("date", true)
                            || queryMetas[1].contains("time", true)) {
                        criteria.add(gte(queryMetas[1], jsonObject.getDate(queryString)?.toLocalDateTime()))
                    } else {
                        criteria.add(gte(queryMetas[1], jsonObject.getBigDecimal(queryString)))
                    }
                }
                LTE.toString() -> {
                    if (queryMetas[1].contains("date", true)
                            || queryMetas[1].contains("time", true)) {
                        criteria.add(lte(queryMetas[1], jsonObject.getDate(queryString)?.toLocalDateTime()))
                    } else {
                        criteria.add(lte(queryMetas[1], jsonObject.getBigDecimal(queryString)))
                    }
                }
                IN.toString() -> criteria.add(`in`(queryMetas[1], jsonObject.getJSONArray(queryString)))
                NIN.toString() -> criteria.add(nin(queryMetas[1], jsonObject.getJSONArray(queryString)))
                "O" -> {
                    if (jsonObject.containsKey(queryString)) {
                        val priority = jsonObject.getIntValue(queryString)
                        when (queryMetas.size) {
                            2 -> orderAndPriorities.add(Sort.Order.by(queryMetas[1]) to priority)
                            3 -> when (queryMetas[2]) {
                                "ASC" -> orderAndPriorities.add(Sort.Order.asc(queryMetas[1]) to priority)
                                "DESC" -> orderAndPriorities.add(Sort.Order.desc(queryMetas[1]) to priority)
                                else -> {
                                    logger.error("查询条件{}定义错误", queryString)
                                    throw CriterionException("查询条件${queryString}定义错误")
                                }
                            }
                            else -> {
                                logger.error("查询条件{}定义错误", queryString)
                                throw CriterionException("查询条件${queryString}定义错误")
                            }
                        }
                    }
                }
//                AND.toString() -> {
//                    if (queryMetas.size < 3) {
//                        logger.error("查询条件{}定义错误", queryString)
//                        throw CriterionException("查询条件${queryString}定义错误")
//                    }
//                    // TODO
//                }
//                OR.toString() -> {
//                    if (queryMetas.size < 3) {
//                        logger.error("查询条件{}定义错误", queryString)
//                        throw CriterionException("查询条件${queryString}定义错误")
//                    }
//                    // TODO
//                }
            }
        }
        val page = PageRequest.of(pageNum, pageSize,
                Sort.by(orderAndPriorities.apply { sortBy { (_, priority) -> priority } }.map { (order, _) -> order }))
        val coursesPage = if (pageDisable) dao.findAll(criteria, Pageable.unpaged()) else dao.findAll(criteria, page)
        return coursesPage.map { convertDO2DTO(it) }
    }

    @Throws(RecordNotFoundException::class)
    @Transactional
    open fun findById(id: Long): DTO {
        return convertDO2DTO(dao.findById(id).orElseThrow {
            RecordNotFoundException("${this.javaClass.simpleName}, id=${id}记录未找到")
        })
    }

    @Transactional
    @Throws(RecordNotFoundException::class)
    open fun save(dto: DTO): DTO {
        val entity = convertDTO2DO(dto)
        val `do` = entity.id?.let { id ->
            dao.findById(id).orElseThrow {
                RecordNotFoundException("${this.javaClass.simpleName}, id=${id}记录未找到")
            }
        } ?: entity.javaClass.newInstance()
        BeanUtils.copyProperties(entity, `do`, *dtoReadOnlyIgnoreFiledList)
        validateDO(`do`)
        dao.save(`do`)
        return convertDO2DTO(`do`)
    }

    @Throws(RecordNotFoundException::class)
    open fun deleteById(id: Long) {
        try {
            dao.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw RecordNotFoundException("${this.javaClass.simpleName}, id=${id}记录未找到")
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