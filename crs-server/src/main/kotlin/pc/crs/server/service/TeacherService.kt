package pc.crs.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.common.bean.IdNameDTO
import pc.crs.domain.TeacherDO
import pc.crs.server.dao.TeacherDAO
import pc.crs.server.manager.UserManager

@Service
class TeacherService(@Autowired override var dao: TeacherDAO,
                     @Autowired val userManager: UserManager)
    : BaseService<TeacherDO, TeacherDO, TeacherDAO>() {

    override fun validateDO(entity: TeacherDO) {
        // todo 验证数据一致性、唯一性
    }

    fun findNotAddedUserIdNameList(): List<IdNameDTO> {
        val addedUserIds = dao.findAll().map { it.userId }
        return userManager.fetchIdNameList().filterNot { addedUserIds.contains(it.id) }
    }
}