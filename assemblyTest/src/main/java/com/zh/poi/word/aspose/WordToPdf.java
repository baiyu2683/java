package com.zh.poi.word.aspose;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.hslf.model.HeadersFooters;

import com.aspose.words.Document;
import com.aspose.words.HeaderFooter;
import com.aspose.words.HeaderFooterCollection;
import com.aspose.words.License;
import com.aspose.words.Paragraph;
import com.aspose.words.Run;
import com.aspose.words.SaveFormat;

/**
 * aspose操作word转pdf
 * 页眉格式混乱
 * @author zh
 * 2018年8月22日
 */
public class WordToPdf {

    public static void main(String[] args) {
//        doc2Pdf("D:\\Documents\\Downloads\\最新0818(30).docx", "d:/test.pdf");
        doc2Pdf("D:/test.docx", "d:/test.pdf");
    }
    
    public static void doc2Pdf(String wordPath, String pdfPath) {
        if (!getLicense()) {
            return;
        }
        try {
            long start = System.currentTimeMillis();
            File file = new File(pdfPath);
            FileOutputStream fos = new FileOutputStream(file);
            Document doc = new Document(wordPath);
            HeaderFooterCollection hfs = doc.getFirstSection().getHeadersFooters();
            HeaderFooter hf = hfs.get(0);
            Paragraph paragraph = hf.getFirstParagraph();
            for(Run run : paragraph.getRuns()) {
                System.out.println(run.getText());
            }
            doc.save(fos, SaveFormat.PDF);
            fos.close();
            long end = System.currentTimeMillis();
            System.out.println("耗时: " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = WordToPdf.class.getResourceAsStream("/aspose-license.xml");
            License asposeLicense = new License();
            asposeLicense.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
