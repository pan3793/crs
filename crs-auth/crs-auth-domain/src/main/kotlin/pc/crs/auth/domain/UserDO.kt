package pc.crs.auth.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import pc.crs.common.base.domain.BaseDO
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "user")
data class UserDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var loginName: String = "",
        @JsonIgnore @Column(nullable = false) var password: String = ""
) : BaseDO()