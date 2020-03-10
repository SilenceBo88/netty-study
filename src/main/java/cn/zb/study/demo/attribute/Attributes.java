package cn.zb.study.demo.attribute;

import cn.zb.study.demo.session.Session;
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

    /**
     * session标识
     */
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
