package com.zh.poi;

import org.apache.commons.io.IOUtils;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;

/**
 * 将html文件转换成word文件
 * 
 * @author 王文路
 * @date 2015-7-23
 */
public class Html2DocConverter {

	private InputStream inputStream; // 输入html字节流

	public Html2DocConverter(String html_source) {
		super();
		try {
			inputStream = new ByteArrayInputStream(html_source.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("编码不支持: utf-8");
		}
	}

	public Html2DocConverter(InputStream is) {
		this.inputStream = is;
	}

	/**
	 * 读取html文件到word
	 * 
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream getWord() throws Exception {
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			// 3 将html文件内容写入doc文件
			POIFSFileSystem poifs = new POIFSFileSystem();
			DirectoryEntry directory = poifs.getRoot();
			directory.createDocument(
					"WordDocument", this.inputStream);
			baos = new ByteArrayOutputStream();
			poifs.writeFilesystem(baos);
			return baos;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		String htmlSource = IOUtils.toString(new FileInputStream("d:/temp.html"), "utf-8");
		Html2DocConverter html2DocConverter = new Html2DocConverter(htmlSource);
		ByteArrayOutputStream baos = html2DocConverter.getWord();
		FileOutputStream fos = new FileOutputStream("d:/text.doc");
		byte[] bytes = baos.toByteArray();
		fos.write(bytes, 0, bytes.length);
	}
}