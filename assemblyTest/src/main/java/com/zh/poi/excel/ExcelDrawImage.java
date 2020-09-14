package com.zh.poi.excel;

import com.zh.image.ImageUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * xlsx添加图片
 */
public class ExcelDrawImage {

    private static Workbook workbook = null;

    public static void main(String[] args) throws IOException {
        workbook = new XSSFWorkbook();
        //新建工作表
        XSSFSheet sheet = (XSSFSheet) workbook.createSheet("hello");
        //创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("第一个");
        Cell cell10 = row.createCell(10);
        cell10.setCellValue("第十个");

        Row row4 = sheet.createRow(4);
        Cell cell42 = row4.createCell(2);
        cell42.setCellValue("第42个");

        // 增加图片
        XSSFDrawing xssfDrawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor xssfClientAnchor = xssfDrawing.createAnchor(0, 0, Units.EMU_PER_PIXEL * -10, Units.EMU_PER_PIXEL * -10, 0, 0, 1, 1);
        byte[] bytes = IOUtils.toByteArray(new URL("file:\\C:\\Users\\Administrator\\Desktop\\output.bmp"));

//        BufferedImage bufferedImage = ImageIO.read(new URL("file:\\C:\\Users\\Administrator\\Desktop\\捕获.png"));
        byte[] bytes1 = ImageUtils.thumbnail(bytes, 1);

        int picIndex = workbook.addPicture(bytes1, Workbook.PICTURE_TYPE_PNG);
        xssfDrawing.createPicture(xssfClientAnchor, picIndex);

        //输出到磁盘中
        FileOutputStream fos = new FileOutputStream(new File("D:/xlsx.xlsx"));
        workbook.write(fos);
        workbook.close();
        fos.close();
        System.out.println("finished...");
    }
}
