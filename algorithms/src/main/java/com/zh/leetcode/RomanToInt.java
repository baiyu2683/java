package com.zh.leetcode;

/**
 * @author Administrator
 * @date 2019/08/05
 */
public class RomanToInt {

    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        int prev = 0;
        for (int i = chars.length - 1 ; i >= 0 ; i--) {
            int temp = map(chars[i]);
            if (temp >= prev) {
                result += temp;
                prev = temp;
            } else {
                result -= temp;
            }
        }
        return result;
    }

    /**
     * 相同的数字连写，所表示的数等于这些数字相加得到的数，如 Ⅲ=3；
     * 小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数，如 Ⅷ=8、Ⅻ=12；
     * 小的数字（限于 Ⅰ、X 和 C）在大的数字的左边，所表示的数等于大数减小数得到的数，如 Ⅳ=4、Ⅸ=9；
     * 在一个数的上面画一条横线，表示这个数增值 1,000 倍
     * @param sign
     * @return
     */
    private int map(char sign) {
        switch(sign) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        throw new IllegalArgumentException("sign:" + sign);
    }



    public static void main(String[] args) {
        RomanToInt rii = new RomanToInt();
        System.out.println(rii.romanToInt("IIII"));
        System.out.println(rii.romanToInt("IIIV"));
        System.out.println(rii.romanToInt("VI"));
        System.out.println(rii.romanToInt("IX"));
        System.out.println(rii.romanToInt("LVIII"));
        System.out.println(rii.romanToInt("MCMXCIV"));
    }
}
