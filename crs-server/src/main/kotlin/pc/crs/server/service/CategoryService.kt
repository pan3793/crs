package pc.crs.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.domain.CategoryDO
import pc.crs.server.dao.CategoryDAO

@Service
class CategoryService(@Autowired override var dao: CategoryDAO)
    : BaseService<CategoryDO, CategoryDO, CategoryDAO>()