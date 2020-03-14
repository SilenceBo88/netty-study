package cn.zb.study.demo.server;

import cn.zb.study.demo.codec.PacketCodecHandler;
import cn.zb.study.demo.codec.Spliter;
import cn.zb.study.demo.handler.IMIdleStateHandler;
import cn.zb.study.demo.server.handler.AuthHandler;
import cn.zb.study.demo.server.handler.HeartBeatRequestHandler;
import cn.zb.study.demo.server.handler.IMRequestHandler;
import cn.zb.study.demo.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * @Description: Netty服务端
 * @Author: zb
 * @Date: 2020-02-25
 */
public class NettyServer {

    private static final Integer PORT = 8000;


    public static void main(String[] args) {
        NioEventLoopGroup bossGrup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGrup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        // 空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        // 分离器 有状态
                        ch.pipeline().addLast(new Spliter());
                        // 编解码器
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        // 登录请求处理器
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        // 心跳请求处理器
                        ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        // 用户认证请求处理器
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        // IM请求处理器
                        ch.pipeline().addLast(IMRequestHandler.INSTANCE);
                    }
                });

        bind(serverBootstrap, PORT);
    }

    /**
     * 绑定端口
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()){
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println(new Date() + ": 端口[" + port + "]绑定失败!");
            }
        });
    }
}
