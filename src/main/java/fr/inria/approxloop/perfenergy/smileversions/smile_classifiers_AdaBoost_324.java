package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_classifiers_AdaBoost_324 {
    int n = 1000;
    double[] importance = new double[n];
    double[] imp = new double[n];
    public double W = 10;

    public smile_classifiers_AdaBoost_324() {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            imp[i] = r.nextDouble();
        }
    }

    public void benchmark() {
        for (int i = 0; i < imp.length; i++) {
            importance[i] += imp[i];
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < imp.length; i+=2) {
            importance[i] += imp[i];
        }
    }

    public void benchmark_NN() {
        for (int i = 0; i < imp.length - 1; i+=2) {
            importance[i] += imp[i];
            importance[i + 1] = importance[i];
        }
        for (int i = imp.length - 1; i < imp.length; i++)
            importance[i] += imp[i];
    }

    public void benchmark_MN() {
        for (int i = 0; i < 1; i++) {
            importance[i] += imp[i];
        }
        for (int i = 2; i < imp.length; i+=2) {
            importance[i] += imp[i];
            importance[i - 1] = (importance[i - 2] + importance[i]) * 0.5;
        }
    }

}
