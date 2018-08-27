package com.zh.poi.word.itext;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HtmlToPdf {

    public static void main(String[] args) throws DocumentException, IOException, TransformerConfigurationException, ParserConfigurationException {
        String filepath = "D:/html.html";
        String outpath = "D:/test.pdf";
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outpath));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, 
                new FileInputStream(filepath), Charset.forName("utf-8"));
        document.close();
        System.out.println("finished..");
    }
    
    public static void wordToHtml() throws FileNotFoundException, IOException, ParserConfigurationException, TransformerConfigurationException {
        String docFile = "d:/html.docx";
        InputStream in = new FileInputStream(new File(docFile));
        XWPFDocument document = new XWPFDocument(in);
        XHTMLOptions options = XHTMLOptions.create();
        options.setExtractor(new FileImageExtractor(new File("d:/images")));
        options.URIResolver(new BasicURIResolver("file:/D:"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(document, baos, options);
        baos.close();
        FileOutputStream fos = new FileOutputStream("d:/html.html");
        IOUtils.write(baos.toByteArray(), fos);
        fos.close();
        System.out.println("finished");
    }
}
