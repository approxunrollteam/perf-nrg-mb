package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 *
 * Created by elmarce on 07/04/17.
 */
public class smile_classifiers_AdaBoost_262 {
    int n = 1000;
    double[] w = new double[n];

    public smile_classifiers_AdaBoost_262() {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            w[i] = r.nextDouble();
        }
    }

    public void benchmark() {
        for (int i = 0; i < n; i++) {
            w[i] = 1.0;
        }
    }

    public void benchmark_NN() {
        for (int i = 0; i < n - 1; i+=2) {
            w[i] = 1.0;
            w[i + 1] = w[i];
        }
        for (int i = n-1; i < n; i+=2) {
            w[i] = 1.0;
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < n; i+=2) {
            w[i] = 1.0;
        }
    }

    public void benchmark_MN() {
        for (int i = 0; i < 1; i++) {
            w[i] = 1.0;
        }
        for (int i = 2; i < n - 1; i+=2) {
            w[i] = 1.0;
            w[i - 1] = (w[i - 2] + w[i]) * 0.5f;
        }
        for (int i = n-1; i < n; i++) {
            w[i] = 1.0;
        }
    }

}
