package cn.zb.study.demo.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Description: 控制台命令接口
 * @Author: zb
 * @Date: 2020-03-12
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
