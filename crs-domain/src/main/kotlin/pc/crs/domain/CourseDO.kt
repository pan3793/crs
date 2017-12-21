package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "course")
data class CourseDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var categoryId: Long = -1,
        @Column(nullable = false) var teacherId: Long = -1,
        @Column(nullable = false) var teacherName: String = "",
        @Column(nullable = false) var teacherDescription: String = "",
        @Column(nullable = false) var description: String = "",
        @Column(nullable = false) var summary: String = "{}",
        @Column(nullable = false) var content: String = "{}"
) : BaseDO()