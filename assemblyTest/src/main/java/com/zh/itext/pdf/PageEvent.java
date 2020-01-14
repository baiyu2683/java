package com.zh.itext.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

import java.io.File;
import java.net.URL;


public class PageEvent extends PdfPageEventHelper {
    private static Font ffont;
    static {
        XMLWorkerFontProvider fp = new XMLWorkerFontProvider();
        fp.registerDirectory("E:\\apache-tomcat-6.0.45\\webapps\\HappyServer\\WEB-INF\\classes\\font\\font", true);
        ffont = fp.getFont("宋体",BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
        ffont.setSize(9);
    }

    private String leftHeader;
    private String centerHeader;
    private String rightHeader;

    public PageEvent(String leftHeader, String centerHeader, String rightHeader) {
        this.leftHeader = leftHeader;
        this.centerHeader = centerHeader;
        this.rightHeader = rightHeader;
    }

    private void addHeader(PdfWriter writer, Document document){
        PdfContentByte cb = writer.getDirectContent();
        Phrase left = new Phrase(leftHeader, ffont);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
                left,
                document.leftMargin(),
                document.top() + 4, 0);
        Phrase center = new Phrase(centerHeader, ffont);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                center,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.top() + 4, 0);
        Phrase right = new Phrase(rightHeader, ffont);
        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT,
                right,
                document.right(),
                document.top() + 4, 0);
        cb.saveState();
        cb.setLineWidth(0.5);
        cb.moveTo(document.leftMargin(), document.top() + 2);
        cb.lineTo(document.right(), document.top() + 2);
        cb.stroke();
        cb.restoreState();
    }

    private void addFooter(PdfWriter writer,Document document){
        PdfContentByte cb = writer.getDirectContent();
        Phrase footer = new Phrase("Page "+writer.getPageNumber(),
                ffont);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer,
                (document.right() - document.left()) / 2 +
                        document.leftMargin(),
                document.bottom() - 10, 0);
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        try{
            addHeader(writer,document);
            addFooter(writer,document);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
