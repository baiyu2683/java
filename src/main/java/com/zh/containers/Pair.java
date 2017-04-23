package com.zh.containers;

/**
 * Created by zh on 2017-04-23.
 */
public class Pair<K, V> {
    public final K key;
    public final V value;
    public Pair(K k, V v) {
        key = k;
        value = v;
    }
}
