package com.zkcm.szcp.project.cell3d.controller;

import com.zkcm.szcp.common.constant.Constants;
import com.zkcm.szcp.common.enums.RenewType;
import com.zkcm.szcp.common.utils.SecurityUtils;
import com.zkcm.szcp.framework.aspectj.lang.annotation.Log;
import com.zkcm.szcp.framework.aspectj.lang.enums.BusinessType;
import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.domain.AjaxResult;
import com.zkcm.szcp.framework.web.page.TableDataInfo;
import com.zkcm.szcp.project.cell3d.domain.ClassCode;
import com.zkcm.szcp.project.cell3d.domain.Org;
import com.zkcm.szcp.project.cell3d.domain.RenewLog;
import com.zkcm.szcp.project.cell3d.service.IClassCodeService;
import com.zkcm.szcp.project.cell3d.service.IOrgService;
import com.zkcm.szcp.project.cell3d.service.IRenewLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 细胞3d 续费记录处理
 *
 * @author hylu
 */
@RestController
@RequestMapping("/renewLog/renewLog")
public class RenewLogController extends BaseController {
    @Autowired
    private IRenewLogService renewLogService;
    @Autowired
    private IOrgService orgService;
    @Autowired
    private IClassCodeService classCodeService;

    /**
     * 获取续费记录列表
     */
    @PreAuthorize("@ss.hasPermi('renewLog:renewLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(RenewLog renewLog) {
        startPage();
        List<RenewLog> list = renewLogService.selectRenewLogList(renewLog);
        return getDataTable(list);
    }

    /**
     * 根据编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('renewLog:renewLog:query')")
    @GetMapping(value = "/{renewLogId}")
    public AjaxResult getInfo(@PathVariable Long renewLogId) {
        return AjaxResult.success(renewLogService.selectRenewLogById(renewLogId));
    }


    /**
     * 新增续费记录
     */
    @Transactional
//    @PreAuthorize("@ss.hasPermi('renewLog:renewLog:add')")
    @Log(title = "续费记录管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RenewLog renewLog) {
        AjaxResult ajaxResult = null;
        String type = renewLog.getType();
        //续费及主体对应的主键
        //不同的续费类型，需要执行相应的续费操作
        if (RenewType.ORG.getCode().equals(type)) {
            //机构续费
            this.doOrgRenew(renewLog);
        } else if (RenewType.CLASS.getCode().equals(type)) {
            //班级续费
            ajaxResult = this.doClassRenew(renewLog);
        } else {
            //用户续费
            ajaxResult = this.doUserRenew(renewLog);
        }

        renewLog.setCreateBy(SecurityUtils.getUsername());
        renewLog.setDelFlag(Constants.NO);
        renewLogService.insertRenewLog(renewLog);
        if (ajaxResult == null) {
            return AjaxResult.success();
        }
        return ajaxResult;
    }

    /**
     * 机构续费
     *
     * @param renewLog
     */
    private void doOrgRenew(RenewLog renewLog) {
        //机构续费
        String businessCode = renewLog.getOrgCode();
        Org org = orgService.selectOrgByCode(businessCode);
        if (org != null) {
            //付费状态
            org.setStatus(1);
            //有效期时长：叠加计算
            org.setLimitTimeCode(org.getLimitTimeCode() + renewLog.getRenewTime());
            //最大可分配激活数量：原最大激活数量+续增激活数量
            org.setMaxActivate(org.getMaxActivate() + renewLog.getRenewMaxActivate());
            org.setEndDate(renewLog.getRenewEndTime());
            orgService.updateOrg(org);
        }
    }

    /**
     * 班级续费
     *
     * @param renewLog
     */
    private AjaxResult doClassRenew(RenewLog renewLog) {
        //机构续费
        String orgCode = renewLog.getOrgCode();
        String classCode = renewLog.getClassCode();
        Org org = orgService.selectOrgByCode(orgCode);
        if (org != null) {
            //查询待续费的账号数量（按班级查找）
            ClassCode query = new ClassCode();
            query.setOrgCode(orgCode);
            query.setClassCode(classCode);
            query.setDeleteFlag(Constants.NO);
            List<ClassCode> classCodeList = classCodeService.selectClassCodeList(query);
            if (classCodeList == null || classCodeList.isEmpty()) {
                return AjaxResult.success();
            }
            //校验激活码容量是否足够
            int activeSize = classCodeList.size();
            Integer maxActivate = org.getMaxActivate();
            Integer activateSum = org.getActivateSum();
            if (maxActivate - activateSum - activeSize < 0) {
                return AjaxResult.error("续费失败，可分配激活码数量不足\n可分配:" + (maxActivate - activateSum) + ",班级待续费:" + activeSize);
            }

            //执行班级续费操作
            //1.变更机构已分配数量
            org.setActivateSum(org.getActivateSum() + activeSize);
            orgService.updateOrg(org);

            //2、修改班级账号有效期限
            for (ClassCode classCodeTemp : classCodeList) {
                classCodeTemp.setActivateInvalidTime(org.getEndDate());
                classCodeService.updateClassCode(classCodeTemp);
            }
        }
        return AjaxResult.success();
    }

    /**
     * 个人续费
     *
     * @param renewLog
     */
    private AjaxResult doUserRenew(RenewLog renewLog) {
        //机构续费
        String orgCode = renewLog.getOrgCode();
        String userId = renewLog.getUserId();
        Org org = orgService.selectOrgByCode(orgCode);
        if (org != null) {
            //查询待续费的账号数量（按班级查找）
            ClassCode classCode = classCodeService.selectClassCodeById(Long.valueOf(userId));
            if (classCode == null) {
                return AjaxResult.success();
            }
            //校验激活码容量是否足够
            Integer maxActivate = org.getMaxActivate();
            Integer activateSum = org.getActivateSum();
            if (maxActivate - activateSum - 1 < 0) {
                return AjaxResult.error("续费失败，可分配激活码数量不足\n可分配:" + (maxActivate - activateSum) + ",待续费:" + 1);
            }

            //执行个人续费操作
            //1.变更机构已分配数量
            org.setActivateSum(org.getActivateSum() + 1);
            orgService.updateOrg(org);

            //2、修改个人账号有效期限
            classCode.setActivateInvalidTime(org.getEndDate());
            classCodeService.updateClassCode(classCode);
        }
        return AjaxResult.success();
    }

}
