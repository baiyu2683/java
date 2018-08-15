package com.zh.poi.word.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import javax.xml.stream.XMLStreamReader;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.values.XmlObjectBase;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.w3c.dom.Node;

/**
 * poi设置word页眉
 *
 * @author zh
 * 2018年8月15日
 */
public class PoiWordHeader {
    
    public static void main(String[] args) throws IOException, XmlException {
        XWPFDocument document = new XWPFDocument();
        
        int margin_left = WordUtil.ONE_UNIT * 2;
        int margin_right = margin_left;
        int margin_top = Math.round(WordUtil.ONE_UNIT * 2.5f);
        int margin_bottom = margin_top;
        
        setDocumentMargin(document, margin_top, margin_right, margin_bottom, margin_left);
        
        createDefaultHeader(document, "页眉");
        
        document.write(new FileOutputStream("d:/poi.docx"));
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
    public static void setDocumentMargin(XWPFDocument document, 
            int left, int top, int right, int bottom) {
      CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
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
    public static void createDefaultHeader(final XWPFDocument docx, final String text) throws IOException, XmlException{
        CTP ctp = CTP.Factory.newInstance();
        
        XWPFParagraph left = new XWPFParagraph(ctp, docx);
        XWPFRun left_run = left.createRun();
        left_run.setText("左边\t\t居中\t\t右边");
        
        CTPPr begin = ctp.addNewPPr();
        begin.addNewPStyle().setVal("header");
        CTBorder border = begin.addNewPBdr().addNewBottom();
        border.setVal(STBorder.SINGLE);
        border.setSz(BigInteger.valueOf(4));
        border.setSpace(BigInteger.valueOf(1));
        border.setColor("FF0000");
        
        
        ctp.addNewR().addNewT().setStringValue(text);
        ctp.addNewR().addNewT().setSpace(SpaceAttribute.Space.PRESERVE);
        CTSectPr sectPr = docx.getDocument().getBody().isSetSectPr() ? docx.getDocument().getBody().getSectPr() : docx.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(docx, sectPr);
        XWPFHeader header = policy.createHeader(STHdrFtr.DEFAULT, new XWPFParagraph[] {left});
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
