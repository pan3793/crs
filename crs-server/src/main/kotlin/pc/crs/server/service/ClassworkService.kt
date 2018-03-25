package pc.crs.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.domain.ClassworkDO
import pc.crs.server.dao.ClassworkDAO

@Service
class ClassworkService(@Autowired override val dao: ClassworkDAO)
    : BaseService<ClassworkDO, ClassworkDO, ClassworkDAO>()