package com.zh.interfaces;

/**
 * 接口继承:
 * 1, 为接口添加新的方法
 * 2, 通过继承可以再新街口中组合数个接口
 * Created by zh on 2017-04-13.
 */
public class HorrorShow {
    static void u(Monster monster) {monster.menace();}
    static void v(DangerousMonster dangerousMonster) {
        dangerousMonster.menace();
        dangerousMonster.destroy();
    }
    static void w(Lethal l) {
        l.kill();
    }

    public static void main(String[] args) {
        DangerousMonster barney = new DragonZilla();
        u(barney);
        v(barney);
        Vampire vampire = new VeryBadVampire();
        u(vampire);
        v(vampire);
        w(vampire);
    }
}
interface Monster {
    void menace();
}
interface DangerousMonster extends Monster {
    void destroy();
}
interface Lethal {
    void kill();
}
class DragonZilla implements DangerousMonster {
    @Override
    public void menace() {
    }
    @Override
    public void destroy() {
    }
}
interface Vampire extends DangerousMonster, Lethal {
    void drinkBlood();
}
class VeryBadVampire implements Vampire {
    public void menace() {}
    public void destroy() {}
    public void kill() {}
    public void drinkBlood() {}
}
