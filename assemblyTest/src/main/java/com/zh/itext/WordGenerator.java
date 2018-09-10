package com.zh.itext;

import com.lowagie.text.*;
import com.lowagie.text.rtf.RtfWriter2;

import java.io.FileOutputStream;
import java.io.IOException;

public class WordGenerator {

    static {
        FontFactory.register("D:\\fonts\\msyh.ttf");
        FontFactory.register("D:\\fonts\\msyhbd.ttf");
    }

    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        RtfWriter2.getInstance(document, new FileOutputStream("d:/itext_newline.rtf"));
        document.open();
        Paragraph paragraph = new Paragraph();
        Chunk chunk = new Chunk("123");
        paragraph.add(chunk);
        paragraph.add(new Chunk("\n"));
        Chunk chunk1 = new Chunk("newline");
        paragraph.add(chunk1);
        document.add(paragraph);
        document.close();
    }

    public static String wrapperSpan(String paramString)
    {
//        if (paramStyle == null) {
//            return paramString;
//        }
//        FRFont localFRFont = paramStyle.getFRFont();
//        String str = localFRFont.getFontName();
//        int i = localFRFont.getSize();
//        StringBuilder localStringBuilder = new StringBuilder();
//        localStringBuilder.append("<span style='font-size:");
//        localStringBuilder.append(i).append("pt;font-family:").append(str);
//        if (localFRFont.isBold()) {
//            localStringBuilder.append(";font-weight:bold");
//        }
//        if (localFRFont.isItalic()) {
//            localStringBuilder.append(";font-style: italic");
//        }
//        if (localFRFont.isStrikethrough()) {
//            localStringBuilder.append(";text-decoration: line-through;");
//        }
//        localStringBuilder.append(";color:rgb(").append(localFRFont.getForeground().getRed()).append(",").append(localFRFont.getForeground().getGreen()).append(",").append(localFRFont.getForeground().getBlue()).append(")'>");
//        localStringBuilder.append(paramString);
//        localStringBuilder.append("</span>");
//        return localStringBuilder.toString();
        return null;
    }
}