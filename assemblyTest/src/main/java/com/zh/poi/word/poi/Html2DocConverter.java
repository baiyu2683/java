package com.zh.poi.word.poi;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import com.zh.poi.util.ColorUtil;
import com.zh.poi.util.DateTimeUtil;
import com.zh.poi.util.HtmlUtil;
import com.zh.poi.util.UnitUtils;
import com.zh.poi.word.poi.Paper.Orientation;

/**
 * html转word
 * 
 * @author zh
 * 2018年8月15日
 */
public class Html2DocConverter {
    

    private ReportInfo reportInfo;
    
    /**
     * font标签size属性映射像素
     */
    private static final Map<String, String> fontTagSizeMap = new HashMap<String, String>();
    /**
     * 颜色名和rgb值映射关系
     */
    private static final Properties colorName = new Properties();
    static {
        fontTagSizeMap.put("1", "12");
        fontTagSizeMap.put("2", "14");
        fontTagSizeMap.put("3", "16");
        fontTagSizeMap.put("4", "18");
        fontTagSizeMap.put("5", "24");
        fontTagSizeMap.put("6", "32");
        fontTagSizeMap.put("7", "48");
        try {
            InputStream is = Html2DocConverter.class.getResourceAsStream("colorname.properties");
            colorName.load(is);
        } catch (IOException e) {
        }
    }
    
    /** 页脚样式 */
    private static final String STYLE_FOOTER = "footer";
    /** 页眉样式 */
    private static final String STYLE_HEADER = "header";
    /** 语言，简体中文 */
    private static final String LANG_ZH_CN = "zh-CN";
    
    public Html2DocConverter(ReportInfo reportInfo) {
        this.reportInfo = reportInfo;
    }
    
    
    /**
     * 读取html文件到word
     * 
     * @return
     * @throws Exception
     */
    public ByteArrayOutputStream getWord() throws Exception {
        InputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            
            XWPFDocument doc = new XWPFDocument();
            // 设置空样式，防止poi重新打开生成的文件时报错
            doc.createStyles();
            
            // 设置纸张
            setPageProp(doc);
            // 设置页眉、页脚
            String date = DateTimeUtil.format(reportInfo.getTimeStamp(), "yyyy年MM月dd日");
            createDefaultHeader(doc, reportInfo.getAlias(), "发布人：" + reportInfo.getOwner(), "发布日期："+date);
            // 解析正文
            String html = HtmlUtil.perfectHtml(reportInfo.getContent());
            Document document = Jsoup.parse(html);
            System.out.println(document.toString());
            Elements allElements = document.getAllElements();
            for (Element element : allElements) {
                String elementName  = element.tagName();
                // 仅处理body中的内容
                if ("body".equals(elementName)) {
                    List<Node> nodes = element.childNodes();
                    recursionSubNodes(nodes, doc);
                    break;
                }
            }
            
            doc.write(baos);
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("html转word失败!");
        } finally {
            if (is != null)
                is.close();
        }
    }

    /**
     * 创建默认页眉，布局为: 左-中-右
     * 页眉默认字体9号，宋体，office中字体大小为9的中文默认一字符0.32cm
     * @param docx
     * @param leftText
     * @param centerText
     * @param rightText
     * @throws IOException
     * @throws XmlException
     */
    private void createDefaultHeader(final XWPFDocument docx,
            final String leftText, final String centerText, final String rightText) throws IOException, XmlException{
        CTSectPr sp = docx.getDocument().getBody().getSectPr();
        
        CTPageSz pageSz = sp.getPgSz();
        BigInteger width = pageSz.getW();
        
        CTPageMar pm = sp.getPgMar();
        BigInteger contentWidth = width.subtract(pm.getLeft()).subtract(pm.getRight());

        // 第一个tab设置到中间页眉的左侧位置: (总宽度  / 2) - (中间页眉宽度  / 2)
        BigInteger leftTabPos = contentWidth.divide(BigInteger.valueOf(2));
        // 第二个tab设置到右侧页眉左边: 总宽度 - 右侧页眉宽度
        BigInteger rightTabPos = contentWidth;
        
        CTP ctp = CTP.Factory.newInstance();
        ctp.addNewR().addNewT().setSpace(SpaceAttribute.Space.DEFAULT);
        CTPPr begin = ctp.addNewPPr();
        
        XWPFParagraph headParagraph = new XWPFParagraph(ctp, docx);
        XWPFRun headerLeftRun = headParagraph.createRun();
        headerLeftRun.setFontFamily("宋体");
        headerLeftRun.setFontSize(9);
        headerLeftRun.setText(leftText);
        headerLeftRun.addTab();
        
        CTTabs cttabs = begin.isSetTabs() ? 
                begin.getTabs() : 
                    begin.addNewTabs();
        
        CTTabStop leftTab = cttabs.addNewTab();
        leftTab.setPos(leftTabPos);
        leftTab.setVal(STTabJc.CENTER);
        
        XWPFRun headerCenterRun = headParagraph.createRun();
        headerCenterRun.setFontFamily("宋体");
        headerCenterRun.setFontSize(9);
        headerCenterRun.setText(centerText);
        headerCenterRun.addTab();
        
        CTTabStop rightTab = cttabs.addNewTab();
        rightTab.setPos(rightTabPos);
        rightTab.setVal(STTabJc.RIGHT);
        
        XWPFRun headerRightRun = headParagraph.createRun();
        headerRightRun.setFontFamily("宋体");
        headerRightRun.setFontSize(9);
        headerRightRun.setText(rightText);
        headerRightRun.addTab();
        
        begin.addNewPStyle().setVal(STYLE_HEADER);
        CTBorder border = begin.addNewPBdr().addNewBottom();
        border.setVal(STBorder.SINGLE);
        border.setSz(BigInteger.valueOf(4));
        border.setSpace(BigInteger.valueOf(1));
        
        CTSectPr sectPr = docx.getDocument().getBody().isSetSectPr() ? docx.getDocument().getBody().getSectPr() : docx.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(docx, sectPr);
        XWPFHeader header = policy.createHeader(STHdrFtr.DEFAULT, new XWPFParagraph[] {headParagraph});
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
    private static void createDefaultFooter(final XWPFDocument docx) throws IOException, XmlException {
        // 设置页码起始值
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
     * 设置纸张属性
     * 
     */
    private void setPageProp(XWPFDocument document) {
        Paper paper = reportInfo.getPaper();
        if (paper == null) return;
        
        boolean isSetSectPr = document.getDocument().getBody().isSetSectPr(); 
        CTSectPr sectPr = null;
        if (isSetSectPr) {
            sectPr = document.getDocument().getBody().getSectPr();
        } else {
            sectPr = document.getDocument().getBody().addNewSectPr();
        }
        BigInteger marginLeft = BigInteger.ZERO;
        if (paper.getMarginLeft() != null) {
            marginLeft = double2BigInteger(UnitUtils.px2Twip(paper.getMarginLeft()));
        }
        BigInteger marginTop = BigInteger.ZERO;
        if (paper.getMarginTop() != null) {
            marginTop = double2BigInteger(UnitUtils.px2Twip(paper.getMarginTop()));
        }
        BigInteger marginRight = BigInteger.ZERO;
        if (paper.getMarginRight() != null) {
            marginRight = double2BigInteger(UnitUtils.px2Twip(paper.getMarginRight()));
        }
        BigInteger marginBottom = BigInteger.ZERO;
        if (paper.getMarginBottom() != null) {
            marginBottom = double2BigInteger(UnitUtils.px2Twip(paper.getMarginBottom()));
        }
        
        // 纸张
        CTPageSz pageSz = sectPr.addNewPgSz();
        if (paper.getWidth() != null) {
            BigInteger width = double2BigInteger(UnitUtils.px2Twip(paper.getWidth()));
            pageSz.setW(width.add(marginLeft).add(marginRight));
        }
        if (paper.getHeight() != null) {
            BigInteger height = double2BigInteger(UnitUtils.px2Twip(paper.getHeight()));
            pageSz.setH(height.add(marginTop).add(marginBottom));
        }
        if (Orientation.landscape.equals(paper.getOrientation())) {
            pageSz.setOrient(STPageOrientation.PORTRAIT);
        }
        
        // 边距
        CTPageMar ctpagemar = sectPr.addNewPgMar();
        ctpagemar.setLeft(marginLeft);
        ctpagemar.setTop(marginTop);
        ctpagemar.setRight(marginRight);
        ctpagemar.setBottom(marginBottom);
    }
    
    /**
     * Root节点下的子节点处理，html、body
     * 这里仅处理body
     * @param nodes
     * @param wordDocument
     * @throws com.lowagie.text.DocumentException
     */
    private void recursionSubNodes(List<Node> nodes, XWPFDocument wordDocument) {
        for (Node node : nodes) {
            if (node instanceof Element) {
                handleNode((Element) node, wordDocument);
            } else if (node instanceof TextNode) {
                XWPFParagraph paragraph = wordDocument.createParagraph();
                XWPFRun subRun = paragraph.createRun();
                subRun.setText(((TextNode) node).text());
            }
        }
    }

    private void handleNode(Node node, XWPFDocument wordDocument) {
        if (node instanceof Element) {
            Element ele = (Element) node;
            Map<String, Object> styleSheet = getStyleSheet(ele);
            if ("div".equals(ele.tagName())) {
                // 例子中html最外层加了一个div的，因此这里body下首层的div不创建段落
                Element parentEle = ele.parent();
                if (parentEle == null || "body".equals(parentEle.tagName())) {
                    recursionSubNodes(ele.childNodes(), wordDocument);
                } else {
                    XWPFParagraph paragraph = wordDocument.createParagraph();
                    addStyle(paragraph, styleSheet);
                    addContentToParagraph(ele, styleSheet, paragraph);
                }
            } else if ("table".equals(ele.tagName())) {
                XWPFTable table = wordDocument.createTable();
                parseTable(ele, table, 0);
            } else {
                XWPFParagraph paragraph = wordDocument.createParagraph();
                addStyle(paragraph, styleSheet);
                addContentToParagraph(ele, styleSheet, paragraph);
            }
        }
    }

    /**
     * table标签生成
     * @param table
     */
    private void parseTable(Element tableEle, XWPFTable table, int rowCount) {
        Elements elements = tableEle.children();
        Map<String, Object> subStyleSheet = getStyleSheet(tableEle);
        addStyle(table, subStyleSheet);

        // 宽度为0,忽略
        if (table.getWidth() <= 0) return;

        int columnSize = 0;
        for (Element ele : elements) {
            String tagName = ele.tagName();
            switch (tagName) {
                case "tbody":
                    parseTable(ele, table, 0);
                    break;
                case "tr":
                    // 标记是第一行...
                    if (rowCount > 0) {
                        table.createRow();
                    } else {
                        //遇到首行时，计算表格列数
                        Elements cells = ele.children();
                        for (Element  cell : cells) {
                            String colspan = cell.attr("colspan");
                            if (StringUtils.isNotBlank(colspan)) {
                                columnSize += Integer.parseInt(colspan);
                            } else {
                                columnSize++;
                            }
                        }
                    }
                    rowCount++;
                    int size = table.getRows().size();
                    XWPFTableRow row = table.getRow(size - 1);
                    parseRow(ele, columnSize, row);
                    break;
            }
        }
    }

    /**
     * 单元格
     * @param tr
     * @param row
     */
    private void parseRow(Element tr, int columnSize, XWPFTableRow row) {
        XWPFTable table = row.getTable();
        // TODO 每个单元格宽度
//        BigInteger cellWidth = BigInteger.valueOf(table.getWidth()).divide(BigInteger.valueOf(columnSize));
        // 添加单元格
        Elements elements = tr.children();
        for (int ci = 0 ; ci < columnSize ; ci++) {
            XWPFTableCell cell = row.getCell(ci);
            if (cell == null) {
                cell = row.createCell();
            }
            // 可能出现columnSize大于td个数的情况
            if (ci < elements.size()) {
                Element td = elements.get(ci);
                cell.setText(td.text());
                Map<String, Object> tdStyle = getStyleSheet(td);
                addStyle(cell, tdStyle);
                // 在单元格中加入内容
                addContentToParagraph(td, tdStyle, cell.getParagraphs().get(0));
            } else {
                // TODO 默认样式
                addStyle(cell, null);
            }
        }
    }

    /**
     * 获取索引为index的table
     * @return
     */
    private XWPFTable getTable(XWPFDocument document) {
        XWPFTable table = document.createTable();
        setTableProp(table);
        return table;
    }

    /**
     * 设置表格属性
     * @param table
     */
    private void setTableProp(XWPFTable table) {

        XWPFDocument document = table.getBody().getXWPFDocument();

        CTTbl ttbl = table.getCTTbl();
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
        CTJc cTJc=tblPr.addNewJc();
        cTJc.setVal(STJc.CENTER);
        tblWidth.setW(BigInteger.valueOf(8000));
        tblWidth.setType(STTblWidth.DXA);
    }

    /**
     * 将当前标签内容加入一个段落
     * br标签实际上当做内容处理就可以了
     * @param element
     * @param styleSheet
     * @param paragraph
     */
    private void addContentToParagraph(Element element, Map<String, Object> styleSheet,
            XWPFParagraph paragraph) {
        if ("br".equals(element.tagName())) {
            XWPFRun br = paragraph.createRun();
            br.addBreak();
        } if ("img".equals(element.tagName())) {
            XWPFRun imgRun = paragraph.createRun();
            Map<String, Object> subElementStyleSheet = getStyleSheet(element);
            Map<String, Object> mergeStyleSheet = mergeMap(styleSheet, subElementStyleSheet);
            addStyle(imgRun, mergeStyleSheet);
            int width = 200;
            int height = 200;
            if (mergeStyleSheet.containsKey("height")) {
                String heightStr = String.valueOf(mergeStyleSheet.get("height"));
                if (!"auto".equals(heightStr)) {
                    height = UnitUtils.pxString2Number(heightStr);
                }
            }
            if (mergeStyleSheet.containsKey("width")) {
                String widthStr = String.valueOf(mergeStyleSheet.get("width"));
                if (!"auto".equals(widthStr)) {
                    width = UnitUtils.pxString2Number(String.valueOf(mergeStyleSheet.get("width")));
                }
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
        } else if ("hr".equals(element.tagName())){
            // TODO 只能在段落上下，中间实现不了。。
            CTP ctp = paragraph.getCTP();
            CTPPr ctpPr = ctp.addNewPPr();
            CTPBdr ctpBdr = ctpPr.addNewPBdr();
            CTBorder ctBorder = ctpBdr.addNewTop();
            ctBorder.setSz(BigInteger.valueOf(4));
            ctBorder.setVal(STBorder.BASIC_THIN_LINES);
            ctBorder.setColor("9A9A9A");
        } else {
            List<Node> subNodes = element.childNodes();
            for (Node subNode : subNodes) {
                if (subNode instanceof Element) {
                    Element subElement = (Element) subNode;
                    Map<String, Object> subElementStyleSheet = getStyleSheet(subElement);
                    Map<String, Object> mergeStyleSheet = mergeMap(styleSheet, subElementStyleSheet);
                    addContentToParagraph(subElement, mergeStyleSheet, paragraph);
                } else if (subNode instanceof TextNode) {
                    XWPFRun subRun = paragraph.createRun();
                    subRun.setText(((TextNode) subNode).text());
                    addStyle(subRun, styleSheet);
                }
            }
        }
    }

    /**
     * 给组件增加样式
     * @param sdt
     * @param styleSheet
     */
    private void addStyle(ISDTContents sdt, Map<String, Object> styleSheet) {
        if (styleSheet != null && styleSheet.size() > 0) {
            if (sdt instanceof XWPFParagraph) { // 段落
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
                // 缩进
                if (StringUtils.isNotBlank(marginLeft)) {
                    BigInteger indentationLeft = double2BigInteger(UnitUtils.px2Twip(UnitUtils.pxString2Number(marginLeft)));
                    paragraph.setIndentationLeft(indentationLeft.intValue());
                }
                if (StringUtils.isNotBlank(marginRight)) {
                    BigInteger indentationRight = double2BigInteger(UnitUtils.px2Twip(UnitUtils.pxString2Number(marginRight)));
                    paragraph.setIndentationRight(indentationRight.intValue());
                }
                if (StringUtils.isNotBlank(marginTop)) {
                    BigInteger indentationBefore = double2BigInteger(UnitUtils.px2Twip(UnitUtils.pxString2Number(marginTop)));
                    paragraph.setSpacingBefore(indentationBefore.intValue());
                }
                if (StringUtils.isNotBlank(marginBottom)) {
                    BigInteger indentationBottom = double2BigInteger(UnitUtils.px2Twip(UnitUtils.pxString2Number(marginBottom)));
                    paragraph.setSpacingAfter(indentationBottom.intValue());
                }
                // 设置段落背景色
                if (styleSheet.containsKey("background-color")) {
                    Color color = (Color) styleSheet.get("background-color");
                    CTP ctp = paragraph.getCTP();
                    CTR[] ctrArr = ctp.getRArray();
                    CTR ctr = null;
                    if (ctrArr != null && ctrArr.length > 0) {
                        ctr = ctrArr[0];
                    } else {
                        ctr = ctp.addNewR();
                    }
                    CTRPr rpr = ctr.isSetRPr() ? ctr.getRPr() : ctr.addNewRPr();
                    CTShd shd = rpr.isSetShd() ? rpr.getShd() : rpr.addNewShd();
                    shd.setFill(ColorUtil.color2String(color).replace("#", ""));
                }
            } else if (sdt instanceof XWPFRun) { // 内容
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
                        boolean isPx = fontSize.endsWith("px");
                        fontSize = fontSize.replace("px", "")
                                           .replace("pt", "");
                        Double fontSizeDouble = Double.valueOf(fontSize);
                        if (isPx) {
                            run.setFontSize((int) Math.round(fontSizeDouble * 0.75f));
                        } else {
                            run.setFontSize((int) Math.round(fontSizeDouble));
                        }
                    }
                }
                if (styleSheet.containsKey("color")) {
                    Color color = (Color) styleSheet.get("color");
                    run.setColor(ColorUtil.color2String(color).replace("#", ""));
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
                if (styleSheet.containsKey("background-color")) {
                    Color color = (Color) styleSheet.get("background-color");
                    CTR ctr = run.getCTR();
                    CTRPr rpr = ctr.isSetRPr() ? ctr.getRPr() : ctr.addNewRPr();
                    CTShd shd = rpr.isSetShd() ? rpr.getShd() : rpr.addNewShd();
                    shd.setFill(ColorUtil.color2String(color).replace("#", ""));
                }
            } else if (sdt instanceof XWPFTable) { // 表格
                XWPFTable table = (XWPFTable) sdt;

                if (styleSheet.containsKey("width")) {
                    Double width = Double.valueOf(String.valueOf(styleSheet.get("width")));
                    table.setWidth(double2BigInteger(UnitUtils.px2Twip(width)).intValue());
                }
            }
        }
    }

    /**
     * 给组件增加样式
     * @param body
     * @param styleSheet
     */
    private void addStyle(IBody body, Map<String, Object> styleSheet) {
        if (styleSheet != null && styleSheet.size() > 0) {
            if (body instanceof XWPFTableCell) {
                XWPFTableCell cell = (XWPFTableCell) body;
                CTTc cttc = cell.getCTTc();
                CTTcPr ctPr = cttc.addNewTcPr();

                // 设置单元格宽度
                CTTblWidth tcw = ctPr.getTcW();
                if (tcw == null) {
                    tcw = ctPr.addNewTcW();
                }
                // 设置宽度,必须有..
                String width = String.valueOf(styleSheet.get("width"));
                tcw.setW(double2BigInteger(UnitUtils.px2Twip(Double.valueOf(width))));
                tcw.setType(STTblWidth.DXA);

                // 设置边框
                CTTcBorders borders = ctPr.addNewTcBorders();
                CTBorder leftBorder = borders.addNewLeft();
                leftBorder.setColor("000000");
                leftBorder.setSz(BigInteger.valueOf(1));
                leftBorder.setVal(STBorder.BASIC_THIN_LINES);

                CTBorder topBorder = borders.addNewLeft();
                topBorder.setColor("000000");
                topBorder.setSz(BigInteger.valueOf(1));
                topBorder.setVal(STBorder.BASIC_THIN_LINES);

                CTBorder rightBorder = borders.addNewLeft();
                rightBorder.setColor("000000");
                rightBorder.setSz(BigInteger.valueOf(1));
                rightBorder.setVal(STBorder.BASIC_THIN_LINES);

                CTBorder bottomBorder = borders.addNewLeft();
                bottomBorder.setColor("000000");
                bottomBorder.setSz(BigInteger.valueOf(1));
                bottomBorder.setVal(STBorder.BASIC_THIN_LINES);
            }
        }
    }

    /**
     * 获得一个元素上的样式表
     * @param ele
     * @return
     */
    private Map<String, Object> getStyleSheet(Element ele) {
        Map<String, Object> styleSheet = new HashMap<String, Object>();
        String styleAttribute = ele.attr("style");
        if (StringUtils.isNotBlank(styleAttribute)) {
            styleSheet.putAll(styleAttr2StyleSheet(styleAttribute));
        }
        String align = ele.attr("align");
        if (StringUtils.isNotBlank(align)) {
            styleSheet.put("text-align", align);
        }
        String valign = ele.attr("valign");
        if (StringUtils.isNotBlank(valign)) {
            styleSheet.put("valign", align);
        }
        String width = ele.attr("width");
        if (StringUtils.isNotBlank(width)) {
            styleSheet.put("width", width);
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
                        value = value.trim().toLowerCase();
                        if (value.startsWith("#")) {
                            Color color = new Color(Integer.parseInt(value.replace("#", ""), 16));
                            styleSheet.put("color", color);
                        } else if (value.startsWith("rgb") || value.startsWith("rgba")) {
                            Color color = ColorUtil.rgbStr2Color(value);
                            styleSheet.put("color", color);
                        } else {
                            // colorname名字需要映射表映射
                            String rgbValue = colorName.getProperty(value);
                            styleSheet.put("color", ColorUtil.rgbStr2Color(rgbValue));
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
    private Map<String, Object> styleAttr2StyleSheet(String styleStr) {
        if (StringUtils.isBlank(styleStr)) return null;
        Map<String, Object> styleMap = new HashMap<String, Object>();
        String[] styleArr = styleStr.split(";");
        for (String style : styleArr) {
            String[] singleStyleArr = style.split(":");
            if (singleStyleArr.length == 2) {
                String key = singleStyleArr[0].trim();
                String value = singleStyleArr[1].trim();
                // bug :font-family: 微软雅黑, 'Microsoft YaHei'
                if (key.equals("font-family") && StringUtils.isNoneBlank(value)) {
                    value = value.replace("\"", "")
                            .replace("'", "");
                    value = value.split(",")[0].trim();
                }
                // 颜色和背景色,需要特殊处理
                if (key.equals("color") || key.equals("background-color")) {
                    Color color = ColorUtil.rgbStr2Color(value);
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
    private Map<String, Object> mergeMap(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.putAll(map1);
        result.putAll(map2);
        return result;
    }
    
    /**
     * double转BigInteger
     * @param num
     * @return
     */
    private BigInteger double2BigInteger(double num) {
        return BigInteger.valueOf(Math.round(num));
    }
    
    public static void main(String[] args) throws Exception {
        
//        InputStream is = Html2DocConverter.class.getResourceAsStream("/html/html.html");
//        String htmlContent = IOUtils.toString(is, "gbk");
        
        String htmlContent = IOUtils.toString(new FileInputStream("d:/RGB颜色对照表.htm"), "utf-8");
        
        // 单位: px
        Paper paper = new Paper();
        paper.setHeight(931);
        paper.setWidth(649);
        paper.setMarginLeft(72);
        paper.setMarginRight(72);
        paper.setMarginTop(100);
        paper.setMarginBottom(100);
        
        ReportInfo ri = new ReportInfo();
        ri.setAlias("alias");
        ri.setContent(htmlContent);
        ri.setFileName("文件名");
        ri.setOwner("所有人");
        ri.setPaper(paper);
        ri.setTimeStamp((new Date()).getTime());
        ri.setVersion("版本");
        
        Html2DocConverter converter = new Html2DocConverter(ri);
        ByteArrayOutputStream baos = converter.getWord();
        FileOutputStream fos = new FileOutputStream(new File("d:/test.docx"), false);
        fos.write(baos.toByteArray());
        fos.close();
        System.out.println("finished...");
    }
}
