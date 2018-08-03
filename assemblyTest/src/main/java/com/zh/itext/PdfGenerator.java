package com.zh.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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
import java.nio.charset.Charset;

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

    public static void main1(String[] args) throws IOException, DocumentException {
        String htmlContent = "";
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(("d:/text.pdf"))));
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(htmlContent), Charset.forName("UTF-8"));
        // step 5
        document.close();
    }

    public static void main(String[] args) throws Exception {
        String htmlContent = "asdf";

        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("d:/test.pdf"));
        document.open();

        MyXMLParser.getInstance(document, pdfWriter).parse(new StringReader(htmlContent));

        document.close();
    }

    protected static class MyXMLParser {
        public static XMLParser getInstance(Document doc, PdfWriter pdfWriter) throws Exception {

            //固定css
            CssFilesImpl cssFiles = new CssFilesImpl();
            cssFiles.add(XMLWorkerHelper.getInstance().getDefaultCSS());
            StyleAttrCSSResolver cssResolver = new StyleAttrCSSResolver(cssFiles);

            HtmlPipelineContext hpc = new HtmlPipelineContext(new CssAppliersImpl(
                    new DefaultFontsProvider()));
            hpc.setAcceptUnknown(true).autoBookmark(true)
                    .setTagFactory(Tags.getHtmlTagProcessorFactory());

            HtmlPipeline htmlPipeline = new HtmlPipeline(hpc, new PdfWriterPipeline(doc, pdfWriter));
            Pipeline<?> pipeline = new CssResolverPipeline(cssResolver, htmlPipeline);
            return new XMLParser(true, new XMLWorker(pipeline, true));
        }
    }

    /**
     * 找不到的字体一律改为微软雅黑
     */
    protected static class DefaultFontsProvider extends XMLWorkerFontProvider {
        public DefaultFontsProvider() {
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
