package com.zh.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.*;
import com.itextpdf.tool.xml.css.CssFilesImpl;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import java.io.*;

/**
 * <p>
 * html转pdf，解决中文不显示问题
 * </p>
 * @see <a href = "https://www.oschina.net/code/snippet_128625_58201"/>
 * Author: Administrator <br/>
 * Date: 2018-07-20 <br/>
 */
public class PdfGenerator {

    static {
        FontFactory.register("D:\\fonts\\msyh.ttf");
        FontFactory.register("D:\\fonts\\msyhbd.ttf");
    }

    public static void main(String[] args) throws Exception {
        String html_content = "<p>\n" +
                "    &nbsp;表头1\n" +
                "</p>\n" +
                "<p>\n" +
                "    &nbsp;\n" +
                "</p>\n" +
                "<p>\n" +
                "    <span>字体</span><span style=\"color: rgb(255, 192, 0);font-family:'微软雅黑'\">字体</span>\n" +
                "</p>\n" +
                "<p>\n" +
                "    &nbsp;\n" +
                "</p>\n" +
                "<p>\n" +
                "    表头3\n" +
                "</p>\n" +
                "<p>\n" +
                "    &nbsp;\n" +
                "</p>\n" +
                "<ol>\n" +
                "    <li>\n" +
                "        列表1\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        <span style=\"color: rgb(255, 0, 0);\">&nbsp;列表2</span>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        &nbsp;列表3\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        列表4\n<img src='https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532067982558&di=90d892b35348e06adcb48fea1eebbf15&imgtype=0&src=http%3A%2F%2Fwww.liangchan.net%2Fsoft%2FUploadPic%2F2010-11%2F2010111716123954326.gif'/>" +
                "    </li>\n" +
                "</ol>\n" +
                "<p>\n" +
                "    &nbsp;\n" +
                "</p>";
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("d:/test.pdf"));
        document.open();

        MyXMLParser.getInstance(document, pdfWriter).parse(new StringReader(html_content));

        document.close();
    }

    protected static class MyXMLParser {
        public static XMLParser getInstance(Document doc, PdfWriter pdfWriter) throws Exception {

            //固定css
            CssFilesImpl cssFiles = new CssFilesImpl();
//            if (StringUtils.isNotBlank(cssFile)) {
//                cssFiles.add(XMLWorkerHelper.getCSS(new FileInputStream(new File(cssFile))));
//            } else {

                cssFiles.add(XMLWorkerHelper.getInstance().getDefaultCSS());
//            }
            StyleAttrCSSResolver cssResolver = new StyleAttrCSSResolver(cssFiles);

            //宋体支持

            HtmlPipelineContext hpc = new HtmlPipelineContext(new CssAppliersImpl(
                    new SongFontsProvider()));

            //图片加载
//            if (StringUtils.isNotBlank(imagePath)) {
//                hpc.setImageProvider(new ImageProvider(imagePath));
//            }
            hpc.setAcceptUnknown(true).autoBookmark(true)
                    .setTagFactory(Tags.getHtmlTagProcessorFactory());
            HtmlPipeline htmlPipeline = new HtmlPipeline(hpc, new PdfWriterPipeline(doc, pdfWriter));
            Pipeline<?> pipeline = new CssResolverPipeline(cssResolver, htmlPipeline);
            return new XMLParser(true, new XMLWorker(pipeline, true));
        }
    }

    /**
     * 找不到的字体一律改为宋体
     */
    protected static class SongFontsProvider extends XMLWorkerFontProvider {
        public SongFontsProvider() {
            super(null, null);
        }

        @Override
        public Font getFont(final String fontname, String encoding, float size, final int style) {

            if (fontname == null) {
                try {
                    final BaseFont baseFont = BaseFont.createFont("STSongStd-Light",
                            "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                    return new Font(baseFont, size, style);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return super.getFont(fontname, encoding, size, style);
        }
    }

    protected static class ImageProvider extends AbstractImageProvider {
        private String imageRootPath;

        public ImageProvider(String imageRootPath) {
            this.imageRootPath = imageRootPath;
        }

        public String getImageRootPath() {
            return imageRootPath;
        }
    }
}
