package com.zh.chapter10;

/**
 * Created by zh on 2017-10-12.
 */
public class ActiveLock {
    //容易发生死锁
//    public void transferMony(Account fromAccount,
//                             Account toAccount,
//                             DollarAmount amount) {
//        synchronized (fromAccount) {
//            synchronized (toAccount) {
//                System.out.println("转账ing。。。");
//            }
//        }
//    }
    private static final Object tieLock = new Object();
    //利用System.identityHashCode方法控制锁的顺序以避免死锁
    public void transferMoney(final Account fromAcct,
                              final Account toAcct,
                              final DollarAmount amount) {
        class Helper {
            public void transfer() {
                System.out.println("转账ing。。。");
            }
        }
        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);
        if(fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if(fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}

class Account{}
class DollarAmount{}
