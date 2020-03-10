package cn.zb.study.demo.protocol.response;

import cn.zb.study.demo.protocol.Packet;
import cn.zb.study.demo.protocol.command.Command;
import lombok.Data;

/**
 * @Description: 登录响应数据包
 * @Author: zb
 * @Date: 2020-02-27
 */
@Data
public class LoginResponsePacket extends Packet {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

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
        return Command.LOGIN_RESPONSE;
    }
}
