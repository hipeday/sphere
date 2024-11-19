package org.hipeday.sphere.core.logging;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * IOT物联网设备日志控制对象
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public interface SphereLogger {

    String SLF4J_LOGGER_CLASS = "org.slf4j.Logger";

    static SphereLogger getLogger(Class<?> clazz) {
        boolean supportSlf4j = false;
        try {
            Class<?> aClass = Class.forName(SLF4J_LOGGER_CLASS);
            supportSlf4j = true;
        } catch (ClassNotFoundException ignore) {}

        if (supportSlf4j) {
            Class<Slf4JSphereLogger> slf4jLoggerClass = Slf4JSphereLogger.class;
            Constructor<Slf4JSphereLogger> constructor = null;
            try {
                constructor = slf4jLoggerClass.getConstructor(Class.class);
                return constructor.newInstance(clazz);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return new JDKSphereLogger(clazz);
    }

    /**
     * 输出DEBUG级别内容到日志
     *
     * @param content 日志内容
     * @param args 参数列表
     */
    void debug(String content, Object ...args);

    /**
     * 输出INFO级别内容到日志
     *
     * @param content 日志内容
     * @param args 参数列表
     */
    void info(String content, Object ...args);

    /**
     * 输出ERROR级别内容到日志
     *
     * @param content 日志内容
     * @param args 参数列表
     */
    void warning(String content, Object ...args);

    /**
     * 输出ERROR级别内容到日志
     *
     * @param content 日志内容
     * @param args 参数列表
     */
    void error(String content, Object ...args);


}
