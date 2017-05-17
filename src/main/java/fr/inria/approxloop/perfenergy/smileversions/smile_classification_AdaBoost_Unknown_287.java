package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_classification_AdaBoost_Unknown_287 {
    int n = 1000;
    double[] w = new double[n];
    public int t = 0;
    boolean[] err = new boolean[n];
    double[][] x = new double[n][n];
    int[] y = new int[n];
    FakeDecisionTree[] trees = new FakeDecisionTree[2];

    public class FakeDecisionTree {
        public int predict(double[] x) {
            int result = 0;
            for (int i = 0; i < n; i++) {
                result += x[i] * x[i] - x[i];
            }
            return result;
        }
    }

    public smile_classification_AdaBoost_Unknown_287() {
        trees[0] = new FakeDecisionTree();
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x[i][j] = r.nextDouble();
            }
        }
    }

    public void benchmark() {
        int t = 0;
        for (int i = 0; i < n; i++) {
            err[i] = trees[t].predict(x[i]) != y[i];
        }
    }

    public void benchmark_PERF() {
        int t = 0;
        for (int i = 0; i < n; i+=2) {
            err[i] = trees[t].predict(x[i]) != y[i];
        }
    }

    public void benchmark_NN() {
        int t = 0;
        for (int i = 0; i < n - 1; i+=2) {
            err[i] = trees[t].predict(x[i]) != y[i];
            err[i + 1] = err[i];
        }
        for (int i = n - 1; i < n; i++) {
            err[i] = trees[t].predict(x[i]) != y[i];
        }
    }

    public void benchmark_NN34() {
        int t = 0;
        for (int i = 0; i < n - 4; i+=4) {
            err[i] = trees[t].predict(x[i]) != y[i];
            err[i + 1] = err[i];
            err[i + 2] = err[i];
            err[i + 3] = err[i];
        }
        for (int i = n - 4; i < n; i++) {
            err[i] = trees[t].predict(x[i]) != y[i];
        }
    }

    public void benchmark_NN4() {
        int t = 0;
        for (int i = 0; i < n - 4; i+=4) {
            err[i] = trees[t].predict(x[i]) != y[i];
            i++;
            err[i] = trees[t].predict(x[i]) != y[i];
            i++;
            err[i] = trees[t].predict(x[i]) != y[i];
            i++;
            err[i] = err[i - 1];
        }
        for (int i = n - 4; i < n; i++) {
            err[i] = trees[t].predict(x[i]) != y[i];
        }
    }
    //YEAH
    public void benchmark_MN() {
        int t = 0;
        for (int i = 0; i < 1; i++) {
            err[i] = trees[t].predict(x[i]) != y[i];
        }
        for (int i = 2; i < n - 1; i+=2) {
            err[i] = trees[t].predict(x[i]) != y[i];
            err[i - 1] = (((err[i - 2] ? 1 : 0) + (err[i - 2] ? 1 : 0)) << 1) < 1;
        }
        for (int i = n - 1; i < n; i++) {
            err[i] = trees[t].predict(x[i]) != y[i];
        }
    }

    public void benchmark_MN4() {
        int t = 0;
        for (int i = 0; i < n - 4; i+=4) {
            err[i] = trees[t].predict(x[i]) != y[i];
            err[i] = trees[t].predict(x[i]) != y[i];
            i+=2;
            err[i] = trees[t].predict(x[i]) != y[i];
            err[i - 1] = (((err[i - 2] ? 1 : 0) + (err[i] ? 1 : 0)) << 1) < 1;
        }
        for (int i = n - 4; i < n; i++) {
            err[i] = trees[t].predict(x[i]) != y[i];
        }
    }

    public void benchmark_MN34() {
        int t = 0;
        for (int i = 0; i < 1; i++) {
            err[i] = trees[t].predict(x[i]) != y[i];
        }
        for (int i = 4; i < n - 4; i += 4) {
            err[i] = trees[t].predict(x[i]) != y[i];
            err[i - 3] = ((err[i - 4] ? 1 : 0) * 0.75f + (err[i] ? 1 : 0) * 0.25) < 1;
            err[i - 2] = (((err[i - 4] ? 1 : 0) + (err[i] ? 1 : 0)) << 1) < 1;
            err[i - 1] = ((err[i - 4] ? 1 : 0) * 0.25f + (err[i] ? 1 : 0) * 0.75) < 1;
        }
        for (int i = n - 4; i < n; i++) {
            err[i] = trees[t].predict(x[i]) != y[i];
        }
    }


}
