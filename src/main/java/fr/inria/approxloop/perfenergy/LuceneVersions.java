package fr.inria.approxloop.perfenergy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  Class containing a conceptual version of the loop generated by the compiler.
 *
 *  These versions are used to validate the compiler optimization. I other words, the compiled versions
 *  must produce the same output as these ones.
 *
 *  Created by elmarce on 12/02/17.
 */
@Deprecated
public class LuceneVersions {


    public final int[] jjrounds = new int[38];
    public int[] arr;
    public int[] prefixStarts = new int[100];
    public char[] text = new char[100];

    public int pos = 0;
    public List<Integer> pending;
    public int[] intUptos;

    public List<Integer> leafBlockFPs;
    public int len = 10;
    public int[] b = new int[10];



    public int offset = 0;
    private int[] bytes;

    public LuceneVersions() {
        pending = new ArrayList<>();
        leafBlockFPs = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            prefixStarts[i] = r.nextInt();
            text[i] = (char) r.nextInt(255);
            pending.add(r.nextInt());
            leafBlockFPs.add(r.nextInt());
            arr = prefixStarts;
        }
        intUptos = prefixStarts;
        bytes = new int[20];
    }



    public byte readByte() {
        Random r = new Random();
        return (byte) r.nextInt(256);
    }

    public long readLong() {
        Random r = new Random();
        return r.nextLong();
    }






    /**
     * org.apache.lucene.util.fst.BytesStore$2::readBytes
     * Similar to: org.apache.lucene.util.packed.Packed64SingleBlock::create
     * Similar to: org.apache.lucene.util.fst.ReverseBytesReader::readBytes
     * /
     * public void benchmark_BytesStore_readBytes() {
     * int offset = 0;
     * for(int i=0;i<len;i++) {
     * b[offset+i] = readByte();
     * }
     * }
     * <p>
     * public void benchmark_CompressingStoredFieldsWriter_flush() {
     * final int[] lengths = endOffsets;
     * for (int i = numBufferedDocs - 1; i > 0; --i) {
     * lengths[i] = endOffsets[i] - endOffsets[i - 1];
     * assert lengths[i] >= 0;
     * }
     * }
     * <p>
     * <p>
     * <p>
     * /**
     * org.apache.lucene.util.bkd.BKDWriter$OneDimensionBKDWriter.finish
     */


    public void benchmark_org_apache_lucene_queryparser_classic_QueryParserTokenManager_ReInitRounds_993() {
        int i;
        for (i = 38; --i > 0;)
            jjrounds[i] = 0x80000000;
    }

    public void benchmark_org_apache_lucene_queryparser_classic_QueryParserTokenManager_ReInitRounds_993_PERF() {
        int i;
        for (i = 37; i > 0; i -= 2)
            jjrounds[i] = 0x80000000;
    }
    public void benchmark_org_apache_lucene_queryparser_classic_QueryParserTokenManager_ReInitRounds_993_NN() {
        int i;
        for (i = 37; i > 0; i -= 2) {
            jjrounds[i] = 0x80000000;
            jjrounds[i - 1] = jjrounds[i];
        }
    }

    public void benchmark_org_apache_lucene_queryparser_classic_QueryParserTokenManager_ReInitRounds_993_MN() {
        int i;
        jjrounds[37] = 0x80000000;
        for (i = 35; i >= 1; i -= 2) {
            jjrounds[i] = 0x80000000;
            jjrounds[i + 1] = (jjrounds[i] + jjrounds[i + 2]) >> 1;
        }
    }

    public int microbenchmarkCount() {
        return 10;
    }
}
