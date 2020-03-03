package cn.zb.study.demo.server.handler;

import cn.zb.study.demo.protocol.request.MessageRequestPacket;
import cn.zb.study.demo.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Description: 消息请求逻辑处理器
 * @Author: zb
 * @Date: 2020-03-03
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        ctx.channel().writeAndFlush(receiveMessage(messageRequestPacket));
    }

    /**
     * 接收消息
     */
    private MessageResponsePacket receiveMessage(MessageRequestPacket messageRequestPacket) {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
        return messageResponsePacket;
    }
}
