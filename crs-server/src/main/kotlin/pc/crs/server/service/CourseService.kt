package pc.crs.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.common.bean.IdNameDTO
import pc.crs.domain.CourseDO
import pc.crs.server.dao.CourseDAO
import pc.crs.server.manager.UserManager

@Service
class CourseService(@Autowired override val dao: CourseDAO)
    : BaseService<CourseDO, CourseDO, CourseDAO>() {

    @Autowired lateinit var userManager: UserManager

    fun fetchTeacherIdNameList(): List<IdNameDTO> = userManager.fetchTeacherIdNameList()
//    fun fetchTeacherIdNameList(): List<IdNameDTO> = emptyList()

    fun fetchStudentIdNameList(): List<IdNameDTO> = userManager.fetchStudentIdNameList()
//    fun fetchStudentIdNameList(): List<IdNameDTO> = emptyList()
}