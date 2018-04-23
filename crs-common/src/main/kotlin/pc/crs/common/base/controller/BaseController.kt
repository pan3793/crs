package pc.crs.common.base.controller

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import pc.crs.common.aop.annotation.Log
import pc.crs.common.base.dao.BaseDAO
import pc.crs.common.base.domain.BaseDO
import pc.crs.common.base.service.BaseService
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.successRestResult

abstract class BaseController<DTO : Any, DO : BaseDO, out S : BaseService<DTO, DO, BaseDAO<DO>>> {

    protected open val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    abstract val service: S

    @Log
    @GetMapping
    open fun get(): RestResult {
        return successRestResult(service.findAll())
    }

    @Log
    @PostMapping("query")
    open fun query(@RequestBody json: String?): RestResult {
        val jsonObject = JSON.parseObject(json) ?: JSONObject()
        return successRestResult(service.query(jsonObject))
    }

    @Log
    @GetMapping("/{id}")
    open fun get(@PathVariable id: Long): RestResult {
        return successRestResult(service.findById(id))
    }

    @Log
    @PostMapping
    open fun save(@RequestBody entity: DTO): RestResult {
        return successRestResult(service.save(entity))
    }

    @Log
    @DeleteMapping("/{id}")
    open fun delete(@PathVariable id: Long): RestResult {
        service.deleteById(id)
        return successRestResult()
    }
}