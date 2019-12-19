package sc.whorl.system.config.jwt;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@Data
public class JWTUserDetail implements Serializable {
    /**
     * 登陆用户编号
     */
    private long userId;
    /**
     * 登陆用户账户名称(可能为手机号邮箱或者名称用户维度唯一)
     */
    private String loginName;
    /**
     * 登陆用户类型
     */
    private UserType userType;
    /**
     * 登陆用户凭证
     */
    private String jwtToken;
    /**
     * 登陆时间
     */
    private Date loginTime;

    public enum UserType {
        User("USER", 1),
        Operator("OPT", 2),
        Erp("ERP", 3);

        private String name;
        private int index;

        private UserType(String name, int index) {
            this.name = name;
            this.index = index;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static String getName(int index) {
            for (UserType c : UserType.values()) {
                if (c.getIndex() == index) {
                    return c.getName();
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }
    }

    public static JWTUserDetail fromJson(String json) {
        return JSONObject.parseObject(json, JWTUserDetail.class);
    }
    public String toJson() {
        return JSONObject.toJSONString(this);
    }

}
