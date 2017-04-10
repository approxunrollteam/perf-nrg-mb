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
public class LuceneVersions {

    public final static int FIRST_LEVEL_SIZE = 5;
    public final int[] jjrounds = new int[38];
    public int[] arr;
    public int[] prefixStarts = new int[100];
    public char[] text = new char[100];
    public int streamCount = 2;
    public int pos = 0;
    public int intUptoStart = 1;
    public List<Integer> pending;
    public int[] intUptos;
    public int bytePool_byteOffset = -(1 << 15);
    public List<Integer> leafBlockFPs;
    public int len = 10;
    public int[] b = new int[10];
    public long[] reader_blocks = new long[10];
    public int[] endOffsets;
    public int numBufferedDocs = 17;
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

        endOffsets = new int[20];
        for (int j = 0; j < 20; j++) {
            endOffsets[j] = r.nextInt();
        }
        bytes = endOffsets;
    }

    public int newSlice(final int size) {
        Random r = new Random();
        return r.nextInt(size);
    }

    public byte readByte() {
        Random r = new Random();
        return (byte) r.nextInt(256);
    }

    public long readLong() {
        Random r = new Random();
        return r.nextLong();
    }

    public void benchmark_org_apache_lucene_codecs_blocktree_SegmentTermsEnumFrame_decodeMetaData_460() {
        for (int i = 0; i < len; i++) {
            b[i] = readByte();
        }
    }

    public void benchmark_org_apache_lucene_codecs_blocktree_SegmentTermsEnumFrame_decodeMetaData_460_PERF() {
        for (int i = 0; i < len; i += 2) {
            b[i] = readByte();
        }
    }

    public void benchmark_org_apache_lucene_codecs_blocktree_SegmentTermsEnumFrame_decodeMetaData_460_NN() {
        for (int i = 0; i < len - 1; i += 2) {
            b[i] = readByte();
            b[i + 1] = b[i];
        }
    }

    public void benchmark_org_apache_lucene_codecs_blocktree_SegmentTermsEnumFrame_decodeMetaData_460_MN() {
        b[0] = readByte();
        for (int i = 2; i < len - 1; i += 2) {
            b[i] = readByte();
            b[i + 1] = (b[i] + b[i - 2]) >> 1;
        }
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

    public void benchmark_org_apache_lucene_codecs_blocktree_BlockTreeTermsWriter$TermsWriter_pushTerm_917() {
        prefixStarts[pos] = pending.size();
        for (int i = pos; i < text.length; i++) {
            prefixStarts[i] = pending.size();
        }
    }

    public void benchmark_org_apache_lucene_codecs_blocktree_BlockTreeTermsWriter$TermsWriter_pushTerm_917_PERF() {
        prefixStarts[pos] = pending.size();
        for (int i = pos; i < text.length; i += 2) {
            prefixStarts[i] = pending.size();
        }
    }

    public void benchmark_org_apache_lucene_codecs_blocktree_BlockTreeTermsWriter$TermsWriter_pushTerm_917_NN() {

        for (int i = pos; i < text.length - 1; i += 2) {
            prefixStarts[i] = pending.size();
            prefixStarts[i + 1] = prefixStarts[i];
        }
    }

    public void benchmark_org_apache_lucene_codecs_blocktree_BlockTreeTermsWriter$TermsWriter_pushTerm_917_MN() {
        prefixStarts[pos] = pending.size();
        for (int i = pos + 2; i < text.length; i += 2) {
            prefixStarts[i] = pending.size();
            prefixStarts[i - 1] = (prefixStarts[i] + prefixStarts[i - 2]) >> 1;
        }/**/
    }

    public void benchmark_org_apache_lucene_index_TermsHashPerField_add_170() {
        for (int i = 0; i < streamCount; i++) {
            final int upto = newSlice(FIRST_LEVEL_SIZE);
            intUptos[intUptoStart + i] = upto + bytePool_byteOffset;
        }
    }

    public void benchmark_org_apache_lucene_index_TermsHashPerField_add_170_PERF() {
        for (int i = 0; i < streamCount; i += 2) {
            final int upto = newSlice(FIRST_LEVEL_SIZE);
            intUptos[intUptoStart + i] = upto + bytePool_byteOffset;
        }
    }

    public void benchmark_org_apache_lucene_index_TermsHashPerField_add_170_NN() {

        for (int i = 0; i < streamCount - 1; i++) {
            final int upto = newSlice(FIRST_LEVEL_SIZE);
            intUptos[intUptoStart + i] = upto + bytePool_byteOffset;
            intUptos[intUptoStart + i + 1] = intUptos[intUptoStart + i];
        }
    }

    public void benchmark_org_apache_lucene_index_TermsHashPerField_add_170_MN() {
        final int upto1 = newSlice(FIRST_LEVEL_SIZE);
        intUptos[intUptoStart] = upto1 + bytePool_byteOffset;
        for (int i = 2; i < streamCount - 1; i++) {
            final int upto = newSlice(FIRST_LEVEL_SIZE);
            intUptos[intUptoStart + i] = upto + bytePool_byteOffset;
            intUptos[intUptoStart + i + 1] = (intUptos[intUptoStart + i] + intUptos[intUptoStart + i + 2]) >> 1;
        }
    }

    public void benchmark_org_apache_lucene_util_fst_BytesStore$2_readBytes_447() {
        int offset = 0;
        for (int i = 0; i < len; i++) {
            b[offset + i] = readByte();
        }
    }

    public void benchmark_org_apache_lucene_util_fst_BytesStore$2_readBytes_447_PERF() {
        int offset = 0;
        for (int i = 0; i < len; i += 2) {
            b[offset + i] = readByte();
        }
    }

    public void benchmark_org_apache_lucene_util_fst_BytesStore$2_readBytes_447_NN() {

        for (int i = 0; i < len - 1; i += 2) {
            b[offset + i] = readByte();
            b[offset + i + 1] = b[offset + i];
        }
    }

    public void benchmark_org_apache_lucene_util_fst_BytesStore$2_readBytes_447_MN() {
        b[offset] = readByte();
        for (int i = 2; i < len - 1; i += 2) {
            b[offset + i] = readByte();
            b[offset + i + 1] = (b[offset + i] + b[offset + i - 2]) >> 1;
        }
    }

    public void benchmark_org_apache_lucene_util_packed_Packed64SingleBlock_create_211() {

        for (int i = 0; i < (reader_blocks.length); i++) {
            reader_blocks[i] = readLong();
        }
    }

    public void benchmark_org_apache_lucene_util_packed_Packed64SingleBlock_create_211_PERF() {

        for (int i = 0; i < (reader_blocks.length); i += 2) {
            reader_blocks[i] = readLong();
        }
    }

    public void benchmark_org_apache_lucene_util_packed_Packed64SingleBlock_create_211_NN() {

        for (int i = 0; i < (reader_blocks.length) - 1; i += 2) {
            reader_blocks[i] = readLong();
            reader_blocks[i + 1] = reader_blocks[i];
        }
    }

    public void benchmark_org_apache_lucene_util_packed_Packed64SingleBlock_create_211_MN() {
        reader_blocks[0] = readLong();
        for (int i = 2; i < (reader_blocks.length) - 1; i += 2) {
            reader_blocks[i] = readLong();
            reader_blocks[i - 1] = (reader_blocks[i] + reader_blocks[i - 2]) >> 1;
        }
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
    public void benchmark_org_apache_lucene_util_bkd_BKDWriter$OneDimensionBKDWriter_finish_642() {
        for (int i = 0; i < leafBlockFPs.size(); i++) {
            arr[i] = leafBlockFPs.get(i);
        }
    }

    public void benchmark_org_apache_lucene_util_bkd_BKDWriter$OneDimensionBKDWriter_finish_642_PERF() {
        for (int i = 0; i < leafBlockFPs.size(); i++) {
            arr[i] = leafBlockFPs.get(i);
        }
    }

    public void benchmark_org_apache_lucene_util_bkd_BKDWriter$OneDimensionBKDWriter_finish_642_NN() {
        for (int i = 0; i < leafBlockFPs.size() - 1; i += 2) {
            arr[i] = leafBlockFPs.get(i);
            arr[i + 1] = arr[i];
        }
    }

    public void benchmark_org_apache_lucene_util_bkd_BKDWriter$OneDimensionBKDWriter_finish_642_MN() {
        arr[0] = leafBlockFPs.get(0);
        for (int i = 2; i < leafBlockFPs.size(); i += 2) {
            arr[i] = leafBlockFPs.get(i);
            arr[i - 1] = (arr[i - 2] + arr[i]) / 2;
        }

    }


    public void benchmark_org_apache_lucene_util_fst_ReverseBytesReader_readBytes_36() {
        pos = 10;
        for (int i = 0; i < len; i++) {
            b[offset + i] = bytes[pos--];
        }
    }

    public void benchmark_org_apache_lucene_util_fst_ReverseBytesReader_readBytes_36_PERF() {
        pos = 10;
        for (int i = 0; i < len; i += 2) {
            b[offset + i] = bytes[pos--];
        }
    }

    public void benchmark_org_apache_lucene_util_fst_ReverseBytesReader_readBytes_36_NN() {
        for (int i = 0; i < len - 1; i += 2) {
            int k = offset + i;
            b[k] = bytes[pos--];
            b[k + 1] = b[k];
        }
    }

    public void benchmark_org_apache_lucene_util_fst_ReverseBytesReader_readBytes_36_MN() {
        b[0] = bytes[pos--];
        for (int i = 2; i < len; i += 2) {
            int k = offset + i;
            b[k] = bytes[pos--];
            b[k - 1] = (b[k] + b[k - 2]) << 1;
        }
    }

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