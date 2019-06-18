package com.zh;

/**
 * 利用堆实现的优先级队列
 *
 * @author Administrator
 * @date 2019/06/17
 */
public class MaxPQ {

    private Comparable[] datas;
    private int N;

    public MaxPQ(int N) {
        this.datas = new Comparable[N];
    }

    private boolean less(int i, int j) {
        return datas[i - 1].compareTo(datas[j - 1]) < 0;
    }

    private void exch(int i, int j) {
        Comparable temp = datas[i - 1];
        datas[i - 1] = datas[j - 1];
        datas[j - 1] = temp;
    }

    /**
     * 将最后一个元素上浮到合适位置
     */
    private void swim(int i) {
        while (i / 2 >= 1) {
            if (less(i, i / 2)) {
                break;
            }
            exch(i, i / 2);
            i = i / 2;
        }
    }

    /**
     * 将元素下沉到合适位置
     * @param i
     */
    private void sink(int i) {
        while (i * 2 <= N) {
            // 取左边子
            int j = i * 2;
            // 如果右边子比左边子大，取右边子
            if (j < N && less(j, j + 1)) {
                j++;
            }

            // 将子中较大的与父比较
            if (!less(i, j)) {
                break;
            }
            exch(i, j);
            i = j;
        }
    }

    /**
     * 在末尾增加一个元素
     * @param data
     */
    public void insert(Comparable data) {
        if (N >= datas.length) {
            throw new RuntimeException("full..");
        }
        datas[N++] = data;
        swim(N);
    }

    public Comparable max() {
        return datas[0];
    }

    public Comparable delMax() {
        exch(1, N);
        Comparable max = datas[N - 1];
        datas[--N] = null;
        sink(1);
        return max;
    }

    public static void main(String[] args) {
        MaxPQ testContent = new MaxPQ(10);
        testContent.insert(20);
        testContent.insert(21);
        testContent.insert(21);
        testContent.insert(21);
        testContent.insert(21);
        testContent.insert(21);
        System.out.println(testContent.max());
        System.out.println(testContent.delMax());
        System.out.println(testContent.max());
        System.out.println(testContent.delMax());
    }

}
