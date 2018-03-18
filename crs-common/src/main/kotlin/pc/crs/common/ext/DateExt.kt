package pc.crs.common.ext

import java.time.Instant.ofEpochMilli
import java.time.LocalDateTime
import java.time.LocalDateTime.ofInstant
import java.time.ZoneId.systemDefault
import java.util.*

fun Date.toLocalDateTime(): LocalDateTime = ofInstant(ofEpochMilli(this.time), systemDefault())
