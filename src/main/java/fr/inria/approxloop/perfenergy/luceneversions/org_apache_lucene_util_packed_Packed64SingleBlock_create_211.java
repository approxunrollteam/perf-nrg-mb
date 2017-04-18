package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_util_packed_Packed64SingleBlock_create_211 {

    public long[] blocks = new long[10];
    org_apache_lucene_util_packed_Packed64SingleBlock_create_211 reader;
    org_apache_lucene_util_packed_Packed64SingleBlock_create_211 in;
    
    public org_apache_lucene_util_packed_Packed64SingleBlock_create_211() {
        reader = this;
        in = this;
    }

    public long readLong() {
        Random r = new Random();
        return r.nextLong();
    }

    public void benchmark() {
        for (int i = 0; i < (reader.blocks.length); i++) {
            reader.blocks[i] = in.readLong();
        }
    }

    public void benchmark_PERF() {

        for (int i = 0; i < (reader.blocks.length); i += 2) {
            reader.blocks[i] = in.readLong();
        }
    }

    public void benchmark_NN() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < (reader.blocks.length) - 1; fr_ii += 2) {
                reader.blocks[fr_ii] = in.readLong();
                reader.blocks[fr_ii + 1] = reader.blocks[fr_ii];
            }
            for (int i = fr_ii; i < (reader.blocks.length); i++) {
                reader.blocks[i] = in.readLong();
            }
        }
    }

    public void benchmark_NN4() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < (reader.blocks.length) - 4; fr_ii += 2) {
                reader.blocks[fr_ii] = in.readLong();
                fr_ii++;
                reader.blocks[fr_ii] = in.readLong();
                fr_ii++;
                reader.blocks[fr_ii] = in.readLong();
                reader.blocks[fr_ii + 1] = reader.blocks[fr_ii];
            }
            for (int i = fr_ii; i < (reader.blocks.length); i++) {
                reader.blocks[i] = in.readLong();
            }
        }
    }

    public void benchmark_NN34() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < (reader.blocks.length) - 4; fr_ii += 4) {
                reader.blocks[fr_ii] = in.readLong();
                reader.blocks[fr_ii + 1] = reader.blocks[fr_ii];
                reader.blocks[fr_ii + 2] = reader.blocks[fr_ii];
                reader.blocks[fr_ii + 3] = reader.blocks[fr_ii];
            }
            for (int i = fr_ii; i < (reader.blocks.length); i++) {
                reader.blocks[i] = in.readLong();
            }
        }
    }


    public void benchmark_MN() {
        {
            reader.blocks[0] = in.readLong();
            int fr_ii;
            for (fr_ii = 2; fr_ii < (reader.blocks.length) - 1; fr_ii += 2) {
                reader.blocks[fr_ii] = in.readLong();
                reader.blocks[fr_ii - 1] = (reader.blocks[fr_ii] + reader.blocks[fr_ii - 2]) >> 1;
            }
            for (int i = fr_ii; i < (reader.blocks.length); i++) {
                reader.blocks[i] = in.readLong();
            }
        }
    }

    public void benchmark_MN4() {
        {
            reader.blocks[0] = in.readLong();
            int fr_ii;
            for (fr_ii = 0; fr_ii < (reader.blocks.length) - 4; fr_ii++) {
                reader.blocks[fr_ii] = in.readLong();
                fr_ii++;
                reader.blocks[fr_ii] = in.readLong();
                fr_ii += 2;
                reader.blocks[fr_ii] = in.readLong();
                reader.blocks[fr_ii - 1] = (reader.blocks[fr_ii] + reader.blocks[fr_ii - 2]) >> 1;

            }
            for (int i = fr_ii; i < (reader.blocks.length); i++) {
                reader.blocks[i] = in.readLong();
            }
        }
    }

    public void benchmark_MN34() {
        {
            reader.blocks[0] = in.readLong();
            int fr_ii;
            for (fr_ii = 4; fr_ii < (reader.blocks.length) - 4; fr_ii += 4) {
                reader.blocks[fr_ii] = in.readLong();
                reader.blocks[fr_ii - 1] = (reader.blocks[fr_ii] * 3 >> 2) + (reader.blocks[fr_ii - 4] >> 2);
                reader.blocks[fr_ii - 2] = (reader.blocks[fr_ii] + reader.blocks[fr_ii - 4]) >> 1;
                reader.blocks[fr_ii - 3] = (reader.blocks[fr_ii] >> 2) + (reader.blocks[fr_ii - 4] * 3 >> 2);
            }
            for (int i = fr_ii; i < (reader.blocks.length); i++) {
                reader.blocks[i] = in.readLong();
            }
        }
    }

}
