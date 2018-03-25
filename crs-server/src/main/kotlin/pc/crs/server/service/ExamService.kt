package pc.crs.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.domain.ExamDO
import pc.crs.server.dao.ExamDAO

@Service
class ExamService(@Autowired override val dao: ExamDAO)
    : BaseService<ExamDO, ExamDO, ExamDAO>() 