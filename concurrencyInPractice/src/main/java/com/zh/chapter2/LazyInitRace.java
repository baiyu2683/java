package com.zh.chapter2;

import com.zh.annotations.NotThreadSafe;
import org.junit.runner.notification.RunListener;

/**
 * Created by zh on 2017-07-06.
 */
@NotThreadSafe
public class LazyInitRace {

    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        //竞态条件
        if(instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }
}

class ExpensiveObject {

}
