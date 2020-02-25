package cn.zb.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * @Description: NIO客户端
 * @Author: zb
 * @Date: 2020-02-25
 */
public class NIOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8000);
                SocketChannel channel = SocketChannel.open();
                channel.connect(address);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (true) {
                    try {
                        buffer.put((new Date() + ": hello world").getBytes());
                        buffer.flip();
                        channel.write(buffer);
                        buffer.clear();

                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
