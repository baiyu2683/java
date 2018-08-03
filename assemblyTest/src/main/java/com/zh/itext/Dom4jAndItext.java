package com.zh.itext;

import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.lowagie.text.Chunk;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;
import com.zh.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Administrator <br/>
 * Date: 2018-08-02 <br/>
 */
public class Dom4jAndItext {

    private static final String rootPath = Dom4jAndItext.class.getResource("/").getPath();

    private static final String htmlPath = rootPath + "/html";

    private static final String fontPath = rootPath + "/font";

    public static void main(String[] args) throws DocumentException, FileNotFoundException, com.lowagie.text.DocumentException {

        // 读取html文件
        File htmlFile = new File(htmlPath + "/html.html");
        if (!htmlFile.exists()) {
            System.out.println("文件不存在: " + htmlFile.getPath());
            return;
        }

        FontFactoryImp fp = new XMLWorkerFontProvider();
        fp.registerDirectory(fontPath, true);

        com.lowagie.text.Document wordDocument = new com.lowagie.text.Document(PageSize.A4);
        RtfWriter2.getInstance(wordDocument, new FileOutputStream(rootPath + "/itext.rtf"));
        wordDocument.open();

        String htmlContent = FileUtil.toString(htmlFile);

        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(htmlContent));
        Element rootElement = document.getRootElement();
        List<Element> elementList = rootElement.elements();
        for (Element element : elementList) {
            String elementName  = element.getName();
            System.out.println(elementName);
            if ("head".equals(elementName)) {
                // TODO 获取样式表
                // ...
            } else if ("body".equals(elementName)) {
                List<Element> bodyElementList = element.elements();
                recursionSubElement(bodyElementList, wordDocument);
            }
        }
        wordDocument.close();
    }

    /**
     * 对每个子元素逐个加入样式等
     * @param elements
     * @param wordDocument
     * @throws com.lowagie.text.DocumentException
     */
    private static void recursionSubElement(List<Element> elements, com.lowagie.text.Document wordDocument) throws com.lowagie.text.DocumentException {
        for (Element ele : elements) {
            System.out.println("recursionSubElement Doc: " + ele.getName());
            System.out.println(ele.getTextTrim());
            Paragraph paragraph = new Paragraph(ele.getTextTrim());
            handleSingleTag(ele, paragraph);

            wordDocument.add(paragraph);
        }
    }

    private static void recursionSubElement(List<Element> elements, Paragraph paragraph) {
        for (Element ele : elements) {
            System.out.println("recursionSubElement: " + ele.getName());
            System.out.println(ele.getTextTrim());
            Paragraph subParagraph = new Paragraph();
            handleSingleTag(ele, subParagraph);

            paragraph.add(subParagraph);
        }
    }

    /**
     * 将dom4jelement转换为itext段落
     * @param ele dom4j元素
     * @param subParagraph itext中父元素
     */
    private static void handleSingleTag(Element ele, Paragraph subParagraph) {
        addStyle(subParagraph, ele);
        List<Element> subElements = ele.elements();
        if ("p".equals(ele.getName())) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Element subElement : subElements) {

            }
        } else if ("div".equals(ele.getName())) {
        }
        recursionSubElement(subElements, subParagraph);
    }

    /**
     * 解析样式
     * @param itextElement
     * @param element
     */
    private static void addStyle(com.lowagie.text.Element itextElement, Element element) {
        Attribute styleAttribute = element.attribute("style");
        if (styleAttribute == null) return;

        String styleValue = styleAttribute.getValue();
        Map<String, String> styleSheet = styleAttr2StyleSheet(styleValue);
        if (styleSheet != null && styleSheet.size() > 0) {
            if (itextElement instanceof Paragraph) {
                Paragraph itextParagraph = (Paragraph) itextElement;
                // TODO 字体样式 转换器
                RtfFont font = new RtfFont("微软雅黑");
                if (styleSheet.containsKey("font-family")) {
                    font.setFamily(styleSheet.get("font-family"));
                }
                if (styleSheet.containsKey("font-size")) {
                    font.setSize(Float.parseFloat(styleSheet.get("font-size")));
                }
                itextParagraph.setFont(font);
                // TODO 对齐 样式转换器
                itextParagraph.setAlignment(Paragraph.ALIGN_CENTER);
                // TODO 前后间距，样式转换器
                itextParagraph.setSpacingBefore(10);
                itextParagraph.setSpacingAfter(10);

                // TODO 宽高
                if (styleSheet.containsKey("witdth")) {
                }
                if (styleSheet.containsKey("height")) {
                }
            }
        }
        System.out.println(styleSheet);
    }

    /**
     * style属性转换为key—value样式表
     * @param styleStr
     * @return
     */
    private static Map<String, String> styleAttr2StyleSheet(String styleStr) {
        if (StringUtils.isBlank(styleStr)) return null;

        Map<String, String> styleMap = new HashMap<>();

        String[] styleArr = styleStr.split(";");
        for (String style : styleArr) {
            String[] singleStyleArr = style.split(":");
            if (singleStyleArr.length == 2) {
                styleMap.put(singleStyleArr[0].trim(), singleStyleArr[1].trim().replace("px", ""));
            }
        }
        return styleMap;
    }
}
