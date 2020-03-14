package cn.zb.study.demo.client;

import cn.zb.study.demo.client.handler.CreateGroupResponseHandler;
import cn.zb.study.demo.client.handler.GroupMessageResponseHandler;
import cn.zb.study.demo.client.handler.JoinGroupResponseHandler;
import cn.zb.study.demo.client.handler.ListGroupMembersResponseHandler;
import cn.zb.study.demo.client.handler.LoginResponseHandler;
import cn.zb.study.demo.client.handler.LogoutResponseHandler;
import cn.zb.study.demo.client.handler.MessageResponseHandler;
import cn.zb.study.demo.client.handler.QuitGroupResponseHandler;
import cn.zb.study.demo.codec.PacketCodecHandler;
import cn.zb.study.demo.codec.PacketDecoder;
import cn.zb.study.demo.codec.PacketEncoder;
import cn.zb.study.demo.codec.Spliter;
import cn.zb.study.demo.console.ConsoleCommandManager;
import cn.zb.study.demo.console.LoginConsoleCommand;
import cn.zb.study.demo.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
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
                        // 分离器 有状态
                        ch.pipeline().addLast(new Spliter());
                        // 编解码器
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        // 登录响应处理器
                        ch.pipeline().addLast(LoginResponseHandler.INSTANCE);
                        // 收消息处理器
                        ch.pipeline().addLast(MessageResponseHandler.INSTANCE);
                        // 创建群响应处理器
                        ch.pipeline().addLast(CreateGroupResponseHandler.INSTANCE);
                        // 加群响应处理器
                        ch.pipeline().addLast(JoinGroupResponseHandler.INSTANCE);
                        // 退群响应处理器
                        ch.pipeline().addLast(QuitGroupResponseHandler.INSTANCE);
                        // 获取群成员响应处理器
                        ch.pipeline().addLast(ListGroupMembersResponseHandler.INSTANCE);
                        // 群聊消息响应处理器
                        ch.pipeline().addLast(GroupMessageResponseHandler.INSTANCE);
                        // 登出响应处理器
                        ch.pipeline().addLast(LogoutResponseHandler.INSTANCE);
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
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()){
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }

    /**
     * 模拟登录响应
     */
    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
