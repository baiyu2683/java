package com.zh.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.concurrent.TimeUnit;

public class SeleniumTest {

    public static void main(String[] args) {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "/home/zh/Projects/dcrawler/dc-downloader-parent/dc-downloader-selenium/src/main/resources/chromedriver");

        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                .build();

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

        driver.get("http://www.baidu.com/");

        System.out.println(driver.getTitle());

        driver.quit();
        // 关闭chromdriver进程
        chromeDriverService.stop();
    }

}
