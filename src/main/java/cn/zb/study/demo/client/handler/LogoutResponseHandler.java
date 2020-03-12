package cn.zb.study.demo.client.handler;

import cn.zb.study.demo.protocol.response.LogoutResponsePacket;
import cn.zb.study.demo.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: 登出响应逻辑处理器
 * @Author: zb
 * @Date: 2020-03-12
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) {
        SessionUtil.unBindSession(ctx.channel());
        System.out.println("您已登出!");
    }
}
