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

    public void benchmark_NN4() {
        {
            int fr_ii;
            for ( fr_ii = 0; fr_ii < p - 4; fr_ii += 2) {
                variables[fr_ii] = fr_ii;
                fr_ii++;
                variables[fr_ii] = fr_ii;
                fr_ii++;
                variables[fr_ii] = fr_ii;
                variables[fr_ii + 1] = variables[fr_ii];
            }
            for (int i = fr_ii; i < p; i++) {
                variables[i] = i;
            }
        }
    }

    public void benchmark_NN34() {
        {
            int fr_ii;
            for ( fr_ii = 0; fr_ii < p - 4; fr_ii += 4) {
                variables[fr_ii] = fr_ii;
                variables[fr_ii + 1] = fr_ii;
                variables[fr_ii + 2] = fr_ii;
                variables[fr_ii + 3] = fr_ii;
            }
            for (int i = fr_ii; i < p; i++) {
                variables[i] = i;
            }
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

    public void benchmark_MN4() {
        {
            variables[0] = 0;
            int fr_ii;
            for (fr_ii = 2; fr_ii < p - 4; fr_ii += 2) {
                variables[fr_ii] = fr_ii;
                fr_ii++;
                variables[fr_ii] = fr_ii;
                fr_ii+=2;
                variables[fr_ii] = fr_ii;
                variables[fr_ii - 1] = (variables[fr_ii - 2] + variables[fr_ii]) >> 1;
            }
            for (int i = fr_ii; i < p; i++) {
                variables[i] = i;
            }
        }
    }

    public void benchmark_MN34() {
        {
            variables[0] = 0;
            int fr_ii;
            for (fr_ii = 4; fr_ii < p - 4; fr_ii += 2) {
                variables[fr_ii] = fr_ii;
                variables[fr_ii - 1] = (variables[fr_ii] * 3 >> 2) + (variables[fr_ii - 4] >> 2);
                variables[fr_ii - 2] = (variables[fr_ii] + variables[fr_ii - 4]) >> 1;
                variables[fr_ii - 3] = (variables[fr_ii - 4] * 3 >> 2) + (variables[fr_ii] >> 2);
            }
            for (int i = fr_ii; i < p; i++) {
                variables[i] = i;
            }
        }
    }

}
