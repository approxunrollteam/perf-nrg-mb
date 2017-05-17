package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_classification_DecisionTree_Unknown_967 {
    int k = 1000;
    int n = 10;
    double[] posteriori = new double[k];
    int[] count = new int[k];
    int[] falseChildPosteriori = new int[k];
    public int tc;
    public int fc;

    public smile_classification_DecisionTree_Unknown_967() {
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
        for (int i = 0; i < k - 1; i+=2) {
            posteriori[i] = (double) count[i] / n;
            posteriori[i + 1] = posteriori[i];
        }
        for (int i = k - 1; i < k; i++) {
            posteriori[i] = (double) count[i] / n;
        }
    }

    public void benchmark_NN4() {
        int fr_ii;
        for (fr_ii = 0; fr_ii < k - 4; fr_ii+=2) {
            posteriori[fr_ii] = (double) count[fr_ii] / n; fr_ii++;
            posteriori[fr_ii] = (double) count[fr_ii] / n; fr_ii++;
            posteriori[fr_ii] = (double) count[fr_ii] / n;
            posteriori[fr_ii + 1] = posteriori[fr_ii];
        }
        for (int i = fr_ii; i < k; i++) {
            posteriori[i] = (double) count[i] / n;
        }
    }

    public void benchmark_NN34() {
        int fr_ii;
        for (fr_ii = 0; fr_ii < k - 4; fr_ii+=4) {
            posteriori[fr_ii] = (double) count[fr_ii] / n;
            posteriori[fr_ii + 1] = posteriori[fr_ii];
            posteriori[fr_ii + 2] = posteriori[fr_ii];
            posteriori[fr_ii + 3] = posteriori[fr_ii];
        }
        for (int i = fr_ii; i < k; i++) {
            posteriori[i] = (double) count[i] / n;
        }
    }

    public void benchmark_MN() {
        for (int i = 0; i < 1; i++) {
            posteriori[i] = (double) count[i] / n;
        }
        for (int i = 2; i < k - 1; i+=2) {
            posteriori[i] = (double) count[i] / n;
            posteriori[i - 1] = (posteriori[i] + posteriori[i - 2]) * 0.5;
        }
        for (int i = k - 1; i < k; i++) {
            posteriori[i] = (double) count[i] / n;
        }
    }

    public void benchmark_MN4() {
        int fr_ii;
        for (fr_ii = 0; fr_ii < k - 4; fr_ii+=2) {
            posteriori[fr_ii] = (double) count[fr_ii] / n;
            fr_ii++;
            posteriori[fr_ii] = (double) count[fr_ii] / n;
            fr_ii+=2;
            posteriori[fr_ii] = (double) count[fr_ii] / n;
            posteriori[fr_ii - 1] = (posteriori[fr_ii] + posteriori[fr_ii - 2]) * 0.5;
        }
        for (int i = fr_ii; i < k; i++) {
            posteriori[i] = (double) count[i] / n;
        }
    }

    public void benchmark_MN34() {
        posteriori[0] = (double) count[0] / n;
        int fr_ii;
        for (fr_ii = 4; fr_ii < k - 4; fr_ii+=4) {
            posteriori[fr_ii] = (double) count[fr_ii] / n;
            posteriori[fr_ii - 1] = (posteriori[fr_ii] * 0.75f + posteriori[fr_ii - 4] * 0.25f);
            posteriori[fr_ii - 2] = (posteriori[fr_ii] + posteriori[fr_ii - 4]) * 0.5f;
            posteriori[fr_ii - 3] = (posteriori[fr_ii] * 0.25f + posteriori[fr_ii - 4] * 0.75f);
        }
        for (int i = fr_ii; i < k; i++) {
            posteriori[i] = (double) count[i] / n;
        }
    }

}
