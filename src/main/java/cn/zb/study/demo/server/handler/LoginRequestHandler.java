package cn.zb.study.demo.server.handler;

import cn.zb.study.demo.protocol.request.LoginRequestPacket;
import cn.zb.study.demo.protocol.response.LoginResponsePacket;
import cn.zb.study.demo.session.Session;
import cn.zb.study.demo.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Description: 登录请求逻辑处理器
 * @Author: zb
 * @Date: 2020-03-03
 */
@ChannelHandler.Sharable // 加上注解标识，表明该 handler 是可以多个 channel 共享的
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    // 构造单例
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        ctx.channel().writeAndFlush(login(ctx, loginRequestPacket));
    }

    /**
     * 用户断线之后取消绑定
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }


    /**
     * 登录情况返回结果
     */
    private LoginResponsePacket login(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUsername());

        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + loginRequestPacket.getUsername() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }
        return loginResponsePacket;
    }

    /**
     * 登录情况校验
     */
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    /**
     * 生成用户uid
     */
    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
