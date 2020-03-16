package cn.zb.study.demo.serialize.impl;

import cn.zb.study.demo.serialize.Serializer;
import cn.zb.study.demo.serialize.SerializerAlogrithm;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 序列化
 * @Author: zb
 * @Date: 2020-02-27
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
