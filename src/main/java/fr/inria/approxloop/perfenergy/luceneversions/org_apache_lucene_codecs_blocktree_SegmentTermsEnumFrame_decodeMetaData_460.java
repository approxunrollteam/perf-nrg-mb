package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_codecs_blocktree_SegmentTermsEnumFrame_decodeMetaData_460 {

    private final HasFr ste;
    public long[] longs = new long[100];
    org_apache_lucene_codecs_blocktree_SegmentTermsEnumFrame_decodeMetaData_460 bytesReader;

    public org_apache_lucene_codecs_blocktree_SegmentTermsEnumFrame_decodeMetaData_460() {
        bytesReader = this;
        ste = new HasFr();
    }

    public static class HaslongsSize {
        public int longsSize = 100;
    }

    public static class HasFr {
        public HaslongsSize fr = new HaslongsSize();
    }

    //ste.fr.longsSize

    public long readVLong() {
        Random r = new Random();
        return r.nextLong();
    }

    public void benchmark() {
        for (int i = 0; i < ste.fr.longsSize; i++) {
            longs[i] = bytesReader.readVLong();
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < ste.fr.longsSize; i += 2) {
            longs[i] = bytesReader.readVLong();
        }
    }

    public void benchmark_NN() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < ste.fr.longsSize - 1; fr_ii += 2) {
                longs[fr_ii] = bytesReader.readVLong();
                longs[fr_ii + 1] = longs[fr_ii];
            }
            for (int i = fr_ii; i < ste.fr.longsSize; i++) {
                longs[i] = bytesReader.readVLong();
            }
        }
    }

    public void benchmark_NN34() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < ste.fr.longsSize - 4; fr_ii += 4) {
                longs[fr_ii] = bytesReader.readVLong();
                longs[fr_ii + 1] = longs[fr_ii];
                longs[fr_ii + 2] = longs[fr_ii];
                longs[fr_ii + 3] = longs[fr_ii];
            }
            for (int i = fr_ii; i < ste.fr.longsSize; i++) {
                longs[i] = bytesReader.readVLong();
            }
        }
    }

    public void benchmark_NN4() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < ste.fr.longsSize - 4; fr_ii += 2) {
                longs[fr_ii] = bytesReader.readVLong();
                fr_ii++;
                longs[fr_ii] = bytesReader.readVLong();
                fr_ii++;
                longs[fr_ii] = bytesReader.readVLong();
                longs[fr_ii + 1] = longs[fr_ii];
            }
            for (int i = fr_ii; i < ste.fr.longsSize; i++) {
                longs[i] = bytesReader.readVLong();
            }
        }
    }


    public void benchmark_MN() {
        {
            longs[0] = bytesReader.readVLong();
            int fr_ii;
            for (fr_ii = 2; fr_ii < ste.fr.longsSize - 1; fr_ii += 2) {
                longs[fr_ii] = bytesReader.readVLong();
                longs[fr_ii + 1] = (byte) ((longs[fr_ii] + longs[fr_ii - 2]) >> 1);
            }
            for (int k = fr_ii; fr_ii < ste.fr.longsSize - 1; fr_ii ++) {
                longs[k] = bytesReader.readVLong();
            }
        }
    }

    public void benchmark_MN34() {
        {
            longs[0] = bytesReader.readVLong();
            int fr_ii;
            for (fr_ii = 4; fr_ii < ste.fr.longsSize - 4; fr_ii += 4) {
                longs[fr_ii] = bytesReader.readVLong();
                longs[fr_ii - 1] = (byte) ((longs[fr_ii] * 3 >> 2) + (longs[fr_ii - 4] >> 1));
                longs[fr_ii - 2] = (byte) ((longs[fr_ii] + longs[fr_ii - 4]) >> 1);
                longs[fr_ii - 3] = (byte) ((longs[fr_ii - 4] * 3 >> 2) + (longs[fr_ii] >> 1));
            }
            for (int k = fr_ii; k < ste.fr.longsSize; k++) {
                longs[k] = bytesReader.readVLong();
            }
        }
    }

    public void benchmark_MN4() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < ste.fr.longsSize - 4; fr_ii++) {
                longs[fr_ii] = bytesReader.readVLong();
                fr_ii++;
                longs[fr_ii] = bytesReader.readVLong();
                fr_ii += 2;
                longs[fr_ii] = bytesReader.readVLong();
                longs[fr_ii - 1] = (byte) ((longs[fr_ii] + longs[fr_ii - 2]) >> 1);
            }
            for (int i = fr_ii; i < ste.fr.longsSize; i++) {
                longs[i] = bytesReader.readVLong();
            }
        }
    }

}
