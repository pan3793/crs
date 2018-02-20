package pc.crs.server.config

import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.RestControllerAdvice
import pc.crs.common.config.BaseExceptionHandler

@Order(1)
@RestControllerAdvice
class GlobalExceptionHandler : BaseExceptionHandler()