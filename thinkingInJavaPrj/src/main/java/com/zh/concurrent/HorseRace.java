package com.zh.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by zh on 2017-01-12.
 */
public class HorseRace {
    static final int FINISH_LINE = 75;
    private List<Horse> horseList = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private CyclicBarrier cyclicBarrier;
    public HorseRace(int nHorses, final int pause) {
        cyclicBarrier = new CyclicBarrier(nHorses, new Runnable() {
            @Override
            public void run() {
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 0; i < FINISH_LINE; i++)
                    stringBuilder.append("=");
                System.out.println(stringBuilder);
                for (Horse horse : horseList)
                    System.out.println(horse.tracks());
                for(Horse horse : horseList)
                    if(horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse + "won!");
                        executorService.shutdownNow();
                        return;
                    }
                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    System.out.println("barrier-action sleep interrupted");
                }
            }
        });
        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(cyclicBarrier);
            horseList.add(horse);
            executorService.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 200;
        if(args.length > 0) {
            int n = new Integer(args[0]);
            nHorses = n > 0? n: nHorses;
        }
        if(args.length > 1) {
            int p = new Integer(args[1]);
            pause = p > -1? p : pause;
        }
        new HorseRace(nHorses, pause);
    }
}

class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random random = new Random(47);
    private static CyclicBarrier cyclicBarrier;
    public Horse(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }
    public synchronized int getStrides() {
        return strides;
    }
    public void run() {
        try{
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3);//Produces 0, 1 or 2
                }
                cyclicBarrier.await();
            }
        } catch (InterruptedException e) {

        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
    public String toString() {
        return "Horse " + id + " ";
    }
    public String tracks() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < getStrides(); i++) {
            stringBuilder.append("*");
        }
        stringBuilder.append(id);
        return stringBuilder.toString();
    }
}
