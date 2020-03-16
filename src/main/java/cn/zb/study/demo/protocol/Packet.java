package cn.zb.study.demo.protocol;

import lombok.Data;

/**
 * @Description: 通信包
 * @Author: zb
 * @Date: 2020-02-27
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 获取指令
     */
    public abstract Byte getCommand();
}
