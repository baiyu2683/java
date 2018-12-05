package com.zh.adapter;

/**
 * adaptee和target的适配器
 *
 * 需要继承转换后的对象
 */
public class TargetAdapter extends Target {

    private Adaptee adaptee;

    public TargetAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void show() {
        adaptee.display();
    }

    public Target convert(Adaptee adaptee) {
        Target target = new Target();
        target.setName(adaptee.getAlias());
        return target;
    }
}
