package org.hipeday.sphere.core.context;

import io.netty.channel.Channel;

/**
 * tcp客户端会话
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class TCPSession implements Session {

    private final Channel channel;

    public TCPSession(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }
}
