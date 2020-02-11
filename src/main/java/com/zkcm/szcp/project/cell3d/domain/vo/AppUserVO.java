package com.zkcm.szcp.project.cell3d.domain.vo;

import java.io.Serializable;

/**
 * 移动端用户对象
 *
 * @author admin
 */
public class AppUserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 新密码
     */
    private String newPassword;


    /**
     * 验证码
     */
    private String code;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
