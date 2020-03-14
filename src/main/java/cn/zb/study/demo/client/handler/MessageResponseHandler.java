package cn.zb.study.demo.client.handler;

import cn.zb.study.demo.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: 消息响应逻辑处理器
 * @Author: zb
 * @Date: 2020-03-03
 */
@ChannelHandler.Sharable // 加上注解标识，表明该 handler 是可以多个 channel 共享的
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    // 构造单例
    public static final MessageResponseHandler INSTANCE = new MessageResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();
        System.out.println(fromUserId + ":" + fromUserName + " -> " + messageResponsePacket .getMessage());
    }
}
