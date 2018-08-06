package com.zh.itext;

import com.zh.util.FileUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.lang.model.util.Elements;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Author: Administrator <br/>
 * Date: 2018-08-06 <br/>
 */
public class Dom4jAndItext {

    private static final String rootPath = JsoupAndItext.class.getResource("/").getPath();

    private static final String htmlPath = rootPath + "/html";

    private static final String fontPath = rootPath + "/font";

    public static void main(String[] args) throws IOException, com.lowagie.text.DocumentException, DocumentException {

        // 读取html文件
        File htmlFile = new File(htmlPath + "/html.html");
        if (!htmlFile.exists()) {
            System.out.println("文件不存在: " + htmlFile.getPath());
            return;
        }

        String htmlSource = FileUtil.toString(htmlFile);

        Document document = DocumentHelper.parseText(htmlSource);
        List<Element> elementList = document.getRootElement().elements();
        for (Element element :elementList) {
            System.out.println(element.getName());
        }
    }
}
