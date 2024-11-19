package org.hipeday.sphere.core.network.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.hipeday.sphere.core.config.SphereConfiguration;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.context.TCPSession;
import org.hipeday.sphere.core.interceptor.InterceptorChain;
import org.hipeday.sphere.core.listener.Listener;
import org.hipeday.sphere.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

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
    public void channelActive(ChannelHandlerContext ctx) {
        if (context.getClient().getSession() == null) {
            context.getClient().setSession(new TCPSession(ctx.channel()));
        }
        interceptorChain.activated(context);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String heartbeatCommand = context.getClient().getConfig().heartbeat();
        if (msg instanceof ByteBuf byteArrayMessage) {
            byte[] data = new byte[byteArrayMessage.readableBytes()];
            byteArrayMessage.readBytes(data);
            String message = new String(data, StandardCharsets.UTF_8);
            // 判断是否是心跳消息
            if (StringUtils.hasText(heartbeatCommand) && heartbeatCommand.equals(message)) {
                Listener heartbeatListener = context.getClient().getConfig().getHeartbeatListener();
                if (heartbeatListener != null) {
                    heartbeatListener.listener(context, data);
                }
                return;
            }
            if (log.isDebugEnabled()) {
                log.debug("客户端收到消息: {}", message);
            }
        }
    }
}
