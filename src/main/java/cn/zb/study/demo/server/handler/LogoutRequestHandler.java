package cn.zb.study.demo.server.handler;

import cn.zb.study.demo.protocol.request.LogoutRequestPacket;
import cn.zb.study.demo.protocol.response.LogoutResponsePacket;
import cn.zb.study.demo.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: 登出请求逻辑处理器
 * @Author: zb
 * @Date: 2020-03-12
 */
@ChannelHandler.Sharable // 加上注解标识，表明该 handler 是可以多个 channel 共享的
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    // 构造单例
    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) {
        System.out.println("用户: " + SessionUtil.getSession(ctx.channel()).getUserName() + " 登出");
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
