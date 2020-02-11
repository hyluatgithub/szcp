package com.zkcm.szcp.project.cell3d.domain;

import com.zkcm.szcp.framework.web.domain.BaseEntity;


/**
 * 组织-班级表
 *
 * @author hylu
 * @email hylu@email.com
 * @date 2019-10-14 17:11:45
 */
public class OrgClass extends BaseEntity {
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
     * 班级名称
     */
    private String className;
    /**
     * 分配的最大激活数量
     */
    private int maxActivate;
    /**
     * 已激活数量
     */
    private int activate;
    /**
     * 逻辑删除：Y已删除 N未删除
     */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getMaxActivate() {
        return maxActivate;
    }

    public void setMaxActivate(int maxActivate) {
        this.maxActivate = maxActivate;
    }

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}
