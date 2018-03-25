package pc.crs.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.domain.ClassworkTaskDO
import pc.crs.server.dao.ClassworkTaskDAO

@Service
class ClassworkTaskService(@Autowired override val dao: ClassworkTaskDAO)
    : BaseService<ClassworkTaskDO, ClassworkTaskDO, ClassworkTaskDAO>()