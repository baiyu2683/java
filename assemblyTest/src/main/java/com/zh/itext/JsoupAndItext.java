package com.zh.itext;

import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Administrator <br/>
 * Date: 2018-08-02 <br/>
 */
public class JsoupAndItext {

    private static final String rootPath = JsoupAndItext.class.getResource("/").getPath();

    private static final String htmlPath = rootPath + "/html";

    private static final String fontPath = rootPath + "/font";

    // 换行
    private static final Chunk new_line = new Chunk("\n");

    public static void main(String[] args) throws IOException, com.lowagie.text.DocumentException {

        // 读取html文件
        File htmlFile = new File(htmlPath + "/html.html");
        if (!htmlFile.exists()) {
            System.out.println("文件不存在: " + htmlFile.getPath());
            return;
        }

        FontFactoryImp fp = new XMLWorkerFontProvider();
        fp.registerDirectory(fontPath, true);

        Document document = Jsoup.parse(htmlFile, "utf-8");

        com.lowagie.text.Document wordDocument = new com.lowagie.text.Document(PageSize.A4);
        RtfWriter2.getInstance(wordDocument, new FileOutputStream(rootPath + "/itext.rtf"));
        wordDocument.open();

        Elements allElements = document.getAllElements();
        for (Element element : allElements) {
            String elementName  = element.tagName();
            if ("head".equals(elementName)) {
                // TODO 获取样式表
                // ...
            } else if ("body".equals(elementName)) {
                Elements bodyElementList = element.children();
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
            System.out.println("recursionSubElement Doc: " + ele.tagName());
            Paragraph paragraph = new Paragraph(ele.ownText());
            handleSingleTag(ele, paragraph);

            wordDocument.add(paragraph);
        }
    }

    private static void recursionSubElement(List<Element> elements, Paragraph paragraph) {
        for (Element ele : elements) {
            System.out.println("recursionSubElement: " + ele.tagName());
            System.out.println(ele.ownText());
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
        Elements subElements = ele.children();
        if ("p".equals(ele.tagName())) {
            for (Element subElement : subElements) {
                String ownText = subElement.html();
                if (ownText != null && ownText.length() > 0) {
                    String[] texts = newLine(ownText);
                    for (int i = 0 ; i < texts.length ; i++) {
                        Chunk chunk = new Chunk(htmlConverter(texts[i]));
                        subParagraph.add(chunk);
                        if (i != texts.length - 1)
                            subParagraph.add(new Chunk("\n"));
                    }
                }
            }
        } else if ("div".equals(ele.tagName())) {
            // TODO 文本处理
        } else if ("span".equals(ele.tagName())) {
            // TODO 文本处理
        }
        recursionSubElement(subElements, subParagraph);
    }

    /**
     * 解析样式
     * @param itextElement
     * @param element
     */
    private static void addStyle(com.lowagie.text.Element itextElement, Element element) {
        String styleAttribute = element.attr("style");
        if (styleAttribute == null) return;

        Map<String, String> styleSheet = styleAttr2StyleSheet(styleAttribute);
        if (styleSheet != null && styleSheet.size() > 0) {
            if (itextElement instanceof Paragraph) {
                Paragraph itextParagraph = (Paragraph) itextElement;
                // TODO 字体样式 转换器
                RtfFont font = new RtfFont("微软雅黑");
                if (styleSheet.containsKey("font-family")) {
                    font.setFamily(styleSheet.get("font-family"));
                }
                if (styleSheet.containsKey("font-size")) {
                    System.out.println(styleSheet.get("font-size"));
                    // 像素转itext字体大小
                    font.setSize(Float.parseFloat(styleSheet.get("font-size")) * 0.75f);
                }
                // TODO 粗体
                font.setStyle(Font.BOLD);
                itextParagraph.setFont(font);
                // TODO 对齐 样式转换器
                if (styleSheet.containsKey("text-align")) {
                    String textAlign = styleSheet.get("text-align");
                    if ("left".equals(textAlign)) {
                        itextParagraph.setAlignment(Paragraph.ALIGN_LEFT);
                    } else if ("right".equals(textAlign)) {
                        itextParagraph.setAlignment(Paragraph.ALIGN_RIGHT);
                    } else if ("center".equals(textAlign)) {
                        itextParagraph.setAlignment(Paragraph.ALIGN_CENTER);
                    } else if ("justify".equals(textAlign)) {
                        itextParagraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
                    }
                }
                // TODO 前后间距，样式转换器
                itextParagraph.setSpacingBefore(0);
                itextParagraph.setSpacingAfter(0);

                // TODO 宽高
                if (styleSheet.containsKey("witdth")) {
                }
                if (styleSheet.containsKey("height")) {
                }
            }
        }
    }

    /**
     * style属性转换为key—value样式表
     * @param styleStr
     * @return
     */
    private static Map<String, String> styleAttr2StyleSheet(String styleStr) {
//        if (StringUtils.isBlank(styleStr)) return null;
//
        Map<String, String> styleMap = new HashMap<>();
//
//        String[] styleArr = styleStr.split(";");
//        for (String style : styleArr) {
//            String[] singleStyleArr = style.split(":");
//            if (singleStyleArr.length == 2) {
//                String key = singleStyleArr[0].trim();
//                String value = singleStyleArr[1].trim();
//                value = value.replace("px", "");
//
//                // bug :font-family: 微软雅黑, 'Microsoft YaHei'
//                if (key.equals("font-family") && StringUtils.isNoneBlank(value)) {
//                    value = value.replace("\"", "")
//                            .replace("'", "");
//                    value = value.split(",")[0].trim();
//                }
//
//                styleMap.put(key, value);
//            }
//        }
        return styleMap;
    }

    /**
     * 处理某些html标签
     * @param html
     * @return
     */
    private static String[] newLine(String html) {
        return html.split("<br\\s{0,}/{0,1}>"); // 空格标签换成换行
    }

    /**
     * 去掉html标签，实体转标签
     * @param html
     * @return
     */
    private static String htmlConverter(String html) {
        return html.replaceAll("<.*?>(.*?)</.*?>", "$1")
                .replace("&amp;", "&")
                .replace("&quot;", "\"")
                .replace("&lt;", "<")
                .replaceAll("&gt;", ">");
    }
}
