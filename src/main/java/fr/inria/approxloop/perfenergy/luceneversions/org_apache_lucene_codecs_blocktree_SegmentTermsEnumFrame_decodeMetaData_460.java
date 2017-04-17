package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_codecs_blocktree_SegmentTermsEnumFrame_decodeMetaData_460 {

    public byte[] b = new byte[10];
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

    public void benchmark_NN34() {
        for (int i = 0; i < len - 4; i += 4) {
            b[i] = readByte();
            b[i + 1] = b[i];
            b[i + 2] = b[i];
            b[i + 3] = b[i];
        }
        for (int i = len - 4; i < len; i ++) {
            b[i] = readByte();
        }
    }

    public void benchmark_NN4() {
        for (int i = 0; i < len - 4; i += 2) {
            b[i] = readByte();
            i++;
            b[i] = readByte();
            i++;
            b[i] = readByte();
            b[i + 1] = b[i];
        }
        for (int i = len - 4; i < len; i ++) {
            b[i] = readByte();
        }
    }


    public void benchmark_MN() {
        b[0] = readByte();
        for (int i = 2; i < len - 1; i += 2) {
            b[i] = readByte();
            b[i + 1] = (byte)((b[i] + b[i - 2]) >> 1);
        }
    }

    public void benchmark_MN34() {
        b[0] = readByte();
        for (int i = 0; i < 1; i ++) {
            b[i] = readByte();
        }
        for (int i = 4; i < len - 4; i += 4) {
            b[i] = readByte();
            b[i - 1] = (byte)((b[i] * 3 >> 2) + (b[i - 4] >> 1));
            b[i - 2] = (byte)((b[i] + b[i - 4]) >> 1);
            b[i - 3] = (byte)((b[i - 4] * 3 >> 2) + (b[i] >> 1));
        }
        for (int i = len - 4; i < len; i ++) {
            b[i] = readByte();
        }
    }

    public void benchmark_MN4() {
        b[0] = readByte();
        for (int i = 0; i < len - 4; i ++) {
            b[i] = readByte();
            i++;
            b[i] = readByte();
            i+=2;
            b[i] = readByte();
            b[i - 1] = (byte)((b[i] + b[i - 2]) >> 1);
        }
        for (int i = len - 4; i < len; i ++) {
            b[i] = readByte();
        }
    }

}
