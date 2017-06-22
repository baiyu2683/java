package com.zh.generics;

/**
 * 基类会劫持接口，导致子类不能窄化泛型类型
 * Created by zh on 2017-06-22.
 */
public class ComparablePet implements Comparable<ComparablePet> {
    @Override
    public int compareTo(ComparablePet o) {
        return 0;
    }
}
/**
 * 无法编译，参数列表必须与Comparable精确相同，完全相同
 * 类型擦除后，相当于实现了两个Comparable接口
 */
//class Cat extends ComparablePet implements Comparable<Cat> {
//    public int compareTo(Cat arg) {
//        return 0;
//    }
//}
class Cat extends ComparablePet implements Comparable<ComparablePet> {

}