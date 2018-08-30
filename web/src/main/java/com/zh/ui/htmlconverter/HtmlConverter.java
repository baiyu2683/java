package com.zh.ui.htmlconverter;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html")
public class HtmlConverter {

    @RequestMapping("/word")
    public ResponseEntity<byte[]> exportWord() throws IOException {
        InputStream is = HtmlConverter.class.getResourceAsStream("/document/test.docx");
        byte[] content = IOUtils.toByteArray(is);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "123.docx");
        return ResponseEntity.ok()
                .contentLength(content.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .headers(headers)
                .body(content);
    }

    @RequestMapping("/pdf")
    @ResponseBody
    public ResponseEntity<byte[]> exportPdf() {
        throw new RuntimeException("123");
    }
}
