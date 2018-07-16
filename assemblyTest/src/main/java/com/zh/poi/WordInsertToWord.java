package com.zh.poi;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.rtf.RtfWriter2;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 测试word中插入word
 *
 * Author: Administrator <br/>
 * Date: 2018-07-09 <br/>
 */
public class WordInsertToWord {

    private static String html_content = "<table><tr><td colspan='2'>1222</td></tr><tr><td>First</td> <td>Row</td></tr>"
            + "<tr><td>Second123</td><td>Row</td></tr></table>";

    public static void main(String[] args) throws IOException, InvalidFormatException, DocumentException {
        convertWithIText();
    }
    private static void convertWithIText() throws IOException, DocumentException {
        String outputWordPath = "d:/itext.doc";
        com.lowagie.text.Document document = new com.lowagie.text.Document();
        RtfWriter2.getInstance(document, new FileOutputStream(outputWordPath));
        document.open();

        Paragraph paragraph = new Paragraph();
        List<Element> elements = HTMLWorker.parseToList(new StringReader(html_content), new StyleSheet());
        for (int i = 0 ; i < elements.size() ; i++) {
            Element element = elements.get(i);
            document.add(element);
        }
        document.close();
    }

    private static void convertWithPOI() throws IOException, InvalidFormatException {
        String outputWordPath = "d:/poi.docx";
        FileOutputStream out = new FileOutputStream(outputWordPath);
        ByteArrayInputStream bais = new ByteArrayInputStream(html_content.getBytes("utf-8"));

        POIFSFileSystem poifs = new POIFSFileSystem();
        DocumentEntry documentEntry = poifs.createDocument(bais, "docx");
        poifs.writeFilesystem(out);
        out.close();
        // 上面写出的文件貌似时ooxml格式的，然而poi目前对这种格式支持有问题
        // @see https://bz.apache.org/bugzilla/show_bug.cgi?id=57699
        OPCPackage opcPackage = OPCPackage.open("d:/poi.docx");
        XWPFDocument document = new XWPFDocument(opcPackage);
        XWPFTable table = document.getTables().get(0);
        List<XWPFTableRow> rows = table.getRows();
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                System.out.println(cell.getText());
            }
        }
    }
}
