package cn.zb.study.demo.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: Session实体
 * @Author: zb
 * @Date: 2020-03-10
 */
@Data
@NoArgsConstructor
public class Session {

    /**
     * 用户uid
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + ":" + userName;
    }
}
