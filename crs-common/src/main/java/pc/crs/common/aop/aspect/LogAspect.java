package pc.crs.common.aop.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pc.crs.common.aop.annotation.Log;
import pc.crs.common.util.JsonUtil;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(pc.crs.common.aop.annotation.Log)")
    public void aspect() {
    }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        String level = log.level();
        boolean enableArgs = log.enableArgs();
        boolean enableRet = log.enableRet();
        if ("OFF".equals(level) || !enableArgs && !enableRet) {
            return joinPoint.proceed();
        }

        Class<?>[] ignoreTypes = log.ignoreTypes();
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        Object[] args;
        String[] parameterNames = signature.getParameterNames();
        String argsString = "";
        if (enableArgs) {
            args = joinPoint.getArgs();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (!contains(ignoreTypes, arg)) {
                    builder.append(parameterNames[i])
                            .append("=")
                            .append(JsonUtil.toJsonString(arg, true))
                            .append("\n");
                }
            }
            argsString = builder.toString();
        }
        Object retVal;
        switch (level) {
            case "ERROR":
                if (enableArgs) logger.error("执行方法{}，入参{}", method.getName(), argsString);
                retVal = joinPoint.proceed();
                if (enableRet)
                    logger.error("执行方法{}，返回值{}", method.getName(), JsonUtil.toJsonString(retVal, true));
                break;
            case "WARN":
                if (enableArgs) logger.warn("执行方法{}，入参{}", method.getName(), argsString);
                retVal = joinPoint.proceed();
                if (enableRet)
                    logger.warn("执行方法{}，返回值{}", method.getName(), JsonUtil.toJsonString(retVal, true));
                break;
            case "INFO":
                if (enableArgs) logger.info("执行方法{}，入参{}", method.getName(), argsString);
                retVal = joinPoint.proceed();
                if (enableRet)
                    logger.info("执行方法{}，返回值{}", method.getName(), JsonUtil.toJsonString(retVal, true));
                break;
            case "DEBUG":
                if (enableArgs) logger.debug("执行方法{}，入参{}", method.getName(), argsString);
                retVal = joinPoint.proceed();
                if (enableRet)
                    logger.debug("执行方法{}，返回值{}", method.getName(), JsonUtil.toJsonString(retVal, true));
                break;
            case "TRACE":
                if (enableArgs) logger.trace("执行方法{}，入参{}", method.getName(), argsString);
                retVal = joinPoint.proceed();
                if (enableRet)
                    logger.trace("执行方法{}，返回值{}", method.getName(), JsonUtil.toJsonString(retVal, true));
                break;
            default:
                throw new RuntimeException("Log.level: " + level + " 标注错误");
        }
        return retVal;
    }

    private boolean contains(Class<?>[] classes, Object object) {
        // null参数都打印
        if (object == null) {
            return false;
        }
        for (Class<?> clazz : classes) {
            if (clazz.isAssignableFrom(object.getClass()))
                return true;
        }
        return false;
    }
}
