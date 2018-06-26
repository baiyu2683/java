package com.zh.poi;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * poi生成word中的表格
 * 要求可以设置每个单元格的字体，背景，对齐，边框。可以合并单元格
 *
 * office2007测试成功，如图07docx.png
 *
 * @see <a href="http://www.cnblogs.com/xlj227/p/5672881.html"></a>
 */
public class WordTest {
    private static void fillTable(XWPFTable table) {
        for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);
            row.setHeight(380);
            for (int colIndex = 0; colIndex < row.getTableCells().size(); colIndex++) {
                XWPFTableCell cell = row.getCell(colIndex);
                if (rowIndex % 2 == 0) {
                    setCellProp(cell, "D4DBED", 1000);
                } else {
                    setCellProp(cell, "AEDE72", 1000);
                }
            }
        }
    }

    private static void setCellProp(XWPFTableCell cell, String bgcolor, int width) {
        CTTc cttc = cell.getCTTc();
        CTTcPr cellPr = cttc.addNewTcPr();
        // 宽度设置
        cellPr.addNewTcW().setW(BigInteger.valueOf(width));
        CTTcPr ctPr = cttc.addNewTcPr();
        //背景设置
        CTShd ctshd = ctPr.addNewShd();
        ctshd.setFill(bgcolor);
        // 垂直对齐
        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        // 水平对齐
        cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
        // 边框
        CTTcBorders borders = ctPr.addNewTcBorders();
        // 左边框
        CTBorder leftBorder = borders.addNewLeft();
        leftBorder.setColor("FF8C00");
        leftBorder.setSz(new BigInteger("1"));
        leftBorder.setVal(STBorder.DECO_ARCH);
        // 右边框
        CTBorder rightBorder = borders.addNewRight();
        rightBorder.setColor("CD853F");
        rightBorder.setSz(new BigInteger("1"));
        rightBorder.setVal(STBorder.DASHED);
        // 上边框
        CTBorder topBorder = borders.addNewTop();
        topBorder.setColor("D2691E");
        topBorder.setSz(new BigInteger("1"));
        topBorder.setVal(STBorder.BASIC_THIN_LINES);
        // 下边框
        CTBorder bottomBorder = borders.addNewBottom();
        bottomBorder.setColor("800000");
        bottomBorder.setSz(new BigInteger("1"));
        bottomBorder.setVal(STBorder.STARS_BLACK);
    }

    private static void setCellTextProp(XWPFTableCell firstCell, String text) {
        //每个单元格只使用默认的段落，不增加其他段落
        CTP cellFirstCTP = firstCell.getCTTc().getPList().get(0);
        XWPFParagraph cellFirstParagraph = firstCell.getParagraph(cellFirstCTP);
        XWPFRun run1 = cellFirstParagraph.createRun();
        run1.setText(text);
        run1.setBold(true);
        run1.setFontSize(16);
        run1.setFontFamily("Consoles");
    }

    /**
     * @Description: 跨列合并
     */
    private static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
            setCellProp(cell, "FFFFFF", 2000);
        }
    }

    /**
     * @Description: 跨行合并
     * @quote  http://stackoverflow.com/questions/24907541/row-span-with-xwpftable
     */
    private static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if ( rowIndex == fromRow ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
            setCellProp(cell, "FFFFFF", 2000);
        }
    }

    private static void setTableWidth(XWPFTable table,String width){
        CTTbl ttbl = table.getCTTbl();
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
        CTJc cTJc=tblPr.addNewJc();
        cTJc.setVal(STJc.Enum.forString("center"));
        tblWidth.setW(new BigInteger(width));
        tblWidth.setType(STTblWidth.DXA);
    }

    private static void fillTable2(XWPFTable table) {
        for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);
            row.setHeight(380);
            for (int colIndex = 0; colIndex < row.getTableCells().size(); colIndex++) {
                XWPFTableCell cell = row.getCell(colIndex);
                cell.setText(" cell " + rowIndex + colIndex + " ");
                if (rowIndex % 2 == 0) {
                    setCellProp(cell, "D4DBED", 1800);
                } else {
                    setCellProp(cell, "AEDE72", 1800);
                }
            }
        }
    }

    private static void createTable(XWPFDocument doc, String str) {
        XWPFTable table = null;
        table = doc.createTable(1, 1);
        setTableWidth(table, "9000");
        XWPFTableRow row = null;
        row = table.getRow(0);
        row.setHeight(380);
        XWPFTableCell cell = row.getCell(0);
        setCellProp(cell, "CCCCCC", 9000);

    }

    public static void main(String[] args) throws IOException {
        XWPFDocument document = new XWPFDocument();

        // 标题
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setVerticalAlignment(TextAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText("这是整个表格的标题");
        run.setFontSize(20);
        run.setColor("2F4F4F");
        run.setFontFamily("微软雅黑");
        run.setBold(true);

        //翻页
//        XWPFParagraph nextPage = document.createParagraph();
//        nextPage.setPageBreak(true);

        int rowCount = 8;
        int colCount = 4;
        // 新建一个两行6列的表格
        XWPFTable table = document.createTable(rowCount, colCount);
        setTableWidth(table, "8000");

        // 首行作为表头，所有单元格合并
        XWPFTableRow firstRow = table.getRow(0);
        firstRow.setHeight(380);
        XWPFTableCell firstCell = firstRow.getCell(0);
        // 设置表头单元格内字体属性, 其他单元格只是内部属性设置不同, 照抄即可
        setCellTextProp(firstCell, "表头");
        // 合并第一行所有单元格
        mergeCellsHorizontal(table, 0, 0, colCount - 1);


        XWPFTableRow secondRow = table.getRow(1);
        secondRow.setHeight(380);
        XWPFTableCell fCell = secondRow.getCell(0);
        fCell.setText("列");
        // 合并第一列
        mergeCellsVertically(table, 0, 1, rowCount - 2);

        // 其他行。。。。
        List<XWPFTableRow> tableRows = table.getRows();
        if (tableRows != null && tableRows.size() > 0) {
            for (int ri = 1; ri < tableRows.size() - 1; ri++) {
                XWPFTableRow row = tableRows.get(ri);
                row.setHeight(380);
                List<XWPFTableCell> cells = row.getTableCells();
                if (cells != null && cells.size() > 0) {
                    for (int ci = 1; ci < cells.size(); ci++) {
                        XWPFTableCell cell = cells.get(ci);
                        cell.setText("单元格: " + ri + "-" + ci + " ");
                        if (ri % 2 == 0) {
                            setCellProp(cell, "D4DBED", 2000);
                        } else {
                            setCellProp(cell, "AEDE72", 2000);
                        }
                    }
                }
            }
        }

        // 表尾
        XWPFTableRow lastRow = table.getRow(rowCount - 1);
        lastRow.setHeight(380);
        XWPFTableCell lastRowfirstCell = lastRow.getCell(0);
        // 设置表头单元格内字体属性, 其他单元格只是内部属性设置不同, 照抄即可
        setCellTextProp(lastRowfirstCell, "表尾");
        // 合并第一行所有单元格
        mergeCellsHorizontal(table, rowCount - 1, 0, colCount - 1);

        //写出文件
        String path = WordTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File file = new File(path + "out.docx");
        FileOutputStream outputStream = new FileOutputStream(file);
        document.write(outputStream);
        outputStream.close();
        System.out.println("docx保存路径:" + file.getAbsolutePath());
    }
}
