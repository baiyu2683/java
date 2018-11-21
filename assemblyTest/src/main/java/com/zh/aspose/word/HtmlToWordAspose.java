package com.zh.aspose.word;

import com.aspose.words.*;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileReader;

/**
 * 测试aspose由html转word
 */
public class HtmlToWordAspose {

    /**
     * 测试导出word各种情况
     * @throws Exception
     */
    @Test
    public void mainTest() throws Exception {
        Document document = new Document();
        // TODO 页面背景色
//        document.setPageColor(Color.BLUE);

        DocumentBuilder documentBuilder = new DocumentBuilder(document);
        //TODO 页面横向
        PageSetup pageSetup = documentBuilder.getPageSetup();
//        pageSetup.setOrientation(Orientation.LANDSCAPE);
        // TODO 页面宽度
//        pageSetup.setPageWidth(1000);
        // TODO 页面高度
//        pageSetup.setPageHeight(500);
        //TODO 页边距
//        pageSetup.setTopMargin(100d);
//        pageSetup.setLeftMargin(200d);

        //TODO 页头页尾,暂时不知道如何实现左中右。。。
//        documentBuilder.moveToHeaderFooter(HeaderFooterType.HEADER_PRIMARY);
//        documentBuilder.getParagraphFormat().getTabStops().add(300, TabAlignment.LEFT, TabLeader.NONE);
//        documentBuilder.write("Left Header" + ControlChar.TAB + "Right header");

        // TODO 页尾加页码
        documentBuilder.moveToHeaderFooter(HeaderFooterType.FOOTER_PRIMARY);
        documentBuilder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        documentBuilder.insertField("PAGE", "");

//        documentBuilder.moveToHeaderFooter(HeaderFooterType.HEADER_FIRST);
//        Shape backGround = documentBuilder.insertImage("http://pic31.photophoto.cn/20140613/0037037524887622_b.jpg");
//        backGround.setLeft(0);
//        backGround.setTop(0);
//        backGround.setWrapType(WrapType.NONE);
//        backGround.setBehindText(true);
        // TODO 插入图片

        documentBuilder.moveToDocumentStart();
        documentBuilder.insertHtml(IOUtils.toString(new FileReader("e:/html.html")));

        document.save("e:/html2.docx");
    }

    /**
     * 根据url导出word
     * @throws Exception
     */
    @Test
    public void toWordByUrl() throws Exception {
        String url = "https://datatables.net/";
        Document document = new Document(url);
        document.save("e:/datatables.docx", SaveFormat.DOCX);
    }

    /**
     * 转table
     * @throws Exception
     */
    @Test
    public void toWordTable() throws Exception {
        String url = "d:/viewScene.html";
        Document document = new Document(url);
        SaveOptions saveOptions = new DocSaveOptions();
        document.save("d:/html.docx", SaveFormat.DOCX);
    }
}
