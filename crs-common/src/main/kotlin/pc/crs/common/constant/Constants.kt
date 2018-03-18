package pc.crs.common.constant

/**
 * RestResult状态码
 */
const val SUCCESS_CODE = 1
const val FAILURE_CODE = 0
const val TOKEN_INVALID_CODE = 401
const val NO_PERMISSION_CODE = 444

/**
 * JPA动态查询字段
 */

val BASE_ALLOWED_QUERY_CONDITION_LIST = listOf(
        "EQ_name",
        "EQ_creator",
        "GT_createTime", "GTE_createTime", "LT_createTime", "LTE_createTime",
        "O_createTime", "O_createTime_ASC", "O_createTime_DESC",
        "P_NUM", "P_SIZE"
)

/**
 * DTO忽略字段，用于DO数据防御
 */
val BASE_DTO_READ_IGNORE_FIELD_LIST = arrayOf("creator", "modifier", "createTime", "modifiedTime", "version", "note")

/**
 * 默认分页参数
 */
const val DEFAULT_PAGE_NUM = 0
const val DEFAULT_PAGE_SIZE = 10