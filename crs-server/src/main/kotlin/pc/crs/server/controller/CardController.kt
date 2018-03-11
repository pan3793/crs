package pc.crs.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.base.controller.BaseController
import pc.crs.domain.CardDO
import pc.crs.server.service.CardService

@RestController
@RequestMapping("/api/card")
class CardController(@Autowired override val service: CardService)
    : BaseController<CardDO, CardDO, CardService>()