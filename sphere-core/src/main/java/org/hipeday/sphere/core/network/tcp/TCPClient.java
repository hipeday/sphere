package org.hipeday.sphere.core.network.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.hipeday.sphere.core.session.Session;
import org.hipeday.sphere.core.session.support.TCPSession;
import org.hipeday.sphere.core.logging.SphereLogger;
import org.hipeday.sphere.core.network.AbstractClient;
import org.hipeday.sphere.core.network.InetAddress;
import org.hipeday.sphere.core.network.NetworkClientConfig;

/**
 * tcp客户端
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class TCPClient extends AbstractClient {

    private static final SphereLogger log = SphereLogger.getLogger(TCPClient.class);

    private final NioEventLoopGroup group = new NioEventLoopGroup();

    private TCPSession session;

    public TCPClient(NetworkClientConfig<?> config) {
        super(config);
    }

    @Override
    public void connect() {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new ClientHandler(config.clientId()));
                        }
                    });
            InetAddress inetAddress = config.serverAddress();
            bootstrap.connect(inetAddress.getHost(), inetAddress.getPort()).sync();
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
        if (msg instanceof byte[] data) {
            writeAndFlush(data);
        } else {
            throw new IllegalArgumentException("Unsupported data type: " + msg.getClass());
        }
    }

    @Override
    public void writeAndFlush(byte[] msg) {
        Channel channel = this.session.getChannel();
        ByteBuf buffer = channel.alloc().buffer(msg.length);
        buffer.writeBytes(msg);
        channel.writeAndFlush(buffer);
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

    @Override
    public String clientId() {
        return config.clientId();
    }
}
