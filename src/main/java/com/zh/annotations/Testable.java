package com.zh.annotations;

import com.zh.annotations.atunit.Test;

/**
 * Created by zhheng on 2016/3/23.
 */
public class Testable {
    public void  execte() {
        System.out.println("Executing...");
    }

    @Test void testExecute(){
        execte();
    }
}
