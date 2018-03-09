package pc.crs.file.server.dto

import pc.crs.file.domain.FileDO

data class UploadFileResultDTO(val successes: List<FileDO> = emptyList(),
                               val fails: List<String> = emptyList())