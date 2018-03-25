package pc.crs.common.constant

import pc.crs.common.bean.CodeNameDTO

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
        "P_NUM", "P_SIZE", "P_DISABLE"
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

/**
 * 题目类型
 */
val QUESTION_TYPE_LIST = listOf(
        CodeNameDTO("1", "单选题"),
        CodeNameDTO("2", "多选题"),
        CodeNameDTO("3", "填空题"),
        CodeNameDTO("4", "问答题")
)

/**
 * 题目批阅类型
 */
val QUESTION_CHECK_TYPE_LIST = listOf(
        CodeNameDTO("1", "自动"),
        CodeNameDTO("2", "手工")
)