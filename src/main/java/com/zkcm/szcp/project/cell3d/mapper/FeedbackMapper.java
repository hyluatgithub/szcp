package com.zkcm.szcp.project.cell3d.mapper;


import com.zkcm.szcp.project.cell3d.domain.Feedback;
import com.zkcm.szcp.project.cell3d.domain.Feedback;

import java.util.List;

/**
 * 个人反馈意见 数据层
 *
 * @author hylu
 */
public interface FeedbackMapper {
    /**
     * 查询个人反馈意见信息
     *
     * @param id
     * @return 个人反馈意见信息
     */
    public Feedback selectFeedback(Long id);

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
     * @return
     */
    public List<Feedback> selectFeedbackDaysList();

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
