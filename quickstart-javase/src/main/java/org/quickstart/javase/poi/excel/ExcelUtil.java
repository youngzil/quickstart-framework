/**
 * 项目名称：quickstart-javase 
 * 文件名：ExcelRead.java
 * 版本信息：
 * 日期：2018年8月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.poi.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * @author：yangzl@asiainfo.com
 * @2018年8月16日 下午10:09:18
 * @since 1.0
 */
public class ExcelUtil {
    public static void main(String[] args) throws Exception {

        File File = new File("");
        String currentPath = File.getCanonicalPath();
        File oldPathFile = new File(currentPath + File.separator + "old");
        File newPathFile = new File(currentPath + File.separator + "new");

        String[] oldFiles = oldPathFile.list();
        if (null != oldFiles && oldFiles.length > 0) {
            for (String oldFile : oldFiles) {
                if (oldFile.endsWith(".xls") || oldFile.endsWith(".xlsx")) {
                    String srcFilePath = oldPathFile.getAbsolutePath() + File.separator + oldFile;
                    String destFilePath = newPathFile.getAbsolutePath() + File.separator + oldFile;
                    transformFile(srcFilePath, destFilePath);
                } else {
                    System.out.println("文件" + oldFile + "不是Excel，不作转换");
                }
            }
        }

        System.out.println("Excel转好了，请去文件夹" + newPathFile.getAbsolutePath() + "下查看");
    }

    public static void transformFile(String srcFilePath, String destFilePath) throws Exception {
        // 读取一个excel表的内容
        InputStream stream = new FileInputStream(srcFilePath);
        POIFSFileSystem fs = new POIFSFileSystem(stream);
        HSSFWorkbook wb = new HSSFWorkbook(fs);

        // 获取excel表的第一个sheet
        HSSFSheet firstSheet = wb.getSheetAt(0);
        int firstSheetRowNum = firstSheet.getLastRowNum();

        for (int i = 1; i < wb.getNumberOfSheets(); i++) {

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

                firstSheetRowNum++;
                HSSFRow firstSheetNewRow = firstSheet.createRow(firstSheetRowNum);

                // 再遍历该行的所有列
                for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
                    HSSFCell cell = row.getCell(cellNum);
                    if (cell == null) {
                        continue;
                    }
                    String strVal = readCellSecondMethod(cell);
                    firstSheetNewRow.createCell(cellNum).setCellValue(strVal);
                }

            }

        }

        // 最后写回磁盘
        FileOutputStream out = new FileOutputStream(destFilePath);
        wb.write(out);
        out.close();

        stream.close();
    }

    /**
     * 读取excel单元格的内容并针对其type进行不同的处理, 其中就包含 读取excel表格中日期格式的cell
     * 
     * @param cell
     * @return
     */
    public static String readCellSecondMethod(HSSFCell cell) {
        // DecimalFormat df = new DecimalFormat("#");
        if (cell == null) {
            return "";
        }

        switch (cell.getCellTypeEnum()) {
            // 数字
            case NUMERIC:

                // 如果为时间格式的内容,日期格式的处理
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                }
                return String.valueOf(cell.getNumericCellValue());

            // new DecimalFormat("0").format(cell.getNumericCellValue());
            // if("General".equals(cell.getCellStyle().getDataFormatString())){
            // value = df.format(cell.getNumericCellValue());
            // }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
            // value = sdf.format(cell.getDateCellValue());
            // }else{
            // value = df2.format(cell.getNumericCellValue());
            // }
            // return df.format(cell.getNumericCellValue());

            // 字符串
            case STRING:
                // value = cell.getRichStringCellValue().getString();
                return cell.getStringCellValue();
            // 公式
            case FORMULA:
                // value = cell.getCellFormula() + "";
                return cell.getCellFormula();

            // 空白
            case BLANK:
                return "";

            // 布尔取值Boolean
            case BOOLEAN:
                // value = cell.getBooleanCellValue();
                return cell.getBooleanCellValue() + "";

            // 错误类型,故障
            case ERROR:
                // value = "非法字符";
                return cell.getErrorCellValue() + "";

            default:
                return cell.toString();
            // value = "未知类型";
        }

    }

}
