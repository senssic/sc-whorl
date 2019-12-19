

package sc.whorl.system.commons;

import java.io.Serializable;

/**
 * 排序规则枚举类定义。<br/>
 *

 */
public enum SortDirection implements Serializable {

    /**
     * 目前支持排序规则有ASC，DESC
     */
    ASC("ASC"), DESC("DESC");

    /**
     * 排序规则
     */
    private String code;

    /**
     * 获取排序规则代码
     *
     * @return 排序规则代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 默认构造函数
     *
     * @param code 排序规则代码
     */
    SortDirection(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "[" + this.name() + " = " + this.getCode() + "]";
    }

}
