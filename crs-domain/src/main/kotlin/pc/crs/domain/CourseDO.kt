package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import pc.crs.common.convert.LongListJsonConverter
import javax.persistence.*

@Entity
@Table(name = "course", indexes = [
    Index(name = "category_id_index", columnList = "categoryId")
])
data class CourseDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var categoryId: Long = -1,
        @Column(nullable = false) var categoryName: String = "",
        @Column(nullable = false) var teacherId: Long = -1,
        @Column(nullable = false) var teacherName: String = "",
        @Column(nullable = false, columnDefinition = "text") var description: String = "",
        @Column(nullable = false, columnDefinition = "text") var imageUrl: String = "",

        @Convert(converter = LongListJsonConverter::class) @Column(nullable = false)
        var cardIds: List<Long> = emptyList()
) : BaseDO()