package pc.crs.common.constant

const val SUCCESS_CODE = 1
const val FAILURE_CODE = 0
const val TOKEN_INVALID_CODE = 401
const val NO_PERMISSION_CODE = 444

val BASE_DTO_READ_IGNORE_FIELD_LIST
        = arrayOf("id", "creator", "modifier", "createTime", "modifiedTime", "version", "note")