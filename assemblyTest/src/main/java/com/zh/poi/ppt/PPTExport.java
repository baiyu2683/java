package com.zh.poi.ppt;

import com.zh.image.ImageUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;

/**
 * @author zhheng
 * @date 2020/11/29
 */
public class PPTExport {

    public static void main(String[] args) throws IOException {
        XMLSlideShow ppt = new XMLSlideShow();
        // 单位: 点
        Dimension dimension = new Dimension(960, 540);
        ppt.setPageSize(dimension);

        XSLFSlide slide = ppt.createSlide();

        // 切图，大小和背景一致
        InputStream inputStream = PPTExport.class.getResourceAsStream("/image/pptbackground.jpg");
        // 单位：像素
//        byte[] destPic = ImageUtils.thumbnail(IOUtils.toByteArray(inputStream), (float)Units.pointsToPixel(dimension.getWidth()), (float)Units.pointsToPixel(dimension.getHeight()));
//        inputStream = new ByteArrayInputStream(destPic);

        // 统一背景图片
        XSLFPictureData xslfPictureData = ppt.addPicture(inputStream, PictureData.PictureType.JPEG);

        XSLFSlideMaster slideMaster = ppt.getSlideMasters().get(0);
        slideMaster.getBackground().setAnchor(new Rectangle(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight()));
        slideMaster.createPicture(xslfPictureData);

        XSLFTextBox textBox = slide.createTextBox();
        // 文本框位置居中
        textBox.setAnchor(new Rectangle(280, 10, 400, 40));
        // 文本框内容居中
        textBox.setHorizontalCentered(true);
        XSLFTextRun textRun = textBox.setText("测试文字");
        textRun.setFontSize(24.0);
        textRun.setFontFamily("微软雅黑");
        textRun.setBold(true);

        String[] firstRow = {"基本信息","异常时段","异常时长","异常发现时长","优先级","是否故障","异常原因","责任处室"};
        String[] secondRow = {"","2018年08月15日  16:50-17:39","49m","0m","A","否","软件-性能不足","企内研发中心-财务管理系统研发部"};
        String[] thirdRow = {"故障影响","影响范围：全网影响内容：无实际业务影响，快递员无法立即查看微信支付结果，但可通过客户微信支付结果查看。故障现象：散单微信支付正常，但支付结果状态返回巴枪缓慢。","","","","","",""};
        String[] fourthRow = {"过程回顾","开始时间","结束时间","处理室","处理人","处理过程描述（变更、决策相关信息）","",""};
        String[] penultimateRow = {"原因检讨过程检讨","原因检讨：8月15日16:40-16:50（异常发生前10分钟），巴枪支付交易明细查询次数206次，与昨天同一时段查询次数73次相比，上涨近3倍，由于支付交易明细查询SQL效率不高，慢SQL在数据库大批量执行全分片扫描，导致数据库性能下降，造成支付结果状态返回巴枪缓慢。过程检讨：定位慢SQL对应的功能模块耗时较长","","","","","",""};
        String[] lastRow = {"改进措施","1、切换巴枪支付查询通道从MYCAT到HBASE       -----IT服务中心/林文海  2018-8-15   【已完成】2、优化支付交易明细查询功能                              -----企内研发中心/夏佳2018-8-23    【计划中】3、支付状态返回巴枪和状态落地功能拆分             -----企内研发中心/ 夏佳 2018-8-31  【计划中】","","","","","",""};
        XSLFTable table1 = slide.createTable();//创建表格
        table1.setAnchor(new Rectangle2D.Double(10, 50,960, 400));

        XSLFTableRow tableRow1 = table1.addRow(); //创建表格行
        for(int j = 0; j < 8; j++) {
            XSLFTableCell tableCell = tableRow1.addCell();//创建表格单元格
            tableCell.clearText();
            XSLFTextRun tr = tableCell.setText(firstRow[j]);
            tr.setFontSize(12.0);
            tr.setFontFamily("微软雅黑");
            tr.setBold(true);
            tableCell.setFillColor(Color.getColor("0xdd7e6b"));
            //p.setTextAlign(TextAlign.CENTER);
            tableCell.setVerticalAlignment(VerticalAlignment.MIDDLE);

            tableCell.setBorderColor(TableCell.BorderEdge.left, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.bottom, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.right, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.top, Color.BLACK);
        }
        tableRow1.setHeight(20);

        XSLFTableRow tableRow2 = table1.addRow(); //创建表格行
        for(int j = 0; j < 8; j++) {
            XSLFTableCell tableCell = tableRow2.addCell();//创建表格单元格
            tableCell.clearText();
            XSLFTextRun tr = tableCell.setText(secondRow[j]);
            tr.setFontSize(12.0);
            tr.setFontFamily("微软雅黑");
            //tr.setBold(true);
            tableCell.setFillColor(Color.getColor("0xdd7e6b"));
            //p.setTextAlign(TextAlign.CENTER);
            tableCell.setVerticalAlignment(VerticalAlignment.MIDDLE);

            tableCell.setBorderColor(TableCell.BorderEdge.left, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.bottom, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.right, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.top, Color.BLACK);
        }
        tableRow2.setHeight(50);

        XSLFTableRow tableRow3 = table1.addRow(); //创建表格行
        for(int j = 0; j < 8; j++) {
            XSLFTableCell tableCell = tableRow3.addCell();
            tableCell.clearText();
            XSLFTextRun tr = tableCell.setText(thirdRow[j]);
            tr.setFontSize(12.0);
            tr.setFontFamily("微软雅黑");
            //tr.setBold(true);
            tableCell.setFillColor(Color.getColor("0xdd7e6b"));
            //p.setTextAlign(TextAlign.CENTER);
            tableCell.setVerticalAlignment(VerticalAlignment.MIDDLE);

            tableCell.setBorderColor(TableCell.BorderEdge.left, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.bottom, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.right, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.top, Color.BLACK);
        }
        tableRow3.setHeight(50);

        XSLFTableRow tableRow4 = table1.addRow(); //创建表格行
        for(int j = 0; j < 8; j++) {
            XSLFTableCell tableCell = tableRow4.addCell();//创建表格单元格
     /*XSLFTextParagraph p = tableCell.addNewTextParagraph();
     XSLFTextRun tr = p.addNewTextRun();
     tr.setText(String.valueOf(firstRow[j]));*/
            tableCell.clearText();
            XSLFTextRun tr = tableCell.setText(fourthRow[j]);
            tr.setFontSize(12.0);
            tr.setFontFamily("微软雅黑");
            //tr.setBold(true);
            tableCell.setFillColor(Color.getColor("0xdd7e6b"));
            //p.setTextAlign(TextAlign.CENTER);
            tableCell.setVerticalAlignment(VerticalAlignment.MIDDLE);

            tableCell.setBorderColor(TableCell.BorderEdge.left, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.bottom, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.right, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.top, Color.BLACK);
        }
        tableRow4.setHeight(50);

        XSLFTableRow tableRow5 = table1.addRow(); //创建表格行
        for(int j = 0; j < 8; j++) {
            XSLFTableCell tableCell = tableRow5.addCell();//创建表格单元格
     /*XSLFTextParagraph p = tableCell.addNewTextParagraph();
     XSLFTextRun tr = p.addNewTextRun();
     tr.setText(String.valueOf(firstRow[j]));*/
            tableCell.clearText();
            XSLFTextRun tr = tableCell.setText(penultimateRow[j]);
            tr.setFontSize(12.0);
            tr.setFontFamily("微软雅黑");
            //tr.setBold(true);
            tableCell.setFillColor(Color.getColor("0xdd7e6b"));
            //p.setTextAlign(TextAlign.CENTER);
            tableCell.setVerticalAlignment(VerticalAlignment.MIDDLE);

            tableCell.setBorderColor(TableCell.BorderEdge.left, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.bottom, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.right, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.top, Color.BLACK);
        }
        tableRow5.setHeight(50);

        XSLFTableRow tableRow6 = table1.addRow(); //创建表格行
        for(int j = 0; j < 8; j++) {
            XSLFTableCell tableCell = tableRow6.addCell();//创建表格单元格
     /*XSLFTextParagraph p = tableCell.addNewTextParagraph();
     XSLFTextRun tr = p.addNewTextRun();
     tr.setText(String.valueOf(firstRow[j]));*/
            tableCell.clearText();
            XSLFTextRun tr = tableCell.setText(lastRow[j]);
            tr.setFontSize(12.0);
            tr.setFontFamily("微软雅黑");
            //tr.setBold(true);
            tableCell.setFillColor(Color.getColor("0xdd7e6b"));
            //p.setTextAlign(TextAlign.CENTER);
            tableCell.setVerticalAlignment(VerticalAlignment.MIDDLE);

            tableCell.setBorderColor(TableCell.BorderEdge.left, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.bottom, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.right, Color.BLACK);
            tableCell.setBorderColor(TableCell.BorderEdge.top, Color.BLACK);
        }
        tableRow6.setHeight(50);
//设置列宽
        table1.setColumnWidth(0, 70);
        table1.setColumnWidth(1, 120);
        table1.setColumnWidth(2, 120);
        table1.setColumnWidth(3, 120);
        table1.setColumnWidth(4, 120);
        table1.setColumnWidth(5, 120);
        table1.setColumnWidth(6, 120);
        table1.setColumnWidth(7, 150);
//合并单元格
        table1.mergeCells(0, 1, 0, 0);
        table1.mergeCells(2, 2, 1, 7);
        table1.mergeCells(3, 3, 5, 7);
        table1.mergeCells(4, 4, 1, 7);
        table1.mergeCells(5, 5, 1, 7);

        File file = new File("d:/test.ppt");
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ppt.write(fileOutputStream);
        fileOutputStream.close();
    }
}
