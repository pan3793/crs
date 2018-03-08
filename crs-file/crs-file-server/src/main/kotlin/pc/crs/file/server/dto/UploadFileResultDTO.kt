package pc.crs.file.server.dto

import pc.crs.common.bean.NameUrlDTO

data class UploadFileResultDTO(val successes: List<NameUrlDTO> = emptyList(),
                               val fails: List<NameUrlDTO> = emptyList())