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

    public void benchmark_NN4() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < n - 4; fr_ii += 2) {
                w[fr_ii] = 1.0;
                fr_ii++;
                w[fr_ii] = 1.0;
                fr_ii++;
                w[fr_ii] = 1.0;
                w[fr_ii + 1] = w[fr_ii];
            }
            for (int i = fr_ii; i < n; i += 2) {
                w[i] = 1.0;
            }
        }
    }

    public void benchmark_NN34() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < n - 4; fr_ii += 2) {
                double k = 1.0;
                w[fr_ii] = k;
                fr_ii++;
                w[fr_ii] = k;
                fr_ii++;
                w[fr_ii] = k;
                fr_ii++;
                w[fr_ii] = k;
            }
            for (int i = fr_ii; i < n; i += 2) {
                w[i] = 1.0;
            }
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
        for (int i = 2; i < n - 4; i+=2) {
            w[i] = 1.0;
            w[i - 1] = (w[i - 2] + w[i]) * 0.5f;
        }
        for (int i = n-1; i < n; i++) {
            w[i] = 1.0;
        }
    }

    public void benchmark_MN4() {
        {
            w[0] = 1.0;
            int fr_ii;
            for (fr_ii = 4; fr_ii < n - 4; fr_ii += 2) {
                w[fr_ii] = 1.0;
                w[fr_ii - 1] = (w[fr_ii] * 0.75f + w[fr_ii - 4] * 0.25f);
                w[fr_ii - 2] = (w[fr_ii] + w[fr_ii - 4]) * 0.5f;
                w[fr_ii - 3] = (w[fr_ii] * 0.25f + w[fr_ii - 4] * 0.75f);
            }
            for (int i = fr_ii; i < n; i++) {
                w[i] = 1.0;
            }
        }
    }

    public void benchmark_MN34() {
        {
            w[0] = 1.0;
            int fr_ii;
            for (fr_ii = 2; fr_ii < n - 4; fr_ii += 2) {
                w[fr_ii] = 1.0;
                fr_ii++;
                w[fr_ii] = 1.0;
                fr_ii += 2;
                w[fr_ii] = 1.0;
                w[fr_ii - 1] = (w[fr_ii - 2] + w[fr_ii]) * 0.5f;
            }
            for (int i = n - 1; i < n; i++) {
                w[i] = 1.0;
            }
        }
    }    
    
}
