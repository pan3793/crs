package pc.crs.server.service

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Service
import pc.crs.common.base.service.BaseService
import pc.crs.common.bean.IdNameDTO
import pc.crs.common.constant.BASE_ALLOWED_QUERY_CONDITION_LIST
import pc.crs.common.exception.RecordNotFoundException
import pc.crs.domain.CourseDO
import pc.crs.server.dao.CardDAO
import pc.crs.server.dao.CourseDAO
import pc.crs.server.dto.CourseDetailDTO
import pc.crs.server.dto.CourseWithCardNameDTO
import pc.crs.server.dto.RecommendedCoursesDTO
import pc.crs.server.manager.FileManager

@Service
class CourseService(@Autowired override val dao: CourseDAO,
                    @Autowired val cardDAO: CardDAO,
                    @Autowired val categoryService: CategoryService)
    : BaseService<CourseDO, CourseDO, CourseDAO>(), ApplicationContextAware {

    // 暂时绕开当前版本 Feign Bug
    lateinit var appCtx: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appCtx = applicationContext
    }

    val fileManager: FileManager by lazy {
        appCtx.getBean<FileManager>(FileManager::javaClass)
    }

    override val allowedQueryConditions: List<String> = BASE_ALLOWED_QUERY_CONDITION_LIST + listOf(
            "LIKE_name",
            "EQ_categoryId", "IN_categoryId",
            "EQ_categoryName", "LIKE_categoryName",
            "EQ_teacherId", "IN_teacherId",
            "EQ_teacherName", "LIKE_teacherName"
    )

    fun fetchIdNameList(): List<IdNameDTO> = dao.findAll().map { IdNameDTO(it.id!!, it.name) }

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

    fun findCourseDetail(id: Long): CourseDetailDTO = this.findById(id).let { courseDO ->
        CourseDetailDTO().apply {
            BeanUtils.copyProperties(courseDO, this)
            this.cards = cardDAO.findAllByIdIn(courseDO.cardIds).map { cardDO ->
                CourseDetailDTO.Card().apply {
                    BeanUtils.copyProperties(cardDO, this)
                    this.files = fileManager.getByIdList(cardDO.fileIds).map { fileDO ->
                        CourseDetailDTO.Card.File().apply {
                            BeanUtils.copyProperties(fileDO, this)
                        }
                    }
                }
            }
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