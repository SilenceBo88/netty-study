package cn.zb.study.demo.server.handler;

import cn.zb.study.demo.protocol.request.HeartBeatRequestPacket;
import cn.zb.study.demo.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: 心跳请求处理器
 * @Author: zb
 * @Date: 2020-03-14
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) {
        //System.out.println("收到心请求!");
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
