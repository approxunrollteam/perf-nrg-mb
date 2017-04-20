package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_util_fst_ReverseBytesReader_readBytes_36 {

    public int len = 100;
    public int pos = 0;
    public byte[] b = new byte[100];
    public byte[] bytes = new byte[200];
    public int offset = 0;

    public org_apache_lucene_util_fst_ReverseBytesReader_readBytes_36() {
        Random r = new Random();
        for (int i = 0; i < 200; i++) {
            bytes[i] = (byte) r.nextInt();
        }
    }

    public void benchmark() {
        pos = len;
        for (int i = 0; i < len; i++) {
            b[offset + i] = bytes[pos--];
        }
    }

    public void benchmark_PERF() {
        pos = len;
        for (int i = 0; i < len; i += 2) {
            b[offset + i] = bytes[pos--];
        }
    }

    public void benchmark_NN() {
        pos = len;
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < len - 1; fr_ii += 2) {
                int k = offset + fr_ii;
                b[k] = bytes[pos--];
                b[k + 1] = b[k];
            }
            for (int i = fr_ii; i < len; i++) {
                b[offset + i] = bytes[pos--];
            }
        }
    }

    public void benchmark_NN34() {
        {
            pos = len;
            int fr_ii;
            for (fr_ii = 0; fr_ii < len - 4; fr_ii += 4) {
                int k = offset + fr_ii;
                b[k] = bytes[pos--];
                b[k + 1] = b[k];
                pos--;
                b[k + 2] = b[k];
                pos--;
                b[k + 3] = b[k];
                pos--;
            }
            for (int i = fr_ii; i < len; i++) {
                b[offset + i] = bytes[pos--];
            }
        }
    }

    public void benchmark_NN4() {
        pos = len;
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < len - 4; fr_ii += 2) {
                b[offset + fr_ii] = bytes[pos--];
                fr_ii++;
                b[offset + fr_ii] = bytes[pos--];
                fr_ii++;
                int k = offset + fr_ii;
                b[k] = bytes[pos--];
                pos--;
                b[k + 1] = b[k];
            }
            for (int i = fr_ii; i < len; i++) {
                b[offset + i] = bytes[pos--];
            }
        }
    }

    public void benchmark_MN() {
        pos = len;
        {
            b[0] = bytes[pos--];
            int fr_ii;
            for (fr_ii = 2; fr_ii < len - 4; fr_ii += 2) {
                int k = offset + fr_ii;
                b[k] = bytes[pos--];
                b[k - 1] = (byte) ((b[k] + b[k - 2]) >> 1);
                pos--;
            }
            for (int i = fr_ii; i < len; i++) {
                b[i] = bytes[pos--];
            }
        }
    }

    public void benchmark_MN34() {
        pos = len;
        {
            b[offset] = bytes[pos--];
            int fr_ii;
            for (fr_ii = 4; fr_ii < len - 4; fr_ii += 4) {
                int k = offset + fr_ii;
                b[k] = bytes[pos--];
                b[k - 1] = (byte) ((b[k] * 3 >> 2) + (b[k - 4] >> 2));
                pos--;
                b[k - 2] = (byte) ((b[k] + b[k - 4]) >> 1);
                pos--;
                b[k - 3] = (byte) ((b[k - 4] * 3 >> 2) + (b[k] >> 2));
                pos--;
            }
            for (int i = fr_ii; i < len; i++) {
                b[offset + i] = bytes[pos--];
            }
        }
    }

    public void benchmark_MN4() {
        pos = len;
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < len - 4; fr_ii += 2) {
                int k = offset + fr_ii;
                b[k] = bytes[pos--];

                fr_ii++;
                k = offset + fr_ii;
                b[k] = bytes[pos--];
                pos--;

                fr_ii += 2;
                k = offset + fr_ii;
                b[k] = bytes[pos--];
                b[k - 1] = (byte) ((b[k] + b[k - 2]) >> 1);
            }
            for (int k = fr_ii; k < len; k++) {
                b[offset + k] = bytes[pos--];
            }
        }
    }

}
