package cn.zb.study.demo.protocol.request;

import cn.zb.study.demo.protocol.Packet;
import cn.zb.study.demo.protocol.command.Command;
import lombok.Data;

/**
 * @Description: 退出群聊请求数据包
 * @Author: zb
 * @Date: 2020-03-13
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    /**
     * 群聊id
     */
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}
