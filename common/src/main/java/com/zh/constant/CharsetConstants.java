package com.zh.constant;

import java.nio.charset.Charset;

public interface CharsetConstants {

    String UTF_8 = "UTF-8";

    String ISO_8859_1 = "ISO-8859-1";

    String GBK = "GBK";

    Charset utf8 = Charset.forName(UTF_8);

    Charset iso88591 = Charset.forName(ISO_8859_1);

    Charset gbk = Charset.forName(GBK);
}
