package cn.zb.study.demo.protocol.response;

import cn.zb.study.demo.protocol.Packet;
import cn.zb.study.demo.protocol.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @Description: 创建群聊响应数据包
 * @Author: zb
 * @Date: 2020-03-12
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 群聊id
     */
    private String groupId;

    /**
     * 用户名列表
     */
    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
