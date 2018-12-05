package com.zh.adapter;

/**
 * 主要是在接口需要兼容情况下使用适配器
 */
public class MainEntry {

    public static void main(String[] args) {
        // 正常使用Client调用Target，显示类名
        Client client = new Client();
        Target target = new Target();
        client.showClassName(target);

        // 由于Client.showClassName方法只接收Target类型
        // 如果我们要使用Client显示Adaptee的类型，就需要使用适配器
        target = new TargetAdapter(new Adaptee());
        client.showClassName(target);

    }
}
