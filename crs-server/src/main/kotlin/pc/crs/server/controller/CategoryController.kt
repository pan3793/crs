package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.failureRestResult
import pc.crs.common.bean.successRestResult
import pc.crs.domain.CategoryDO
import pc.crs.server.service.CategoryService

@RestController
@RequestMapping("/api/category")
class CategoryController(@Autowired private val categoryService: CategoryService) {

    @GetMapping
    fun get(@RequestParam pageNumber: Int?, @RequestParam pageSize: Int?): RestResult {
        val categories = categoryService.findAll(pageNumber ?: 1, pageSize ?: 20)
        return successRestResult(categories)
    }

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): RestResult {
        categoryService.findById(id)?.let {
            return successRestResult(it)
        }
        return failureRestResult("找不到id=${id}的记录")
    }

    @PostMapping
    fun save(@RequestBody categoryDO: CategoryDO): RestResult {
        categoryService.save(categoryDO)
        return successRestResult()
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): RestResult {
        categoryService.findById(id)?.let {
            categoryService.deleteById(id)
            return successRestResult()
        }
        return failureRestResult("找不到id=${id}的记录")
    }
}