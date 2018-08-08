package com.zh.poi;

import com.lowagie.text.DocumentException;
import com.zh.ColorUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
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

    /**
     * font标签size属性映射像素
     */
    private static final HashMap<String, String> fontTagSizeMap = new HashMap<String, String>();
    static {
        fontTagSizeMap.put("1", "10");
        fontTagSizeMap.put("2", "14");
        fontTagSizeMap.put("3", "16");
        fontTagSizeMap.put("4", "18");
        fontTagSizeMap.put("5", "24");
        fontTagSizeMap.put("6", "32");
        fontTagSizeMap.put("7", "48");
    }

    public static void main(String[] args) throws IOException, com.lowagie.text.DocumentException {
        // 读取html文件
        File htmlFile = new File(htmlPath + "/html.html");
        if (!htmlFile.exists()) {
            System.out.println("文件不存在: " + htmlFile.getPath());
            return;
        }

        XWPFDocument doc = new XWPFDocument();

        org.jsoup.nodes.Document document = Jsoup.parse(htmlFile, "utf-8");
        Elements allElements = document.getAllElements();
        for (Element element : allElements) {
            if ("body".equals(element.tagName())) {
                List<Node> nodes = element.childNodes();
                recursionSubNodes(nodes, doc);
            }
        }

        doc.write(new FileOutputStream(rootPath + "/poi.doc", false));
    }

    /**
     * Root节点下的子节点处理，html、body
     * 这里仅处理body
     * @param nodes
     * @param wordDocument
     * @throws com.lowagie.text.DocumentException
     */
    private static void recursionSubNodes(List<Node> nodes, XWPFDocument wordDocument) throws com.lowagie.text.DocumentException {
        for (Node node : nodes) {
            System.out.println("recursionSubElement Doc: " + node.getClass().getSimpleName());
            if (node instanceof Element) {
                handleNode((Element) node, wordDocument);
            }
        }
    }

    private static void handleNode(Node node, XWPFDocument wordDocument) throws DocumentException {
        if (node instanceof Element) {
            Element ele = (Element) node;
            List<Node> subNodes = ele.childNodes();
            Map<String, Object> styleSheet = getStyleSheet(ele);
            if ("p".equals(ele.tagName())) {
                XWPFParagraph paragraph = wordDocument.createParagraph();
                addStyle(paragraph, styleSheet);
                // p标签下面可能有span, i，b，u，strike并可能嵌套，样式需要逐层传递
                List<Node> eleChildNodes = ele.childNodes();
                for (Node eleChildNode : eleChildNodes) {
                    if (eleChildNode instanceof TextNode) {
                        XWPFRun run = paragraph.createRun();
                        String text = ((TextNode)eleChildNode).text();
                        run.setText(br2newLine(text));
                    } else if (eleChildNode instanceof Element) {
                        Map<String, Object> subStyleSheet = getStyleSheet((Element) eleChildNode);
                        Map<String, Object> mergeStyleSheet = mergeMap(styleSheet, subStyleSheet);
                        handlParagraph(((Element)eleChildNode).childNodes(), mergeStyleSheet, paragraph);
                    }
                }
            } else if ("div".equals(ele.tagName())) {
                boolean recursion = false;
                for (Node subNode : ele.childNodes()) {
                    if (subNode instanceof Element) {
                        Element subElement = (Element) subNode;
                        String tagName = subElement.tagName();
                        if ("div".equals(tagName) || "p".equals(tagName) || "img".equals(tagName)) {
                            recursion = true;
                            break;
                        }
                    }
                }
                XWPFParagraph paragraph = wordDocument.createParagraph();
                if (recursion) {
                    recursionSubNodes(ele.childNodes(), wordDocument);
                } else {
                    // div中直接有文字的情况
                    addStyle(paragraph, styleSheet);
                    String ownText = ele.html();
                    if (ownText != null && ownText.length() > 0) {
                        String texts = br2newLine(ownText);
                        String text = htmlConverter(texts);
                        XWPFRun run = paragraph.createRun();
                        run.setText(text);
                        // 字体样式需要设置在run上
                        addStyle(run, styleSheet);
                    }
                }
            } else if ("img".equals(ele.tagName())) {
                // div中直接有文字的情况
                XWPFParagraph paragraph = wordDocument.getLastParagraph();
                addStyle(paragraph, styleSheet);
                String text = ele.text();
                XWPFRun imgRun = paragraph.createRun();
                String src = ele.attr("src");
                try {
                    URL url = new URL(src);
                    URLConnection urlconn = url.openConnection();
                    urlconn.connect();
                    InputStream imgStream = urlconn.getInputStream();
                    imgRun.addPicture(imgStream, Document.PICTURE_TYPE_PNG, UUID.randomUUID().toString(), 3500000, 3500000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * p标签下可能有i、b、u、strike等，样式需要逐层加入
     * @param nodes
     * @param styleSheet
     */
    private static void handlParagraph(List<Node> nodes, Map<String, Object> styleSheet, XWPFParagraph paragraph) {
        for (Node node : nodes) {
            if (node instanceof Element) {
                Element element = (Element) node;
                if ("span".equals(element.tagName())) {
                    Map<String, Object> spanStyleSheet = getStyleSheet(element);
                    styleSheet = mergeMap(styleSheet, spanStyleSheet);
                    addToParagraph(element.childNodes(), styleSheet, paragraph);
                } else if ("font".equals(element.tagName())) {
                    // 字体样式处理
                    Map<String, Object> fontStyleMap = getStyleSheet(element);
                    Map<String, Object> mergeStyle = mergeMap(styleSheet, fontStyleMap);
                    addToParagraph(element.childNodes(), styleSheet, paragraph);
                } else if ("i".equals(element.tagName())) {
                    // font标签的斜体
                    styleSheet.put("font-italic", 1);
                    addToParagraph(element.childNodes(), styleSheet, paragraph);
                } else if ("b".equals(element.tagName())) {
                    // font标签的粗体
                    styleSheet.put("font-bold", 1);
                    addToParagraph(element.childNodes(), styleSheet, paragraph);
                } else if ("u".equals(element.tagName())) {
                    // font标签的下划线
                    styleSheet.put("font-underline", 1);
                    addToParagraph(element.childNodes(), styleSheet, paragraph);
                } else if ("strike".equals(element.tagName())) {
                    // font标签的删除线
                    styleSheet.put("font-strike", 1);
                    addToParagraph(element.childNodes(), styleSheet, paragraph);
                } else if ("br".equals(element.tagName())) {
                    XWPFRun br = paragraph.createRun();
                    br.setText("\r");
                }
            } else if (node instanceof TextNode) {
                // TODO 段落中的文本，直接加入段落
                TextNode textNode = (TextNode) node;
                XWPFRun textRun = paragraph.createRun();
                textRun.setText(textNode.text());
                addStyle(textRun, styleSheet);
            }
        }
    }

    /**
     * 将当前节点内容加入段落
     * @param strikeSubNodes
     * @param styleSheet
     * @param paragraph
     */
    private static void addToParagraph(List<Node> strikeSubNodes, Map<String, Object> styleSheet, XWPFParagraph paragraph) {
        for (Node strikeSubNode : strikeSubNodes) {
            if (strikeSubNode instanceof TextNode) {
                XWPFRun strikeSubRun = paragraph.createRun();
                strikeSubRun.setText(((TextNode) strikeSubNode).text());
                addStyle(strikeSubRun, styleSheet);
            } if (strikeSubNode instanceof Element) {
                Element strikeSubElement = (Element) strikeSubNode;
                handlParagraph(strikeSubElement.childNodes(), styleSheet, paragraph);
            }
        }
    }

    /**
     * 获得一个元素上的样式表
     * @param ele
     * @return
     */
    private static Map<String, Object> getStyleSheet(Element ele) {
        Map<String, Object> styleSheet = new HashMap<String, Object>();
        String styleAttribute = ele.attr("style");
        if (StringUtils.isNotBlank(styleAttribute)) {
            styleSheet.putAll(styleAttr2StyleSheet(styleAttribute));
        }
        String align = ele.attr("align");
        if (StringUtils.isNotBlank(align)) {
            styleSheet.put("align", align);
        }
        // TODO 所有其他样式属性，慢慢补充吧。。。
        // ........
        if ("font".equals(ele.tagName())) {
            Attributes attrs = ele.attributes();
            // 字体样式处理
            Map<String, Object> fontStyleMap = new HashMap<String, Object>();
            for (Attribute attribute : attrs) {
                if ("face".equals(attribute.getKey())) {
                    String face = attribute.getValue();
                    if (StringUtils.isNotBlank(face)) {
                        String[] subFace = face.split(",");
                        fontStyleMap.put("font-family", subFace[0].trim());
                    }
                } else if ("size".equals(attribute.getKey())) {
                    String size = attribute.getValue();
                    if (StringUtils.isNotBlank(size)) {
                        fontStyleMap.put("font-size", fontTagSizeMap.get(attribute.getValue()));
                    }
                } else if ("color".equals(attribute.getKey())) { // TODO color方式过多，暂忽略
                }
            }
        }
        if ("u".equals(ele.tagName())) {
            styleSheet.put("font-underline", 1);
        }
        if ("i".equals(ele.tagName())) {
            styleSheet.put("font-italic", 1);
        }
        if ("b".equals(ele.tagName())) {
            styleSheet.put("font-bold", 1);
        }
        if ("strike".equals(ele.tagName())) {
            styleSheet.put("font-strike", 1);
        }
        return styleSheet;
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
                if (styleSheet.containsKey("text-align") ||
                        styleSheet.containsKey("align")) {
                    String align = (String) styleSheet.get("text-align");
                    if (StringUtils.isBlank(align)) {
                        align = (String) styleSheet.get("align");
                    }
                    if ("left".equals(align)) {
                       paragraph.setAlignment(ParagraphAlignment.LEFT);
                    } else if ("right".equals(align)) {
                        paragraph.setAlignment(ParagraphAlignment.RIGHT);
                    } else if ("center".equals(align)) {
                        paragraph.setAlignment(ParagraphAlignment.CENTER);
                    } else if ("justify".equals(align)) {
                        paragraph.setAlignment(ParagraphAlignment.BOTH);
                    }
                }
                //外间距
                String marginLeft = "0";
                String marginRight = "0";
                String marginTop = "0";
                String marginBottom = "0";
                if (styleSheet.containsKey("margin")) {
                    String margin = (String) styleSheet.get("margin");
                    String[] margins = margin.split(" ");
                    if (margins.length < 2) {
                        marginLeft = margins[0];
                        marginRight = margins[0];
                        marginTop = margins[0];
                        marginBottom = margins[0];
                    } else if (margins.length < 3) {
                        marginLeft = margins[1];
                        marginRight = margins[1];
                        marginTop = margins[0];
                        marginBottom = margins[0];
                    } else if (margins.length < 4) {
                        marginRight = margins[2];
                        marginTop = margins[0];
                        marginBottom = margins[1];
                    } else {
                        marginLeft = margins[3];
                        marginRight = margins[2];
                        marginTop = margins[0];
                        marginBottom = margins[0];
                    }
                }

                //单位暂不清楚
                if (StringUtils.isNotBlank(marginLeft) && !"0".equals(marginLeft)) {
                    int left = Integer.parseInt(marginLeft);
                    if (left > 0) {
                        paragraph.setIndentationLeft(left * 5);
                    }
                }

                if (StringUtils.isNotBlank(marginRight) && !"0".equals(marginRight)) {
                    int right = Integer.parseInt(marginRight);
                    if (right > 0) {
                        paragraph.setIndentationRight(right * 5);
                    }
                }

                if (StringUtils.isNotBlank(marginTop) && !"0".equals(marginTop)) {
                    paragraph.setSpacingBefore(Integer.parseInt(marginTop) * 5);
                }

                if (StringUtils.isNotBlank(marginBottom) && !"0".equals(marginBottom)) {
                    paragraph.setSpacingAfter(Integer.parseInt(marginBottom) * 5);
                }

                // TODO 段落背景色(无法实现)
            } else if (sdt instanceof XWPFRun) {
                XWPFRun run = (XWPFRun) sdt;
                if (styleSheet.containsKey("font-family")) {
                    run.setFontFamily((String) styleSheet.get("font-family"));
                } else {
                    run.setFontFamily("微软雅黑");
                }
                if (styleSheet.containsKey("font-size")) {
                    // 像素转itext字体大小
                    String fontSize = (String) styleSheet.get("font-size");
                    if (fontSize.endsWith("px")) {
                        fontSize = fontSize.replace("px", "");
                        run.setFontSize(Math.round(Float.parseFloat(fontSize) * 0.75f));
                    } else if (fontSize.endsWith("pt")){
                        fontSize = fontSize.replace("pt", "");
                        run.setFontSize(Math.round(Float.parseFloat(fontSize)));
                    }

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
                // font 标签样式最后加
                if (styleSheet.containsKey("font-italic")) {
                    run.setItalic(true);
                }
                if (styleSheet.containsKey("font-bold")) {
                    run.setBold(true);
                }
                if (styleSheet.containsKey("font-underline")) {
                    run.setUnderline(UnderlinePatterns.SINGLE);
                }
                if (styleSheet.containsKey("font-strike")) {
                    run.setStrikeThrough(true);
                }
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
//                value = value.replace("px", "");

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
    private static String br2newLine(String html) {
        return html.replaceAll("<br\\s{0,}/{0,1}>", "\r"); // 空格标签换成换行
    }

    /**
     * 去掉html标签，实体转标签
     * @param html
     * @return
     */
    private static String htmlConverter(String html) {
        return html.replaceAll("<(.*?)>(.*?)</\1>", "$2")
                .replace("&amp;", "&")
                .replace("&quot;", "\"")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&nbsp;", " ");
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
