package pc.crs.file.server.controller

import org.slf4j.Logger

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import pc.crs.common.bean.NameUrlDTO
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.failureRestResult
import pc.crs.common.bean.successRestResult
import pc.crs.file.domain.FileDO
import pc.crs.file.server.dto.UploadFileResultDTO
import pc.crs.file.server.service.FileService
import java.io.File
import java.io.IOException
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/api/file")
class FileController(@Autowired val fileService: FileService,
                     @Value("\${file.basePath}") val basePath: String,
                     @Value("\${ip}") val ip: String) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("upload")
    fun upload(@RequestParam("files") files: Array<MultipartFile>): RestResult {

        if (files.isEmpty()) {
            return failureRestResult("上传文件列表不能为空")
        }

        val uploadSuccessFileNameUrls = arrayListOf<NameUrlDTO>()
        val uploadFailedFileNameUrls = arrayListOf<NameUrlDTO>()

        files.forEach { file ->
            val uuid = UUID.randomUUID()
            val filename = file.originalFilename ?: "file-$uuid"
            try {
                val filePath = "$basePath/${LocalDate.now()}/$uuid"
                File(filePath).mkdirs()
                val storeFile = File(filePath, filename)
                file.transferTo(storeFile)

                val type = when (filename.substringAfterLast('.', "")) {
                    "doc", "docx" -> "word"
                    "xls", "xlsx" -> "excel"
                    "ppt", "pptx" -> "ppt"
                    "pdf" -> "pdf"
                    "zip", "rar", "7z" -> "zip"
                    else -> "other"
                }
                val url = "http://$ip/res/$uuid/$filename"
                fileService.save(FileDO(filename, type, url))
                uploadSuccessFileNameUrls += NameUrlDTO(filename, url)
            } catch (e: IOException) {
                e.printStackTrace()
                uploadFailedFileNameUrls += NameUrlDTO(filename)
                logger.error("文件<{}>存储出错", filename)
            }
        }
        val uploadFileResultDTO = UploadFileResultDTO(uploadSuccessFileNameUrls, uploadFailedFileNameUrls)
        return when {
            uploadSuccessFileNameUrls.size == files.size ->
                successRestResult("共上传${files.size}个文件，全部上传成功", uploadFileResultDTO)

            uploadSuccessFileNameUrls.isEmpty() ->
                failureRestResult("共上传${files.size}个文件，全部上传失败", uploadFileResultDTO)

            else -> failureRestResult("共上传${files.size}个文件，" +
                    "${uploadSuccessFileNameUrls.size}个上传成功，" +
                    "${uploadFailedFileNameUrls.size}个上传失败", uploadFileResultDTO)
        }
    }
}