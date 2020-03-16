package cn.zb.study.demo.protocol.response;

import cn.zb.study.demo.protocol.Packet;
import cn.zb.study.demo.protocol.command.Command;
import lombok.Data;

/**
 * @Description: 登出响应数据包
 * @Author: zb
 * @Date: 2020-03-12
 */
@Data
public class LogoutResponsePacket extends Packet {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 远影
     */
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
