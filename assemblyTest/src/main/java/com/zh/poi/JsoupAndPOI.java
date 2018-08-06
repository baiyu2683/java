package com.zh.poi;

import com.lowagie.text.DocumentException;
import com.zh.ColorUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.List;

/**
 * Author: Administrator <br/>
 * Date: 2018-08-02 <br/>
 */
public class JsoupAndPOI {

    private static final String rootPath = JsoupAndPOI.class.getResource("/").getPath();

    private static final String htmlPath = rootPath + "/html";

    private static final String fontPath = rootPath + "/font";


    public static void main(String[] args) throws IOException, com.lowagie.text.DocumentException {

        // 读取html文件
        File htmlFile = new File(htmlPath + "/html.html");
        if (!htmlFile.exists()) {
            System.out.println("文件不存在: " + htmlFile.getPath());
            return;
        }

        Document document = Jsoup.parse(htmlFile, "utf-8");

        XWPFDocument doc = new XWPFDocument();

        Elements allElements = document.getAllElements();
        for (Element element : allElements) {
            String elementName  = element.tagName();
            if ("body".equals(elementName)) {
                Elements bodyElementList = element.children();
                recursionSubElement(bodyElementList, doc);
            }
        }
        doc.write(new FileOutputStream(rootPath + "/poi.doc", false));
    }

    /**
     * Root的子标签
     * @param elements
     * @param wordDocument
     * @throws com.lowagie.text.DocumentException
     */
    private static void recursionSubElement(List<Element> elements, XWPFDocument wordDocument) throws com.lowagie.text.DocumentException {
        for (Element ele : elements) {
            System.out.println("recursionSubElement Doc: " + ele.tagName());
            handler(ele, wordDocument);
        }
    }

    /**
     * @param ele dom4j元素
     * @param  ele itext中父元素
     */
    private static void handler(Element ele, XWPFDocument wordDocument) throws DocumentException {
        Map<String, Object> styleSheet = getStyleSheet(ele);
        Elements subElements = ele.children();
        if ("p".equals(ele.tagName())) {
            XWPFParagraph paragraph = wordDocument.createParagraph();
            addStyle(paragraph, styleSheet);
            for (Element subElement : subElements) {
                System.out.println(ele.tagName() + "->" + subElement.tagName());
                String ownText = subElement.html();
                if (ownText != null && ownText.length() > 0) {
                    String[] texts = newLine(ownText);
                    // <br/> 转换行
                    for (int i = 0 ; i < texts.length ; i++) {
                        String text = htmlConverter(texts[i]);
                        XWPFRun run = paragraph.createRun();
                        run.setText(text);
                        Map<String, Object> subStyleSheet = getStyleSheet(subElement);
                        // 合并此标签父标签和此标签的样式，同名取此标签的值
                        subStyleSheet = mergeMap(styleSheet, subStyleSheet);
                        addStyle(run, subStyleSheet);
                        if (i != texts.length - 1) { // 换行
                            XWPFRun new_line = paragraph.createRun();
                            new_line.setText("\r");
                        }
                    }
                }
            }
        } else if ("div".equals(ele.tagName())) {
            boolean recursion = false;
            for (Element subElement : subElements) {
                String tagName = subElement.tagName();
                if ("div".equals(tagName) || "p".equals(tagName) || "img".equals(tagName)) {
                    recursion = true;
                }
            }
            if (recursion) {
                recursionSubElement(subElements, wordDocument);
            } else {
                // div中直接有文字的情况
                XWPFParagraph paragraph = wordDocument.createParagraph();
                addStyle(paragraph, styleSheet);
                String ownText = ele.html();
                if (ownText != null && ownText.length() > 0) {
                    String[] texts = newLine(ownText);
                    for (int i = 0 ; i < texts.length ; i++) {
                        String text = htmlConverter(texts[i]);
                        XWPFRun run = paragraph.createRun();
                        run.setText(text);
                        // 字体样式需要设置在run上
                        addStyle(run, styleSheet);
                        if (i != texts.length - 1) {
                            XWPFRun new_line = paragraph.createRun();
                            new_line.setText("\r");
                        }
                    }
                }
            }
        } else if ("img".equals(ele.tagName())) {
            // div中直接有文字的情况
            XWPFParagraph paragraph = wordDocument.createParagraph();
            addStyle(paragraph, styleSheet);
            String text = ele.text();
            XWPFRun imgRun = paragraph.createRun();
            // TODO 获取高宽

            // TODO 下载并插入图片
            String src = ele.attr("src");
            try {
                URL url = new URL(src);
                URLConnection urlconn = url.openConnection();
                urlconn.connect();
                InputStream imgStream = urlconn.getInputStream();
                imgRun.addPicture(imgStream, org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_PNG, UUID.randomUUID().toString(),3500000, 3500000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得一个元素上的样式表
     * @param ele
     * @return
     */
    private static Map<String, Object> getStyleSheet(Element ele) {
        String styleAttribute = ele.attr("style");
        if (StringUtils.isBlank(styleAttribute)) return Collections.EMPTY_MAP;
        return styleAttr2StyleSheet(styleAttribute);
    }

    /**
     * 解析样式
     * @param sdt
     * @param styleSheet
     */
    private static void addStyle(ISDTContents sdt, Map<String, Object> styleSheet) {
        if (styleSheet != null && styleSheet.size() > 0) {
            if (sdt instanceof XWPFParagraph) {
                XWPFParagraph paragraph = (XWPFParagraph) sdt;
                // 对齐
                if (styleSheet.containsKey("text-align")) {
                    String textAlign = (String) styleSheet.get("text-align");
                    if ("left".equals(textAlign)) {
                       paragraph.setAlignment(ParagraphAlignment.LEFT);
                    } else if ("right".equals(textAlign)) {
                        paragraph.setAlignment(ParagraphAlignment.RIGHT);
                    } else if ("center".equals(textAlign)) {
                        paragraph.setAlignment(ParagraphAlignment.CENTER);
                    } else if ("justify".equals(textAlign)) {
                        paragraph.setAlignment(ParagraphAlignment.BOTH);
                    }
                }
                // TODO 段落背景色(无法实现)
            } else if (sdt instanceof XWPFRun) {
                XWPFRun run = (XWPFRun) sdt;
                run.setFontFamily("微软雅黑");
                if (styleSheet.containsKey("font-family")) {
                    run.setFontFamily((String) styleSheet.get("font-family"));
                }
                if (styleSheet.containsKey("font-size")) {
                    // 像素转itext字体大小
                    run.setFontSize(Math.round(Float.parseFloat((String) styleSheet.get("font-size")) * 0.75f));
                }
                // 字体颜色
                if (styleSheet.containsKey("color")) {
                    Color color = (Color) styleSheet.get("color");
                    run.setColor(ColorUtil.color2String(color).replace("#", ""));
                }
                // 粗体
                if (styleSheet.containsKey("font-weight")) {
                    String weight = (String) styleSheet.get("font-weight");
                    if ("bold".equals(weight) || "bolder".equals(weight))
                        run.setBold(true);
                }
                // TODO 其他字体样式
                // ...
            }
        }
    }

    /**
     * style属性转换为key—value样式表
     * @param styleStr
     * @return
     */
    private static Map<String, Object> styleAttr2StyleSheet(String styleStr) {
        if (StringUtils.isBlank(styleStr)) return null;

        Map<String, Object> styleMap = new HashMap<>();

        String[] styleArr = styleStr.split(";");
        for (String style : styleArr) {
            String[] singleStyleArr = style.split(":");
            if (singleStyleArr.length == 2) {
                String key = singleStyleArr[0].trim();
                String value = singleStyleArr[1].trim();
                value = value.replace("px", "");

                // bug :font-family: 微软雅黑, 'Microsoft YaHei'
                if (key.equals("font-family") && StringUtils.isNoneBlank(value)) {
                    value = value.replace("\"", "")
                            .replace("'", "");
                    value = value.split(",")[0].trim();
                }

                // 颜色和背景色,需要特殊处理
                if (key.equals("color") || key.equals("background-color")) {
                    Color color = str2Color(value);
                    styleMap.put(key, color);
                } else {
                    styleMap.put(key, value);
                }
            }
        }
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

    /**
     * 两个的同名属性取第二个的值
     * @param map1
     * @param map2
     * @return
     */
    private static Map<String, Object> mergeMap(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> result = new HashMap<>();
        result.putAll(map1);
        result.putAll(map2);
        return result;
    }

    private static Color str2Color(String color) {
        try {
            color = color.toLowerCase();
            if (color.startsWith("rgb") && !color.startsWith("rgba")) {
                color = color
                        .replace("rgb(", "")
                        .replace(")", "");
                // 34, 34, 34
                String[] rgb = color.split(", ");
                Color poiColor = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]),
                        Integer.parseInt(rgb[2]));
                return poiColor;
            } else if (color.startsWith("rgba")) {
                color = color
                        .replace("rgba(", "")
                        .replace(")", "");
                // 34, 34, 34
                String[] rgb = color.split(", ");
                Color poiColor = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]),
                        Integer.parseInt(rgb[2]));
                return poiColor;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
