package com.zkcm.szcp.project.cell3d.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkcm.szcp.project.cell3d.domain.Org;

import java.io.Serializable;

/**
 * 组织机构表 org
 *
 * @author hylu
 */
public class OrgVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 组织编码
     */
    private String orgCode;
    /**
     * 组织名称
     */
    private String orgName;

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
     * 已分配激活数量
     */
    private Integer activate;

    /**
     * 禁用状态：Y已禁用 N未禁用
     */
    private String disableFlag;

    public OrgVO() {
    }

    /**
     * 带已分配激活数量的构造参数
     *
     * @param org
     * @param activate 已分配激活数量
     */
    public OrgVO(Org org, int activate) {
        if (org != null) {
            this.id = org.getId();
            this.orgCode = org.getOrgCode();
            this.orgName = org.getOrgName();
            this.status = org.getStatus();
            this.limitTimeCode = org.getLimitTimeCode();
            this.startDate = org.getStartDate();
            this.endDate = org.getEndDate();
            this.maxActivate = org.getMaxActivate();
            this.activate = activate;
            this.disableFlag = org.getDisableFlag();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public Integer getActivate() {
        return activate;
    }

    public void setActivate(Integer activate) {
        this.activate = activate;
    }

    public String getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(String disableFlag) {
        this.disableFlag = disableFlag;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Override
    public String toString() {
        return "OrgVO{" +
                "id=" + id +
                ", orgCode='" + orgCode + '\'' +
                ", orgName='" + orgName + '\'' +
                ", status=" + status +
                ", limitTimeCode='" + limitTimeCode + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", maxActivate=" + maxActivate +
                ", activate=" + activate +
                ", disableFlag='" + disableFlag + '\'' +
                '}';
    }
}
