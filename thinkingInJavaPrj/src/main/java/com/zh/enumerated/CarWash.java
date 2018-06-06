package com.zh.enumerated;

import java.util.EnumSet;

/**
 * Created by zh on 2017-03-19.
 */
public class CarWash {
    public enum Cycle {
        UNDERBODY {
            void action() {
                System.out.println("Spraying the underbody");
            }
        },
        WHEELWASH {
            void action() {
                System.out.println("Washing the wheels");
            }
        },
        PREWASH {
            void action() {
                System.out.println("Losening th dirt");
            }
        },
        BASIC {
            void action() {
                System.out.println("The basic wash");
            }
        },
        HOTWAX {
            void action() {
                System.out.println("Applying hot wax");
            }
        },
        RINSE {
            void action() {
                System.out.println("Rinsing");
            }
        },
        BLOWDRY {
            void action() {
                System.out.println("Blowing dry");
            }
        };

        abstract void action();
    }
    EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);
    public void add(Cycle cycle) {
        cycles.add(cycle);
    }
    public void washCar() {
        for (Cycle c : cycles)
            c.action();
    }
    public String toString() {
        return cycles.toString();
    }

    public static void main(String[] args) {
        CarWash carWash = new CarWash();
        System.out.println(carWash);
        carWash.washCar();
        carWash.add(Cycle.BLOWDRY);
        carWash.add(Cycle.BLOWDRY);
        carWash.add(Cycle.RINSE);
        carWash.add(Cycle.HOTWAX);
        System.out.println(carWash);
        carWash.washCar();
    }
}
