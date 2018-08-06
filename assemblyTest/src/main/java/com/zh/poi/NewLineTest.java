package com.zh.poi;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileNotFoundException;
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
        XWPFRun run = paragraph.createRun();
        run.setText("123123");

        XWPFParagraph paragraph1 = document.createParagraph();
//        XWPFRun run1 = paragraph1.createRun();
//        run1.setText("asdf");
        XWPFParagraph paragraph2 = document.createParagraph();
        XWPFRun run2 = paragraph2.createRun();
        run2.setText("asdf");

        document.write(new FileOutputStream("d:/poi_doc.doc"));

    }
}
