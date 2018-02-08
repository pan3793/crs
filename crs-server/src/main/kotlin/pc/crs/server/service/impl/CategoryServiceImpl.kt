package pc.crs.server.service.impl

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import pc.crs.domain.CategoryDO
import pc.crs.server.dao.CategoryDAO
import pc.crs.server.service.CategoryService

@Service
class CategoryServiceImpl(@Autowired private val categoryDAO: CategoryDAO) : CategoryService {
    override fun findAll(pageNumber: Int, pageSize: Int): Page<CategoryDO> {
        return categoryDAO.findAll(PageRequest.of(pageNumber, pageSize))
    }

    override fun findById(id: Long): CategoryDO? {
        return categoryDAO.findById(id).orElse(null)
    }

    override fun save(categoryDO: CategoryDO) {
        categoryDO.id?.let { id ->
            categoryDAO.findById(id).orElse(null)?.let {
                BeanUtils.copyProperties(categoryDO, it,
                        "id", "creator", "modifier", "createTime", "modifiedTime", "version")
                categoryDAO.save(it)
                return
            }
            throw RuntimeException("id=${id}记录未找到")
        }
        categoryDAO.save(categoryDO)
    }


    override fun deleteById(id: Long) {
        categoryDAO.deleteById(id)
    }
}