package cn.zb.study.demo.server;

import cn.zb.study.demo.codec.PacketDecoder;
import cn.zb.study.demo.codec.PacketEncoder;
import cn.zb.study.demo.codec.Spliter;
import cn.zb.study.demo.server.handler.AuthHandler;
import cn.zb.study.demo.server.handler.CreateGroupRequestHandler;
import cn.zb.study.demo.server.handler.GroupMessageRequestHandler;
import cn.zb.study.demo.server.handler.JoinGroupRequestHandler;
import cn.zb.study.demo.server.handler.ListGroupMembersRequestHandler;
import cn.zb.study.demo.server.handler.LoginRequestHandler;
import cn.zb.study.demo.server.handler.LogoutRequestHandler;
import cn.zb.study.demo.server.handler.MessageRequestHandler;
import cn.zb.study.demo.server.handler.QuitGroupRequestHandler;
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
                        // 分离器
                        ch.pipeline().addLast(new Spliter());
                        // 解码器
                        ch.pipeline().addLast(new PacketDecoder());
                        // 登录请求处理器
                        ch.pipeline().addLast(new LoginRequestHandler());
                        // 用户认证请求处理器
                        ch.pipeline().addLast(new AuthHandler());
                        // 单聊消息请求处理器
                        ch.pipeline().addLast(new MessageRequestHandler());
                        // 创建群请求处理器
                        ch.pipeline().addLast(new CreateGroupRequestHandler());
                        // 加群请求处理器
                        ch.pipeline().addLast(new JoinGroupRequestHandler());
                        // 退群请求处理器
                        ch.pipeline().addLast(new QuitGroupRequestHandler());
                        // 获取群成员请求处理器
                        ch.pipeline().addLast(new ListGroupMembersRequestHandler());
                        // 群聊消息请求处理器
                        ch.pipeline().addLast(new GroupMessageRequestHandler());
                        // 登出请求处理器
                        ch.pipeline().addLast(new LogoutRequestHandler());
                        // 编码器
                        ch.pipeline().addLast(new PacketEncoder());
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
