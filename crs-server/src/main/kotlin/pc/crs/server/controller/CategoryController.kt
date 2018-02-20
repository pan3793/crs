package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.base.controller.BaseController
import pc.crs.domain.CategoryDO
import pc.crs.server.service.CategoryService

@RestController
@RequestMapping("/api/category")
class CategoryController(@Autowired override var service: CategoryService)
    : BaseController<CategoryDO, CategoryDO, CategoryService>()