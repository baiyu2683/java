package com.zh.chapter10;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zh on 2017-10-13.
 */
public class TestTaxi {
    public static void main(String[] args) {

    }
}
class Taxi {
    private Point location, destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }
    //需要获取taxi和dispatcher两个锁
    public synchronized void setLocation(Point location) {
        this.location = location;
        if(location.equals(destination))
            dispatcher.notifyAvailable(this);
    }
}
class Dispatcher {
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<>();
        availableTaxis = new HashSet<>();
    }
    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }
    //需要获取Dispatcher和Taxi两个锁
    public synchronized Image getImage() {
        Image image = new Image();
        for (Taxi t : taxis)
            image.drawMarker(t.getLocation());
        return image;
    }
}

class Point {
}
class Image{
    public void drawMarker(Point p){}
}
