package cn.zb.study.demo.client.handler;

import cn.zb.study.demo.protocol.response.LoginResponsePacket;
import cn.zb.study.demo.session.Session;
import cn.zb.study.demo.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: 登录响应逻辑处理器
 * @Author: zb
 * @Date: 2020-03-03
 */
@ChannelHandler.Sharable // 加上注解标识，表明该 handler 是可以多个 channel 共享的
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    // 构造单例
    public static final LoginResponseHandler INSTANCE = new LoginResponseHandler();

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        // 创建登录对象
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserId(UUID.randomUUID().toString());
//        loginRequestPacket.setUsername("zhaobo");
//        loginRequestPacket.setPassword("123456");
//
//        // 写数据
//        ctx.channel().writeAndFlush(loginRequestPacket);
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket){
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if (loginResponsePacket.isSuccess()) {
            System.out.println("[" + userName + "]登录成功，userId 为: " + loginResponsePacket.getUserId());
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            System.out.println("[" + userName + "]登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}
