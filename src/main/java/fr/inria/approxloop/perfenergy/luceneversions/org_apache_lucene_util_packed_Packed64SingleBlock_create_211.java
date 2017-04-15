package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_util_packed_Packed64SingleBlock_create_211 {

    public long[] reader_blocks = new long[10];

    public long readLong() {
        Random r = new Random();
        return r.nextLong();
    }

    public void benchmark() {

        for (int i = 0; i < (reader_blocks.length); i++) {
            reader_blocks[i] = readLong();
        }
    }

    public void benchmark_PERF() {

        for (int i = 0; i < (reader_blocks.length); i += 2) {
            reader_blocks[i] = readLong();
        }
    }

    public void benchmark_NN() {

        for (int i = 0; i < (reader_blocks.length) - 1; i += 2) {
            reader_blocks[i] = readLong();
            reader_blocks[i + 1] = reader_blocks[i];
        }
    }

    public void benchmark_MN() {
        reader_blocks[0] = readLong();
        for (int i = 2; i < (reader_blocks.length) - 1; i += 2) {
            reader_blocks[i] = readLong();
            reader_blocks[i - 1] = (reader_blocks[i] + reader_blocks[i - 2]) >> 1;
        }
    }

}
