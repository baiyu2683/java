import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zh2683
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 负责为接受的连接创建channel
        EventLoopGroup boss = new NioEventLoopGroup();
        // 负责将boss创建的channel绑定到自己的eventloop上
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .localAddress(1080)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ChannelInboundHandlerAdapter(){
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        super.channelActive(ctx);
                                    }
                                });
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind();
        channelFuture.sync();
    }
}
