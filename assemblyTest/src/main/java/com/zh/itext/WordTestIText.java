package com.zh.itext;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.table.RtfCell;

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

        Table table = new Table(1, 1);
        table.setPadding(0);
        table.setSpacing(0);
        table.setAutoFillEmptyCells(true);

        RtfCell cell = new RtfCell();
        Paragraph paragraph = new Paragraph("456");
        // 字体注册
//        URL url = WordTestIText.class.getClassLoader().getResource("font");
//        FontFactory.registerDirectory(url.getPath());
        Font font = new Font();
        font.setFamily("微软雅黑");
        paragraph.setFont(font);
        paragraph.setSpacingBefore(0);
        paragraph.setSpacingAfter(0);
        cell.addElement(paragraph);

        table.addCell(cell);
        BigDecimal decimal = new BigDecimal(60 * 15);
        table.getRow(0).setHeight(decimal.intValue());

        document.add(table);

        document.close();
        System.out.println("finished...");
    }
}
