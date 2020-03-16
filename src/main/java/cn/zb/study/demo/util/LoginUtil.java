package cn.zb.study.demo.util;

import cn.zb.study.demo.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Description: 登录工具类
 * @Author: zb
 * @Date: 2020-02-27
 */
@Deprecated
public class LoginUtil {

    /**
     * 标记登录
     */
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    /**
     * 判断是否登录
     */
    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
