package pc.crs.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.domain.CourseDO
import pc.crs.server.dao.CourseDAO

@Service
class CourseService(@Autowired override var dao: CourseDAO)
    : BaseService<CourseDO, CourseDAO>()