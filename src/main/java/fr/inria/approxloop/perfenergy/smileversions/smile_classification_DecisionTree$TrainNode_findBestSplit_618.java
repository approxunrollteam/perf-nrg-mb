package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_classification_DecisionTree$TrainNode_findBestSplit_618 {
    int k = 1000;
    int[] falseCount = new int[k];
    int[] count = new int[k];
    int[] trueCount = new int[k];

    public smile_classification_DecisionTree$TrainNode_findBestSplit_618() {
        Random r = new Random();
        for (int i = 0; i < k; i++) {
            trueCount[i] = r.nextInt();
            count[i] = r.nextInt();
        }
    }

    public void benchmark() {
        for (int l = 0; l < k; l++) {
            falseCount[l] = count[l] - trueCount[l];
        }
    }

    public void benchmark_PERF() {
        for (int l = 0; l < k; l+=2) {
            falseCount[l] = count[l] - trueCount[l];
        }
    }

    public void benchmark_NN() {
        for (int l = 0; l < k - 1; l+= 2) {
            falseCount[l] = count[l] - trueCount[l];
            falseCount[l + 1] = falseCount[l];
        }
        for (int l = k - 1; l < k; l++) {
            falseCount[l] = count[l] - trueCount[l];
        }
    }

    public void benchmark_NN4() {
        int fr_ii;
        for (fr_ii = 0; fr_ii < k - 1; fr_ii+= 2) {
            falseCount[fr_ii] = count[fr_ii] - trueCount[fr_ii];
            fr_ii++;
            falseCount[fr_ii] = count[fr_ii] - trueCount[fr_ii];
            fr_ii++;
            falseCount[fr_ii] = count[fr_ii] - trueCount[fr_ii];
            falseCount[fr_ii + 1] = falseCount[fr_ii];
        }
        for (int l = fr_ii; l < k; l++) {
            falseCount[l] = count[l] - trueCount[l];
        }
    }

    public void benchmark_NN34() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < k - 4; fr_ii += 4) {
                int k = count[fr_ii] - trueCount[fr_ii];
                falseCount[fr_ii] = k;
                fr_ii++;
                falseCount[fr_ii] = k;
                fr_ii++;
                falseCount[fr_ii] = k;
                fr_ii++;
                falseCount[fr_ii] = k;
                fr_ii++;
            }
            for (int l = fr_ii; l < k; l++) {
                falseCount[l] = count[l] - trueCount[l];
            }
        }
    }

    public void benchmark_MN() {
        for (int l = 0; l < 1; l+= 2) {
            falseCount[l] = count[l] - trueCount[l];
        }
        for (int l = 2; l < k - 1; l+= 2) {
            falseCount[l] = count[l] - trueCount[l];
            falseCount[l - 1] = (falseCount[l - 2] + falseCount[l]) >> 1;
        }
        for (int l = k - 1; l < k; l++) {
            falseCount[l] = count[l] - trueCount[l];
        }
    }

    public void benchmark_MN4() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < k - 4; fr_ii += 2) {
                falseCount[fr_ii] = count[fr_ii] - trueCount[fr_ii];
                fr_ii++;
                falseCount[fr_ii] = count[fr_ii] - trueCount[fr_ii];
                fr_ii += 2;
                falseCount[fr_ii] = count[fr_ii] - trueCount[fr_ii];
                falseCount[fr_ii - 1] = (falseCount[fr_ii - 2] + falseCount[fr_ii]) >> 1;
            }
            for (int l = fr_ii; l < k; l++) {
                falseCount[l] = count[l] - trueCount[l];
            }
        }
    }

    public void benchmark_MN34() {

        {
            int fr_ii = 0;

            falseCount[fr_ii] = count[fr_ii] - trueCount[fr_ii];
            for (fr_ii = 4; fr_ii < k - 4; fr_ii += 4) {
                falseCount[fr_ii] = count[fr_ii] - trueCount[fr_ii];
                falseCount[fr_ii - 1] = (falseCount[fr_ii] * 3 >> 2) + (falseCount[fr_ii - 4] >> 2);
                falseCount[fr_ii - 2] = (falseCount[fr_ii] + falseCount[fr_ii - 4]) >> 1;
                falseCount[fr_ii - 3] = (falseCount[fr_ii - 4] * 3 >> 2) + (falseCount[fr_ii] >> 2);
            }
            for (int l = fr_ii; l < k; l++) {
                falseCount[l] = count[l] - trueCount[l];
            }
        }
    }

}
