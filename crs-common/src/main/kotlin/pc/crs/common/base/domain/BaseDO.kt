package pc.crs.common.base.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class BaseDO(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = -1,
        @Column(nullable = false, columnDefinition = "text") var note: String = "",
        @JsonIgnore @CreatedDate @Column(nullable = false) var createTime: LocalDateTime = LocalDateTime.now(),
        @JsonIgnore @LastModifiedDate @Column(nullable = false) var modifiedTime: LocalDateTime = LocalDateTime.now(),
        @Column(nullable = false) var enabled: Boolean = true
) : Serializable