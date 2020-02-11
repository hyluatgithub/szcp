package com.zkcm.szcp.project.cell3d.controller;

import cn.hutool.core.util.RandomUtil;
import com.zkcm.szcp.common.constant.Constants;
import com.zkcm.szcp.common.utils.DateUtils;
import com.zkcm.szcp.framework.web.controller.BaseController;
import com.zkcm.szcp.framework.web.domain.AjaxResult;
import com.zkcm.szcp.framework.web.page.TableDataInfo;
import com.zkcm.szcp.project.cell3d.domain.ClassCode;
import com.zkcm.szcp.project.cell3d.domain.vo.FreecodeDO;
import com.zkcm.szcp.project.cell3d.service.IClassCodeService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 细胞3d 激活码管理
 *
 * @author hylu
 */
@RestController
@RequestMapping("/classCode")
public class ClassCodeController extends BaseController {
    @Autowired
    private IClassCodeService classCodeService;

    /**
     * 获取激活码列表
     */
//    @PreAuthorize("@ss.hasPermi('classCode:classCode:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClassCode classCode) {
        startPage();
        List<ClassCode> list = classCodeService.selectClassCodeList(classCode);
        return getDataTable(list);
    }

    /**
     * 获取免费激活码列表
     */
    @GetMapping("/freeList")
    public TableDataInfo freeList(ClassCode classCode) {
        startPage();
        //免费激活码参数固定
        classCode.setOrgCode("admin");
        classCode.setClassCode("test");
        List<ClassCode> list = classCodeService.selectClassCodeList(classCode);
        return getDataTable(list);
    }

    /**
     * 生成免费激活码
     */
    @PostMapping("/addFreeCode")
    public AjaxResult addFreeCode(@RequestBody FreecodeDO freecodeDO) {
        ClassCode classCode;
        String activateInvalidTime = freecodeDO.getActivateInvalidTime();
        Integer maxActivate = freecodeDO.getMaxActivate();
        for (int i = 0; i < maxActivate; i++) {
            classCode = new ClassCode();
            classCode.setOrgCode("admin");
            classCode.setClassCode("test");
            classCode.setCode(RandomUtil.randomString(8));
            classCode.setActivateInvalidTime(activateInvalidTime);
            classCode.setActivateFlag(Constants.NO);
            classCode.setCreateTime(new Date());
            classCode.setDeleteFlag(Constants.NO);
            classCodeService.insertClassCode(classCode);
        }
        return AjaxResult.success();
    }

    /**
     * 导出激活码
     */
    @ResponseBody
    @PostMapping("/export")
    public void export(HttpServletResponse response, String classCode, String type) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("委托方信息列表");
        HSSFRow row = null;
        row = sheet.createRow(0);
        row.setHeight((short) (30 * 20));
        row.createCell(0).setCellValue("委托方信息列表");

        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion(rowRegion);
        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));//设置行高
        row.createCell(0).setCellValue("Id");//为第一个单元格设值
        row.createCell(1).setCellValue("logo");//为第二个单元格设值
        row.createCell(2).setCellValue("委托方中文名称");//为第三个单元格设值

        for (int i = 0; i < 10; i++) {
            row = sheet.createRow(i + 2);
            row.createCell(0).setCellValue("3" + i);
            row.createCell(1).setCellValue("2" + i);
            row.createCell(2).setCellValue("1" + i);
        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }
        String title = "company";
        String fileName = new String(title.getBytes("GB2312"), "ISO_8859_1");
        fileName = URLEncoder.encode(fileName, "utf-8");

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");//默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
    }

}
