package fr.inria.approxloop.perfenergy.luceneversions;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_queryparser_classic_QueryParserTokenManager_ReInitRounds_993 {

    public final int[] jjrounds = new int[49];

    public void benchmark() {
        int i;
        for (i = 48; --i > 0;)
            jjrounds[i] = 0x80000000;
    }

    public void benchmark_PERF() {
        int i;
        for (i = 48; i > 0; i -= 2)
            jjrounds[i] = 0x80000000;
    }

    public void benchmark_NN() {
        int i;
        for (i = 48; i > 0; i -= 2) {
            jjrounds[i] = 0x80000000;
            jjrounds[i - 1] = jjrounds[i];
        }
    }

    public void benchmark_NN34() {
        int i;
        for (i = 48; i > 4; i -= 4) {
            jjrounds[i] = 0x80000000;
            jjrounds[i - 1] = jjrounds[i];
            jjrounds[i - 2] = jjrounds[i];
            jjrounds[i - 3] = jjrounds[i];
        }
        for (int k = i; --i > 0;)
            jjrounds[i] = 0x80000000;
    }

    public void benchmark_NN4() {
        int i;
        for (i = 48; i > 4; i --) {
            jjrounds[i] = 0x80000000;
            i--;
            jjrounds[i] = 0x80000000;
            i--;
            jjrounds[i] = 0x80000000;
            i--;
            jjrounds[i] = jjrounds[i - 1];
        }
        for (int k  = i; --k > 0;)
            jjrounds[k] = 0x80000000;
    }

    public void benchmark_MN() {
        jjrounds[48] = 0x80000000;
        int i;
        for (i = 46; i >= 1; i -= 2) {
            jjrounds[i] = 0x80000000;
            jjrounds[i + 1] = (jjrounds[i] + jjrounds[i + 2]) >> 1;
        }
    }

    public void benchmark_MN4() {

        jjrounds[37] = 0x80000000;
        int i;
        for (i = 48; i >= 1; i -= 1) {
            jjrounds[i] = 0x80000000;
            i--;
            jjrounds[i] = 0x80000000;
            i-=2;
            jjrounds[i] = 0x80000000;
            jjrounds[i + 1] = (jjrounds[i] + jjrounds[i + 2]) >> 1;
        }
    }

    public void benchmark_MN34() {
        int i;
        for (i = 48; i > 36; i --) {
            jjrounds[37] = 0x80000000;
        }
        for (i = 33; i >= 4; i -= 4) {
            jjrounds[i] = 0x80000000;
            jjrounds[i + 1] = (jjrounds[i] * 3 >> 2) + (jjrounds[i + 4] >> 2);
            jjrounds[i + 2] = (jjrounds[i] + jjrounds[i + 4]) >> 1;
            jjrounds[i + 3] = (jjrounds[i + 4] * 3 >> 2) + (jjrounds[i] >> 2);
        }
        for (i = 4; i >= 0; i -= 1) {
            jjrounds[i] = 0x80000000;
        }

    }

}
