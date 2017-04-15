package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_util_fst_ReverseBytesReader_readBytes_36 {

    public int len = 100;
    public int pos = 0;
    public int[] b = new int[100];
    public int[] bytes = new int[200];
    public int offset = 0;

    public org_apache_lucene_util_fst_ReverseBytesReader_readBytes_36() {
        Random r = new Random();
        for (int i = 0; i < 200; i++) {
            bytes[i] = r.nextInt();
        }
    }

    public void benchmark() {
        pos = 10;
        for (int i = 0; i < len; i++) {
            b[offset + i] = bytes[pos--];
        }
    }

    public void benchmark_PERF() {
        pos = 10;
        for (int i = 0; i < len; i += 2) {
            b[offset + i] = bytes[pos--];
        }
    }

    public void benchmark_NN() {
        pos = 10;
        for (int i = 0; i < len - 1; i += 2) {
            int k = offset + i;
            b[k] = bytes[pos--];
            b[k + 1] = b[k];
        }
    }

    public void benchmark_NN34() {
        pos = 10;
        for (int i = 0; i < len - 1; i += 4) {
            int k = offset + i;
            b[k] = bytes[pos--];
            b[k + 1] = b[k];
            b[k + 2] = b[k];
            b[k + 3] = b[k];
        }
        for (int i = len - 1; i < len; i ++) {
            b[offset + i] = bytes[pos--];
        }
    }

    public void benchmark_NN4() {
        pos = 10;
        for (int i = 0; i < len - 1; i += 2) {
            int k = offset + i;
            b[k] = bytes[pos--];
            i++;
            k = offset + i;
            b[k] = bytes[pos--];
            i++;
            k = offset + i;
            b[k] = bytes[pos--];
            b[k + 1] = b[k];
        }
        for (int i = len - 1; i < len; i++) {
            b[offset + i] = bytes[pos--];
        }
    }

    public void benchmark_MN() {
        pos = 10;
        for (int i = 0; i < 1; i ++) {
            b[i] = bytes[pos--];
        }
        for (int i = 4; i < len - 4; i += 4) {
            int k = offset + i;
            b[k] = bytes[pos--];
            b[k - 1] = (b[k] * 3 >> 4 + b[k - 4]) >> 2;
            b[k - 2] = (b[k] + b[k - 4]) >> 1;
            b[k - 3] = (b[k - 4] * 3 >> 4 + b[k]) >> 2;
        }
        for (int i = len - 4; i < len; i ++) {
            b[i] = bytes[pos--];
        }
    }

    public void benchmark_MN34() {
        pos = 10;
        b[0] = bytes[pos--];
        for (int i = 4; i < len; i += 2) {
            int k = offset + i;
            b[k] = bytes[pos--];
            b[k - 1] = b[k] * 3 >> 2 + b[k - 4] >> 2;
            b[k - 2] = (b[k] + b[k - 4]) >> 1;
            b[k - 3] = b[k - 4] * 3 >> 2 + b[k] >> 2;
        }
    }

    public void benchmark_MN4() {
        pos = 10;
        for (int i = 0; i < 1; i ++) {
            int k = offset + i;
            b[k] = bytes[pos--];
        }
        for (int i = 2; i < len; i += 2) {
            int k = offset + i;
            b[k] = bytes[pos--];

            i++;

            k = offset + i;
            b[k] = bytes[pos--];

            pos--;

            i+= 2;
            k = offset + i;
            b[k] = bytes[pos--];
            b[k - 1] = (b[k] + b[k - 2]) >> 1;
        }
    }

}
