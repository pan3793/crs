package pc.crs.auth.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import pc.crs.common.base.domain.BaseDO
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user")
data class UserDO(
        @Column(nullable = false) var name: String = "",
        @Column(nullable = false) var loginName: String = "",
        @JsonIgnore @Column(nullable = false) var password: String = "",
        @Column(nullable = false) var clientId: Long = -1
) : BaseDO()