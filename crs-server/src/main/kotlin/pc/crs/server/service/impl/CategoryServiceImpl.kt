package pc.crs.server.service.impl

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.constant.BASE_VO_READ_IGNORE_FIELD_LIST
import pc.crs.domain.CategoryDO
import pc.crs.server.dao.CategoryDAO
import pc.crs.server.service.CategoryService

@Service
class CategoryServiceImpl(@Autowired private val categoryDAO: CategoryDAO) : CategoryService {
    override fun findAll(): Iterable<CategoryDO> {
        return categoryDAO.findAll()
    }

    override fun findById(id: Long): CategoryDO? {
        return categoryDAO.findById(id).orElse(null)
    }

    override fun save(categoryDO: CategoryDO) {
        categoryDO.id?.let { id ->
            categoryDAO.findById(id).orElse(null)?.let {
                BeanUtils.copyProperties(categoryDO, it, *BASE_VO_READ_IGNORE_FIELD_LIST)
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