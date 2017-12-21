package pc.crs.domain

import pc.crs.common.base.domain.BaseDO
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user_ext")
data class UserExtDO(
        @Column(nullable = false) var userId: Long = -1,
        @Column(nullable = false) var userName: String = "",
        @Column(nullable = false) var code: String = "",
        @Column(nullable = false) var gender: String = "",
        @Column(nullable = false) var nickname: String = "",
        @Column(nullable = true) var birthday: LocalDate?,
        @Column(nullable = false) var description: String = "",
        @Column(nullable = false) var email: String = "",
        @Column(nullable = false) var phone: String = "",
        @Column(nullable = false) var address: String = "",
        @Column(nullable = false) var avatarUrl: String = "",
        @Column(nullable = false) var detail: String = ""
) : BaseDO()