package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_classifiers_DecisionTree_708 {
    int k = 1000;
    double[] trueChildPosteriori = new double[k];
    double[] falseChildPosteriori = new double[k];
    public int tc;
    public int fc;

    public smile_classifiers_DecisionTree_708() {
        Random r = new Random();
        for (int i = 0; i < k; i++) {
            trueChildPosteriori[i] = r.nextDouble();
            falseChildPosteriori[i] = r.nextDouble();
        }
        tc = 10;
        fc = 5;
    }

    public void benchmark() {
        for (int i = 0; i < k; i++) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < k; i+=2) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
        }
    }


    public void benchmark_NN() {
        for (int i = 0; i < k - 1; i+=2) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
            trueChildPosteriori[i + 1] = trueChildPosteriori[i];
            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
            falseChildPosteriori[i + 1] = falseChildPosteriori[i];
        }
        for (int i = k - 1; i < k; i++) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
        }
    }

    public void benchmark_MN() {
        for (int i = 0; i < 1; i++) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
        }
        for (int i = 2; i < k - 1; i+=2) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
            trueChildPosteriori[i - 1] = (trueChildPosteriori[i - 2] + trueChildPosteriori[i]) *0.5;
            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
            falseChildPosteriori[i - 1] = (falseChildPosteriori[i - 2] + falseChildPosteriori[i]) *0.5;
        }
        for (int i = k - 1; i < k; i++) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
        }
    }

}
