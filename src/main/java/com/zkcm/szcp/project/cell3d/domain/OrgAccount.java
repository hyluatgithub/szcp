package com.zkcm.szcp.project.cell3d.domain;

import com.zkcm.szcp.framework.web.domain.BaseEntity;


/**
 * 机构用户账号表
 *
 * @author hylu
 * @email hylu@email.com
 * @date 2019-10-16 13:45:50
 */
public class OrgAccount extends BaseEntity {
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户手机号码
     */
    private String userMobile;
    /**
     * 用户密码
     */
    private String userPwd;
    /**
     * 用户邮箱
     */
    private String userEmail;
    /**
     * 激活状态：N未激活 Y已激活
     */
    private String activateFlag;
    /**
     * 组织机构编码
     */
    private String orgCode;
    /**
     * 班级编码
     */
    private String classCode;
    /**
     * 激活码
     */
    private String code;
    /**
     * 失效日期
     */
    private String activateInvalidTime;
    /**
     * 逻辑删除：N未删除  Y已删除
     */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getActivateFlag() {
        return activateFlag;
    }

    public void setActivateFlag(String activateFlag) {
        this.activateFlag = activateFlag;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getActivateInvalidTime() {
        return activateInvalidTime;
    }

    public void setActivateInvalidTime(String activateInvalidTime) {
        this.activateInvalidTime = activateInvalidTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
