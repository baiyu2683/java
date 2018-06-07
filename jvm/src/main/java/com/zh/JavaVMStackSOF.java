package com.zh;

/**
 * VM args: -Xss228k
 *
 * Hotsop不区分虚拟机栈和本地方法栈
 * -Xoss(本地方法栈的设置存在但是并不生效)
 *
 * 栈StackOverflowError
 *
 * @Author zh
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        } catch (Throwable throwable) {
            System.out.printf("stack lenght:" + javaVMStackSOF.stackLength);
            throw throwable;
        }
    }
}
