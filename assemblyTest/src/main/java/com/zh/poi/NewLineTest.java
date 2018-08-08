package com.zh.poi;

import com.lowagie.text.html.simpleparser.StyleSheet;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
        run.setText("123123");
        run = paragraph.createRun();
        run.setText("asdf");

        XWPFParagraph paragraph2 = document.createParagraph();
        XWPFRun run2 = paragraph2.createRun();
        paragraph2.setSpacingBefore(0);
        run2.setFontFamily("微软雅黑");
        run2.setText("33333asdfasdfo;fljweofjiopwjefopjiweop\rfijopawjiefopjopwaqejfopjaweofjopawjefojiwoeijfowjefojaweofijoawjiefopjawoefjojioawijefojaowpejf");

        document.write(new FileOutputStream("d:/poi_doc.doc"));
    }

    private void setSpacing(XWPFParagraph paragraph) {
    }
}
