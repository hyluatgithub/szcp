package com.zkcm.szcp.common.enums;

/**
 * 续费类型
 *
 * @author hylu
 */
public enum RenewType {
    ORG("1", "机构"), CLASS("2", "班级"), USER("3", "用户");

    private final String code;
    private final String info;

    RenewType(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
