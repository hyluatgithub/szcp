package com.zkcm.szcp.common.utils.poi;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 导出excel工具类
 *
 * @Version: 1.0
 */
public class ExportExcelUtil {
    /**
     * 常用普通文件下载
     *
     * @param response
     * @param fileName
     * @param sheetName
     * @param data
     */
    public static void down(HttpServletResponse response, String fileName, String sheetName, List<Map<String, Object>> data) {
        // 生成提示信息，
        response.setContentType("application/vnd.ms-excel");
        if (StringUtils.isBlank(fileName)) {
            fileName = Long.toString(System.currentTimeMillis());
        }
        OutputStream fOut = null;
        try {
            // 进行转码，使其支持中文文件名
            fOut = response.getOutputStream();
//            String codedFileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
            String codedFileName = "111111";
            response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 产生工作表对象
            HSSFSheet sheet = workbook.createSheet(sheetName);

            for (int i = 0; i < data.size(); i++) {
                HSSFRow row = sheet.createRow((int) i);
                Map<String, Object> map = data.get(i);

                //遍历map中的值
                int j = 0;
                for (Object value : map.values()) {
                    HSSFCell cell = row.createCell((int) j);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    if (value == null) {
                        value = "";
                    }
                    cell.setCellValue(value.toString());
                    j++;
                }
            }
            workbook.write(fOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
