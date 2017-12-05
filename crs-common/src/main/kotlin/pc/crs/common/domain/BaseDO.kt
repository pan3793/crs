package pc.crs.common.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class BaseDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
        @Column var note: String = "",
        @JsonIgnore @Column var createTime: LocalDateTime = LocalDateTime.now(),
        @JsonIgnore @Column var modifiedTime: LocalDateTime = LocalDateTime.now(),
        @JsonIgnore @Column var enabled: Boolean = true
) : Serializable
