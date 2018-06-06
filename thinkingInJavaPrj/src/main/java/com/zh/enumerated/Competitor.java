package com.zh.enumerated;

/**
 * Created by zh on 2017-03-19.
 */
public interface Competitor<T extends Competitor<T>> {
    Outcome compete(T competitor);
}
