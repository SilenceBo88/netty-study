package cn.zb.study.demo.protocol.request;

import cn.zb.study.demo.protocol.Packet;
import cn.zb.study.demo.protocol.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @Description: 创建群聊请求数据包
 * @Author: zb
 * @Date: 2020-03-12
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    /**
     * 用户id列表
     */
    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
