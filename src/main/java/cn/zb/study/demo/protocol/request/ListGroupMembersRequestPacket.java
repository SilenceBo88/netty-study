package cn.zb.study.demo.protocol.request;

import cn.zb.study.demo.protocol.Packet;
import cn.zb.study.demo.protocol.command.Command;
import lombok.Data;

/**
 * @Description: 展示群聊成员请求数据包
 * @Author: zb
 * @Date: 2020-03-13
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    /**
     * 群聊id
     */
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
