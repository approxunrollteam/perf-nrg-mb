package fr.inria.approxloop.perfenergy.smileversions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_data_DataSet_291 {

    private static class WithX {
        double x;
        public WithX(double x) {
            this.x = x;
        }
    }

    int n = 1000;
    double[] a = new double[n];
    List<WithX> withX = new ArrayList<>();
    public smile_data_DataSet_291() {

        withX = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            withX.add(new WithX(r.nextDouble()));
        }
    }

    public WithX get(int i) {
        return withX.get(i);
    }

    public void benchmark() {
        for (int i = 0; i < n; i++) {
            a[i] = get(i).x;
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < n; i+=2) {
            a[i] = get(i).x;
        }
    }

    public void benchmark_NN() {
        for (int i = 0; i < n - 1; i++) {
            a[i] = get(i).x;
            a[i + 1] = a[i];
        }
        for (int i = n - 1; i < n; i++) {
            a[i] = get(i).x;
        }
    }
/*
    public void benchmark_MN() {
        for (int i = 0; i < 1; i++) {
            a[i] = get(i).x;
        }
        for (int i = 2; i < n - 1; i++) {
            a[i] = get(i).x;
            a[i - 1] = (a[i - 2] + a[i]) * 0.5;
        }
        for (int i = n - 1; i < n; i++) {
            a[i] = get(i).x;
        }
    }*/
}
