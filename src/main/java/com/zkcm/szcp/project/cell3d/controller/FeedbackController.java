package com.zkcm.szcp.project.cell3d.controller;

import cn.hutool.extra.mail.MailUtil;
import com.zkcm.szcp.common.constant.Constants;
import com.zkcm.szcp.common.utils.DateUtils;
import com.zkcm.szcp.common.utils.StringUtils;
import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.page.TableDataInfo;
import com.zkcm.szcp.project.cell3d.domain.Feedback;
import com.zkcm.szcp.project.cell3d.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 个人意见反馈
 *
 * @author admin
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController extends BaseController {
    @Autowired
    private IFeedbackService feedbackService;

    /**
     * 获取反馈意见列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Feedback feedback) {
        startPage();
        List<Feedback> list = feedbackService.selectFeedbackList(feedback);
        return getDataTable(list);
    }

    /**
     * 获取反馈意见列表
     */
    @GetMapping("/sendEmil")
    public void sendEmil() {
        String sendContent = this.selectFeedbackDaysList();
        String title = DateUtils.getDate() + "意见反馈";
        System.out.println(title);
        System.out.println(sendContent);
        if (StringUtils.isNotEmpty(sendContent)) {
            MailUtil.send("1325101787@qq.com", title, sendContent, false);
        }
    }

    /**
     * 汇总当天反馈意见，并发送到指定邮箱
     */
    private String selectFeedbackDaysList() {
        StringBuilder emailContent = new StringBuilder();
        List<Feedback> list = feedbackService.selectFeedbackDaysList();
        if (list == null || list.isEmpty()) {
            return "";
        }
        String content;
        for (Feedback feedback : list) {
            content = "[" + feedback.getCreateBy() + "]\n" + feedback.getContent() + "\n";
            emailContent.append(content);
            feedback.setDealFlag(Constants.YES);
            feedbackService.updateFeedback(feedback);
        }

        return emailContent.toString();
    }
}
