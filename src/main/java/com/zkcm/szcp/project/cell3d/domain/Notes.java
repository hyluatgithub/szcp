package com.zkcm.szcp.project.cell3d.domain;

import com.zkcm.szcp.framework.web.domain.BaseEntity;


/**
 * 个人笔记表
 *
 * @author hylu
 * @email hylu@email.com
 * @date 2019-10-17 14:29:50
 */
public class Notes extends BaseEntity {
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户手机号
     */
    private String userMobile;
    /**
     * 关联知识点
     */
    private String points;
    /**
     * 标题
     */
    private String title;
    /**
     * 笔记内容
     */
    private String content;
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
     * 设置：用户手机号
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    /**
     * 获取：用户手机号
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * 设置：关联知识点
     */
    public void setPoints(String points) {
        this.points = points;
    }

    /**
     * 获取：关联知识点
     */
    public String getPoints() {
        return points;
    }

    /**
     * 设置：标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置：笔记内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：笔记内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：逻辑删除：Y已删除 N未删除
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取：逻辑删除：Y已删除 N未删除
     */
    public String getDelFlag() {
        return delFlag;
    }
}
