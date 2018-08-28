package com.zh.poi.word.poi;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Author: Administrator <br/>
 * Date: 2018-08-06 <br/>
 */
public class NewLineTest {

    public static void main(String[] args) throws IOException, XmlException {
        XWPFDocument document = new XWPFDocument();
        
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
        XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

        XWPFParagraph paragraphHeader = header.getParagraphArray(0);
        paragraphHeader.setAlignment(ParagraphAlignment.LEFT);
        paragraphHeader.setBorderBottom(Borders.THICK);

        CTTabStop tabStop = paragraphHeader.getCTP().getPPr().addNewTabs().addNewTab();
        tabStop.setVal(STTabJc.RIGHT);
        int twipsPerInch =  1440;
        tabStop.setPos(BigInteger.valueOf(6 * twipsPerInch));

        XWPFRun runHeader = paragraphHeader.createRun();
        runHeader.setText("header");
        
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setSpacingAfter(0);
        XWPFRun run = paragraph.createRun();
        run.setText("不缩进");
        run = paragraph.createRun();
        run.setText("123");

        XWPFParagraph hrParagraph = document.createParagraph();
        CTP ctp = hrParagraph.getCTP();
        CTPPr ctpPr = ctp.addNewPPr();
        CTPBdr ctpBdr = ctpPr.addNewPBdr();
        CTBorder ctBorder = ctpBdr.addNewBetween();
        ctBorder.setSz(BigInteger.valueOf(4));
        ctBorder.setVal(STBorder.BASIC_THIN_LINES);
        ctBorder.setColor("9A9A9A");

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
