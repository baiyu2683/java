package com.zh.concurrent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by zh on 2017-01-16.
 */
public class CarBuilder {
    public static void main(String[] args) throws Exception {
        CarQueue chassisQueue = new CarQueue(),
                finishingQueue = new CarQueue();
        ExecutorService executorService = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();
        executorService.execute(new EngineRobot(robotPool));
        executorService.execute(new DriveTrainRobot(robotPool));
        executorService.execute(new WheelRobot(robotPool));
        executorService.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
        executorService.execute(new Reporter(finishingQueue));
        executorService.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        executorService.shutdownNow();
    }
}
class Car3 {
    private final int id;
    private boolean engine = false, driveTrain =false, wheels = false;
    public Car3 (int idn) {
        id = idn;
    }
    public Car3() {
        id = -1;
    }
    public synchronized int getId() {
        return id;
    }
    public synchronized void addEngine() {
        engine = true;
    }
    public synchronized void addDriveTrain() {
        driveTrain = true;
    }
    public synchronized void addWheels() {
        wheels = true;
    }
    public synchronized String toString() {
        return "Car " + id + " [" + " engine: " + engine +
                " driveTrain: " + driveTrain +
                " wheels: " + wheels + " ]";
    }
}
class CarQueue extends LinkedBlockingQueue<Car3> {

}

class ChassisBuilder implements Runnable {
    private CarQueue carQueue;
    private int counter = 0;
    public ChassisBuilder(CarQueue car3s) {
        carQueue = car3s;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                Car3 car3 = new Car3(counter++);
                carQueue.add(car3);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted: ChassisBuilder");
        }
        System.out.println("ChassisBuilder off");
    }
}
class Assembler implements Runnable {
    private CarQueue chassisQueue, finishingQueue;
    private Car3 car3;
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
    private RobotPool robotPool;
    public Assembler(CarQueue car3s, CarQueue finishingQueue, RobotPool robotPool) {
        this.chassisQueue = car3s;
        this.finishingQueue = finishingQueue;
        this.robotPool = robotPool;
    }
    public Car3 car() {
        return car3;
    }
    public CyclicBarrier barrier() {
        return cyclicBarrier;
    }
    public void run() {
        try{
            while (!Thread.interrupted()) {
                car3 = chassisQueue.take();
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                cyclicBarrier.await();
                finishingQueue.put(car3);
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting Assembler via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Assembler off");
    }
}
class Reporter implements Runnable {
    private CarQueue carQueue;
    public Reporter(CarQueue car3s) {
        this.carQueue = car3s;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(carQueue.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting Reporter via interrupt");
        }
    }
}
abstract class Robot implements Runnable {
    private RobotPool pool;
    public Robot(RobotPool pool) {
        this.pool = pool;
    }
    protected Assembler assembler;
    public Robot assignAssembler(Assembler assembler) {
        this.assembler = assembler;
        return this;
    }
    private boolean engage = false;
    public synchronized void engage() {
        engage = true;
        notifyAll();
    }
    abstract protected void performService();
    public void run() {
        try {
            powerDown();
            while(!Thread.interrupted()) {
                performService();
                assembler.barrier().await();
                //释放robot
                powerDown();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this + " off");
    }
    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        assembler = null;
        pool.release(this);
        while (engage == false)
            wait();
    }
    public String toString() {
        return getClass().getName();
    }
}
class EngineRobot extends Robot {
    public EngineRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing engine");
        assembler.car().addEngine();
    }
}
class DriveTrainRobot extends Robot {
    public DriveTrainRobot(RobotPool pool) {
        super(pool);
    }
    protected void performService() {
        System.out.println(this + " installing DriveTrain");
        assembler.car().addDriveTrain();
    }
}
class WheelRobot extends Robot {
    public WheelRobot(RobotPool pool) {
        super(pool);
    }
    protected void performService() {
        System.out.println(this + " installing DriveTrain");
        assembler.car().addWheels();
    }
}
class RobotPool {
    private Set<Robot> poolSet = new HashSet<>();
    public synchronized void add(Robot robot) {
        poolSet.add(robot);
        notifyAll();
    }
    public synchronized void release(Robot robot) {
        add(robot);
    }
    public synchronized void hire(Class<? extends Robot> robotType, Assembler assembler) throws InterruptedException {
        for(Robot robot : poolSet) {
            if(robot.getClass().equals(robotType)) {
                poolSet.remove(robot);
                robot.assignAssembler(assembler);
                robot.engage();
                return;
            }
        }
        wait();
        hire(robotType, assembler);
    }
}
