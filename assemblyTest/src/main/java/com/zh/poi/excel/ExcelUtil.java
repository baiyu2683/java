package com.zh.poi.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;

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
        HSSFCell cell1 = row.createCell(1);

        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cell1.setCellStyle(cellStyle);

        HSSFRichTextString richTextString = new HSSFRichTextString("一二三、\r\n四五六、\r\n七八九");
        Font font1 = workbook.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short)20);
        font1.setItalic(true);
        richTextString.applyFont(0, 3, font1);

        Font font2 = workbook.createFont();
        font2.setFontName("隶书");
        font2.setFontHeightInPoints((short)20);
        font2.setItalic(true);
        font2.setBold(true);
        font2.setStrikeout(true);
        font2.setUnderline(Font.U_SINGLE);
        font2.setColor(Font.COLOR_RED);
        font2.setTypeOffset(Font.SS_SUPER);
        richTextString.applyFont(6, 9, font2);

        cell1.setCellValue(richTextString);

        //输出到磁盘中
        FileOutputStream fos = new FileOutputStream(new File("D:/xls.xls"));
        workbook.write(fos);
        workbook.close();
        fos.close();
        System.out.println("finished...");
    }

    /**
     * xls
     * @throws IOException
     */
    public static void excelXlsx() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        //新建工作表
        XSSFSheet sheet = workbook.createSheet("hello");
        //创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
        XSSFRow row = sheet.createRow(0);

        //创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
        XSSFCell cell1 = row.createCell(1);

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cell1.setCellStyle(cellStyle);

        XSSFRichTextString richTextString = new XSSFRichTextString();
        XSSFFont font1 = workbook.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short)20);
        font1.setItalic(true);
        font1.setBold(true);
//        richTextString.applyFont(0, 3, font1);
        richTextString.append("一二三", font1);

        XSSFFont font2 = workbook.createFont();
        font2.setFontName("隶书");
        font2.setFontHeightInPoints((short)20);
        font2.setItalic(true);
        font2.setBold(true);
        font2.setStrikeout(true);
        font2.setUnderline(Font.U_SINGLE);
        font2.setColor(Font.COLOR_RED);
        font2.setTypeOffset(Font.SS_SUPER);
        richTextString.append("\r\n四五六", font2);

        cell1.setCellValue(richTextString);

        //输出到磁盘中
        FileOutputStream fos = new FileOutputStream(new File("D:/xlsx.xlsx"));
        workbook.write(fos);
        workbook.close();
        fos.close();
        System.out.println("finished...");
    }

}
