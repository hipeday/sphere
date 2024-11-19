package org.hipeday.sphere.core.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Slf4j日志对象实例
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class Slf4JSphereLogger extends AbstractSphereLogger {

    private final Logger logger;


    public Slf4JSphereLogger(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    protected void logging(LogLevel level, String content, Object... args) {
        switch (level) {
            case DEBUG -> {
                if (logger.isDebugEnabled()) {
                    logger.debug(content, args);
                }
            }
            case INFO -> {
                if (logger.isInfoEnabled()) {
                    logger.info(content, args);
                }
            }
            case WARNING -> {
                if (logger.isWarnEnabled()) {
                    logger.warn(content, args);
                }
            }
            case ERROR -> {
                if (logger.isErrorEnabled()) {
                    logger.error(content, args);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + level);
        }
    }
}
