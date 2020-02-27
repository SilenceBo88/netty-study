package cn.zb.study.demo.attribute;

import io.netty.util.AttributeKey;

/**
 * @Description: 状态标识
 * @Author: zb
 * @Date: 2020-02-27
 */
public interface Attributes {

    /**
     * 登录标识
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
