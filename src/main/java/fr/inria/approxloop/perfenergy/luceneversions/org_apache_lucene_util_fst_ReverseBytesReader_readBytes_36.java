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

    public void benchmark_org_apache_lucene_util_fst_ReverseBytesReader_readBytes_36() {
        pos = 10;
        for (int i = 0; i < len; i++) {
            b[offset + i] = bytes[pos--];
        }
    }

    public void benchmark_org_apache_lucene_util_fst_ReverseBytesReader_readBytes_36_PERF() {
        pos = 10;
        for (int i = 0; i < len; i += 2) {
            b[offset + i] = bytes[pos--];
        }
    }

    public void benchmark_org_apache_lucene_util_fst_ReverseBytesReader_readBytes_36_NN() {
        for (int i = 0; i < len - 1; i += 2) {
            int k = offset + i;
            b[k] = bytes[pos--];
            b[k + 1] = b[k];
        }
    }

    public void benchmark_org_apache_lucene_util_fst_ReverseBytesReader_readBytes_36_MN() {
        b[0] = bytes[pos--];
        for (int i = 2; i < len; i += 2) {
            int k = offset + i;
            b[k] = bytes[pos--];
            b[k - 1] = (b[k] + b[k - 2]) << 1;
        }
    }

}
