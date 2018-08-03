package com.zh.itext;

import com.lowagie.text.*;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.rtf.RtfWriter2;

import java.io.*;
import java.util.List;

/**
 * html片段转word
 * 可以设置纸张大小(单位: px)，纸张边距，纸张背景，纸张方向(默认纵向，调用rotate(）获得横向纸张)
 * Author: Administrator <br/>
 * Date: 2018-07-27 <br/>
 */
public class HtmlToWord {

    public static void main(String[] args) throws DocumentException, IOException {
        OutputStream out = new FileOutputStream("d://text.doc");
        Document document = new Document(PageSize.A4);
        RtfWriter2.getInstance(document, out);
        document.open();
        Paragraph context = new Paragraph();
        String htmlContent = "";
        StyleSheet ss = new StyleSheet();
        List htmlList = HTMLWorker.parseToList(new StringReader(htmlContent), ss);
        for (int i = 0; i < htmlList.size(); i++) {
            com.lowagie.text.Element e = (com.lowagie.text.Element) htmlList.get(i);
            context.add(e);
        }
        document.add(context);
        document.close();
        System.out.println("ok");
    }
}
