package com.zh.generics;

/**
 * Created by zh on 2017-06-22.
 */
interface Payable<T> {
}
class Employee implements Payable {}
//class Hourly extends Employee implements Payable<Hourly> {}
class Hourly extends Employee implements Payable {}