package com.zh.poi.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * poi生成excel
 *
 * @author zh
 * 2018年8月14日
 */
public class ExcelUtil {
    
    public static void main(String[] args) throws IOException {
        excelXlsx();
    }

    /**
     * xls
     * @throws IOException
     */
    public static void excelXls() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //新建工作表
        HSSFSheet sheet = workbook.createSheet("hello");
        //创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
        HSSFRow row = sheet.createRow(0);
        //创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
        HSSFCell cell = row.createCell(2);
        //设置单元格的值,即C1的值(第一行,第三列)
        cell.setCellValue("hello sheet");
        //输出到磁盘中
        FileOutputStream fos = new FileOutputStream(new File("D:/poi_excel.xls"));
        workbook.write(fos);
        workbook.close();
        fos.close();
        System.out.println("finished...");
    }

    /**
     * xlsx
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void excelXlsx() throws FileNotFoundException, IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        //新建工作表
        XSSFSheet sheet = workbook.createSheet("hello");
        //创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
        XSSFRow row = sheet.createRow(0);
        //创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
        XSSFCell cell = row.createCell(2);
        //设置单元格的值,即C1的值(第一行,第三列)
        cell.setCellValue("hello sheet");
        //输出到磁盘中
        FileOutputStream fos = new FileOutputStream(new File("D:/poi_excel.xlsx"));
        workbook.write(fos);
        workbook.close();
        fos.close();
        System.out.println("finished...");
    }
}
