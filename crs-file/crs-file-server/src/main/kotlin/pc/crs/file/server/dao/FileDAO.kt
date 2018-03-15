package pc.crs.file.server.dao

import org.springframework.stereotype.Repository
import pc.crs.common.base.dao.BaseDAO
import pc.crs.file.domain.FileDO

@Repository
interface FileDAO : BaseDAO<FileDO> {

    fun findAllByIdIn(ids: List<Long>): List<FileDO>
}