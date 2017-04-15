package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_util_bkd_BKDWriter$OneDimensionBKDWriter_finish_642 {

    public int[] arr;
    public List<Integer> leafBlockFPs;

    public org_apache_lucene_util_bkd_BKDWriter$OneDimensionBKDWriter_finish_642() {
        leafBlockFPs = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            leafBlockFPs.add(r.nextInt());
            arr[i] = r.nextInt();
        }
    }

    public void benchmark() {
        for (int i = 0; i < leafBlockFPs.size(); i++) {
            arr[i] = leafBlockFPs.get(i);
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < leafBlockFPs.size(); i++) {
            arr[i] = leafBlockFPs.get(i);
        }
    }

    public void benchmark_NN() {
        for (int i = 0; i < leafBlockFPs.size() - 1; i += 2) {
            arr[i] = leafBlockFPs.get(i);
            arr[i + 1] = arr[i];
        }
    }

    public void benchmark_MN() {
        arr[0] = leafBlockFPs.get(0);
        for (int i = 2; i < leafBlockFPs.size(); i += 2) {
            arr[i] = leafBlockFPs.get(i);
            arr[i - 1] = (arr[i - 2] + arr[i]) / 2;
        }
    }

}
