package fr.inria.approxloop.perfenergy.smileversions;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_classification_AdaBoost_Unknown_275 {
    int n = 1000;
    double[] w = new double[n];
    public double W = 10;

    public void benchmark() {
        for (int i = 0; i < n; i++) {
            w[i] /= W;
        }
    }

    public void benchmark_NN() {
        for (int i = 0; i < n - 1; i+=2) {
            w[i] /= W;
            w[i + 1] = w[i];
        }
        for (int i = n - 1; i < n; i++) {
            w[i] /= W;
        }
    }

    public void benchmark_NN4() {
        for (int i = 0; i < n - 4; i+=2) {
            w[i] /= W;
            i++;
            w[i] /= W;
            i++;
            w[i] /= W;
            w[i + 1] = w[i];
        }
        for (int i = n - 1; i < n; i++) {
            w[i] /= W;
        }
    }

    public void benchmark_NN34() {
        int fr_ii;
        for (fr_ii = 0; fr_ii < n - 4; fr_ii++) {
            double k = w[fr_ii] / W;
            w[fr_ii] = k;
            fr_ii++;
            w[fr_ii] = k;
            fr_ii++;
            w[fr_ii] = k;
            fr_ii++;
            w[fr_ii] = k;
        }
        for (int i = fr_ii; i < n; i++) {
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
        for (int i = 2; i < n - 1; i+=2) {
            w[i] /= W;
            w[i - 1] = (w[i - 2] + w[i]) * 0.5f;
        }
        for (int i = n - 1; i < n; i++) {
            w[i] /= W;
        }
    }

    public void benchmark_MN34() {
        {
            w[0] /= W;
            int fr_ii;
            for (fr_ii = 4; fr_ii < n - 4; fr_ii += 2) {
                w[fr_ii] /= W;
                w[fr_ii - 1] = (w[fr_ii] * 0.75f + w[fr_ii - 4] * 0.25f);
                w[fr_ii - 2] = (w[fr_ii] + w[fr_ii - 4]) * 0.5f;
                w[fr_ii - 3] = (w[fr_ii] * 0.25f + w[fr_ii - 4] * 0.75f);
            }
            for (int i = fr_ii; i < n; i++) {
                w[i] /= W;
            }
        }
    }

    public void benchmark_MN4() {
        {
            w[0] /= W;
            for (int i = 2; i < n - 4; i += 2) {
                w[i] /= W;
                i++;
                w[i] /= W;
                i += 2;
                w[i] /= W;
                w[i - 1] = (w[i - 2] + w[i]) * 0.5f;
            }
            for (int i = n - 1; i < n; i++) {
                w[i] /= W;
            }
        }
    }

}
