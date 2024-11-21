package org.hipeday.sphere.core.network;

/**
 * 网络客户端连接超时异常
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class ConnectTimeoutException extends ConnectException {

    public ConnectTimeoutException() {
    }

    public ConnectTimeoutException(String message) {
        super(message);
    }

    public ConnectTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectTimeoutException(Throwable cause) {
        super(cause);
    }

    public ConnectTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
