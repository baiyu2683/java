package com.zh.aspose.html;

import com.aspose.html.HTMLDocument;
import com.aspose.html.rendering.HtmlRenderer;
import com.aspose.html.rendering.pdf.PdfDevice;
import com.aspose.html.rendering.pdf.PdfRenderingOptions;

public class HtmlToPdfAspose {

    public static void main(String[] args) {
        HTMLDocument html = new HTMLDocument("e:/html.html");
        HtmlRenderer renderer = new HtmlRenderer();
        renderer.render(new PdfDevice(new PdfRenderingOptions(), "e:/html.pdf"), html);
    }
}
