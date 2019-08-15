package com.zh.leetcode;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 *
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * TODO 待优化
 */
public class AddBinary {

    public static void main(String[] args) {
        String a = "1111";
        String b = "1111";
        AddBinary addBinary = new AddBinary();
        System.out.println(addBinary.addBinary(a, b));
    }

    public String addBinary(String a, String b) {
        int al = a.length();
        int bl = b.length();
        int maxLength = al > bl ? al + 1 : bl + 1;
        int[] result = new int[maxLength];
        int carry = 0;
        while (maxLength > 0) {
            int ae = 0;
            if (al > 0) {
                ae = a.charAt(--al) - '0';
            }
            int be = 0;
            if (bl > 0) {
                be = b.charAt(--bl) - '0';
            }
            int sum = ae + be + carry;
            if (sum > 1) {
                carry = sum / 2;
                sum = sum % 2;
            } else {
                carry = 0;
            }
            result[--maxLength] = sum;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < result.length ; i++) {
            int e = result[i];
            if (i == 0) {
                if (e != 0) {
                    stringBuilder.append(e);
                }
            } else {
                stringBuilder.append(e);
            }
        }
        return stringBuilder.toString();
    }
}
