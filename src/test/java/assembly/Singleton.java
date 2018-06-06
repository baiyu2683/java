package assembly;

import java.io.Serializable;

public class Singleton implements Serializable{
    private transient static final Singleton singleton = new Singleton();
    private Singleton(){}
    public static Singleton getInstance() {
        return singleton;
    }

    private Object readResolve() {
        return singleton;
    }
}
