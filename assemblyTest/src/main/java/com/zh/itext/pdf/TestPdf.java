package com.zh.itext.pdf;

import com.aspose.words.HorizontalAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.lowagie.text.pdf.PdfCell;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Administrator
 * @date  2019/06/06
 */
public class TestPdf {

    public static void main(String[] args) throws IOException, DocumentException {
        FontFactoryImp fp = new XMLWorkerFontProvider();
        fp.register("E:\\apache-tomcat-6.0.45\\webapps\\HappyServer\\WEB-INF\\classes\\font\\fontResource");
        FontFactory.setFontImp(fp);

        Document document = new Document();
        PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(new File("d:/test1.pdf")));
        pdfWriter.setPageEvent(new PageEvent("智能报告", "发布人: 测试用户", "发布时间: 2019年06月10日"));
        Rectangle pageSize = new Rectangle(595, 842);
        document.setPageSize(pageSize);

        document.open();

        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table.setTotalWidth(new float[]{200});
        table.getDefaultCell().setPadding(1);
        table.setLockedWidth(true);
        table.setSplitLate(false);
        table.setSplitRows(false);

        // 第一行
        PdfPCell cell11 = new PdfPCell();
        cell11.setMinimumHeight(300);
        cell11.setRowspan(1);
        cell11.setBorderWidth(1);
        String html =
                "<p>\n" +
                        "    <strong>1</strong>\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    <em>2</em>\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    <span style=\"text-decoration:underline;\">23</span>\n" +
                        "</p>\n" +
                        "<hr/>\n" +
                        "<p style=\"text-align: center;\">\n" +
                        "    34\n" +
                        "</p>\n" +
                        "<p style=\"text-align: right;\">\n" +
                        "    <sup><em><span style=\"text-decoration:underline;\"><span style=\"text-decoration:line-through;\"><span style=\"font-size:32px\">亚欧非美南大4</span></span></span></em></sup>\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    5\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    6\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    <span style=\"color:#ff0000\">67</span>\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    <span style=\"color:#ff0000\"><br/></span>\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    <span style=\"color:#ff0000\"><sup>上标</sup></span>\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    <span style=\"font-family:隶书, SimLi;color:#ffff00;font-size:32px\">阿斯蒂芬<span style=\"font-family: 宋体, SimSun;\"><span style=\"font-family: 隶书, SimLi;\"><span style=\"font-family: sans-serif;\"></span></span></span></span>\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    <span style=\"color:#ff0000\"><sub>下标</sub></span>\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    <span style=\"color:#ff0000\"><br/></span>\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    <span style=\"color:#ff0000\"><span><span style=\"background-color: rgb(255, 255, 0);\">345q</span></span><br/></span>\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    <a href=\"http://www.baidu.com\" target=\"_blank\" title=\"百度\"><strong><em><span style=\"text-decoration:line-through;\"><span style=\"font-size:24px;color:#ffc000;background-color: rgb(0, 176, 80);font-family:楷体, 楷体_GB2312, SimKai\">http://www.baidu.com</span></span></em></strong></a><br/>\n" +
                        "</p>\n" +
                        "<p>\n" +
                        "    <br/>\n" +
                        "</p>\n" +
                        "<ol>\n" +
                        "    <li>\n" +
                        "        <p>\n" +
                        "            <strong><em><span style=\"text-decoration:underline;\"><span style=\"text-decoration:line-through;\"><span style=\"color:#ffff00;background-color: rgb(255, 0, 0);\">列表项1</span></span></span></em></strong>\n" +
                        "        </p>\n" +
                        "    </li>\n" +
                        "    <li>\n" +
                        "        <p>\n" +
                        "            列表项2\n" +
                        "        </p>\n" +
                        "    </li>\n" +
                        "</ol>\n" +
                        "<div>\n" +
                        "    <ul>\n" +
                        "        <li>\n" +
                        "            <strong><em><span style=\"text-decoration:underline;\"><span style=\"text-decoration:line-through;\"><span style=\"color:#0070c0;background-color: rgb(255, 255, 0);\">无序列表项1</span></span></span></em></strong><br/>\n" +
                        "        </li>\n" +
                        "        <li>\n" +
                        "            无序列表项2\n" +
                        "        </li>\n" +
                        "    </ul>\n" +
                        "</div>";
        System.out.println(html);
        ElementList elements = XMLWorkerHelper.parseToElementList(html, "");
        for (Element element : elements) {
            if (element instanceof Paragraph) {
                Paragraph paragraph = (Paragraph) element;
                List<Chunk> chunks = paragraph.getChunks();
                for (Chunk chunk : chunks) {
                    Font chunkFont = chunk.getFont();
                    String fontName = chunkFont.getFamilyname();
                    if (fontName.length() == 0 || "unknown".equalsIgnoreCase(fontName)) {
                        fontName = "宋体";
                        Font font = fp.getFont(fontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                        font.setStyle(chunkFont.getStyle());
                        font.setSize(chunkFont.getSize());
                        font.setColor(chunkFont.getColor());
                        chunk.setFont(font);
                    }
                }
            } else {
//                System.out.println(element.getClass().getSimpleName());
            }
            cell11.addElement(element);
        }
        table.addCell(cell11);
        table.completeRow();


        document.add(table);
        document.close();
        pdfWriter.flush();
    }

    private static PdfPCellEvent getCellBackGroundImageEvent() {
        return (cell, position, canvases) -> {
            System.out.println("=====================");
            System.out.println(position.getLeft() + "-" + position.getTop());
            try {
                File file = new File("d:/动态图片.gif");
                System.out.println(file.toURI().toURL());
                BufferedImage srcImage = ImageIO.read(file.toURI().toURL());

                int imageWidth = srcImage.getWidth();
                int imageHeight = srcImage.getHeight();

                BufferedImage destImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D graphics2D = destImage.createGraphics();
                graphics2D.setComposite(AlphaComposite.Clear);
                graphics2D.fillRect(0, 0, imageWidth, imageHeight);
                graphics2D.setComposite(AlphaComposite.Src);
                graphics2D.fillRect(0, 0, imageWidth, imageHeight);
                graphics2D.drawImage(srcImage,
                        0, 0, imageWidth / 2, imageHeight,
                        0, 0, imageWidth / 2, imageHeight,
                        null);
                destImage.flush();
                graphics2D.dispose();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(destImage, "png", outputStream);
                byte[] newImageData = outputStream.toByteArray();
                IOUtils.write(newImageData, new FileOutputStream("d:/test_result.png"));
                Image image = Image.getInstance(newImageData);
                image.setAbsolutePosition(cell.getLeft(), cell.getBottom());
                canvases[PdfPTable.BACKGROUNDCANVAS].addImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private static void longImage() throws IOException {
        BufferedImage srcImage = ImageIO.read(new URL("file:/d:/test1.png"));
        BufferedImage destImage = new BufferedImage(srcImage.getWidth(), 10000, srcImage.getType());
        Graphics2D graphics2D = destImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Clear);
        graphics2D.fillRect(0, 0, destImage.getWidth(), destImage.getHeight());
        graphics2D.setComposite(AlphaComposite.Src);

        int hCount = (destImage.getHeight() / srcImage.getHeight()) + 1;
        for (int h = 0 ; h < hCount ; h++) {
            int dx1 = 0;
            int dy1 = srcImage.getHeight() * h;
            int dx2 = srcImage.getWidth();
            int dy2 = dy1 + srcImage.getHeight();
            if (dy2 > destImage.getHeight()) {
                dy2 = destImage.getHeight();
            }
            graphics2D.drawImage(srcImage,
                    dx1, dy1, dx2, dy2,
                    0, 0, dx2 - dx1, dy2 - dy1,
                    null);
        }
        destImage.flush();
        graphics2D.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(destImage, "png", baos);
        IOUtils.write(baos.toByteArray(), new FileOutputStream("d://longpic.png"));
    }

}
