package com.zh.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-01-09.
 */
public class Restaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);
    BusBoy busBoy = new BusBoy(this);
    public Restaurant() {
        exec.execute(waitPerson);
        exec.execute(chef);
        exec.execute(busBoy);
    }
    public static void main(String[] args) {
        new Restaurant();
    }
}
class Meal {
    private final int orderNum;
    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }
    public String toString() {
        return "Meal " + orderNum;
    }
}
class WaitPerson implements Runnable {
    private Restaurant restaurant;
    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null)
                        wait();;
                }
                System.out.println("Waitperson got " + restaurant.meal);
                synchronized (restaurant.busBoy) {
                    restaurant.busBoy.waitPerson = this;
                    restaurant.busBoy.notifyAll();
                }
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("WaitingPerson interrupted");
        }
    }
}

class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;
    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null)
                        wait();
                }
                if(++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.exec.shutdownNow();
//                    return;
                }
                System.out.println("Order up!");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                //这个是可中断的，如果中断会抛出InterruptedException异常
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}

class BusBoy implements Runnable {
    private Restaurant restaurant;
    WaitPerson waitPerson;
    public BusBoy(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void run() {
        while (!Thread.interrupted()) {
            while (waitPerson != null){
                synchronized (waitPerson) {
                    System.out.println("BusBoy clear....");
                    waitPerson = null;
                }
            }
        }
    }
}
