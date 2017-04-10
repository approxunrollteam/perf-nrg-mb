package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_classifiers_DecisionTree_618 {
    int k = 1000;
    int[] falseCount = new int[k];
    int[] count = new int[k];
    int[] trueCount = new int[k];

    public smile_classifiers_DecisionTree_618() {
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

}
