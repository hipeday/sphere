package org.hipeday.sphere.core.logging;

import org.hipeday.sphere.core.util.ArrayUtils;

import java.text.MessageFormat;

/**
 * jdk默认日志对象
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class JDKSphereLogger extends AbstractSphereLogger {

    private final java.util.logging.Logger logger;

    public JDKSphereLogger(Class<?> clazz) {
        logger = java.util.logging.Logger.getLogger(clazz.getName());
    }

    @Override
    protected void logging(LogLevel level, String content, Object... args) {
        switch (level) {
            case DEBUG, INFO -> {
                if (ArrayUtils.isEmpty(args)) {
                    logger.info(content);
                } else {
                    logger.info(MessageFormat.format(content, args));
                }
            }
            case WARNING -> {
                if (ArrayUtils.isEmpty(args)) {
                    logger.warning(content);
                } else {
                    logger.warning(MessageFormat.format(content, args));
                }
            }
            case ERROR -> {
                if (ArrayUtils.isEmpty(args)) {
                    logger.severe(content);
                } else {
                    logger.severe(MessageFormat.format(content, args));
                }
            }
            default -> throw new IllegalArgumentException("Unsupported log level: " + level);
        }
    }
}
