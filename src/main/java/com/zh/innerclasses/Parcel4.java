package com.zh.innerclasses;

/**
 * 内部类向上转型是隐藏了具体的实现细节
 * Created by zh on 2017-04-16.
 */
public class Parcel4 {
    private class PContents implements Contents {
        private int i = 11;
        public int value() {
            return i;
        }
    }
    protected class PDestination implements Destination {
        private String label;
        private PDestination(String whereTo) {
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }
    public Destination destination(String s) {
        return new PDestination(s);
    }
    public Contents contents() {
        return new PContents();
    }
}
