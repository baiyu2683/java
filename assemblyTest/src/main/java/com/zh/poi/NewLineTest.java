package com.zh.poi;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author: Administrator <br/>
 * Date: 2018-08-06 <br/>
 */
public class NewLineTest {

    public static void main(String[] args) throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setSpacingAfter(0);
        XWPFRun run = paragraph.createRun();
        run.setText("不缩进");
        run = paragraph.createRun();
        run.setText("123");

        XWPFParagraph paragraph2 = document.createParagraph();
        XWPFRun run2 = paragraph2.createRun();
        paragraph2.setSpacingBefore(0);
        run2.setText("缩进两字符");
        // 100算是一个ascii字符， 比如：1，或者四分之一个汉字
        paragraph2.setIndentationLeft(400);

        document.write(new FileOutputStream("d:/poi_doc.doc"));
        System.out.println("finished...");
    }
}
