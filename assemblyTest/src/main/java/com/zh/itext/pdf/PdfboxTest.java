package com.zh.itext.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class PdfboxTest {

    public static void main(String[] args) throws IOException {
        PDDocument document = new PDDocument();
        PDPage pdPage = new PDPage();
        document.addPage(pdPage);
        PDImageXObject pdImageXObject = PDImageXObject.createFromFile("d:\\data\\1587973021992.png", document);
        PDPageContentStream contentStream = new PDPageContentStream(document, pdPage);
        contentStream.drawImage(pdImageXObject,0, 0);
        contentStream.close();
        document.save(new File("C:\\Users\\Administrator\\Desktop\\test.pdf"));
        document.close();
    }
}
