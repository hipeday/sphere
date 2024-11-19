package org.hipeday.sphere.core.network.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.hipeday.sphere.core.config.SphereConfiguration;
import org.hipeday.sphere.core.context.Session;
import org.hipeday.sphere.core.context.TCPClientContext;
import org.hipeday.sphere.core.context.TCPSession;
import org.hipeday.sphere.core.logging.SphereLogger;
import org.hipeday.sphere.core.network.AbstractClient;
import org.hipeday.sphere.core.network.InetAddress;
import org.hipeday.sphere.core.network.SphereClientConfig;

/**
 * tcp客户端
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class TCPClient extends AbstractClient {

    private static final SphereLogger log = SphereLogger.getLogger(TCPClient.class);

    private final NioEventLoopGroup group = new NioEventLoopGroup();

    private TCPSession session;

    public TCPClient(SphereClientConfig<?> config, SphereConfiguration configuration) {
        super(config, configuration);
    }

    @Override
    public void connect() {
        setContext(new TCPClientContext(this));
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new ClientHandler(interceptorChain, getContext()));
                        }
                    });
            InetAddress inetAddress = config.serverAddress();
            bootstrap.connect(inetAddress.getHost(), inetAddress.getPort()).sync();
            log.info("{} 客户端 {} 与服务器 {}:{} 连接成功", config.protocol(), config.clientId(), inetAddress.getHost(), inetAddress.getPort());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session session) {
        this.session = (TCPSession) session;
    }

    @Override
    public void writeAndFlush(Object msg) {
        if (session.getChannel() == null) {
            throw new IllegalStateException("Channel is not connected");
        }
        session.getChannel().writeAndFlush(msg);
    }

    @Override
    public boolean isConnected() {
        return session != null && session.getChannel() != null && session.getChannel().isActive();
    }

    @Override
    public void close() {
        if (session.getChannel() != null) {
            log.info("关闭客户端 {}", config.clientId());
            session.getChannel().close();
        }
        group.shutdownGracefully();
    }


}