package pc.crs.common.base.controller

import org.springframework.web.bind.annotation.*
import pc.crs.common.base.dao.BaseDAO
import pc.crs.common.base.domain.BaseDO
import pc.crs.common.base.service.BaseService
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.failureRestResult
import pc.crs.common.bean.successRestResult

abstract class BaseController<DTO : Any, DO : BaseDO, out S : BaseService<DTO, DO, BaseDAO<DO>>> {

    abstract val service: S

    @GetMapping
    open fun get(): RestResult {
        return successRestResult(service.findAll())
    }

    @GetMapping("/{id}")
    open fun get(@PathVariable id: Long): RestResult {
        return successRestResult(service.findById(id))
    }

    @PostMapping
    open fun save(@RequestBody entity: DTO): RestResult {
        service.save(entity)
        return successRestResult()
    }

    @DeleteMapping("/{id}")
    open fun delete(@PathVariable id: Long): RestResult {
        service.deleteById(id)
        return successRestResult()
    }
}