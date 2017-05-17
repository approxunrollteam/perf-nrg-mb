package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_classification_AdaBoost_Unknown_324 {
    int n = 1000;
    double[] importance = new double[n];
    double[] imp = new double[n];
    public double W = 10;

    public smile_classification_AdaBoost_Unknown_324() {
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

    public void benchmark_NN4() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < imp.length - 4; fr_ii += 2) {
                importance[fr_ii] += imp[fr_ii];
                fr_ii++;
                importance[fr_ii] += imp[fr_ii];
                fr_ii ++;
                importance[fr_ii] += imp[fr_ii];
                importance[fr_ii + 1] = importance[fr_ii];
            }
            for (int i = fr_ii; i < imp.length; i++)
                importance[i] += imp[i];
        }
    }

    public void benchmark_NN34() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < imp.length - 4; fr_ii ++) {
                double k = importance[fr_ii] + imp[fr_ii];
                importance[fr_ii] =k;
                fr_ii++;
                importance[fr_ii] =k;
                fr_ii++;
                importance[fr_ii] =k;
                fr_ii++;
                importance[fr_ii] =k;
            }
            for (int i = fr_ii; i < imp.length; i++)
                importance[i] += imp[i];
        }
    }

    public void benchmark_MN() {
        for (int i = 0; i < 1; i++) {
            importance[i] += imp[i];
        }
        for (int i = 2; i < imp.length - 1; i+=2) {
            importance[i] += imp[i];
            importance[i - 1] = (importance[i - 2] + importance[i]) * 0.5;
        }
    }

    public void benchmark_MN4() {
        importance[0] += imp[0];
        int fr_ii;
        for (fr_ii = 2; fr_ii < imp.length-4; fr_ii+=2) {
            importance[fr_ii] += imp[fr_ii];
            fr_ii++;
            importance[fr_ii] += imp[fr_ii];
            fr_ii+=2;
            importance[fr_ii] += imp[fr_ii];
            importance[fr_ii - 1] = (importance[fr_ii - 2] + importance[fr_ii]) * 0.5;
        }
        for (int i = fr_ii; i < imp.length; i++) {
            importance[i] += imp[i];
        }
    }

    public void benchmark_MN34() {
        importance[0] += imp[0];
        int fr_ii;
        for (fr_ii = 4; fr_ii < imp.length-4; fr_ii+=4) {
            importance[fr_ii] += imp[fr_ii];
            importance[fr_ii - 1] = (importance[fr_ii] * 0.75f + importance[fr_ii - 4] * 0.25f);
            importance[fr_ii - 2] = (importance[fr_ii] + importance[fr_ii - 4]) * 0.5f;
            importance[fr_ii - 3] = (importance[fr_ii] * 0.25f + importance[fr_ii - 4] * 0.75f);
        }
        for (int i = fr_ii; i < imp.length; i++) {
            importance[i] += imp[i];
        }
    }
    

}
