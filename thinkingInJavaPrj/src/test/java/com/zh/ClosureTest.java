package com.zh;

/**
 * 接口和闭包，实现回调
 * @author zh
 * 2018年8月31日
 */
public class ClosureTest {

    public static void main(String[] args) {
        OuterClass oc = new OuterClass();
        oc.showInnerCounter();
        
        CallBackInterface cbi = oc.getClosure();
        cbi.callback();
        
        oc.showInnerCounter();
    }
    
}
// 回调接口
interface CallBackInterface {
    void callback();
}
class OuterClass {
    private int counter = 1;
    
    //非静态内部类可以获得外部类的引用，借此可以获得访问外部类所有的域
    private class InnerClosureClass implements CallBackInterface {

        @Override
        public void callback() {
            OuterClass.this.counter++;
        }
        
    }
    
    public CallBackInterface getClosure() {
        return new InnerClosureClass();
    }
    
    public void showInnerCounter() {
        System.out.println(counter);
    }
}
