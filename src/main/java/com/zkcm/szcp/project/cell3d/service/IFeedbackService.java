package com.zkcm.szcp.project.cell3d.service;

import com.zkcm.szcp.project.cell3d.domain.Feedback;

import java.util.List;

/**
 * 个人反馈意见 服务层
 *
 * @author hylu
 */
public interface IFeedbackService {

    /**
     * 查询个人反馈意见列表
     *
     * @param feedback 个人反馈意见信息
     * @return 个人反馈意见集合
     */
    public List<Feedback> selectFeedbackList(Feedback feedback);

    /**
     * 查询汇总一段时间内的反馈意见
     *
     * @return 个人反馈意见集合
     */
    public List<Feedback> selectFeedbackDaysList();

    /**
     * 查询个人反馈意见信息
     *
     * @param feedbackId 个人反馈意见ID
     * @return 个人反馈意见信息
     */
    public Feedback selectFeedbackById(Long feedbackId);

    /**
     * 新增个人反馈意见
     *
     * @param feedback 个人反馈意见信息
     * @return 结果
     */
    public int insertFeedback(Feedback feedback);

    /**
     * 修改个人反馈意见
     *
     * @param feedback 个人反馈意见信息
     * @return 结果
     */
    public int updateFeedback(Feedback feedback);

}
