package com.zh.jsoup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 获得colorname和rbg的对照表
 *
 * @author zh
 * 2018年8月23日
 */
public class ColorName2RGB {

    public static void main(String[] args) throws MalformedURLException, IOException {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String url = "http://tool.oschina.net/commons?type=3";
        Document doc = Jsoup.parse(new URL(url), 2000);
        Elements elements = doc.getElementsByAttributeValue("class", "toolTable table");
        Element table = elements.get(0);
        Elements trs = table.getElementsByTag("tr");
        for (int i = 1 ; i < trs.size() ; i++) {
            Element tr = trs.get(i);
            Elements tds = tr.getAllElements();
            Element name1 = tds.get(2);
            Element rgb1 = tds.get(3);
            String rgbStr1 = rgb1.text().trim().replace("#", "");
            rgbStr1 = "rgb(" + rgbStr1.replace(" ", ", ") + ")";
            sb.append(name1.text().trim().toLowerCase() + "=" + rgbStr1 + "\n");
            if (tds.size() > 6) {
                Element name2 = tds.get(6);
                Element rgb2 = tds.get(7);
                String rgbStr2 = rgb2.text().trim().replace("#", "");
                rgbStr2 = "rgb(" + rgbStr2.replace(" ", ", ") + ")";
                sb2.append(name2.text().trim().toLowerCase() + "=" + rgbStr2 + "\n");
            }
        }
        FileOutputStream fos = new FileOutputStream("d:/colorname.properties", true);
        fos.write(sb.toString().getBytes("utf-8"));
        fos.write(sb2.toString().getBytes("utf-8"));
        fos.close();
    }
}
