package pc.crs.common.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import pc.crs.common.bean.RestResult
import pc.crs.common.bean.failureRestResult
import pc.crs.common.bean.noPermissionRestResult
import pc.crs.common.exception.NoPermissionException
import pc.crs.common.exception.RecordNotFoundException
import pc.crs.common.exception.ValidateException

abstract class BaseExceptionHandler {

    open val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(RecordNotFoundException::class)
    fun handleRecordNotFoundException(e: RecordNotFoundException): RestResult {
        e.printStackTrace()
        logger.error("捕获到异常: {}", e.message)
        return failureRestResult(e.message ?: "记录不存在")
    }

    @ExceptionHandler(ValidateException::class)
    fun handleValidateException(e: ValidateException): RestResult {
        e.printStackTrace()
        logger.error("捕获到异常: {}", e.message)
        return failureRestResult(e.message ?: "数据不合法")
    }

    @ExceptionHandler(NoPermissionException::class)
    fun handleNoPermissionException(e: NoPermissionException): RestResult {
        e.printStackTrace()
        logger.error("捕获到异常: {}", e.message)
        return noPermissionRestResult(e.message ?: "无权访问")
    }

    @ExceptionHandler(Throwable::class)
    fun handleThrowable(e: Throwable): RestResult {
        e.printStackTrace()
        logger.error("捕获到异常: {}", e.message)
        return failureRestResult(e.message ?: "服务器发生异常")
    }
}