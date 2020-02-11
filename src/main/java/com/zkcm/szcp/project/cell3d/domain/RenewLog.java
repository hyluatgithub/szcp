package com.zkcm.szcp.project.cell3d.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkcm.szcp.framework.web.domain.BaseEntity;

/**
 * 续费记录表 renew_log
 *
 * @author hylu
 */
public class RenewLog extends BaseEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 续费类型：1 机构续费 2 班级续费 3 个人续费
     */
    private String type;

    /**
     * 续费主体编码，对应组织机构编码
     */
    private String orgCode;
    /**
     * 续费主体编码，对应班级编码
     */
    private String classCode;
    /**
     * 续费主体编码，对应账号主键
     */
    private String userId;
    /**
     * 续期时长，以月为单位
     */
    private Integer renewTime;

    /**
     * 续期后失效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String renewEndTime;

    /**
     * 最大激活码续增数量
     */
    private Integer renewMaxActivate;

    /**
     * 续期后，最大激活数量
     */
    private Integer maxActivate;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRenewTime() {
        return renewTime;
    }

    public void setRenewTime(Integer renewTime) {
        this.renewTime = renewTime;
    }

    public String getRenewEndTime() {
        return renewEndTime;
    }

    public void setRenewEndTime(String renewEndTime) {
        this.renewEndTime = renewEndTime;
    }

    public Integer getRenewMaxActivate() {
        return renewMaxActivate;
    }

    public void setRenewMaxActivate(Integer renewMaxActivate) {
        this.renewMaxActivate = renewMaxActivate;
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
}
