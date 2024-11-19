package org.hipeday.sphere.core.network;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 地址信息
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class InetAddress {

    private final String host;

    private final Integer port;

    public InetAddress(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public InetAddress(Integer port) {
        this.port = port;
        host = getDefaultHost();
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getDefaultHost() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<java.net.InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    java.net.InetAddress inetAddress = inetAddresses.nextElement();
                    // 忽略未启用或者回环地址
                    if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                        continue;
                    }
                    Enumeration<java.net.InetAddress> addresses = networkInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        java.net.InetAddress address = addresses.nextElement();

                        // 检查是否是IPv4地址，并且不是回环地址
                        if (inetAddress instanceof java.net.Inet4Address && !inetAddress.isLoopbackAddress()) {
                            String ip = inetAddress.getHostAddress();
                            // 仅返回局域网地址
                            if (ip.startsWith("192.") || ip.startsWith("10.") || ip.startsWith("172.")) {
                                return ip;
                            }
                        }
                    }
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return "127.0.0.1";
    }
}
