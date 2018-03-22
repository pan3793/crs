package pc.crs.server.dto

import pc.crs.domain.CourseDO

data class RecommendedCoursesDTO(var all: List<CourseDO> = emptyList(),
                                 var categories: List<CategoryWithRecommendedCourse> = emptyList()) {
    data class CategoryWithRecommendedCourse(var id: Long? = null,
                                             var name: String = "",
                                             var courses: List<CourseDO> = emptyList())
}

