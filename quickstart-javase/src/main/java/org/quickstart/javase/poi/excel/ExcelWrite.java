/**
 * 项目名称：quickstart-javase 
 * 文件名：ExcelWrite.java
 * 版本信息：
 * 日期：2018年8月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.poi.excel;

import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;

/**
 * ExcelWrite
 * 
 * @author：yangzl@asiainfo.com
 * @2018年8月16日 下午10:08:29
 * @since 1.0
 */
public class ExcelWrite {
    public static void main(String[] args) throws Exception {

        // 创建一个工作簿 即excel文件,再在该文件中创建一个sheet
        HSSFWorkbook wb = new HSSFWorkbook();
        for (int i = 0; i < 10; i++) {
            HSSFSheet sheet = wb.createSheet("第" + i + "一个sheet");

            // 在sheet中创建一行
            HSSFRow row = sheet.createRow(0);

            // 在该行写入各种类型的数据
            row.createCell(0).setCellValue(true);
            row.createCell(1).setCellValue("钟林森" + i);
            row.createCell(2).setCellValue(23 + i);

            // 设置保留两位小数
            HSSFCell cell = row.createCell(3);
            cell.setCellValue(6000);
            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
            cell.setCellStyle(cellStyle);

            // 在写入 日期格式的 数据需要进行特殊处理(这是一种 简单的处理方式)
            CreationHelper createHelper = wb.getCreationHelper();
            HSSFCellStyle style = wb.createCellStyle();
            style.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

            cell = row.createCell(4);
            cell.setCellValue(new Date());
            cell.setCellStyle(style);

        }

        // 最后写回磁盘
        FileOutputStream out = new FileOutputStream("/Users/yangzl/Desktop/转换前.xls");
        wb.write(out);
        out.close();

        System.out.println("写完了!");
    }
}
