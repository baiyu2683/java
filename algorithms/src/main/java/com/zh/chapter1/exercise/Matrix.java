package com.zh.chapter1.exercise;

/**
 *
 * @Author zh2683
 */
public class Matrix {

    /**
     * 向量点乘
     * @param x
     * @param y
     * @return
     */
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length)
            throw new IllegalArgumentException();
        double result = 0;
        for (int i = 0 ; i < x.length ; i++) {
            result += x[i] * y[i];
        }
        return result;
    }
//
//    /**
//     * 矩阵乘积（错的。。）
//     * @param a
//     * @param b
//     * @return
//     */
//    public static double[][] mult(double[][] a, double[][] b) {
//        double[][] result = new double[b.length][b[0].length];
//        for (int i = 0 ; i < a.length ; i++) {
//            for (int j = 0 ; j < a[0].length ; j++) {
//                result[j][i] = a[i][j] * b[j][i];
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 转置矩阵
//     * @param a
//     * @return
//     */
//    public static double[][] transpose(double[][] a) {
//        double[][] result = new double[a[0].length][a.length];
//        for (int i = 0 ; i < a.length ; i++) {
//            for (int j = 0 ; j < a[0].length ; j++) {
//                result[j][i] = a[i][j];
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 矩阵和向量之积
//     * @param a
//     * @param x
//     * @return
//     */
//    public static double[] mult(double[][] a, double[] x) {
//        for (int i = 0 ; i < x.length ; i++) {
//            x[i] = a[i][0] * x[i];
//        }
//        return x;
//    }
//
//    public static double[] mult(double[] x, double[][] a) {
//        return null;
//    }

}
