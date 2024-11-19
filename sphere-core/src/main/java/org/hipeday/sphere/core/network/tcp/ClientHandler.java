package org.hipeday.sphere.core.network.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.interceptor.InterceptorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端处理器
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(ClientHandler.class);

    private final InterceptorChain interceptorChain;
    private final SphereContext context;

    public ClientHandler(InterceptorChain interceptorChain, SphereContext context) {
        this.interceptorChain = interceptorChain;
        this.context = context;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        interceptorChain.activated(context);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf byteArrayMessage) {
            byte[] data = new byte[byteArrayMessage.readableBytes()];
            byteArrayMessage.readBytes(data);
            if (log.isDebugEnabled()) {
                log.debug("客户端收到消息: {}", new String(data));
            }
        }
    }
}
