package cn.zb.study.demo.client.console;

import cn.zb.study.demo.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * @Description: 退出群聊命令
 * @Author: zb
 * @Date: 2020-03-13
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        System.out.print("输入 groupId，退出群聊：");
        String groupId = scanner.next();

        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
