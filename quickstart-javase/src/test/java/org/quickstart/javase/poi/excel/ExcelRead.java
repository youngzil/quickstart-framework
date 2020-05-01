/**
 * 项目名称：quickstart-javase 
 * 文件名：ExcelRead.java
 * 版本信息：
 * 日期：2018年8月16日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.poi.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * ExcelRead
 * 
 * @author：youngzil@163.com
 * @2018年8月16日 下午10:09:18
 * @since 1.0
 */
public class ExcelRead {
    public static void main(String[] args) throws Exception {

        // 读取一个excel表的内容
        InputStream stream = new FileInputStream("/Users/yangzl/Desktop/excel写数据.xls");
        POIFSFileSystem fs = new POIFSFileSystem(stream);
        HSSFWorkbook wb = new HSSFWorkbook(fs);

        for (int i = 0; i < wb.getNumberOfSheets(); i++) {

            // 获取excel表的第一个sheet
            HSSFSheet sheet = wb.getSheetAt(i);
            if (sheet == null) {
                return;
            }

            // 遍历该sheet的行
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }

                // 再遍历改行的所有列
                for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
                    HSSFCell cell = row.getCell(cellNum);
                    if (cell == null) {
                        continue;
                    }

                    String strVal = readCellSecondMethod(cell);
                    if (cellNum == 2) {
                        strVal = strVal.contains(".") ? strVal.substring(0, strVal.indexOf(".")) : strVal;
                    }
                    System.out.print(" " + strVal);

                    // System.out.print(" " + readCellFirstMethod(cell));
                    // System.out.print(" " + readCellSecondMethod(cell));

                }
                System.out.println();
            }
        }

        stream.close();
    }

    /**
     * 第一种方法 读取excel单元格的内容并针对其type进行不同的处理, 其中就包含 读取excel表格中日期格式的cell
     * 
     * @param cell
     * @return
     */
    public static String readCellFirstMethod(HSSFCell cell) {
        if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
            }
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return cell.getStringCellValue();
        }
    }

    /**
     * 第二种方法 读取excel单元格的内容并针对其type进行不同的处理, 其中就包含 读取excel表格中日期格式的cell
     * 
     * @param cell
     * @return
     */
    public static String readCellSecondMethod(HSSFCell cell) {
        // DecimalFormat df = new DecimalFormat("#");
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {

            // 数字
            case HSSFCell.CELL_TYPE_NUMERIC:

                // 日期格式的处理
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                }

                return String.valueOf(cell.getNumericCellValue());
            // return df.format(cell.getNumericCellValue());

            // 字符串
            case HSSFCell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            // 公式
            case HSSFCell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();

            // 空白
            case HSSFCell.CELL_TYPE_BLANK:
                return "";

            // 布尔取值
            case HSSFCell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() + "";

            // 错误类型
            case HSSFCell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue() + "";
        }

        return "";
    }

}
