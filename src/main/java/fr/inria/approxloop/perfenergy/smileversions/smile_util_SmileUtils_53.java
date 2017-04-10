package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_util_SmileUtils_53 {
    int n = 1000;
    double[] a = new double[n];
    double[][] x = new double[n][n];

    public smile_util_SmileUtils_53() {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x[i][j] = r.nextDouble();
            }
        }
    }

    public void benchmark() {
        int j = 0;
        for (int i = 0; i < n; i++) {
            a[i] = x[i][j];
        }
    }

    public void benchmark_PERF() {
        int j = 0;
        for (int i = 0; i < n; i++) {
            a[i] = x[i][j];
        }
    }

    public void benchmark_NN() {
        int j = 0;
        for (int i = 0; i < n - 1; i+=2) {
            a[i] = x[i][j];
            a[i + 1] = a[i];
        }
    }

    public void benchmark_MN() {
        int j = 0;
        for (int i = 0; i < 1; i++) {
            a[i] = x[i][j];
        }
        for (int i = 2; i < n - 1; i+=2) {
            a[i] = x[i][j];
            a[i - 1] = (a[i - 2] + a[i]) * 0.5f;
        }
        for (int i = n - 1; i < n; i++) {
            a[i] = x[i][j];
        }
    }

}
