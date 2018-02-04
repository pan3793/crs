package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pc.crs.server.service.CategoryService

@RestController
@RequestMapping("/api/category")
class CategoryController(@Autowired private val categoryService: CategoryService) {

    @GetMapping("{id}")
    fun get(@RequestParam id: Long) {
    }
}