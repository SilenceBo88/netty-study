package cn.zb.study.demo.protocol.request;

import cn.zb.study.demo.protocol.Packet;
import cn.zb.study.demo.protocol.command.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 消息请求数据包
 * @Author: zb
 * @Date: 2020-02-27
 */
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    /**
     * 要发送的用户
     */
    private String toUserId;

    /**
     * 消息
     */
    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}