package com.zh.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2017-06-25.
 */
public class ThrowGenericException<T, E extends Exception> {
}
interface Processor<T, E extends Exception> {
    void process(List<T> resultCollector) throws E;
}
class ProcessRunner<T, E extends Exception> extends ArrayList<Processor<T, E>> {
    List<T> processAll() throws E {
        List<T> resultCollector = new ArrayList<>();
        for(Processor<T, E> processor : this)
            processor.process(resultCollector);
        return resultCollector;
    }
}

class Failure1 extends Exception{}
class Processor1 implements Processor<String, Failure1> {
    static int count = 3;
    @Override
    public void process(List<String> resultCollector) throws Failure1 {
        if(count-- > 1)
            resultCollector.add("hep!");
        else
            resultCollector.add("Ho!");
        if(count < 0)
            throw new Failure1();
    }
}