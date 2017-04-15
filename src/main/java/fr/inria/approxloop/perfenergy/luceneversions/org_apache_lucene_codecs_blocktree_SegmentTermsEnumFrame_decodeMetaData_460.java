package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_codecs_blocktree_SegmentTermsEnumFrame_decodeMetaData_460 {

    public int[] b = new int[10];
    public int len = 10;

    public byte readByte() {
        Random r = new Random();
        return (byte) r.nextInt(256);
    }

    public void benchmark() {
        for (int i = 0; i < len; i++) {
            b[i] = readByte();
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < len; i += 2) {
            b[i] = readByte();
        }
    }

    public void benchmark_NN() {
        for (int i = 0; i < len - 1; i += 2) {
            b[i] = readByte();
            b[i + 1] = b[i];
        }
    }

    public void benchmark_MN() {
        b[0] = readByte();
        for (int i = 2; i < len - 1; i += 2) {
            b[i] = readByte();
            b[i + 1] = (b[i] + b[i - 2]) >> 1;
        }
    }
}
