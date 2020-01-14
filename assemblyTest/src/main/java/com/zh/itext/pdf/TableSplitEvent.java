package com.zh.itext.pdf;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEventSplit;

public class TableSplitEvent implements PdfPTableEventSplit {
    @Override
    public void splitTable(PdfPTable table) {
        System.out.println("splitTable");
    }

    @Override
    public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases) {
        System.out.println("tableLayout");
    }
}
