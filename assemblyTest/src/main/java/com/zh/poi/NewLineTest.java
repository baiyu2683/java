package com.zh.poi;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;

import com.itextpdf.text.Paragraph;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Author: Administrator <br/>
 * Date: 2018-08-06 <br/>
 */
public class NewLineTest {

    public static void main(String[] args) throws IOException, XmlException {
        XWPFDocument document = new XWPFDocument();
        
        XWPFParagraph header = document.createParagraph();
        XWPFRun headerRun = header.createRun();
        headerRun.setText("页眉");
        
        XWPFHeaderFooter headerFooter = new XWPFHeader();
        headerFooter.setXWPFDocument(document);
        List<XWPFParagraph> ps = headerFooter.getParagraphs();
        System.out.println(ps);
        
        
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
