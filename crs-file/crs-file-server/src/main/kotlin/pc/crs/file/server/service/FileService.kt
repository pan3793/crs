package pc.crs.file.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.file.domain.FileDO
import pc.crs.file.server.dao.FileDAO

@Service
class FileService(@Autowired override val dao: FileDAO)
    : BaseService<FileDO, FileDO, FileDAO>() {

    fun findAllByIdIn(ids: List<Long>): List<FileDO> {
        return dao.findAllByIdIn(ids)
    }
}