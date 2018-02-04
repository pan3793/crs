package pc.crs.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import pc.crs.common.base.domain.BaseDO
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "user_ext", indexes = [
    Index(name = "user_id_index", columnList = "userId")
])
data class UserExtDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
        @Column(nullable = false) var userId: Long = -1,
        @Column(nullable = false) var userName: String = "",
        @Column(nullable = false) var code: String = "",
        @Column(nullable = false) var gender: String = "",
        @Column(nullable = false) var nickname: String = "",
        @Column(nullable = true) var birthday: LocalDate?,
        @Column(nullable = false, columnDefinition = "text") var description: String = "",
        @Column(nullable = false) var email: String = "",
        @Column(nullable = false) var phone: String = "",
        @Column(nullable = false, columnDefinition = "text") var address: String = "",
        @Column(nullable = false, columnDefinition = "text") var avatarUrl: String = "",

        @Basic(fetch = FetchType.LAZY) @Column(nullable = false, columnDefinition = "mediumtext")
        var detail: String = "",

        @CreatedBy @Column(nullable = false) var creator: String = "",
        @LastModifiedBy @Column(nullable = false) var modifier: String = "",
        @CreatedDate @Column(nullable = false) var createTime: LocalDateTime?,
        @LastModifiedDate @Column(nullable = false) var modifiedTime: LocalDateTime?,
        @JsonIgnore @Column(nullable = false, columnDefinition = "text") var note: String = "",
        @JsonIgnore @Version @Column(nullable = false) var version: Long?
)