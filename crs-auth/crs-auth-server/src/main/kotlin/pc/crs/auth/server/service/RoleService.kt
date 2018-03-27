package pc.crs.auth.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.auth.domain.RoleDO
import pc.crs.auth.server.dao.RoleDAO
import pc.crs.common.base.service.BaseService
import pc.crs.common.bean.IdNameDTO
import pc.crs.common.constant.BASE_ALLOWED_QUERY_CONDITION_LIST

@Service
class RoleService(@Autowired override var dao: RoleDAO)
    : BaseService<RoleDO, RoleDO, RoleDAO>() {

    override val allowedQueryConditions: List<String> = BASE_ALLOWED_QUERY_CONDITION_LIST + listOf(
            "EQ_code", "LIKE_code",
            "EQ_name", "LIKE_name"
    )

    fun getIdNameList(): List<IdNameDTO> = dao.findAll().map { IdNameDTO(it.id!!, it.name) }
}