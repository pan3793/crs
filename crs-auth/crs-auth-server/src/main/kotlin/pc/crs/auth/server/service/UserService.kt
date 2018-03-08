package pc.crs.auth.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pc.crs.auth.domain.UserDO
import pc.crs.auth.server.dao.UserDAO
import pc.crs.auth.server.dao.RoleDAO
import pc.crs.auth.server.dao.UserRoleDAO
import pc.crs.common.base.service.BaseService
import pc.crs.common.bean.IdNameDTO
import pc.crs.common.constant.BASE_DTO_READ_IGNORE_FIELD_LIST

@Service
class UserService(@Autowired override val dao: UserDAO,
                  @Autowired val userRoleDAO: UserRoleDAO,
                  @Autowired val roleDAO: RoleDAO)
    : BaseService<UserDO, UserDO, UserDAO>() {

    // 屏蔽DTO密码，避免直接修改
    override val dtoReadOnlyIgnoreFiledList = BASE_DTO_READ_IGNORE_FIELD_LIST + "password"

    fun fetchIdNameList(): List<IdNameDTO> = dao.findAll().map { IdNameDTO(it.id!!, it.name) }

    @Transactional
    fun fetchTeacherIdNameList(): List<IdNameDTO> {
        roleDAO.findByCode("Teacher").firstOrNull()?.id?.let { teacherRoleId ->
            userRoleDAO.findByRoleId(teacherRoleId).map { it.userId }.let { teacherUserIds ->
                return dao.findByIdIn(teacherUserIds).map { IdNameDTO(it.id!!, it.name) }
            }
        }
        return emptyList()
    }

    @Transactional
    fun fetchStudentIdNameList(): List<IdNameDTO> {
        roleDAO.findByCode("Student").firstOrNull()?.id?.let { teacherRoleId ->
            userRoleDAO.findByRoleId(teacherRoleId).map { it.userId }.let { teacherUserIds ->
                return dao.findByIdIn(teacherUserIds).map { IdNameDTO(it.id!!, it.name) }
            }
        }
        return emptyList()
    }
}