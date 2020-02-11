package com.zkcm.szcp.project.cell3d.domain;

import com.zkcm.szcp.framework.web.domain.BaseEntity;


/**
 * 个人反馈意见表
 *
 * @author hylu
 * @date 2019-12-3
 */
public class Feedback extends BaseEntity {
    /**
     * 主键
     */
    private Long id;
    /**
     * 反馈意见
     */
    private String content;
    /**
     * 处理状态：Y已处理 N未处理
     */
    private String dealFlag;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDealFlag() {
        return dealFlag;
    }

    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
