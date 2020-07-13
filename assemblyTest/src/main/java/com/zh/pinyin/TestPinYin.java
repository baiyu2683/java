package com.zh.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.junit.Test;

import java.util.Arrays;

public class TestPinYin {

    @Test
    public  void test1() {
        String[] s = PinyinHelper.toHanyuPinyinStringArray('统');
        System.out.println(Arrays.toString(s)); // [tong3]
    }
}
