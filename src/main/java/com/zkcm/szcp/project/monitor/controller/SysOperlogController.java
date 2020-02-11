package com.zkcm.szcp.project.monitor.controller;

import java.util.List;

import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.page.TableDataInfo;
import com.zkcm.szcp.project.monitor.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.page.TableDataInfo;
import com.zkcm.szcp.project.monitor.domain.SysOperLog;
import com.zkcm.szcp.project.monitor.service.ISysOperLogService;

/**
 * 操作日志记录
 *
 * @author hylu
 */
@RestController
@RequestMapping("/monitor/operlog")
public class SysOperlogController extends BaseController
{
    @Autowired
    private ISysOperLogService operLogService;

    @PreAuthorize("@ss.hasPermi('monitor:operlog:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOperLog operLog)
    {
        startPage();
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }
}
