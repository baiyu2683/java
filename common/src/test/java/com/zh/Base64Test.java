package com.zh;

import com.zh.encrypt.Base64;
import org.junit.Assert;
import org.junit.Test;

public class Base64Test {

    @Test
    public void testEncryptAndDecrypt() {
        String source = "asdf阿斯蒂芬asdfasdfasdfAAAAAAALKJSDOPFJDOPSDFfasdfasdfasdfsafe";
        String base64Source = Base64.encrypt(source);
        System.out.println(base64Source);
        String decryptSource = Base64.decrypt(base64Source);
        System.out.println(decryptSource);
        Assert.assertEquals(source, decryptSource);
    }

}
