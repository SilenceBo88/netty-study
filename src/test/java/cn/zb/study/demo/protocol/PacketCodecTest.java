package cn.zb.study.demo.protocol;

import cn.zb.study.demo.protocol.request.LoginRequestPacket;
import cn.zb.study.demo.serialize.Serializer;
import cn.zb.study.demo.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Assert;
import org.junit.Test;

public class PacketCodecTest {

    @Test
    public void test() {
        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId("001");
        loginRequestPacket.setUsername("zhaobo");
        loginRequestPacket.setPassword("123456");

        PacketCodec packetCodec = new PacketCodec();
        ByteBuf byteBuf = packetCodec.encode(ByteBufAllocator.DEFAULT.ioBuffer(), loginRequestPacket);
        Packet decodedPacket = packetCodec.decode(byteBuf);

        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));
    }
}