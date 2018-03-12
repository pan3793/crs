package pc.crs.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.common.exception.RecordNotFoundException
import pc.crs.domain.CourseDO
import pc.crs.server.dao.CourseDAO

@Service
class CourseService(@Autowired override val dao: CourseDAO)
    : BaseService<CourseDO, CourseDO, CourseDAO>() {

    fun bindImage(id: Long, imageUrl: String) {
        validateImage(imageUrl)
        val courseDO = dao.findById(id).orElseThrow {
            RecordNotFoundException("${this.javaClass.simpleName},id=${id}记录未找到")
        }.apply { this.imageUrl = imageUrl }
        dao.save(courseDO)
    }

    fun validateImage(imageUrl: String) {

    }

    fun removeImage(id: Long) {
        val courseDO = dao.findById(id).orElseThrow {
            RecordNotFoundException("${this.javaClass.simpleName},id=${id}记录未找到")
        }.apply { this.imageUrl = "" }
        dao.save(courseDO)
    }
}