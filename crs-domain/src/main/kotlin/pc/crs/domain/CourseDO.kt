package pc.crs.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import pc.crs.common.base.domain.BaseDO
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "course",indexes = [
    Index(name = "category_id_index", columnList = "categoryId")
])
data class CourseDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var categoryId: Long = -1,
        @Column(nullable = false) var teacherId: Long = -1,
        @Column(nullable = false) var teacherName: String = "",
        @Column(nullable = false, columnDefinition = "text") var teacherDescription: String = "",
        @Column(nullable = false, columnDefinition = "text") var description: String = "",

        @Basic(fetch = FetchType.LAZY) @Column(nullable = false, columnDefinition = "text")
        var summary: String = "{}",

        @Basic(fetch = FetchType.LAZY) @Column(nullable = false, columnDefinition = "mediumtext")
        var content: String = "{}",

        @CreatedBy @Column(nullable = false) var creator: String = "",
        @LastModifiedBy @Column(nullable = false) var modifier: String = "",
        @CreatedDate @Column(nullable = false) var createTime: LocalDateTime?,
        @LastModifiedDate @Column(nullable = false) var modifiedTime: LocalDateTime?,
        @JsonIgnore @Column(nullable = false, columnDefinition = "text") var note: String = "",
        @JsonIgnore @Version @Column(nullable = false) var version: Long?
)