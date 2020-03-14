package cn.zb.study.demo.codec;

import cn.zb.study.demo.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Description: 解码器
 * @Author: zb
 * @Date: 2020-03-03
 */
@Deprecated
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) {
        out.add(PacketCodec.INSTANCE.decode(in));
    }
}
