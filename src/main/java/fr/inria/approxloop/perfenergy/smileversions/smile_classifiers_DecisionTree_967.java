package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_classifiers_DecisionTree_967 {
    int k = 1000;
    int n = 10;
    double[] posteriori = new double[k];
    int[] count = new int[k];
    int[] falseChildPosteriori = new int[k];
    public int tc;
    public int fc;

    public smile_classifiers_DecisionTree_967() {
        Random r = new Random();
        for (int i = 0; i < k; i++) {
            posteriori[i] = r.nextDouble();
            count[i] = r.nextInt();
        }
        tc = 10;
        fc = 5;
    }

    public void benchmark() {
        for (int i = 0; i < k; i++) {
            posteriori[i] = (double) count[i] / n;
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < k; i+=2) {
            posteriori[i] = (double) count[i] / n;
        }
    }

    public void benchmark_NN() {
        for (int i = 0; i < k - 1; i++) {
            posteriori[i] = (double) count[i] / n;
            posteriori[i + 1] = posteriori[i];
        }
        for (int i = k - 1; i < k; i++) {
            posteriori[i] = (double) count[i] / n;
        }
    }

    public void benchmark_MN() {
        for (int i = 0; i < 1; i++) {
            posteriori[i] = (double) count[i] / n;
        }
        for (int i = 2; i < k - 1; i++) {
            posteriori[i] = (double) count[i] / n;
            posteriori[i - 1] = (posteriori[i] + posteriori[i - 2]) * 0.5;
        }
        for (int i = k - 1; i < k; i++) {
            posteriori[i] = (double) count[i] / n;
        }
    }

}
