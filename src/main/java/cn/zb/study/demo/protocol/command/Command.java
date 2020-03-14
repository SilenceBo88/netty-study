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

    /**
     * 消息请求
     */
    Byte MESSAGE_REQUEST = 3;

    /**
     * 消息响应
     */
    Byte MESSAGE_RESPONSE = 4;

    /**
     * 登出请求
     */
    Byte LOGOUT_REQUEST = 5;

    /**
     * 登出响应
     */
    Byte LOGOUT_RESPONSE = 6;

    /**
     * 创建群聊请求
     */
    Byte CREATE_GROUP_REQUEST = 7;

    /**
     * 创建群聊响应
     */
    Byte CREATE_GROUP_RESPONSE = 8;

    /**
     * 展示群聊成员
     */
    Byte LIST_GROUP_MEMBERS_REQUEST = 9;

    /**
     * 展示群聊成员
     */
    Byte LIST_GROUP_MEMBERS_RESPONSE = 10;

    /**
     * 加入群聊
     */
    Byte JOIN_GROUP_REQUEST = 11;

    /**
     * 加入群聊
     */
    Byte JOIN_GROUP_RESPONSE = 12;

    /**
     * 退出群聊
     */
    Byte QUIT_GROUP_REQUEST = 13;

    /**
     * 退出群聊
     */
    Byte QUIT_GROUP_RESPONSE = 14;

    /**
     * 群聊消息请求
     */
    Byte GROUP_MESSAGE_REQUEST = 15;

    /**
     * 群聊消息响应
     */
    Byte GROUP_MESSAGE_RESPONSE = 16;
}
