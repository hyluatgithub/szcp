package com.zkcm.szcp.project.cell3d.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkcm.szcp.framework.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 组织机构表 org
 *
 * @author hylu
 */
public class Org extends BaseEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 组织编码
     */
    private String orgCode;

    /**
     * 负责人手机号码
     */
    private String adminUserMobile;

    /**
     * 负责人姓名
     */
    private String adminUserName;

    /**
     * 状态：0体验 1付费 2失效
     */
    private Integer status;

    /**
     * 有效期时长
     */
    private Integer limitTimeCode;

    /**
     * 计时开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    /**
     * 计时结束日期（失效日期）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endDate;

    /**
     * 最大激活数量
     */
    private Integer maxActivate;

    /**
     * 已分配激活码数量
     */
    private Integer activateSum;

    /**
     * 禁用状态：Y已禁用 N未禁用
     */
    private String disableFlag;

    /**
     * 逻辑删除状态：Y已删除 N未删除
     */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "组织名称不能为空")
    @Size(min = 0, max = 100, message = "组织名称不能超过100个字符")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @NotBlank(message = "负责人手机号码不能为空")
    @Size(min = 0, max = 500, message = "负责人手机号码不能超过500个字符")
    public String getAdminUserMobile() {
        return adminUserMobile;
    }

    public void setAdminUserMobile(String adminUserMobile) {
        this.adminUserMobile = adminUserMobile;
    }

    @NotBlank(message = "负责人姓名不能为空")
    @Size(min = 0, max = 500, message = "负责人姓名不能超过500个字符")
    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLimitTimeCode() {
        return limitTimeCode;
    }

    public void setLimitTimeCode(Integer limitTimeCode) {
        this.limitTimeCode = limitTimeCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getMaxActivate() {
        return maxActivate;
    }

    public void setMaxActivate(Integer maxActivate) {
        this.maxActivate = maxActivate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(String disableFlag) {
        this.disableFlag = disableFlag;
    }

    public Integer getActivateSum() {
        return activateSum;
    }

    public void setActivateSum(Integer activateSum) {
        this.activateSum = activateSum;
    }
}
