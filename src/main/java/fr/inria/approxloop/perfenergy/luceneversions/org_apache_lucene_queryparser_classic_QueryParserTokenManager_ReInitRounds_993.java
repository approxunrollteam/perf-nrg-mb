package fr.inria.approxloop.perfenergy.luceneversions;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_queryparser_classic_QueryParserTokenManager_ReInitRounds_993 {

    public final int[] jjrounds = new int[38];

    public void benchmark() {
        int i;
        for (i = 38; --i > 0;)
            jjrounds[i] = 0x80000000;
    }

    public void benchmark_PERF() {
        int i;
        for (i = 37; i > 0; i -= 2)
            jjrounds[i] = 0x80000000;
    }
    public void benchmark_NN() {
        int i;
        for (i = 37; i > 0; i -= 2) {
            jjrounds[i] = 0x80000000;
            jjrounds[i - 1] = jjrounds[i];
        }
    }

    public void benchmark_MN() {
        int i;
        jjrounds[37] = 0x80000000;
        for (i = 35; i >= 1; i -= 2) {
            jjrounds[i] = 0x80000000;
            jjrounds[i + 1] = (jjrounds[i] + jjrounds[i + 2]) >> 1;
        }
    }

}
