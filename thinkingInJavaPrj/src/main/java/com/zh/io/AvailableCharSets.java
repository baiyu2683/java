package com.zh.io;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * Created by zh on 2017-03-06.
 */
public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String, Charset> charsetSortedMap = Charset.availableCharsets();
        Iterator<String> it = charsetSortedMap.keySet().iterator();
        while (it.hasNext()) {
            String csName = it.next();
            System.out.print(csName);
            Iterator aliases = charsetSortedMap.get(csName).aliases().iterator();
            if(aliases.hasNext())
                System.out.print(": ");
            while (aliases.hasNext()) {
                System.out.print(aliases.next());
                if(aliases.hasNext())
                    System.out.print(",");
            }
            System.out.println();
        }
    }
}
