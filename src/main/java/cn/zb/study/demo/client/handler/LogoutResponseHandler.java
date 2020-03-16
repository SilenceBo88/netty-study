package cn.zb.study.demo.client.handler;

import cn.zb.study.demo.protocol.response.LogoutResponsePacket;
import cn.zb.study.demo.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: 登出响应逻辑处理器
 * @Author: zb
 * @Date: 2020-03-12
 */
@ChannelHandler.Sharable // 加上注解标识，表明该 handler 是可以多个 channel 共享的
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    // 构造单例
    public static final LogoutResponseHandler INSTANCE = new LogoutResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) {
        SessionUtil.unBindSession(ctx.channel());
        System.out.println("您已登出!");
    }
}
