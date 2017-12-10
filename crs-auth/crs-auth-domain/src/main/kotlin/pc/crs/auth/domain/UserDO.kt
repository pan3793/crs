package pc.crs.auth.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import pc.crs.common.base.domain.BaseDO
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user")
data class UserDO(
        @Column(nullable = false) var code: String = "",
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var gender: String = "unknown",
        @Column(nullable = false) var loginName: String = "",
        @JsonIgnore @Column(nullable = false) var password: String = "",
        @Column var birthday: LocalDate? = null,
        @Column(nullable = false) var phone: String = "",
        @Column(nullable = false) var email: String = "",
        @Column(nullable = false) var description: String = "",
        @Column(nullable = false) var clientId: Long = -1
) : BaseDO()