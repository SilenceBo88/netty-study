package cn.zb.study.demo.protocol.request;

import cn.zb.study.demo.protocol.Packet;
import cn.zb.study.demo.protocol.command.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 群聊消息请求数据包
 * @Author: zb
 * @Date: 2020-03-14
 */
@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {

    /**
     * 群聊id
     */
    private String toGroupId;

    /**
     * 消息
     */
    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}
