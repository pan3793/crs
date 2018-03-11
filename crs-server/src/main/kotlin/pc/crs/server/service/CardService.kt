package pc.crs.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.domain.CardDO
import pc.crs.server.dao.CardDAO

@Service
class CardService(@Autowired override val dao: CardDAO)
    : BaseService<CardDO, CardDO, CardDAO>()