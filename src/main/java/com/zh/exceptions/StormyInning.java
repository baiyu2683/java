package com.zh.exceptions;

/**
 * Created by zh on 2017-03-26.
 */
class BaseballException extends Exception{}
class Foul extends BaseballException{}
class Strike extends BaseballException{}
abstract class Inning {
    public Inning() throws BaseballException {
    }
    public void event() throws BaseballException {
        //Doesn't actually have to throw anything
    }
    public abstract void atBat() throws Strike, Foul;
    public void walk() {} //throws no checked exceptions
}
class StormException extends Exception {}
class RainedOut extends StormException{}
class PopFoul extends Foul {}
interface Storm {
    public void event() throws RainedOut;
    public void rainHard() throws RainedOut;
}
public class StormyInning extends Inning implements Storm {
    public StormyInning() throws RainedOut, BaseballException {
    }

    public StormyInning(String s) throws Foul, BaseballException {
    }

    @Override
    public void atBat() throws PopFoul {

    }

    @Override
    public void rainHard() throws RainedOut {

    }
    //基类声明了异常，子类可以不声明
    public void event() {}

    public static void main(String[] args) {
    }
}
