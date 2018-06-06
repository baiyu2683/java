package com.zh.concurrent;

import org.junit.experimental.theories.DataPoint;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-01-15.
 */
public class GreenhouseScheduler {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";
    public synchronized String getThermostat() {
        return thermostat;
    }
    public synchronized void setThermostat(String value) {
        this.thermostat = value;
    }
    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
    public void schedule(Runnable event, long delay) {
        scheduledThreadPoolExecutor.schedule(event, delay, TimeUnit.MILLISECONDS);
    }
    public void repeat(Runnable event, long initialDelay, long period) {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
    }
    class LightOn implements Runnable {
        public void run() {
            System.out.println("Turning on lights");
            light = true;
        }
    }
    class LightOff implements Runnable {
        public void run() {
            System.out.println("Turning off lights");
            light = false;
        }
    }
    class WaterOn implements Runnable {
        public void run() {
            System.out.println("Turning grenhouse water on");
            water = true;
        }
    }
    class WaterOff implements Runnable {
        public void run() {
            System.out.println("Turning greenhouse water off");
            water = false;
        }
    }
    class ThermostatNight implements Runnable {
        public void run() {
            System.out.println("Thermostat to night setting");
            setThermostat("Night");
        }
    }
    class ThermostatDay implements Runnable {
        public void run() {
            System.out.println("Thermostat to day setting");
            setThermostat("Day");
        }
    }
    class Bell implements Runnable {
        public void run() {
            System.out.println("Bing!");
        }
    }
    class Terminate implements Runnable {
        public void run() {
            System.out.println("Terminating");
            scheduledThreadPoolExecutor.shutdownNow();
            new Thread(()->{
                for(DataPoint d : data)
                    System.out.println(d);
            }).start();
        }
    }
    static class DataPoint {
        final Calendar time;
        final float temperature;
        final float humidity;
        public DataPoint(Calendar d, float temp, float hum) {
            this.time = d;
            this.temperature = temp;
            this.humidity = hum;
        }
        public String toString() {
            return time.getTime() + String.format(" temperature: %1$.1f humidity: %2$.2f", temperature, humidity);
        }
    }
    private Calendar lastTime = Calendar.getInstance();
    {
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 00);
    }
    private float lastTemp = 65.0f;
    private int tempDirection = +1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = +1;
    private Random random = new Random(47);
    List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());
    class CollectData implements Runnable {
        public void run() {
            System.out.println("Collecting data");
            synchronized (GreenhouseScheduler.this) {
                lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE) + 30);
                if(random.nextInt(5) == 4)
                    tempDirection = -tempDirection;
                lastTemp = lastTemp + tempDirection * (1.0f + random.nextFloat());
                if(random.nextInt(5) == 4)
                    humidityDirection = -humidityDirection;
                lastHumidity = lastHumidity + humidityDirection * random.nextFloat();
                data.add(new DataPoint((Calendar)lastTime.clone(), lastTemp, lastHumidity));
            }
        }
    }
    public static void main(String[] args) {
        GreenhouseScheduler greenhouseScheduler = new GreenhouseScheduler();
        greenhouseScheduler.schedule(greenhouseScheduler.new Terminate(), 5000);
        greenhouseScheduler.repeat(greenhouseScheduler.new Bell(), 0, 1000);
        greenhouseScheduler.repeat(greenhouseScheduler.new ThermostatNight(), 0, 2000);
        greenhouseScheduler.repeat(greenhouseScheduler.new LightOn(), 0, 200);
        greenhouseScheduler.repeat(greenhouseScheduler.new LightOff(), 0, 400);
        greenhouseScheduler.repeat(greenhouseScheduler.new WaterOn(), 0, 600);
        greenhouseScheduler.repeat(greenhouseScheduler.new WaterOff(), 0, 800);
        greenhouseScheduler.repeat(greenhouseScheduler.new ThermostatDay(), 0, 1400);
        greenhouseScheduler.repeat(greenhouseScheduler.new CollectData(), 500, 500);
    }
}
