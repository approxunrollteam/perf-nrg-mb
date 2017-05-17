package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_classification_DecisionTree$TrainNode_split_708 {
    int k = 1000;
    double[] trueChildPosteriori = new double[k];
    double[] falseChildPosteriori = new double[k];
    public int tc;
    public int fc;

    public smile_classification_DecisionTree$TrainNode_split_708() {
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

    public void benchmark_NN4() {
        {
            int fr_ii;
            for ( fr_ii = 0; fr_ii < k - 4; fr_ii += 2) {
                trueChildPosteriori[fr_ii] = (trueChildPosteriori[fr_ii] + 1) / (tc + k);
                falseChildPosteriori[fr_ii] = (falseChildPosteriori[fr_ii] + 1) / (fc + k);
                fr_ii++;

                trueChildPosteriori[fr_ii] = (trueChildPosteriori[fr_ii] + 1) / (tc + k);
                falseChildPosteriori[fr_ii] = (falseChildPosteriori[fr_ii] + 1) / (fc + k);
                fr_ii++;

                trueChildPosteriori[fr_ii] = (trueChildPosteriori[fr_ii] + 1) / (tc + k);
                falseChildPosteriori[fr_ii] = (falseChildPosteriori[fr_ii] + 1) / (fc + k);
                trueChildPosteriori[fr_ii + 1] = trueChildPosteriori[fr_ii];
                falseChildPosteriori[fr_ii + 1] = falseChildPosteriori[fr_ii];

            }
            for (int i = fr_ii; i < k; i++) {
                trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
                falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
            }
        }
    }

    public void benchmark_NN34() {
        {
        int fr_ii;
        for (fr_ii = 0; fr_ii < k - 4; fr_ii++) {
            double kkkk2 = (trueChildPosteriori[fr_ii] + 1) / (tc + k);
            double kkkk1 = (falseChildPosteriori[fr_ii] + 1) / (fc + k);
            trueChildPosteriori[fr_ii] = kkkk2;
            falseChildPosteriori[fr_ii] = kkkk1;
            fr_ii++;
            trueChildPosteriori[fr_ii] = kkkk2;
            falseChildPosteriori[fr_ii] = kkkk1;
            fr_ii++;
            trueChildPosteriori[fr_ii] = kkkk2;
            falseChildPosteriori[fr_ii] = kkkk1;
            fr_ii++;
            trueChildPosteriori[fr_ii] = kkkk2;
            falseChildPosteriori[fr_ii] = kkkk1;
        }
        for (int i = fr_ii; i < k; i++) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
        }
    }
    }

    public void benchmark_MN() {
        for (int i = 0; i < 1; i++) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
        }
        for (int i = 2; i < k - 1; i+=2) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);

            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
            trueChildPosteriori[i - 1] = (trueChildPosteriori[i - 2] + trueChildPosteriori[i]) *0.5;
            falseChildPosteriori[i - 1] = (falseChildPosteriori[i - 2] + falseChildPosteriori[i]) *0.5;
        }
        for (int i = k - 1; i < k; i++) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
        }
    }

    public void benchmark_MN4() {
        int fr_ii;
        for (fr_ii = 0; fr_ii < k - 4; fr_ii+=2) {
            trueChildPosteriori[fr_ii] = (trueChildPosteriori[fr_ii] + 1) / (tc + k);
            falseChildPosteriori[fr_ii] = (falseChildPosteriori[fr_ii] + 1) / (fc + k);
            fr_ii++;
            trueChildPosteriori[fr_ii] = (trueChildPosteriori[fr_ii] + 1) / (tc + k);
            falseChildPosteriori[fr_ii] = (falseChildPosteriori[fr_ii] + 1) / (fc + k);
            fr_ii+= 2;
            trueChildPosteriori[fr_ii] = (trueChildPosteriori[fr_ii] + 1) / (tc + k);
            falseChildPosteriori[fr_ii] = (falseChildPosteriori[fr_ii] + 1) / (fc + k);

            trueChildPosteriori[fr_ii - 1] = (trueChildPosteriori[fr_ii - 2] + trueChildPosteriori[fr_ii]) *0.5;
            falseChildPosteriori[fr_ii - 1] = (falseChildPosteriori[fr_ii - 2] + falseChildPosteriori[fr_ii]) *0.5;
        }
        for (int i = fr_ii; i < k; i++) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
        }
    }

    public void benchmark_MN34() {
        {
        trueChildPosteriori[0] = (trueChildPosteriori[0] + 1) / (tc + k);
        falseChildPosteriori[0] = (falseChildPosteriori[0] + 1) / (fc + k);
            int fr_ii;
        for ( fr_ii= 4; fr_ii < k - 4; fr_ii+=4) {
            trueChildPosteriori[fr_ii] = (trueChildPosteriori[fr_ii] + 1) / (tc + k);
            falseChildPosteriori[fr_ii] = (falseChildPosteriori[fr_ii] + 1) / (fc + k);

            trueChildPosteriori[fr_ii - 1] = (trueChildPosteriori[fr_ii] * 0.75f + trueChildPosteriori[fr_ii - 4] * 0.25f);
            trueChildPosteriori[fr_ii - 2] = (trueChildPosteriori[fr_ii] + trueChildPosteriori[fr_ii - 4]) * 0.5f;
            trueChildPosteriori[fr_ii - 3] = (trueChildPosteriori[fr_ii] * 0.25f + trueChildPosteriori[fr_ii - 4] * 0.75f);

            falseChildPosteriori[fr_ii - 1] = (falseChildPosteriori[fr_ii] * 0.75f + falseChildPosteriori[fr_ii - 4] * 0.25f);
            falseChildPosteriori[fr_ii - 2] = (falseChildPosteriori[fr_ii] + falseChildPosteriori[fr_ii - 4]) * 0.5f;
            falseChildPosteriori[fr_ii - 3] = (falseChildPosteriori[fr_ii] * 0.25f + falseChildPosteriori[fr_ii - 4] * 0.75f);

        }
        for (int i = fr_ii; i < k; i++) {
            trueChildPosteriori[i] = (trueChildPosteriori[i] + 1) / (tc + k);
            falseChildPosteriori[i] = (falseChildPosteriori[i] + 1) / (fc + k);
        }
        }
    }

}
