package cn.zb.study.demo.client;

import cn.zb.study.demo.client.handler.LoginResponseHandler;
import cn.zb.study.demo.client.handler.MessageResponseHandler;
import cn.zb.study.demo.codec.PacketDecoder;
import cn.zb.study.demo.codec.PacketEncoder;
import cn.zb.study.demo.codec.Spliter;
import cn.zb.study.demo.protocol.PacketCodec;
import cn.zb.study.demo.protocol.request.MessageRequestPacket;
import cn.zb.study.demo.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Netty客户端
 * @Author: zb
 * @Date: 2020-02-25
 */
public class NettyClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;
    private static final Integer MAX_RETRY = 5 ;

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        //ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    /**
     * 连接服务器（指数退避的客户端重连机制）
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println(new Date() + ": 重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                // 定时重试
                bootstrap
                        .config()
                        .group()
                        .schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    /**
     * 开启控制台线程
     */
    private static void startConsoleThread(Channel channel){
        new Thread(() -> {
              while (!Thread.interrupted()){
                  if (LoginUtil.hasLogin(channel)) {
                      System.out.println("输入消息发送至服务端: ");
                      Scanner sc = new Scanner(System.in);
                      String line = sc.nextLine();

                      MessageRequestPacket packet = new MessageRequestPacket();
                      packet.setMessage(line);
                      ByteBuf byteBuf = PacketCodec.INSTANCE.encode(channel.alloc().ioBuffer(), packet);
                      channel.writeAndFlush(byteBuf);
                  }
              }
        }).start();
    }
}
