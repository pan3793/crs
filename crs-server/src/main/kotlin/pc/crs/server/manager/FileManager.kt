package pc.crs.server.manager

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import pc.crs.file.domain.FileDO

@FeignClient(name = "crs-file-server", path = "/api/file")
interface FileManager {
    @PostMapping("queryByIds")
    fun getByIdList(@RequestBody ids: List<Long>): List<FileDO>
}