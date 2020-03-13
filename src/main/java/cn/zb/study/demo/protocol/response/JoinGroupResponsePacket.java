package cn.zb.study.demo.protocol.response;

import cn.zb.study.demo.protocol.Packet;
import cn.zb.study.demo.protocol.command.Command;
import lombok.Data;

/**
 * @Description: 加入群聊响应数据包
 * @Author: zb
 * @Date: 2020-03-13
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    /**
     * 群聊id
     */
    private String groupId;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 原因
     */
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
