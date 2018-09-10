package com.zh.itext;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.table.RtfBorder;
import com.lowagie.text.rtf.table.RtfBorderGroup;
import com.lowagie.text.rtf.table.RtfCell;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

/**
 *
 * 使用itext导出word
 * Author: Administrator <br/>
 * Date: 2018-07-10 <br/>
 */
public class WordTestIText {

    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();

        RtfWriter2.getInstance(document, new FileOutputStream("d:/itext.doc"));
        document.open();

        // 3列2行
        Table table = new Table(1, 1);
        table.setPadding(0);
        table.setSpacing(0);
        table.setAutoFillEmptyCells(true);

        RtfCell cell = new RtfCell();
        Paragraph paragraph = new Paragraph("456");
//        URL url = WordTestIText.class.getClassLoader().getResource("msyh.ttf");
//        BaseFont bfChinese = BaseFont.createFont(url.getPath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 字体注册
        URL url = WordTestIText.class.getClassLoader().getResource("");
        FontFactory.registerDirectory(url.getPath());
        Font font = FontFactory.getFont("微软雅黑");
        paragraph.setFont(font);
        paragraph.setSpacingBefore(0);
        paragraph.setSpacingAfter(0);
        cell.addElement(paragraph);
        RtfBorderGroup borderGroup = new RtfBorderGroup();
        // 1 上 2 下 4 左 8 右
        borderGroup.addBorder(4, RtfBorder.BORDER_SINGLE, 2, Color.BLACK);
        cell.setBorders(borderGroup);

        table.addCell(cell);
        BigDecimal decimal = new BigDecimal(60 * 15);
        table.getRow(0).setHeight(decimal.intValue());

        document.add(table);

        Rectangle rectangle = new Rectangle(1000, 1000);
        
        document.setPageSize(rectangle);
        document.close();
    }
}
