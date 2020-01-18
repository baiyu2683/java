package com.zh.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.nio.charset.Charset;

public class HtmlUnitTest {

    public static void main(String[] args) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        HtmlPage page = webClient.getPage("http://www.baidu.com");
        System.out.println(page.getWebResponse().getContentAsString(Charset.forName("utf-8")));
    }

}
