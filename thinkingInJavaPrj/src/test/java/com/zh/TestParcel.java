package com.zh;

import com.zh.innerclasses.Contents;
import com.zh.innerclasses.Destination;
import com.zh.innerclasses.Parcel4;

/**
 * Created by zh on 2017-04-16.
 */
public class TestParcel {
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents c = p.contents();
        Destination d = p.destination("Tianjin");
    }
}
