package fr.inria.approxloop.perfenergy.smileversions;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_classifiers_AdaBoost_275 {
    int n = 1000;
    double[] w = new double[n];
    public double W = 10;

    public void benchmark() {
        for (int i = 0; i < n; i++) {
            w[i] /= W;
        }
    }

    public void benchmark_NN() {
        for (int i = 0; i < n - 1; i++) {
            w[i] /= W;
            w[i + 1] = w[i];
        }
        for (int i = n - 1; i < n; i++) {
            w[i] /= W;
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < n; i+=2) {
            w[i] /= W;
        }
    }

    public void benchmark_MN() {
        for (int i = 0; i < 1; i++) {
            w[i] /= W;
        }
        for (int i = 2; i < n - 1; i++) {
            w[i] /= W;
            w[i - 1] = (w[i - 2] + w[i]) * 0.5f;
        }
        for (int i = n - 1; i < n; i++) {
            w[i] /= W;
        }
    }

}
