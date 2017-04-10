package fr.inria.approxloop.perfenergy.smileversions;

import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_classifiers_DecisionTree_494 {
    int p = 1000;
    int[] variables = new int[p];

    public void benchmark() {
        for (int i = 0; i < p; i++) {
            variables[i] = i;
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < p; i+=2) {
            variables[i] = i;
        }
    }

    public void benchmark_NN() {
        for (int i = 0; i < p - 1; i+= 2) {
            variables[i] = i;
            variables[i + 1] = variables[i];
        }
        for (int i = p - 1; i < p; i++) {
            variables[i] = i;
        }
    }

    public void benchmark_MN() {
        for (int i = 0; i < 1; i++) {
            variables[i] = i;
        }
        for (int i = 2; i < p - 1; i+= 2) {
            variables[i] = i;
            variables[i - 1] = (variables[i - 2] + variables[i]) >> 1;
        }
        for (int i = p - 1; i < p; i++) {
            variables[i] = i;
        }
    }

}
