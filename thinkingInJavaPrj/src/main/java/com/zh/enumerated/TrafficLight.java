package com.zh.enumerated;

/**
 * Created by zh on 2017-03-18.
 */
public class TrafficLight {
    Signal color = Signal.RED;
    public void change() {
        switch (color) {
            case RED:
                color = Signal.GERRN;
                return;
            case GERRN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
//            default:
//                    return color;
        }
    }
    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        for(int i = 0; i < 7; i++) {
            System.out.println(trafficLight);
            trafficLight.change();
        }
    }
}
enum Signal{
    GERRN,YELLOW,RED
}
