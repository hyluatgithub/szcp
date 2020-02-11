package com.zkcm.szcp.project.cell3d.controller.mobile;

import com.zkcm.szcp.common.constant.Constants;
import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.domain.AjaxResult;
import com.zkcm.szcp.project.cell3d.domain.Feedback;
import com.zkcm.szcp.project.cell3d.domain.OrgAccount;
import com.zkcm.szcp.project.cell3d.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 移动端
 *
 * @author admin
 */
@RestController
@RequestMapping("/mobile/feedback")
public class FeedbackMobileController extends BaseController {
    @Autowired
    private IFeedbackService feedbackService;

    /**
     * 新增个人意见反馈
     */
    @PostMapping
    public AjaxResult save(HttpServletRequest request, String content) {
        OrgAccount user = (OrgAccount) request.getAttribute("user");
        String userMobile = user.getUserMobile();
        //每个知识点的个人笔记 只能存一条
        Feedback feedback = new Feedback();
        feedback.setCreateBy(userMobile);
        feedback.setDealFlag(Constants.NO);
        feedback.setDelFlag(Constants.NO);
        feedback.setContent(content);
        feedback.setCreateTime(new Date());
        return toAjax(feedbackService.insertFeedback(feedback));
    }
}
