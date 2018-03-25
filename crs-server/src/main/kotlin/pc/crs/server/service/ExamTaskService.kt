package pc.crs.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.domain.ExamTaskDO
import pc.crs.server.dao.ExamTaskDAO

@Service
class ExamTaskService(@Autowired override val dao: ExamTaskDAO)
    : BaseService<ExamTaskDO, ExamTaskDO, ExamTaskDAO>()