package com.zkcm.szcp.project.tool.codegen.controller;

import cn.hutool.core.io.IoUtil;
import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.page.TableDataInfo;
import com.zkcm.szcp.project.tool.codegen.domain.GenConfig;
import com.zkcm.szcp.project.tool.codegen.domain.TableEntity;
import com.zkcm.szcp.project.tool.codegen.service.IGeneratorCodeService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 * @author hylu
 */
@RestController
@RequestMapping("/tool/gen")
public class GenController extends BaseController {

    @Autowired
    private IGeneratorCodeService generatorCodeService;

    /**
     * 获取组织机构列表
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermi('tool:gen:list')")
    public TableDataInfo list(TableEntity tableEntity) {
        startPage();
        List<Map<String, Object>> list = generatorCodeService.queryPage(tableEntity);
        return getDataTable(list);
    }

    /**
     * 生成代码
     */
    @PostMapping("/code")
    @PreAuthorize("@ss.hasPermi('tool:gen:code')")
    public void code(@RequestBody GenConfig genConfig, HttpServletResponse response) throws IOException {
        byte[] data = generatorCodeService.generatorCode(genConfig);

        this.genCode(response, data);

        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
    }


    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"gencode.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
