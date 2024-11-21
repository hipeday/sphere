package org.hipeday.sphere.core.exception;

/**
 * Sphere 运行时异常
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class SphereRuntimeException extends RuntimeException {

    public SphereRuntimeException() {
    }

    public SphereRuntimeException(String message) {
        super(message);
    }

    public SphereRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SphereRuntimeException(Throwable cause) {
        super(cause);
    }

    public SphereRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
