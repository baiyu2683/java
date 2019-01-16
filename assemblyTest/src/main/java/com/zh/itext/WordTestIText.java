package com.zh.itext;

import com.lowagie.text.*;
import com.lowagie.text.pdf.DefaultSplitCharacter;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;
import com.lowagie.text.rtf.table.RtfCell;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 *
 * 使用itext导出word
 * Author: Administrator <br/>
 * Date: 2018-07-10 <br/>
 */
public class WordTestIText {

    public static void main(String[] args) throws IOException, DocumentException {

        // 字体注册
        FontFactory.registerDirectory("E:\\apache-tomcat-6.0.45\\webapps\\HappyServer\\WEB-INF\\classes\\font\\fontResource");
        FontFactory.defaultEncoding = "utf8";

        Document document = new Document();

        RtfWriter2.getInstance(document, new FileOutputStream("d:/itext.doc"));
        document.open();

        Table table = new Table(1, 1);
        table.setPadding(0);
        table.setSpacing(0);
        table.setAutoFillEmptyCells(true);

        RtfCell cell = new RtfCell();
        Chunk chunk = new Chunk("456asd师打发斯asdfasdfasdfqw3ef阿瑟大方权威番茄味废弃未付钱未付钱未付全微分全微分df师打发斯蒂芬阿斯蒂芬");
        Phrase phrase = new Phrase(chunk);
        Paragraph paragraph = new Paragraph(phrase);
        // 解决office中中文字体，字体选择框里乱码问题
        String fontName = new String("微软雅黑".getBytes("gb18030"), "iso-8859-1");
        RtfFont rtfFont = new RtfFont(fontName);
        rtfFont.setCharset(134);
//        rtfFont.setSize(20);
//        BaseFont.createFont("微软雅黑",  , BaseFont.EMBEDDED);
        paragraph.setFont(rtfFont);
        paragraph.setSpacingBefore(0);
        paragraph.setSpacingAfter(0);
        Font font = paragraph.getFont();
        cell.addElement(paragraph);

        table.addCell(cell);
        BigDecimal decimal = new BigDecimal(60 * 15);
        table.getRow(0).setHeight(decimal.intValue());

        document.add(table);

        document.close();
        System.out.println("finished...");
    }
}
