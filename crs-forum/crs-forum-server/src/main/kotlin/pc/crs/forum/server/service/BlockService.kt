package pc.crs.forum.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.common.bean.IdNameDTO
import pc.crs.common.constant.BASE_ALLOWED_QUERY_CONDITION_LIST
import pc.crs.forum.domain.BlockDO
import pc.crs.forum.server.dao.BlockDAO

@Service
class BlockService(@Autowired override val dao: BlockDAO)
    : BaseService<BlockDO, BlockDO, BlockDAO>() {

    override val allowedQueryConditions: List<String> = BASE_ALLOWED_QUERY_CONDITION_LIST + listOf(
            "LIKE_name"
    )

    fun getIdNameList(): List<IdNameDTO> = dao.findAll().map { IdNameDTO(it.id!!, it.name) }
}