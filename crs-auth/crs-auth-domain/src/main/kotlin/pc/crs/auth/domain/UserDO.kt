package pc.crs.auth.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import pc.crs.common.domain.BaseDO
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user")
data class UserDO(
        @Column var code: String = "",
        @Column var name: String = "",
        @Column var gender: String = "未知",
        @Column var loginName: String = "",
        @JsonIgnore  @Column var password: String = "",
        @Column var birthday: LocalDate = LocalDate.MIN,
        @Column var phone: String = "",
        @Column var email: String = "",
        @Column var description: String = "",
        @Column var clientId: Long = -1
) : BaseDO()