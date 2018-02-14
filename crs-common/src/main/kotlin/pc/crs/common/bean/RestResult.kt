package pc.crs.common.bean

import pc.crs.common.constant.FAILURE_CODE
import pc.crs.common.constant.NO_PERMISSION_CODE
import pc.crs.common.constant.SUCCESS_CODE

data class RestResult(
        val code: Int,
        val msg: String,
        val data: Any = "",
        val meta: Any = "")

fun successRestResult() = RestResult(SUCCESS_CODE, "操作成功")

fun successRestResult(msg: String) = RestResult(SUCCESS_CODE, msg)

fun successRestResult(data: Any) = RestResult(SUCCESS_CODE, "操作成功", data)

fun successRestResult(data: Any, meta: Any) = RestResult(SUCCESS_CODE, "操作成功", data, meta)

fun successRestResult(msg: String, data: Any) = RestResult(SUCCESS_CODE, msg, data)

fun successRestResult(msg: String, data: Any, meta: Any) = RestResult(SUCCESS_CODE, msg, data, meta)

fun failureRestResult() = RestResult(FAILURE_CODE, "操作失败")

fun failureRestResult(msg: String) = RestResult(FAILURE_CODE, msg)

fun failureRestResult(data: Any) = RestResult(FAILURE_CODE, "操作失败", data)

fun failureRestResult(data: Any, meta: Any) = RestResult(FAILURE_CODE, "操作失败", data, meta)

fun failureRestResult(msg: String, data: Any) = RestResult(FAILURE_CODE, msg, data)

fun failureRestResult(msg: String, data: Any, meta: Any) = RestResult(FAILURE_CODE, msg, data, meta)

fun noPermissionRestResult() = RestResult(NO_PERMISSION_CODE, "无权操作")

fun noPermissionRestResult(msg: String) = RestResult(NO_PERMISSION_CODE, msg)

fun noPermissionRestResult(data: Any) = RestResult(NO_PERMISSION_CODE, "无权操作", data)

fun noPermissionRestResult(data: Any, meta: Any) = RestResult(NO_PERMISSION_CODE, "无权操作", data, meta)

fun noPermissionRestResult(msg: String, data: Any) = RestResult(NO_PERMISSION_CODE, msg, data)

fun noPermissionRestResult(msg: String, data: Any, meta: Any) = RestResult(NO_PERMISSION_CODE, msg, data, meta)