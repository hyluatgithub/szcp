package com.zkcm.szcp.project.cell3d.service.impl;

import com.zkcm.szcp.project.cell3d.domain.Feedback;
import com.zkcm.szcp.project.cell3d.mapper.FeedbackMapper;
import com.zkcm.szcp.project.cell3d.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个人笔记 服务层实现
 *
 * @author hylu
 */
@Service
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 查询个人笔记信息
     *
     * @param feedbackId 个人笔记ID
     * @return 个人笔记信息
     */
    @Override
    public Feedback selectFeedbackById(Long feedbackId) {
        return feedbackMapper.selectFeedback(feedbackId);
    }

    /**
     * 查询个人笔记列表
     *
     * @param feedback 个人笔记信息
     * @return 个人笔记集合
     */
    @Override
    public List<Feedback> selectFeedbackList(Feedback feedback) {
        return feedbackMapper.selectFeedbackList(feedback);
    }

    /**
     * 查询汇总一段时间内的反馈意见
     *
     * @return 个人反馈意见集合
     */
    @Override
    public List<Feedback> selectFeedbackDaysList() {
        return feedbackMapper.selectFeedbackDaysList();
    }

    /**
     * 新增个人笔记
     *
     * @param feedback 个人笔记信息
     * @return 结果
     */
    @Override
    public int insertFeedback(Feedback feedback) {
        return feedbackMapper.insertFeedback(feedback);
    }

    /**
     * 修改个人反馈意见
     *
     * @param feedback 个人反馈意见信息
     * @return 结果
     */
    @Override
    public int updateFeedback(Feedback feedback) {
        return feedbackMapper.updateFeedback(feedback);
    }

}
