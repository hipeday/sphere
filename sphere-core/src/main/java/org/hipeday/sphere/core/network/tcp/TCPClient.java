package org.hipeday.sphere.core.network.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.hipeday.sphere.core.context.TCPClientContext;
import org.hipeday.sphere.core.network.AbstractClient;
import org.hipeday.sphere.core.network.InetAddress;
import org.hipeday.sphere.core.network.SphereClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * tcp客户端
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class TCPClient extends AbstractClient {

    private static final Logger log = LoggerFactory.getLogger(TCPClient.class);

    private final NioEventLoopGroup group = new NioEventLoopGroup();

    private Channel channel;

    public TCPClient(SphereClientConfig<?> config) {
        super(config);
    }

    @Override
    public void connect() {
        setContext(new TCPClientContext());
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
            ChannelFuture future = bootstrap.connect(inetAddress.getHost(), inetAddress.getPort()).sync();
            if (log.isInfoEnabled()) {
                log.info("{} 客户端 {} 与服务器 {}:{} 连接成功", config.protocol(), config.clientId(), inetAddress.getHost(), inetAddress.getPort());
            }
            this.channel = future.channel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeAndFlush(Object msg) {
        if (channel == null) {
            throw new IllegalStateException("Channel is not connected");
        }
        channel.writeAndFlush(msg);
    }

    @Override
    public boolean isConnected() {
        return channel != null && channel.isActive();
    }

    @Override
    public void close() {
        group.shutdownGracefully();
    }


}
