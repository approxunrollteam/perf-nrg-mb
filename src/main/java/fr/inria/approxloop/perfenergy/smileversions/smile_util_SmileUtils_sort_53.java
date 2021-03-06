package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_util_SmileUtils_sort_53 {
    int n = 1000;
    double[] a = new double[n];
    double[][] x = new double[n][n];

    public smile_util_SmileUtils_sort_53() {
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
        for (int i = 0; i < n; i+=2) {
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

    public void benchmark_NN4() {
        {
            int fr_ii;    int j = 0;
            for (fr_ii = 0; fr_ii < n - 4; fr_ii += 2) {
                a[fr_ii] = x[fr_ii][j];
                fr_ii++;
                a[fr_ii] = x[fr_ii][j];
                fr_ii++;
                a[fr_ii] = x[fr_ii][j];
                a[fr_ii + 1] = a[fr_ii];
            }
            for (int i = fr_ii; i < n; i++) {
                a[fr_ii] = x[fr_ii][j];
            }
        }
    }

    public void benchmark_NN34() {
        int fr_ii; int j = 0;
        for (fr_ii = 0; fr_ii < n - 4; fr_ii+=4) {
            a[fr_ii] = x[fr_ii][j];
            a[fr_ii + 1] = a[fr_ii];
            a[fr_ii + 2] = a[fr_ii];
            a[fr_ii + 3] = a[fr_ii];
        }
        for (int i = fr_ii; i < n; i++) {
            a[fr_ii] = x[fr_ii][j];
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

    public void benchmark_MN4() {
        int j = 0;
        {
            int fr_ii;

            for (fr_ii = 0; fr_ii < n - 4; fr_ii += 2) {
                a[fr_ii] = x[fr_ii][j];
                fr_ii++;
                a[fr_ii] = x[fr_ii][j];
                fr_ii += 2;
                a[fr_ii] = x[fr_ii][j];
                a[fr_ii - 1] = (a[fr_ii] + a[fr_ii - 2]) * 0.5;
            }
            for (int i = fr_ii; i < n; i++) {
                a[fr_ii] = x[fr_ii][j];
            }
        }
    }

    public void benchmark_MN34() {
        int j = 0;
        {
            a[0] = x[0][0];
            int fr_ii;
            for (fr_ii = 4; fr_ii < n - 4; fr_ii += 4) {
                a[fr_ii] =  x[fr_ii][j];
                a[fr_ii - 1] = (a[fr_ii] * 0.75f + a[fr_ii - 4] * 0.25f);
                a[fr_ii - 2] = (a[fr_ii] + a[fr_ii - 4]) * 0.5f;
                a[fr_ii - 3] = (a[fr_ii] * 0.25f + a[fr_ii - 4] * 0.75f);
            }
            for (int i = fr_ii; i < n; i++) {
                a[i] =  x[i][j];
            }
        }
    }    

}
