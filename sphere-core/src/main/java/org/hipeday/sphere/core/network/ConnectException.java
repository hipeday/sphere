package org.hipeday.sphere.core.network;

/**
 * 网络客户端连接异常
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class ConnectException extends RuntimeException {

    public ConnectException() {
    }

    public ConnectException(String message) {
        super(message);
    }

    public ConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectException(Throwable cause) {
        super(cause);
    }

    public ConnectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
