package assembly;

public class EnumSingleton {

    private enum InnerSingletonEnum {
        SINGLETON;
        EnumSingleton singleton = new EnumSingleton();
    }

    public static EnumSingleton getInstance() {
        return InnerSingletonEnum.SINGLETON.singleton;
    }
}
