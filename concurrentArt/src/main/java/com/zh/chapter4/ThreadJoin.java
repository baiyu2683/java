package com.zh.chapter4;

public class ThreadJoin {

    public static void main(String[] args) {
        Thread preThread = null;
        for (int i = 0 ; i < 10 ; i++) {
            Thread thread = new Diomino(preThread);
            thread.setName(String.valueOf(i));
            preThread = thread;
            thread.start();
        }
    }

    private static class Diomino extends Thread {

        private Thread preThread;

        public Diomino(Thread preThread) {
            this.preThread = preThread;
        }

        @Override
        public void run() {
            try {
                if (preThread != null) {
                    preThread.join();
                }
                System.out.println("name: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
