package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_codecs_compressing_CompressingStoredFieldsWriter_flush_222 {

    public int[] endOffsets = new int[20];
    public int numBufferedDocs = 17;

    public org_apache_lucene_codecs_compressing_CompressingStoredFieldsWriter_flush_222() {
        Random r = new Random();
        for (int j = 0; j < 20; j++)
            endOffsets[j] = r.nextInt();
    }

    public void benchmark_org_apache_lucene_codecs_compressing_CompressingStoredFieldsWriter_flush_222() {
        final int[] lengths = endOffsets;

        for (int i = numBufferedDocs - 1; i > 0; i -= 1) {
            lengths[i] = endOffsets[i] - endOffsets[i - 1];
            assert lengths[i] >= 0;
        }
    }

    public void benchmark_org_apache_lucene_codecs_compressing_CompressingStoredFieldsWriter_flush_222_PERF() {
        final int[] lengths = endOffsets;

        for (int i = numBufferedDocs - 1; i > 0; i -= 2) {
            lengths[i] = endOffsets[i] - endOffsets[i - 1];
            assert lengths[i] >= 0;
        }
    }

    public void benchmark_org_apache_lucene_codecs_compressing_CompressingStoredFieldsWriter_flush_222_NN() {
        final int[] lengths = endOffsets;
        for (int i = numBufferedDocs - 1; i > 0; i -= 2) {
            lengths[i] = endOffsets[i] - endOffsets[i - 1];
            lengths[i + 1] = lengths[i];
            assert lengths[i] >= 0;
        }
    }

    public void benchmark_org_apache_lucene_codecs_compressing_CompressingStoredFieldsWriter_flush_222_MN() {
        final int[] lengths = endOffsets;
        lengths[0] = endOffsets[0] - endOffsets[numBufferedDocs];
        for (int i = numBufferedDocs - 2; i > 0; i -= 2) {
            lengths[i] = endOffsets[i] - endOffsets[i - 1];
            lengths[i + 1] = (lengths[i] + lengths[i + 2]) >> 1;
            assert lengths[i] >= 0;
        }
    }

}
