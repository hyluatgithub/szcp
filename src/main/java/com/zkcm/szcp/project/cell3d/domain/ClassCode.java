package com.zkcm.szcp.project.cell3d.domain;

import com.zkcm.szcp.framework.web.domain.BaseEntity;


/**
 * 激活码列表
 *
 * @author hylu
 * @email hylu@email.com
 * @date 2019-10-14 17:26:21
 */
public class ClassCode extends BaseEntity {
    /**
     * 主键
     */
    private Long id;
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
     * 激活状态：Y已激活 N未激活
     */
    private String activateFlag;
    /**
     * 失效日期
     */
    private String activateInvalidTime;
    /**
     * 激活日期
     */
    private String activateTime;
    /**
     * 绑定用户姓名
     */
    private String userName;
    /**
     * 绑定手机号码
     */
    private String userMobile;
    /**
     * 逻辑删除：Y已删除 N未删除
     */
    private String delFlag;

    /**
     * 设置：主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：组织机构编码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 获取：组织机构编码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置：班级编码
     */
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    /**
     * 获取：班级编码
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * 设置：激活码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取：激活码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置：激活状态：Y已激活 N未激活
     */
    public void setActivateFlag(String activateFlag) {
        this.activateFlag = activateFlag;
    }

    /**
     * 获取：激活状态：Y已激活 N未激活
     */
    public String getActivateFlag() {
        return activateFlag;
    }

    /**
     * 设置：激活日期
     */
    public void setActivateTime(String activateTime) {
        this.activateTime = activateTime;
    }

    /**
     * 获取：激活日期
     */
    public String getActivateTime() {
        return activateTime;
    }

    /**
     * 设置：逻辑删除：Y已删除 N未删除
     */
    public void setDeleteFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取：逻辑删除：Y已删除 N未删除
     */
    public String getDelFlag() {
        return delFlag;
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

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getActivateInvalidTime() {
        return activateInvalidTime;
    }

    public void setActivateInvalidTime(String activateInvalidTime) {
        this.activateInvalidTime = activateInvalidTime;
    }
}
