package cn.zb.study.demo.util;

import java.util.UUID;

/**
 * @Description: ID生成工具类
 * @Author: zb
 * @Date: 2020-03-12
 */
public class IDUtil {

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
