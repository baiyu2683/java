package com.zh;

import com.zh.util.StringUtil;
import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void testContainsChinese() {
        String source = "asdfasdfasdf";
        Assert.assertFalse(StringUtil.containsChinese(source));
        source = "asd阿斯蒂芬asdf阿斯蒂芬";
        Assert.assertTrue(StringUtil.containsChinese(source));
        source = "阿斯顿发斯蒂芬 ";
        Assert.assertTrue(StringUtil.containsChinese(source));
        source = "   ";
        Assert.assertFalse(StringUtil.containsChinese(source));
        source = "";
        Assert.assertFalse(StringUtil.containsChinese(source));
    }
}
