package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.aop.annotation.Log
import pc.crs.common.base.controller.BaseController
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.successRestResult
import pc.crs.domain.CategoryDO
import pc.crs.server.service.CategoryService

@RestController
@RequestMapping("/api/category")
class CategoryController(@Autowired override val service: CategoryService)
    : BaseController<CategoryDO, CategoryDO, CategoryService>() {

    @Log
    @GetMapping("idNameList")
    fun getIdNameList(): RestResult {
        return successRestResult(service.fetchIdNameList())
    }
}