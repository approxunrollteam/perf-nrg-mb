package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_util_fst_BytesStore$2_readBytes_447 {

    public int len = 100;
    public int[] b = new int[100];
    public int offset = 0;

    public byte readByte() {
        Random r = new Random();
        return (byte) r.nextInt(256);
    }

    public void benchmark() {
        int offset = 0;
        for (int i = 0; i < len; i++) {
            b[offset + i] = readByte();
        }
    }

    public void benchmark_PERF() {
        int offset = 0;
        for (int i = 0; i < len; i += 2) {
            b[offset + i] = readByte();
        }
    }

    public void benchmark_NN() {
        for (int i = 0; i < len - 1; i += 2) {
            b[offset + i] = readByte();
            b[offset + i + 1] = b[offset + i];
        }
    }

    public void benchmark_MN() {
        b[offset] = readByte();
        for (int i = 2; i < len - 1; i += 2) {
            b[offset + i] = readByte();
            b[offset + i + 1] = (b[offset + i] + b[offset + i - 2]) >> 1;
        }
    }

}
