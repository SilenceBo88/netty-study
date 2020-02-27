package cn.zb.study.demo.protocol.command;

/**
 * @Description: 指令
 * @Author: zb
 * @Date: 2020-02-27
 */
public interface Command {

    /**
     * 登录请求
     */
    Byte LOGIN_REQUEST = 1;

    /**
     * 登录响应
     */
    Byte LOGIN_RESPONSE = 2;
}
