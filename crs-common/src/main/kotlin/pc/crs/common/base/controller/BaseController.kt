package pc.crs.common.base.controller

import org.springframework.web.bind.annotation.*
import pc.crs.common.base.dao.BaseDAO
import pc.crs.common.base.domain.BaseDO
import pc.crs.common.base.service.BaseService
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.failureRestResult
import pc.crs.common.bean.successRestResult

abstract class BaseController<DO : BaseDO, S : BaseService<DO, out BaseDAO<DO>>> {

    open lateinit var service: S

    @GetMapping
    fun get(): RestResult {
        return successRestResult(service.findAll())
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): RestResult {
        service.findById(id)?.let {
            return successRestResult(it)
        }
        return failureRestResult("找不到id=${id}的记录")
    }

    @PostMapping
    fun save(@RequestBody entity: DO): RestResult {
        service.save(entity)
        return successRestResult()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): RestResult {
        service.findById(id)?.let {
            service.deleteById(id)
            return successRestResult()
        }
        return failureRestResult("找不到id=${id}的记录")
    }

}