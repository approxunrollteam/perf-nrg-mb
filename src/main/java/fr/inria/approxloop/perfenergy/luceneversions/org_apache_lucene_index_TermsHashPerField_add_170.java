package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_index_TermsHashPerField_add_170 {

    public int intUptoStart = 1;
    public int[] intUptos = new int[100];
    public final static int FIRST_LEVEL_SIZE = 5;
    public int streamCount = 2;
    public int bytePool_byteOffset = -(1 << 15);

    public int newSlice(final int size) {
        Random r = new Random();
        return r.nextInt(size);
    }

    public void benchmark() {
        for (int i = 0; i < streamCount; i++) {
            final int upto = newSlice(FIRST_LEVEL_SIZE);
            intUptos[intUptoStart + i] = upto + bytePool_byteOffset;
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < streamCount; i += 2) {
            final int upto = newSlice(FIRST_LEVEL_SIZE);
            intUptos[intUptoStart + i] = upto + bytePool_byteOffset;
        }
    }

    public void benchmark_NN() {

        for (int i = 0; i < streamCount - 1; i++) {
            final int upto = newSlice(FIRST_LEVEL_SIZE);
            intUptos[intUptoStart + i] = upto + bytePool_byteOffset;
            intUptos[intUptoStart + i + 1] = intUptos[intUptoStart + i];
        }
    }

    public void benchmark_MN() {
        final int upto1 = newSlice(FIRST_LEVEL_SIZE);
        intUptos[intUptoStart] = upto1 + bytePool_byteOffset;
        for (int i = 2; i < streamCount - 1; i++) {
            final int upto = newSlice(FIRST_LEVEL_SIZE);
            intUptos[intUptoStart + i] = upto + bytePool_byteOffset;
            intUptos[intUptoStart + i + 1] = (intUptos[intUptoStart + i] + intUptos[intUptoStart + i + 2]) >> 1;
        }
    }

}
