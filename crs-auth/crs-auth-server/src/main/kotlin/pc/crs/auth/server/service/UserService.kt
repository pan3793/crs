package pc.crs.auth.server.service

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pc.crs.auth.domain.UserDO
import pc.crs.auth.domain.UserRoleDO
import pc.crs.auth.server.dao.RoleDAO
import pc.crs.auth.server.dao.UserDAO
import pc.crs.auth.server.dao.UserRoleDAO
import pc.crs.auth.server.dto.UserDTO
import pc.crs.common.base.service.BaseService
import pc.crs.common.bean.IdNameDTO
import pc.crs.common.constant.BASE_ALLOWED_QUERY_CONDITION_LIST
import pc.crs.common.constant.BASE_DTO_READ_IGNORE_FIELD_LIST
import pc.crs.common.exception.RecordNotFoundException
import pc.crs.common.exception.ValidateException

@Service
class UserService(@Autowired override val dao: UserDAO,
                  @Autowired val userRoleDAO: UserRoleDAO,
                  @Autowired val roleDAO: RoleDAO)
    : BaseService<UserDTO, UserDO, UserDAO>() {

    override val allowedQueryConditions: List<String> = BASE_ALLOWED_QUERY_CONDITION_LIST + listOf(
            "LIKE_name",
            "EQ_loginName", "LIKE_loginName"
    )

    override val dtoReadOnlyIgnoreFiledList = BASE_DTO_READ_IGNORE_FIELD_LIST + listOf(
            "password"
    )

    fun fetchIdNameList(): List<IdNameDTO> = dao.findAll().map { IdNameDTO(it.id!!, it.name) }

    fun resetPassword(id: Long, newPassword: String) {
        validatePassword(newPassword)
        val userDO = dao.findById(id).orElseThrow {
            RecordNotFoundException("${this.javaClass.simpleName},id=${id}记录未找到")
        }.apply { password = encryptPassword(newPassword) }
        dao.saveAndFlush(userDO)
    }

    @Throws(ValidateException::class)
    fun validatePassword(password: String) {
        if (password.isBlank()) throw ValidateException("密码不合法")
    }

    fun encryptPassword(password: String): String {
        return password
    }

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

    @Transactional
    @Throws(RecordNotFoundException::class)
    override fun save(dto: UserDTO): UserDTO {
        val entity = convertDTO2DO(dto)
        val userDO: UserDO = entity.id?.let { id ->
            dao.findById(id).orElseThrow {
                RecordNotFoundException("${this.javaClass.simpleName},id=${id}记录未找到")
            }
        } ?: entity.javaClass.newInstance()
        BeanUtils.copyProperties(entity, userDO, *dtoReadOnlyIgnoreFiledList)
        validateDO(userDO)
        dao.saveAndFlush(userDO)

        // 修改 user_role
        val originalRoleIds = userRoleDAO.findByUserId(userDO.id!!).map { it.roleId }
        val newRoleIds = dto.roleIds
        val toRemoveRoleIds = originalRoleIds.minus(newRoleIds)
        val toAddRoleIds = newRoleIds.minus(originalRoleIds)

        if (toRemoveRoleIds.isNotEmpty()) {
            userRoleDAO.findByUserIdAndRoleIdIn(userDO.id!!, toRemoveRoleIds).forEach { userRoleDAO.delete(it) }
        }
        toAddRoleIds.forEach { userRoleDAO.saveAndFlush(UserRoleDO(userId = userDO.id!!, roleId = it)) }

        return convertDO2DTO(userDO)
    }

    override fun convertDO2DTO(entity: UserDO): UserDTO {
        val dto = UserDTO::class.java.newInstance()
        BeanUtils.copyProperties(entity, dto)
        dto.roleIds = userRoleDAO.findByUserId(entity.id!!).map { it.roleId }
        return dto
    }

    override fun convertDTO2DO(dto: UserDTO): UserDO {
        val entity = UserDO::class.java.newInstance()
        BeanUtils.copyProperties(dto, entity, *dtoReadOnlyIgnoreFiledList)
        return entity
    }
}