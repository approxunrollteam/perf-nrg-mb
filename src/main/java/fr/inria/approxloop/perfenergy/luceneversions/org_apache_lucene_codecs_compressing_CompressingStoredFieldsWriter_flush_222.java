package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_codecs_compressing_CompressingStoredFieldsWriter_flush_222 {

    public int[] endOffsets = new int[200];
    public int[] lengths = new int[200];
    public int numBufferedDocs = 170;

    public org_apache_lucene_codecs_compressing_CompressingStoredFieldsWriter_flush_222() {
        Random r = new Random();
        for (int j = 0; j < 20; j++)
            endOffsets[j] = r.nextInt();
    }

    public void benchmark() {
        for (int i = numBufferedDocs - 1; i > 0; i -= 1) {
            lengths[i] = endOffsets[i] - endOffsets[i - 1];
            assert lengths[i] >= 0;
        }
    }

    public void benchmark_PERF() {
        int fr_ii;
        for (fr_ii = numBufferedDocs - 1; fr_ii > 0; fr_ii -= 2) {
            lengths[fr_ii] = endOffsets[fr_ii] - endOffsets[fr_ii - 1];
            assert lengths[fr_ii] >= 0;
        }

    }

    public void benchmark_NN() {
        {
            int fr_ii;
            for (fr_ii = numBufferedDocs - 1; fr_ii > 0; fr_ii -= 2) {
                lengths[fr_ii] = endOffsets[fr_ii] - endOffsets[fr_ii - 1];
                lengths[fr_ii + 1] = lengths[fr_ii];
                assert lengths[fr_ii] >= 0;
            }
            for (int i = fr_ii; i > 0; i -= 1) {
                lengths[i] = endOffsets[i] - endOffsets[i - 1];
                assert lengths[i] >= 0;
            }
        }
    }

    public void benchmark_NN4() {
        {
            int fr_ii;
            for (fr_ii = numBufferedDocs - 1; fr_ii > 4; fr_ii -= 2) {
                lengths[fr_ii] = endOffsets[fr_ii] - endOffsets[fr_ii - 1];
                fr_ii--;
                lengths[fr_ii] = endOffsets[fr_ii] - endOffsets[fr_ii - 1];
                fr_ii--;
                lengths[fr_ii] = endOffsets[fr_ii] - endOffsets[fr_ii - 1];
                lengths[fr_ii + 1] = lengths[fr_ii];
                assert lengths[fr_ii] >= 0;
            }
            for (int i = fr_ii; i > 0; i -= 2) {
                lengths[i] = endOffsets[i] - endOffsets[i - 1];
                assert lengths[i] >= 0;
            }
        }
    }

    public void benchmark_NN34() {
        {
            int fr_ii;
            for (fr_ii = numBufferedDocs - 1; fr_ii > 4; fr_ii -= 4) {
                lengths[fr_ii] = endOffsets[fr_ii] - endOffsets[fr_ii - 1];
                lengths[fr_ii - 1] = lengths[fr_ii];
                lengths[fr_ii - 2] = lengths[fr_ii];
                lengths[fr_ii - 3] = lengths[fr_ii];
                assert lengths[fr_ii] >= 0;
            }
            for (int i = fr_ii; i > 0; i -= 1) {
                lengths[i] = endOffsets[i] - endOffsets[i - 1];
                assert lengths[i] >= 0;
            }
        }
    }

    public void benchmark_MN() {
        {
            lengths[0] = endOffsets[0] - endOffsets[numBufferedDocs];
            int fr_ii;
            for (fr_ii = numBufferedDocs - 2; fr_ii > 0; fr_ii -= 2) {
                lengths[fr_ii] = endOffsets[fr_ii] - endOffsets[fr_ii - 1];
                lengths[fr_ii + 1] = (lengths[fr_ii] + lengths[fr_ii + 2]) >> 1;
                assert lengths[fr_ii] >= 0;
            }
            for (int i = fr_ii; i > 0; i -= 1) {
                lengths[i] = endOffsets[i] - endOffsets[i - 1];
                assert lengths[i] >= 0;
            }
        }
    }

    public void benchmark_MN4() {
        {
            /*
            for (int i = numBufferedDocs - 1; i > 0; i -= 1) {
                lengths[i] = endOffsets[i] - endOffsets[i - 1];
                assert lengths[i] >= 0;
            }
            */
            int fr_ii;
            for (fr_ii = numBufferedDocs - 1; fr_ii > 4; fr_ii -= 1) {
                lengths[fr_ii] = endOffsets[fr_ii] - endOffsets[fr_ii - 1];
                fr_ii--;
                lengths[fr_ii] = endOffsets[fr_ii] - endOffsets[fr_ii - 1];
                fr_ii -= 2;
                lengths[fr_ii] = endOffsets[fr_ii] - endOffsets[fr_ii - 1];
                lengths[fr_ii + 1] = (lengths[fr_ii] + lengths[fr_ii + 2]) >> 1;
                assert lengths[fr_ii] >= 0;
            }
            for (int i = fr_ii; i > 0; i -= 1) {
                lengths[i] = endOffsets[i] - endOffsets[i - 1];
                assert lengths[i] >= 0;
            }
        }
    }

    public void benchmark_MN34() {
        {
            lengths[0] = endOffsets[numBufferedDocs - 1] - endOffsets[numBufferedDocs - 2];
            assert lengths[0] >= 0;
            int fr_ii;
            for (fr_ii = numBufferedDocs - 5; fr_ii > 4; fr_ii -= 4) {
                lengths[fr_ii] = endOffsets[fr_ii] - endOffsets[fr_ii - 1];
                lengths[fr_ii + 1] = (lengths[fr_ii] * 3 >> 2) + (lengths[fr_ii + 4] >> 1);
                lengths[fr_ii + 2] = (lengths[fr_ii] + lengths[fr_ii + 4]) >> 1;
                lengths[fr_ii + 3] = ((lengths[fr_ii + 4] * 3 >> 2) + (lengths[fr_ii]) >> 1);
                assert lengths[fr_ii] >= 0;
            }
            for (int i = fr_ii; i > 0; --i) {
                lengths[i] = endOffsets[i] - endOffsets[i - 1];
                assert lengths[i] >= 0;
            }
        }
    }

}
