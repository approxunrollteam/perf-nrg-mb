package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_util_fst_BytesStore$2_readBytes_447 {

    public int len = 100;
    public byte[] b = new byte[100];
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
        {
            int fr_ii;
            for ( fr_ii = 0; fr_ii < len - 1; fr_ii += 2) {
                b[offset + fr_ii] = readByte();
                b[offset + fr_ii + 1] = b[offset + fr_ii];
            }
            for (int i = fr_ii; i < len; i++) {
                b[offset + i] = readByte();
            }
        }
    }

    public void benchmark_NN4() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < len - 4; fr_ii += 2) {
                b[offset + fr_ii] = readByte();
                fr_ii++;
                b[offset + fr_ii] = readByte();
                fr_ii++;
                b[offset + fr_ii] = readByte();
                b[offset + fr_ii + 1] = b[offset + fr_ii];
            }
            for (int i = fr_ii; i < len; i++) {
                b[offset + i] = readByte();
            }
        }
    }

    public void benchmark_NN34() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < len - 4; fr_ii += 4) {
                int k = offset + fr_ii;
                byte v = readByte();
                b[k] = v;
                b[k + 1] = v;
                b[k + 2] = v;
                b[k + 3] = v;
            }
            for (int i = fr_ii; i < len; i++) {
                b[offset + i] = readByte();
            }
        }
    }

    public void benchmark_MN() {
        {
            b[offset] = readByte();
            int fr_ii;
            for (fr_ii = 2; fr_ii < len - 1; fr_ii += 2) {
                b[offset + fr_ii] = readByte();
                b[offset + fr_ii + 1] = (byte) ((b[offset + fr_ii] + b[offset + fr_ii - 2]) >> 1);
            }
            for (int i = fr_ii; i < len; i++) {
                b[offset + i] = readByte();
            }
        }
    }

    public void benchmark_MN4() {
        {
        b[offset] = readByte();
        int fr_ii;
        for (fr_ii = 0; fr_ii < len - 4; fr_ii++) {
            b[offset + fr_ii] = readByte();
            fr_ii++;
            b[offset + fr_ii] = readByte();
            fr_ii += 2;
            b[offset + fr_ii] = readByte();
            b[offset + fr_ii - 1] = (byte) ((b[offset + fr_ii] + b[offset + fr_ii - 2]) >> 1);
        }
        for (int i = fr_ii; i < len; i++) {
            b[offset + i] = readByte();
        }
    }
    }

    public void benchmark_MN34() {
        {
        b[offset] = readByte();
        int fr_ii;
        for (fr_ii = 4; fr_ii < len - 4; fr_ii+=4) {
            b[offset + fr_ii] = readByte();
            b[offset + fr_ii - 1] = (byte) ((b[offset + fr_ii] * 3 >> 2) + (b[offset + fr_ii - 4] >> 2));
            b[offset + fr_ii - 2] = (byte) ((b[offset + fr_ii - 4] + b[offset + fr_ii - 4]) >> 1);
            b[offset + fr_ii - 3] = (byte) ((b[offset + fr_ii] * 3 >> 2) + (b[offset + fr_ii - 4] >> 2));
        }
        for (int i = fr_ii; i < len; i++) {
            b[offset + i] = readByte();
        }
    }
    }

}
