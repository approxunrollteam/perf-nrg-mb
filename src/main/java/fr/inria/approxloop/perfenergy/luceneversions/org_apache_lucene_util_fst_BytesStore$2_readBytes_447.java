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

    public void benchmark_NN4() {
        for (int i = 0; i < len - 4; i += 2) {
            b[offset + i] = readByte();
            i++;
            b[offset + i] = readByte();
            i++;
            b[offset + i] = readByte();
            i++;
            b[offset + i + 1] = b[offset + i];
        }
        for (int i = len - 4; i < len; i++) {
            b[offset + i] = readByte();
        }
    }

    public void benchmark_NN34() {
        for (int i = 0; i < len - 4; i += 4) {
            b[offset + i] = readByte();
            b[offset + i + 1] = b[offset + i];
            b[offset + i + 2] = b[offset + i];
            b[offset + i + 3] = b[offset + i];
        }
        for (int i = len - 4; i < len; i++) {
            b[offset + i] = readByte();
        }
    }

    public void benchmark_MN() {
        b[offset] = readByte();
        for (int i = 2; i < len - 1; i += 2) {
            b[offset + i] = readByte();
            b[offset + i + 1] = (b[offset + i] + b[offset + i - 2]) >> 1;
        }
    }

    public void benchmark_MN4() {
        b[offset] = readByte();
        for (int i = 0; i < len - 4; i++) {
            b[offset + i] = readByte();
            i++;
            b[offset + i] = readByte();
            i+=2;
            b[offset + i] = readByte();
            b[offset + i - 1] = (b[offset + i] + b[offset + i - 2]) >> 1;
        }
        for (int i = len - 4; i < len; i++) {
            b[offset + i] = readByte();
        }
    }

    public void benchmark_MN34() {
        b[offset] = readByte();
        for (int i = 0; i < 1; i++) {
            b[offset + i] = readByte();
        }
        for (int i = 4; i < len - 4; i++) {
            b[offset + i] = readByte();
            b[offset + i - 1] = b[offset + i] * 3 >> 2 + b[offset + i - 4] >> 2;
            b[offset + i - 2] = (b[offset + i - 4] + b[offset + i - 4]) >> 1;
            b[offset + i - 3] = b[offset + i] * 3 >> 2 + b[offset + i - 4] >> 2;
        }
        for (int i = len - 4; i < len; i++) {
            b[offset + i] = readByte();
        }
    }

}
