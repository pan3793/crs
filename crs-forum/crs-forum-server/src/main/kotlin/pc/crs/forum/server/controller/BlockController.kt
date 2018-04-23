package pc.crs.forum.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pc.crs.common.aop.annotation.Log
import pc.crs.common.base.controller.BaseController
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.successRestResult
import pc.crs.forum.domain.BlockDO
import pc.crs.forum.server.service.BlockService

@RestController
@RequestMapping("/api/block")
class BlockController(@Autowired override val service: BlockService)
    : BaseController<BlockDO, BlockDO, BlockService>() {

    @Log
    @GetMapping("idNameList")
    fun getIdNameList(): RestResult {
        return successRestResult(service.getIdNameList())
    }
}