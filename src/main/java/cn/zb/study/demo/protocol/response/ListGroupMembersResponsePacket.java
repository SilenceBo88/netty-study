package cn.zb.study.demo.protocol.response;

import cn.zb.study.demo.protocol.Packet;
import cn.zb.study.demo.protocol.command.Command;
import cn.zb.study.demo.session.Session;
import lombok.Data;

import java.util.List;

/**
 * @Description: 展示群聊成员响应数据包
 * @Author: zb
 * @Date: 2020-03-13
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    /**
     * 群聊id
     */
    private String groupId;

    /**
     * 用户session列表
     */
    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
