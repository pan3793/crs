package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "user_ext", indexes = [
    Index(name = "user_id_index", columnList = "userId")
])
data class UserExtDO(
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
        var detail: String = ""
) : BaseDO()