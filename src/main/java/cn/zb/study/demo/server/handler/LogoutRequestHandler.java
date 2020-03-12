package cn.zb.study.demo.server.handler;

import cn.zb.study.demo.protocol.request.LogoutRequestPacket;
import cn.zb.study.demo.protocol.response.LogoutResponsePacket;
import cn.zb.study.demo.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: 登出请求逻辑处理器
 * @Author: zb
 * @Date: 2020-03-12
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) {
        System.out.println("用户: " + SessionUtil.getSession(ctx.channel()).getUserName() + " 登出");
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
