package cn.zb.study.demo.server.handler;

import cn.zb.study.demo.protocol.Packet;
import static cn.zb.study.demo.protocol.command.Command.CREATE_GROUP_REQUEST;
import static cn.zb.study.demo.protocol.command.Command.GROUP_MESSAGE_REQUEST;
import static cn.zb.study.demo.protocol.command.Command.JOIN_GROUP_REQUEST;
import static cn.zb.study.demo.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;
import static cn.zb.study.demo.protocol.command.Command.LOGOUT_REQUEST;
import static cn.zb.study.demo.protocol.command.Command.MESSAGE_REQUEST;
import static cn.zb.study.demo.protocol.command.Command.QUIT_GROUP_REQUEST;
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
public class IMRequestHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMRequestHandler INSTANCE = new IMRequestHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMRequestHandler() {
        handlerMap = new HashMap<>();

        handlerMap.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
        handlerMap.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
        handlerMap.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
    }



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
