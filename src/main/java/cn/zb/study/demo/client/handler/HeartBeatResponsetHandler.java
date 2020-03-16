package cn.zb.study.demo.client.handler;

import cn.zb.study.demo.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: 心跳响应处理器
 * @Author: zb
 * @Date: 2020-03-14
 */
@ChannelHandler.Sharable
public class HeartBeatResponsetHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {

    public static final HeartBeatResponsetHandler INSTANCE = new HeartBeatResponsetHandler();

    private HeartBeatResponsetHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatResponsePacket heartBeatResponsePacket) {
        //System.out.println("收到心跳响应!");
    }
}
