package pc.crs.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners
@Table(name = "category")
data class CategoryDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false, columnDefinition = "text") var description: String = "",

        @CreatedBy @Column(nullable = false) var creator: String = "",
        @LastModifiedBy @Column(nullable = false) var modifier: String = "",
        @CreatedDate @Column(nullable = false) var createTime: LocalDateTime?,
        @LastModifiedDate @Column(nullable = false) var modifiedTime: LocalDateTime?,
        @JsonIgnore @Column(nullable = false, columnDefinition = "text") var note: String = "",
        @JsonIgnore @Version @Column(nullable = false) var version: Long?
)