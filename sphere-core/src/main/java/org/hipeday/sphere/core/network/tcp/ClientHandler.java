package org.hipeday.sphere.core.network.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.hipeday.sphere.core.context.ApplicationContext;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.listener.ListenerChain;
import org.hipeday.sphere.core.logging.SphereLogger;
import org.hipeday.sphere.core.session.support.TCPSession;
import org.hipeday.sphere.core.util.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * 客户端处理器
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private static final SphereLogger log = SphereLogger.getLogger(ClientHandler.class);

    private final SphereContext context;

    public ClientHandler(String clientId) {
        this.context = ApplicationContext.getApplicationContext().getSphereContextRegistry().getInstance(clientId);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        if (context.getClient().getSession() == null) {
            context.getClient().setSession(new TCPSession(ctx.channel()));
        }
        context.getListenerChain().activated(context);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String heartbeatCommand = context.getClient().getConfig().heartbeat();
        ListenerChain listenerChain = context.getListenerChain();
        if (msg instanceof ByteBuf byteArrayMessage) {
            byte[] data = new byte[byteArrayMessage.readableBytes()];
            byteArrayMessage.readBytes(data);
            String message = new String(data, StandardCharsets.UTF_8);
            // 判断是否是心跳消息
            if (StringUtils.hasText(heartbeatCommand) && heartbeatCommand.equals(message)) {
                if (listenerChain != null) {
                    listenerChain.heartbeat(context, data);
                }
            }
            if (listenerChain != null) {
                listenerChain.message(context, data);
            }
        }
    }
}
