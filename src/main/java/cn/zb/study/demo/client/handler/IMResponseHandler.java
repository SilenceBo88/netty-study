package cn.zb.study.demo.client.handler;

import cn.zb.study.demo.protocol.Packet;
import static cn.zb.study.demo.protocol.command.Command.CREATE_GROUP_RESPONSE;
import static cn.zb.study.demo.protocol.command.Command.GROUP_MESSAGE_RESPONSE;
import static cn.zb.study.demo.protocol.command.Command.JOIN_GROUP_RESPONSE;
import static cn.zb.study.demo.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;
import static cn.zb.study.demo.protocol.command.Command.LOGOUT_RESPONSE;
import static cn.zb.study.demo.protocol.command.Command.MESSAGE_RESPONSE;
import static cn.zb.study.demo.protocol.command.Command.QUIT_GROUP_RESPONSE;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: IM请求处理器
 * @Author: zb
 * @Date: 2020-03-14
 */
@ChannelHandler.Sharable
public class IMResponseHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMResponseHandler INSTANCE = new IMResponseHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMResponseHandler() {
        handlerMap = new HashMap<>();

        handlerMap.put(MESSAGE_RESPONSE, MessageResponseHandler.INSTANCE);
        handlerMap.put(CREATE_GROUP_RESPONSE, CreateGroupResponseHandler.INSTANCE);
        handlerMap.put(JOIN_GROUP_RESPONSE, JoinGroupResponseHandler.INSTANCE);
        handlerMap.put(QUIT_GROUP_RESPONSE, QuitGroupResponseHandler.INSTANCE);
        handlerMap.put(LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponseHandler.INSTANCE);
        handlerMap.put(GROUP_MESSAGE_RESPONSE, GroupMessageResponseHandler.INSTANCE);
        handlerMap.put(LOGOUT_RESPONSE, LogoutResponseHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
