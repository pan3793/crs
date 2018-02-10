package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.*

@Entity
@Table(name = "course",indexes = [
    Index(name = "category_id_index", columnList = "categoryId")
])
data class CourseDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var categoryId: Long = -1,
        @Column(nullable = false) var teacherId: Long = -1,
        @Column(nullable = false) var teacherName: String = "",
        @Column(nullable = false, columnDefinition = "text") var teacherDescription: String = "",
        @Column(nullable = false, columnDefinition = "text") var description: String = "",

        @Basic(fetch = FetchType.LAZY) @Column(nullable = false, columnDefinition = "text")
        var summary: String = "{}",

        @Basic(fetch = FetchType.LAZY) @Column(nullable = false, columnDefinition = "mediumtext")
        var content: String = "{}"
) : BaseDO()