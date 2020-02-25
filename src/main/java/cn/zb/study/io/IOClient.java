package cn.zb.study.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @Description: IO客户端
 * @Author: zb
 * @Date: 2020-02-25
 */
public class IOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
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
