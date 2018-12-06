package com.zh;

import com.zh.util.Base64Util;
import org.junit.Assert;
import org.junit.Test;

public class Base64UtilTest {

    @Test
    public void testEncryptAndDecrypt() {
        String source = "asdf阿斯蒂芬asdfasdfasdfAAAAAAALKJSDOPFJDOPSDFfasdfasdfasdfsafe";
        String base64Source = Base64Util.encrypt(source);
        System.out.println(base64Source);
        String decryptSource = Base64Util.decrypt(base64Source);
        System.out.println(decryptSource);
        Assert.assertEquals(source, decryptSource);
    }

}
