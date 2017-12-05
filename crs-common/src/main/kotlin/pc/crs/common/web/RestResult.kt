package pc.crs.common.web

data class RestResult(
        val code: Int,
        val msg: String,
        val data: Any = "",
        val meta: Any = "")

fun successRestResult() = RestResult(1, "success")

fun successRestResult(msg: String) = RestResult(1, msg)

fun successRestResult(data: Any) = RestResult(1, "success", data)

fun successRestResult(data: Any, meta: Any) = RestResult(1, "success", data, meta)

fun successRestResult(msg: String, data: Any) = RestResult(1, msg, data)

fun successRestResult(msg: String, data: Any, meta: Any) = RestResult(1, msg, data, meta)

fun failureRestResult() = RestResult(0, "failure")

fun failureRestResult(msg: String) = RestResult(0, msg)

fun failureRestResult(data: Any) = RestResult(0, "failure", data)

fun failureRestResult(data: Any, meta: Any) = RestResult(0, "failure", data, meta)

fun failureRestResult(msg: String, data: Any) = RestResult(0, msg, data)

fun failureRestResult(msg: String, data: Any, meta: Any) = RestResult(0, msg, data, meta)