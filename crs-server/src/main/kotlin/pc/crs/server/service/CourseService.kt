package pc.crs.server.service

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.common.constant.BASE_ALLOWED_QUERY_CONDITION_LIST
import pc.crs.common.exception.RecordNotFoundException
import pc.crs.domain.CourseDO
import pc.crs.server.dao.CardDAO
import pc.crs.server.dao.CourseDAO
import pc.crs.server.dto.CourseWithCardNameDTO
import pc.crs.server.dto.RecommendedCoursesDTO

@Service
class CourseService(@Autowired override val dao: CourseDAO,
                    @Autowired val cardDAO: CardDAO,
                    @Autowired val categoryService: CategoryService)
    : BaseService<CourseDO, CourseDO, CourseDAO>() {

    override val allowedQueryConditions: List<String> = BASE_ALLOWED_QUERY_CONDITION_LIST + listOf(
            "LIKE_name",
            "EQ_categoryId", "IN_categoryId",
            "EQ_categoryName", "LIKE_categoryName",
            "EQ_teacherId", "IN_teacherId",
            "EQ_teacherName", "LIKE_teacherName"
    )

    fun findRecommended(): RecommendedCoursesDTO =
            RecommendedCoursesDTO(all = dao.findAllByOrderByModifiedTimeLimit(5),
                    categories = categoryService.fetchIdNameList().map { (id, name) ->
                        RecommendedCoursesDTO.CategoryWithRecommendedCourse(id = id, name = name,
                                courses = dao.findAllByCategoryIdAndOrderByModifiedTimeLimit(id, 4))
                    })

    fun findAllWithCardName(): Iterable<CourseWithCardNameDTO> {
        val courseDOs = dao.findAll()
        val cardIdNameMap = cardDAO.findAllByIdIn(courseDOs.flatMap { it.cardIds }.distinct())
                .associate { it.id to it.name }

        return courseDOs.map { courseDO ->
            val dto = CourseWithCardNameDTO()
            BeanUtils.copyProperties(courseDO, dto)
            courseDO.cardIds.forEach { cardId ->
                dto.cards += CourseWithCardNameDTO.Card(cardId, cardIdNameMap.getOrDefault(cardId, ""))
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