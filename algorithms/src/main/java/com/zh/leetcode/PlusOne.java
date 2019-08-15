package com.zh.leetcode;

import java.util.Arrays;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 *
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 思路: 最低为+1, 计算进位，高位加进位。特殊的如果是999,必定结果是1000, 声明一个内容是1000的数组即可
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {
        int length = digits.length;
        int carry = 0;
        for (int i = length - 1 ; i >= 0 ; i--) {
            int current = digits[i];
            if (i == length - 1) {
                current = current + 1;
            } else {
                current = current + carry;
            }
            if (current >= 10) {
                carry = 1;
                digits[i] = current - 10;
            } else {
                carry = 0;
                digits[i] = current;
                break;
            }
        }
        if (carry <= 0) {
            return digits;
        }
        int[] result = new int[length + 1];
        result[0] = carry;
        return result;
    }

    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        System.out.println(Arrays.toString(plusOne.plusOne(new int[]{1,2,9})));
        System.out.println(Arrays.toString(plusOne.plusOne(new int[]{1,9,9})));
        System.out.println(Arrays.toString(plusOne.plusOne(new int[]{0})));
        System.out.println(Arrays.toString(plusOne.plusOne(new int[]{9,9,9})));
    }
}
