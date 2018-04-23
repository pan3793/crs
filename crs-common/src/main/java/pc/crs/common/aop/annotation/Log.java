package pc.crs.common.aop.annotation;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 方法级日志切面注解
 *
 * @author pancheng
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface Log {
    /**
     * 日志等级，支持 "OFF", "ERROR", "WARN", "DEBUG", "INFO", "TRACE"
     */
    String level() default "INFO";

    /**
     * 是否记录参数
     */
    boolean enableArgs() default true;

    /**
     * 是否记录返回值
     */
    boolean enableRet() default true;

    /**
     * 忽略记录的类型
     */
    Class<?>[] ignoreTypes() default {ServletRequest.class, ServletResponse.class};
}
