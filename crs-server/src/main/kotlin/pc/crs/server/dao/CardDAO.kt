package pc.crs.server.dao

import org.springframework.stereotype.Repository
import pc.crs.common.base.dao.BaseDAO
import pc.crs.domain.CardDO

@Repository
interface CardDAO : BaseDAO<CardDO> {
    fun findAllByIdIn(ids: List<Long>): List<CardDO>
}