package cn.zb.study.demo.client.handler;

import cn.zb.study.demo.protocol.response.GroupMessageResponsePacket;
import cn.zb.study.demo.session.Session;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: 群聊消息响应逻辑处理器
 * @Author: zb
 * @Date: 2020-03-14
 */
@ChannelHandler.Sharable // 加上注解标识，表明该 handler 是可以多个 channel 共享的
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    // 构造单例
    public static final GroupMessageResponseHandler INSTANCE = new GroupMessageResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket responsePacket) {
        String fromGroupId = responsePacket.getFromGroupId();
        Session fromUser = responsePacket.getFromUser();
        System.out.println("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + responsePacket.getMessage());
    }
}
