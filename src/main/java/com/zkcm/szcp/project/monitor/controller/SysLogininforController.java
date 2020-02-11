package com.zkcm.szcp.project.monitor.controller;

import java.util.List;

import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.page.TableDataInfo;
import com.zkcm.szcp.project.monitor.domain.SysLogininfor;
import com.zkcm.szcp.project.monitor.service.ISysLogininforService;

/**
 * 系统访问记录
 *
 * @author hylu
 */
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController
{
    @Autowired
    private ISysLogininforService logininforService;

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLogininfor logininfor)
    {
        startPage();
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }
}
