package com.zkcm.szcp.project.animal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 激活码
 *
 * @author hylu
 * @email hylu@email.com
 * @date 2020-2-20
 */
public class AnimalKey implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 激活码
     */
    private String key;
    /**
     * 批次
     */
    private Integer batchId;
    /**
     * 失效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date limitDate;
    /**
     * 激活次数
     */
    private Integer activationTimes;
    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Integer getActivationTimes() {
        return activationTimes;
    }

    public void setActivationTimes(Integer activationTimes) {
        this.activationTimes = activationTimes;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
