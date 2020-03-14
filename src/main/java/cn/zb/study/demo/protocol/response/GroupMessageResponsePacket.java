package cn.zb.study.demo.protocol.response;

import cn.zb.study.demo.protocol.Packet;
import cn.zb.study.demo.protocol.command.Command;
import cn.zb.study.demo.session.Session;
import lombok.Data;

/**
 * @Description: 群聊消息请求数据包
 * @Author: zb
 * @Date: 2020-03-14
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    /**
     * 群聊id
     */
    private String fromGroupId;

    /**
     * 发送消息用户
     */
    private Session fromUser;

    /**
     * 消息
     */
    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
