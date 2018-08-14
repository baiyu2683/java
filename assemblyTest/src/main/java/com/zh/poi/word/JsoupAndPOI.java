package com.zh.poi.word;

import com.lowagie.text.DocumentException;
import com.zh.poi.util.ColorUtils;
import com.zh.poi.util.UnitUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
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
    
    /** 页脚样式 */
    public static final String STYLE_FOOTER = "footer";
    /** 页眉样式 */
    public static final String STYLE_HEADER = "header";
    /** 语言，简体中文 */
    public static final String LANG_ZH_CN = "zh-CN";

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
    
    private static final String NEW_LINE = "\r";

    public static void main(String[] args) throws IOException, com.lowagie.text.DocumentException, XmlException {
        // 读取html文件
        File htmlFile = new File(htmlPath + "/html.html");
        if (!htmlFile.exists()) {
            System.out.println("文件不存在: " + htmlFile.getPath());
            return;
        }

        XWPFDocument doc = new XWPFDocument();
        
        // 设置页边距, 这个设置了页眉和页脚才有位置放
        setDocumentMargin(doc, (int)(WordUtil.ONE_UNIT*2.5)+"", (WordUtil.ONE_UNIT*2)+"", (int)(WordUtil.ONE_UNIT*2.5)+"", (WordUtil.ONE_UNIT*2)+"");
        // 设置页眉、页脚
        createDefaultHeader(doc, "页眉。。");
        createDefaultFooter(doc);
        
        org.jsoup.nodes.Document document = Jsoup.parse(htmlFile, "utf-8");
        Elements allElements = document.getAllElements();
        for (Element element : allElements) {
            if ("body".equals(element.tagName())) {
                List<Node> nodes = element.childNodes();
                recursionSubNodes(nodes, doc);
            }
        }
        
        doc.write(new FileOutputStream(rootPath + "/poi.docx", false));
        System.out.println(rootPath);
        System.out.println("finished...");
    }
    
    /**
     * 设置页边距 (word中1厘米约等于567) 
     * @param document
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public static void setDocumentMargin(XWPFDocument document, String left,String top, String right, String bottom) {  
      CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
      CTPageMar ctpagemar = sectPr.addNewPgMar();
      if (StringUtils.isNotBlank(left)) {  
        ctpagemar.setLeft(new BigInteger(left));  
      }  
      if (StringUtils.isNotBlank(top)) {  
        ctpagemar.setTop(new BigInteger(top));  
      }  
      if (StringUtils.isNotBlank(right)) {  
        ctpagemar.setRight(new BigInteger(right));  
      }  
      if (StringUtils.isNotBlank(bottom)) {  
        ctpagemar.setBottom(new BigInteger(bottom));  
      }  
    } 
    
    /**
     * 创建默认页眉
     *
     * @param docx XWPFDocument文档对象
     * @param text 页眉文本
     * @return 返回文档帮助类对象，可用于方法链调用
     * @throws XmlException XML异常
     * @throws IOException IO异常
     * @throws InvalidFormatException 非法格式异常
     * @throws FileNotFoundException 找不到文件异常
     */
    public static void createDefaultHeader(final XWPFDocument docx, final String text) throws IOException, XmlException{
        CTP ctp = CTP.Factory.newInstance();
        XWPFParagraph paragraph = new XWPFParagraph(ctp, docx);
        ctp.addNewR().addNewT().setStringValue(text);
        ctp.addNewR().addNewT().setSpace(SpaceAttribute.Space.PRESERVE);
        CTSectPr sectPr = docx.getDocument().getBody().isSetSectPr() ? docx.getDocument().getBody().getSectPr() : docx.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(docx, sectPr);
        XWPFHeader header = policy.createHeader(STHdrFtr.DEFAULT, new XWPFParagraph[] { paragraph });
        header.setXWPFDocument(docx);
    }
    
    /**
     * 创建默认的页脚(该页脚主要只居中显示页码)
     * 
     * @param docx
     *            XWPFDocument文档对象
     * @return 返回文档帮助类对象，可用于方法链调用
     * @throws XmlException
     *             XML异常
     * @throws IOException
     *             IO异常
     */
    public static void createDefaultFooter(final XWPFDocument docx) throws IOException, XmlException {
        // TODO 设置页码起始值
        CTP pageNo = CTP.Factory.newInstance();
        XWPFParagraph footer = new XWPFParagraph(pageNo, docx);
        CTPPr begin = pageNo.addNewPPr();
        begin.addNewPStyle().setVal(STYLE_FOOTER);
        begin.addNewJc().setVal(STJc.CENTER);
        pageNo.addNewR().addNewFldChar().setFldCharType(STFldCharType.BEGIN);
        pageNo.addNewR().addNewInstrText().setStringValue("PAGE   \\* MERGEFORMAT");
        pageNo.addNewR().addNewFldChar().setFldCharType(STFldCharType.SEPARATE);
        CTR end = pageNo.addNewR();
        CTRPr endRPr = end.addNewRPr();
        endRPr.addNewNoProof();
        endRPr.addNewLang().setVal(LANG_ZH_CN);
        end.addNewFldChar().setFldCharType(STFldCharType.END);
        CTSectPr sectPr = docx.getDocument().getBody().isSetSectPr() ? docx.getDocument().getBody().getSectPr() : docx.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(docx, sectPr);
        policy.createFooter(STHdrFtr.DEFAULT, new XWPFParagraph[] { footer });
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
            if (node instanceof Element) {
                handleNode((Element) node, wordDocument);
            }
        }
    }

    private static void handleNode(Node node, XWPFDocument wordDocument) throws DocumentException {
        if (node instanceof Element) {
            Element ele = (Element) node;
            Map<String, Object> styleSheet = getStyleSheet(ele);
            if ("p".equals(ele.tagName())) {
                XWPFParagraph paragraph = wordDocument.createParagraph();
                addStyle(paragraph, styleSheet);
                List<Node> eleChildNodes = ele.childNodes();
                for (Node eleChildNode : eleChildNodes) {
                    if (eleChildNode instanceof TextNode) {
                        XWPFRun run = paragraph.createRun();
                        addStyle(run, styleSheet);
                        run.setText(((TextNode)eleChildNode).text());
                    } if (eleChildNode instanceof Element) {
                        Element eleChildElement = (Element) eleChildNode;
                        addContentToParagraph(eleChildElement, styleSheet, paragraph);
                    }
                }
            } else if ("div".equals(ele.tagName())) {
                // body下首层的div不创建段落
                Element parentEle = ele.parent();
                if (parentEle == null || "body".equals(parentEle.tagName())) {
                    recursionSubNodes(ele.childNodes(), wordDocument);
                } else {
                    XWPFParagraph paragraph = wordDocument.createParagraph();
                    addStyle(paragraph, styleSheet);
                    addContentToParagraph(ele, styleSheet, paragraph);
                }
            }
        }
    }

    /**
     * 将当前标签内容加入一个段落
     * br标签实际上当做内容处理就可以了
     * @param element
     * @param styleSheet
     * @param paragraph
     */
    private static void addContentToParagraph(Element element, Map<String, Object> styleSheet,
            XWPFParagraph paragraph) {
        Map<String, Object> subStyleSheet = getStyleSheet(element);
        subStyleSheet = mergeMap(styleSheet, subStyleSheet);
        if ("br".equals(element.tagName())) {
            XWPFRun br = paragraph.createRun();
            br.setText(NEW_LINE);
        } else if ("hr".equals(element.tagName())) {
            // TODO 分割线
        } else if ("img".equals(element.tagName())) {
            XWPFRun imgRun = paragraph.createRun();
            addStyle(imgRun, subStyleSheet);
            int width = 200;
            int height = 200;
            if (subStyleSheet.containsKey("height")) {
                height = UnitUtils.pxString2Number(String.valueOf(subStyleSheet.get("height")));
            }
            if (subStyleSheet.containsKey("width")) {
                width = UnitUtils.pxString2Number(String.valueOf(subStyleSheet.get("width")));
            }
            String src = element.attr("src");
            try {
                URL url = new URL(src);
                URLConnection urlconn = url.openConnection();
                urlconn.connect();
                InputStream imgStream = urlconn.getInputStream();
                imgRun.addPicture(imgStream, org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_PNG, UUID.randomUUID().toString(), Units.toEMU(UnitUtils.px2Point(width)), Units.toEMU(UnitUtils.px2Point(height)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            List<Node> subNodes = element.childNodes();
            for (Node subNode : subNodes) {
                if (subNode instanceof Element) {
                    Element subElement = (Element) subNode;
                    Map<String, Object> subElementStyleSheet = getStyleSheet(subElement);
                    Map<String, Object> mergeStyleSheet = mergeMap(subStyleSheet, subElementStyleSheet);
                    addContentToParagraph(subElement, mergeStyleSheet, paragraph);
                } else if (subNode instanceof TextNode) {
                    XWPFRun subRun = paragraph.createRun();
                    subRun.setText(((TextNode) subNode).text());
                    addStyle(subRun, subStyleSheet);
                }
            }
        }
    }

    /**
     * 给组件增加样式
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
                    if ("left".equals(align) || "start".equals(align)) {
                       paragraph.setAlignment(ParagraphAlignment.LEFT);
                    } else if ("right".equals(align) || "end".equals(align)) {
                        paragraph.setAlignment(ParagraphAlignment.RIGHT);
                    } else if ("center".equals(align)) {
                        paragraph.setAlignment(ParagraphAlignment.CENTER);
                    } else if ("justify".equals(align)) {
                        paragraph.setAlignment(ParagraphAlignment.BOTH);
                    }
                }
                //行距
                if (styleSheet.containsKey("line-height")) {
                    paragraph.setSpacingLineRule(LineSpacingRule.AT_LEAST);
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
                // TODO 缩进的单位
                if (StringUtils.isNotBlank(marginLeft)) {
                    paragraph.setIndentationLeft(UnitUtils.pxString2Number(marginLeft) * 10);
                }
                if (StringUtils.isNotBlank(marginRight)) {
                    paragraph.setIndentationRight(UnitUtils.pxString2Number(marginRight) * 10);
                }
                if (StringUtils.isNotBlank(marginTop)) {
                    paragraph.setSpacingBefore(UnitUtils.pxString2Number(marginTop) * 10);
                }
                if (StringUtils.isNotBlank(marginBottom)) {
                    paragraph.setSpacingAfter(UnitUtils.pxString2Number(marginBottom) * 10);
                }
                // TODO 段落背景色(poi无法设置背景色)
                if (styleSheet.containsKey("background-color")) {
                }
            } else if (sdt instanceof XWPFRun) {
                XWPFRun run = (XWPFRun) sdt;
                if (styleSheet.containsKey("font-family")) {
                    String fontFamily = (String)styleSheet.get("font-family");
                    run.setFontFamily(fontFamily);
                } else {
                    run.setFontFamily("微软雅黑");
                }
                if (styleSheet.containsKey("font-size")) {
                    String fontSize = (String) styleSheet.get("font-size");
                    if (StringUtils.isNoneBlank(fontSize)) {
                        fontSize = fontSize.replace("px", "")
                                            .replace("pt", "");
                        Double fontSizeDouble = Double.valueOf(fontSize);
                        if (fontSize.endsWith("px")) {
                            run.setFontSize((int) Math.round(fontSizeDouble * 0.75f));
                        } else {
                            run.setFontSize((int) Math.round(fontSizeDouble));
                        }
                    }
                }
                if (styleSheet.containsKey("color")) {
                    Color color = (Color) styleSheet.get("color");
                    run.setColor(ColorUtils.color2String(color).replace("#", ""));
                }
                if (styleSheet.containsKey("font-weight")) {
                    String weight = (String) styleSheet.get("font-weight");
                    if ("bold".equals(weight) || "bolder".equals(weight))
                        run.setBold(true);
                }
                if (styleSheet.containsKey("font-style")) {
                    String italic = (String) styleSheet.get("font-style");
                    if ("italic".equals(italic))
                        run.setItalic(true);
                }
                if (styleSheet.containsKey("text-decoration")) {
                    String textDecoration = (String) styleSheet.get("text-decoration");
                    if ("underline".equals(textDecoration))
                        run.setUnderline(UnderlinePatterns.SINGLE);
                }
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
            styleSheet.put("text-align", align);
        }
        if ("font".equals(ele.tagName())) {
            Attributes attrs = ele.attributes();
            for (Attribute attribute : attrs) {
                if ("face".equals(attribute.getKey())) {
                    String face = attribute.getValue();
                    if (StringUtils.isNotBlank(face)) {
                        String[] subFace = face.split(",");
                        styleSheet.put("font-family", subFace[0].trim());
                    }
                } else if ("size".equals(attribute.getKey())) {
                    String size = attribute.getValue();
                    if (StringUtils.isNotBlank(size)) {
                        styleSheet.put("font-size", fontTagSizeMap.get(attribute.getValue()));
                    }
                } else if ("color".equals(attribute.getKey())) {
                    String value = attribute.getValue();
                    if (StringUtils.isNotBlank(value)) {
                        value = value.toLowerCase();
                        if (value.startsWith("#")) {
                            Color color = new Color(Integer.parseInt(value.replace("#", ""), 16));
                            styleSheet.put("color", color);
                        } else if (value.startsWith("rgb") || value.startsWith("rgba")) {
                            Color color = ColorUtils.rgbStr2Color(value);
                            styleSheet.put("color", color);
                        } else {
                            // TODO colorname名字需要映射表映射
                        }
                    }
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
     * style属性转换为key—value样式表
     * @param styleStr
     * @return
     */
    private static Map<String, Object> styleAttr2StyleSheet(String styleStr) {
        if (StringUtils.isBlank(styleStr)) return null;
        Map<String, Object> styleMap = new HashMap<String, Object>();
        String[] styleArr = styleStr.split(";");
        for (String style : styleArr) {
            String[] singleStyleArr = style.split(":");
            if (singleStyleArr.length == 2) {
                String key = singleStyleArr[0].trim();
                String value = singleStyleArr[1].trim();
                // bug: font-family: '微软雅黑', 'Microsoft YaHei'
                if (key.equals("font-family") && StringUtils.isNoneBlank(value)) {
                    value = value.replace("\"", "")
                            .replace("'", "");
                    value = value.split(",")[0].trim();
                }
                // 颜色和背景色,需要特殊处理
                if (key.equals("color") || key.equals("background-color")) {
                    Color color = ColorUtils.rgbStr2Color(value);
                    styleMap.put(key, color);
                } else {
                    styleMap.put(key, value);
                }
            }
        }
        return styleMap;
    }

    /**
     * 合并两个map
     * 两个的同名属性取第二个的值
     * @param map1
     * @param map2
     * @return
     */
    private static Map<String, Object> mergeMap(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.putAll(map1);
        result.putAll(map2);
        return result;
    }

}