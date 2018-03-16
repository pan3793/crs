package pc.crs.server.service

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.common.exception.RecordNotFoundException
import pc.crs.domain.CourseDO
import pc.crs.server.dao.CardDAO
import pc.crs.server.dao.CourseDAO
import pc.crs.server.dto.CourseDTOWithCardName

@Service
class CourseService(@Autowired override val dao: CourseDAO,
                    @Autowired val cardDAO: CardDAO)
    : BaseService<CourseDO, CourseDO, CourseDAO>() {

    fun findAllWithCardName(): Iterable<CourseDTOWithCardName> {
        val courseDOs = dao.findAll()
        val cardIdNameMap = cardDAO.findAllByIdIn(courseDOs.flatMap { it.cardIds }.distinct())
                .associate { it.id to it.name }

        return courseDOs.map { courseDO ->
            val dto = CourseDTOWithCardName()
            BeanUtils.copyProperties(courseDO, dto)
            courseDO.cardIds.forEach { cardId ->
                dto.cards += CourseDTOWithCardName.Card(cardId, cardIdNameMap.getOrDefault(cardId, ""))
            }
            dto
        }
    }

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

    fun bindCard(id: Long, cardId: Long) {
        val courseDO = findById(id)
        if (!courseDO.cardIds.contains(cardId))
            courseDO.cardIds += cardId
        dao.save(courseDO)
    }

    fun removeCard(id: Long, cardId: Long) {
        val courseDO = findById(id)
        courseDO.cardIds -= cardId
        dao.save(courseDO)
    }
}