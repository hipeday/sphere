package org.hipeday.sphere.core.logging;

/**
 * 抽象日志对象实例
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public abstract class AbstractSphereLogger implements SphereLogger {

    protected abstract void logging(LogLevel level, String content, Object... args);

    @Override
    public void debug(String content, Object... args) {
        logging(LogLevel.DEBUG, content, args);
    }

    @Override
    public void info(String content, Object... args) {
        logging(LogLevel.INFO, content, args);
    }

    @Override
    public void warning(String content, Object... args) {
        logging(LogLevel.WARNING, content, args);
    }

    @Override
    public void error(String content, Object... args) {
        logging(LogLevel.ERROR, content, args);
    }
}
