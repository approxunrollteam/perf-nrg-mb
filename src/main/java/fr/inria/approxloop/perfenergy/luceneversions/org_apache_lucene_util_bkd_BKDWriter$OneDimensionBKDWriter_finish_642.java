package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_util_bkd_BKDWriter$OneDimensionBKDWriter_finish_642 {

    public int[] arr = new int[100];
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
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < leafBlockFPs.size() - 1; fr_ii += 2) {
                arr[fr_ii] = leafBlockFPs.get(fr_ii);
                arr[fr_ii + 1] = arr[fr_ii];
            }
            for (int i = fr_ii; i < leafBlockFPs.size(); i++) {
                arr[i] = leafBlockFPs.get(i);
            }
        }
    }

    public void benchmark_NN4() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < leafBlockFPs.size() - 4; fr_ii += 2) {
                arr[fr_ii] = leafBlockFPs.get(fr_ii);
                fr_ii++;
                arr[fr_ii] = leafBlockFPs.get(fr_ii);
                fr_ii++;
                arr[fr_ii] = leafBlockFPs.get(fr_ii);
                arr[fr_ii + 1] = arr[fr_ii];
            }
            for (int i = fr_ii; i < leafBlockFPs.size(); i++) {
                arr[i] = leafBlockFPs.get(i);
            }
        }
    }

    public void benchmark_NN34() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < leafBlockFPs.size() - 4; fr_ii += 4) {
                arr[fr_ii] = leafBlockFPs.get(fr_ii);
                arr[fr_ii + 1] = arr[fr_ii];
                arr[fr_ii + 2] = arr[fr_ii];
                arr[fr_ii + 3] = arr[fr_ii];
            }
            for (int i = fr_ii; i < leafBlockFPs.size(); i++) {
                arr[i] = leafBlockFPs.get(i);
            }
        }
    }

    public void benchmark_MN() {
        {
            arr[0] = leafBlockFPs.get(0);
            int fr_ii;
            for (fr_ii = 2; fr_ii < leafBlockFPs.size(); fr_ii += 2) {
                arr[fr_ii] = leafBlockFPs.get(fr_ii);
                arr[fr_ii - 1] = (arr[fr_ii - 2] + arr[fr_ii]) / 2;
            }
            for (int i = fr_ii; i < leafBlockFPs.size(); i++) {
                arr[i] = leafBlockFPs.get(i);
            }
        }
    }

    public void benchmark_MN34() {
        {
            arr[0] = leafBlockFPs.get(0);
            int fr_ii;
            for (fr_ii = 4; fr_ii < leafBlockFPs.size() - 4; fr_ii += 4) {
                arr[fr_ii] = leafBlockFPs.get(fr_ii);
                arr[fr_ii - 1] = (arr[fr_ii - 4] * 3 >> 2) + (arr[fr_ii] >> 2);
                arr[fr_ii - 2] = (arr[fr_ii - 4] + arr[fr_ii]) >> 1;
                arr[fr_ii - 3] = (arr[fr_ii - 4] >> 2) + (arr[fr_ii] * 3 >> 2);
            }
            for (int i = fr_ii; i < leafBlockFPs.size(); i++) {
                arr[i] = leafBlockFPs.get(i);
            }
        }
    }

    public void benchmark_MN4() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < leafBlockFPs.size() - 4; fr_ii++) {
                arr[fr_ii] = leafBlockFPs.get(fr_ii);
                fr_ii++;
                arr[fr_ii] = leafBlockFPs.get(fr_ii);
                fr_ii += 2;
                arr[fr_ii] = leafBlockFPs.get(fr_ii);
                arr[fr_ii - 1] = (arr[fr_ii - 2] + arr[fr_ii]) >> 1;
            }
            for (int i = fr_ii; i < leafBlockFPs.size(); i++) {
                arr[i] = leafBlockFPs.get(i);
            }
        }
    }

}
