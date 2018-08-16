package com.zh.poi.word.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabTlc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTPageSzImpl;

/**
 * poi设置word页眉
 *
 * @author zh
 * 2018年8月15日
 */
public class PoiWordHeader {
    
    public static void main(String[] args) throws IOException, XmlException {
        XWPFDocument document = new XWPFDocument();
        
        int margin_left = (int)(WordUtil.ONE_UNIT * 1.91);
        int margin_right = margin_left;
        int margin_top = (int)(WordUtil.ONE_UNIT * 2.54f);
        int margin_bottom = margin_top;
        
        setPageSz(document);
        
        setDocumentMargin(document, margin_left, margin_top, margin_right, margin_bottom);
        
        createDefaultHeader(document, "左边：123", "中间：456", "右边：789");
        
        document.write(new FileOutputStream("d:/poi.docx"));
        System.out.println("finished...");
    }
    
    public static void setPageSz(XWPFDocument document) {
        boolean isSetSectPr = document.getDocument().getBody().isSetSectPr(); 
        CTSectPr sectPr = null;
        if (isSetSectPr) {
            sectPr = document.getDocument().getBody().getSectPr();
        } else {
            sectPr = document.getDocument().getBody().addNewSectPr();
        }
        
        CTPageSz pageSz = sectPr.addNewPgSz();
        pageSz.setW(BigInteger.valueOf(1l));
        pageSz.setH(BigInteger.valueOf(2l));
        
        pageSz.setW(BigInteger.valueOf(11907));
        pageSz.setH(BigInteger.valueOf(16840));
//        pageSz.setOrient(STPageOrientation.PORTRAIT);
    }


    /**
     * 设置页边距 (word中1厘米约等于567) 
     * @param document
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public static void setDocumentMargin(XWPFDocument document,
            int left, int top, int right, int bottom) {
        boolean isSetSectPr = document.getDocument().getBody().isSetSectPr(); 
        CTSectPr sectPr = null;
        if (isSetSectPr) {
            sectPr = document.getDocument().getBody().getSectPr();
        } else {
            sectPr = document.getDocument().getBody().addNewSectPr();
        }
        CTPageMar ctpagemar = sectPr.addNewPgMar();
        ctpagemar.setLeft(BigInteger.valueOf(left));  
        ctpagemar.setTop(BigInteger.valueOf(top));
        ctpagemar.setRight(BigInteger.valueOf(right));
        ctpagemar.setBottom(BigInteger.valueOf(bottom));
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
    public static void createDefaultHeader(final XWPFDocument docx, 
            final String left, final String center, final String right) throws IOException, XmlException{
        CTSectPr sp = docx.getDocument().getBody().getSectPr();
        
        CTPageSz pageSz = sp.getPgSz();
        BigInteger height = pageSz.getH();
        BigInteger width = pageSz.getW();
        System.out.println(height.intValue());
        System.out.println(width.intValue());
        
        CTPageMar pm = sp.getPgMar();
        System.out.println(pm.getLeft().toString());
        System.out.println(pm.getRight().toString());
        
        CTP ctp = CTP.Factory.newInstance();
        CTPPr begin = ctp.addNewPPr();
        
        XWPFParagraph headParagraph = new XWPFParagraph(ctp, docx);
        XWPFRun headRun = headParagraph.createRun();
        headRun.setText(left);
        headRun.addTab();
        
        CTTabStop tab1 = begin.addNewTabs().addNewTab();
        tab1.setPos(BigInteger.valueOf(4810));
//        tab1.setLeader(STTabTlc.DOT);
        
        headRun.setText(center);
        headRun.addTab();
        
        CTTabStop tab2 = begin.addNewTabs().addNewTab();
        tab2.setPos(BigInteger.valueOf(9620));
//        tab2.setLeader(STTabTlc.DOT);
        
        headRun.setText(right);
//        headRun.addTab();
        
        begin.addNewPStyle().setVal("header");
        CTBorder border = begin.addNewPBdr().addNewBottom();
        border.setVal(STBorder.SINGLE);
        border.setSz(BigInteger.valueOf(4));
        border.setSpace(BigInteger.valueOf(1));
        border.setColor("FF0000");
        
//        ctp.addNewR().addNewT().setStringValue(text);
        ctp.addNewR().addNewT().setSpace(SpaceAttribute.Space.PRESERVE);
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
    public static void createDefaultFooter(final XWPFDocument docx) throws IOException, XmlException {
        CTP pageNo = CTP.Factory.newInstance();
        XWPFParagraph footer = new XWPFParagraph(pageNo, docx);
        CTPPr begin = pageNo.addNewPPr();
        begin.addNewPStyle().setVal(WordUtil.STYLE_FOOTER);
        begin.addNewJc().setVal(STJc.CENTER);
        pageNo.addNewR().addNewFldChar().setFldCharType(STFldCharType.BEGIN);
        pageNo.addNewR().addNewInstrText().setStringValue("PAGE   \\* MERGEFORMAT");
        pageNo.addNewR().addNewFldChar().setFldCharType(STFldCharType.SEPARATE);
        CTR end = pageNo.addNewR();
        CTRPr endRPr = end.addNewRPr();
        endRPr.addNewNoProof();
        endRPr.addNewLang().setVal(WordUtil.LANG_ZH_CN);
        end.addNewFldChar().setFldCharType(STFldCharType.END);
        CTSectPr sectPr = docx.getDocument().getBody().isSetSectPr() ? docx.getDocument().getBody().getSectPr() : docx.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(docx, sectPr);
        policy.createFooter(STHdrFtr.DEFAULT, new XWPFParagraph[] { footer });
    }
    
    
}
