package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_index_TermsHashPerField_add_170 {

    public int intUptoStart = 1;
    public int[] intUptos = new int[400];
    public int FIRST_LEVEL_SIZE = 5;
    public int streamCount = 200;
    public int byteOffset = -(1 << 15);
    public org_apache_lucene_index_TermsHashPerField_add_170 bytePool;

    public org_apache_lucene_index_TermsHashPerField_add_170() {
        bytePool = this;
    }

    public int newSlice(final int size) {
        Random r = new Random();
        return r.nextInt(size);
    }

    public void benchmark() {
        for(int i=0;i<streamCount;i++) {
            final int upto = bytePool.bytePool.newSlice(FIRST_LEVEL_SIZE);
            intUptos[intUptoStart+i] = upto + bytePool.byteOffset;
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < streamCount; i += 2) {
            final int upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
            intUptos[intUptoStart + i] = upto + bytePool.byteOffset;
        }
    }

    public void benchmark_NN() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < streamCount - 1; fr_ii++) {
                final int upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart + fr_ii] = upto + bytePool.byteOffset;
                intUptos[intUptoStart + fr_ii + 1] = intUptos[intUptoStart + fr_ii];
            }
            for(int i=fr_ii;i<streamCount;i++) {
                final int upto = bytePool.bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart+i] = upto + bytePool.byteOffset;
            }
        }
    }

    public void benchmark_NN34() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < streamCount - 4; fr_ii += 4) {
                final int upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart + fr_ii] = upto + bytePool.byteOffset;
                intUptos[intUptoStart + fr_ii + 1] = intUptos[intUptoStart + fr_ii];
                intUptos[intUptoStart + fr_ii + 2] = intUptos[intUptoStart + fr_ii];
                intUptos[intUptoStart + fr_ii + 3] = intUptos[intUptoStart + fr_ii];
            }
            for (int k = fr_ii; k < streamCount; k++) {
                final int upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart + k] = upto + bytePool.byteOffset;
            }
        }
    }

    public void benchmark_NN4() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < streamCount - 4; fr_ii += 2) {
                int upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart + fr_ii] = upto + bytePool.byteOffset;
                fr_ii++;
                upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart + fr_ii] = upto + bytePool.byteOffset;
                fr_ii++;
                upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart + fr_ii] = upto + bytePool.byteOffset;
                intUptos[intUptoStart + fr_ii + 1] = intUptos[intUptoStart + fr_ii];
            }
            for (int k = fr_ii; k < streamCount; k++) {
                final int upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart + k] = upto + bytePool.byteOffset;
            }
        }
    }


    public void benchmark_MN() {
        final int upto1 = bytePool.newSlice(FIRST_LEVEL_SIZE);
        {
            intUptos[intUptoStart] = upto1 + bytePool.byteOffset;
            int fr_ii;
            for (fr_ii = 2; fr_ii < streamCount - 1; fr_ii++) {
                final int upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart + fr_ii] = upto + bytePool.byteOffset;
                intUptos[intUptoStart + fr_ii + 1] = (intUptos[intUptoStart + fr_ii] + intUptos[intUptoStart + fr_ii + 2]) >> 1;
            }
            for(int i=fr_ii;i<streamCount;i++) {
                final int upto = bytePool.bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart+i] = upto + bytePool.byteOffset;
            }
        }
    }

    public void benchmark_MN4() {
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < streamCount - 4; fr_ii++) {
                final int upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart + fr_ii] = upto + bytePool.byteOffset;
                fr_ii++;
                intUptos[intUptoStart + fr_ii] = upto + bytePool.byteOffset;
                fr_ii += 2;
                intUptos[intUptoStart + fr_ii] = upto + bytePool.byteOffset;
                intUptos[intUptoStart + fr_ii + 1] = (intUptos[intUptoStart + fr_ii] + intUptos[intUptoStart + fr_ii + 2]) >> 1;
            }
            for (int k = fr_ii; k < streamCount - 1; k++) {
                final int upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart + k] = upto + bytePool.byteOffset;
            }
        }
    }

    public void benchmark_MN34() {
        {
            int upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
            intUptos[intUptoStart + 0] = upto + bytePool.byteOffset;
            int fr_ii;
            for (fr_ii = 4; fr_ii < streamCount - 4; fr_ii += 4) {
                upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart + fr_ii] = upto + bytePool.byteOffset;
                intUptos[intUptoStart + fr_ii - 1] = (intUptos[intUptoStart + fr_ii] * 3 >> 2) + (intUptos[intUptoStart + fr_ii - 4] >> 1);
                intUptos[intUptoStart + fr_ii - 2] = (intUptos[intUptoStart + fr_ii] + intUptos[intUptoStart + fr_ii - 4]) >> 1;
                intUptos[intUptoStart + fr_ii - 3] = (intUptos[intUptoStart + fr_ii - 4] * 3 >> 2) + (intUptos[intUptoStart + fr_ii] >> 1);
            }
            for (int k = fr_ii; k < streamCount; k++) {
                upto = bytePool.newSlice(FIRST_LEVEL_SIZE);
                intUptos[intUptoStart + k] = upto + bytePool.byteOffset;
            }
        }
    }

}
