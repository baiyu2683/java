package com.zh.chapter1.chapter13.exercise;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 回环数组实现环形缓冲区
 * @author zh
 * 2018年8月19日
 */
public class RingBuffer<T> {

	private T[] datas;
	private int N;
	private int readIndex;
	private int writeIndex;
	
	private final Lock lock = new ReentrantLock();
	private Condition full = lock.newCondition();
	private Condition empty = lock.newCondition();
	
	@SuppressWarnings("unchecked")
	public RingBuffer(int capacity) {
		datas = (T[]) new Object[capacity];
	}
	
	public void write(T t) {
		final Lock lock = this.lock;
		lock.lock();
		try {
			if (N == datas.length) {
				full.await(); // 队列满，写等待
			}
			datas[writeIndex] = t;
			writeIndex = (writeIndex + 1) % datas.length;
			N++;
			empty.signalAll(); // 写入之后，不空了。。empty释放
		} catch (InterruptedException e) {
		} finally {
			lock.unlock();
		}
	}
	
	public T read() {
		final Lock lock = this.lock;
		lock.lock();
		try {
			if (N == 0) {
				empty.await(); // 队列空, 读等待
			}
			T t = datas[readIndex];
			datas[readIndex] = null;
			readIndex = (readIndex + 1) % datas.length;
			N--;
			full.signalAll(); // 读了一个元素之后，队列不再满了。。full释放
			return t;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return null;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		final RingBuffer<String> rb = new RingBuffer<>(10);
		final Random random = new Random(47);
		Thread t1 = new Thread(() -> {
			System.out.println("写线程启动。。。");
			while(!Thread.currentThread().isInterrupted()) {
				String write = random.nextInt(100) + "";
				System.out.println("write: " + write);
				rb.write(write);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(() -> {
			System.out.println("读线程启动。。。");
			while(!Thread.currentThread().isInterrupted()) {
				String read = rb.read();
				System.out.println("read: " + read);
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
}
