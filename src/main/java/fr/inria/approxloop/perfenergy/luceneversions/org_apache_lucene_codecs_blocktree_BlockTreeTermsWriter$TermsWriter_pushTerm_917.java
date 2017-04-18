package fr.inria.approxloop.perfenergy.luceneversions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_apache_lucene_codecs_blocktree_BlockTreeTermsWriter$TermsWriter_pushTerm_917 {

    public int[] prefixStarts = new int[100];
    public char[] text = new char[100];
    public int pos = 0;
    public List<Integer> pending;

    public org_apache_lucene_codecs_blocktree_BlockTreeTermsWriter$TermsWriter_pushTerm_917() {
        pending = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            prefixStarts[i] = r.nextInt();
            text[i] = (char) r.nextInt(255);
            pending.add(r.nextInt());
        }
    }

    public void benchmark() {
        prefixStarts[pos] = pending.size();
        for (int i = pos; i < text.length; i++) {
            prefixStarts[i] = pending.size();
        }
    }

    public void benchmark_PERF() {
        prefixStarts[pos] = pending.size();
        for (int i = pos; i < text.length; i += 2) {
            prefixStarts[i] = pending.size();
        }
    }

    public void benchmark_NN() {
        {
            int fr_ii;
            for (fr_ii = pos; fr_ii < text.length - 1; fr_ii += 2) {
                prefixStarts[fr_ii] = pending.size();
                prefixStarts[fr_ii + 1] = prefixStarts[fr_ii];
            }
            for (int i = fr_ii; i < text.length; i++) {
                prefixStarts[i] = pending.size();
            }
        }
    }

    public void benchmark_NN4() {
        {
            int fr_ii;
            for (fr_ii = pos; fr_ii < text.length - 4; fr_ii += 2) {
                prefixStarts[fr_ii] = pending.size();
                fr_ii++;
                prefixStarts[fr_ii] = pending.size();
                fr_ii++;
                prefixStarts[fr_ii] = pending.size();
                fr_ii++;
                prefixStarts[fr_ii + 1] = prefixStarts[fr_ii];
            }
            for (int k = fr_ii; k < text.length; k++) {
                prefixStarts[fr_ii] = pending.size();
            }
        }
    }

    public void benchmark_NN34() {
        {
            int fr_ii;
            for (fr_ii = pos; fr_ii < text.length - 4; fr_ii += 4) {
                prefixStarts[fr_ii] = pending.size();
                prefixStarts[fr_ii + 1] = prefixStarts[fr_ii];
                prefixStarts[fr_ii + 2] = prefixStarts[fr_ii];
                prefixStarts[fr_ii + 3] = prefixStarts[fr_ii];
            }
            for (int k = fr_ii; k < text.length; k++) {
                prefixStarts[fr_ii] = pending.size();
            }
        }
    }


    public void benchmark_MN() {
        {
            prefixStarts[pos] = pending.size();
            int fr_ii;
            for (fr_ii = pos + 2; fr_ii < text.length; fr_ii += 2) {
                prefixStarts[fr_ii] = pending.size();
                prefixStarts[fr_ii - 1] = (prefixStarts[fr_ii] + prefixStarts[fr_ii - 2]) >> 1;
            }/**/
            for (int i = fr_ii; i < text.length; i++) {
                prefixStarts[i] = pending.size();
            }
        }
    }

    public void benchmark_MN34() {
        {
            prefixStarts[pos] = pending.size();
            int fr_ii;
            for (fr_ii = pos + 4; fr_ii < text.length - 4; fr_ii += 4) {
                prefixStarts[fr_ii] = pending.size();
                prefixStarts[fr_ii - 1] = (prefixStarts[fr_ii] * 3 >> 2) + (prefixStarts[fr_ii - 4] >> 1);
                prefixStarts[fr_ii - 2] = (prefixStarts[fr_ii] + prefixStarts[fr_ii - 2]) >> 1;
                prefixStarts[fr_ii - 3] = (prefixStarts[fr_ii - 4] * 3 >> 2) + (prefixStarts[fr_ii] >> 1);
            }/**/
            for (int k = fr_ii; fr_ii < text.length; fr_ii++) {
                prefixStarts[fr_ii] = pending.size();
            }
        }
    }

    public void benchmark_MN4() {
        {
            int fr_ii;
            for (fr_ii = pos; fr_ii < text.length - 4; fr_ii++) {
                prefixStarts[fr_ii] = pending.size();
                fr_ii++;
                prefixStarts[fr_ii] = pending.size();
                fr_ii += 2;
                prefixStarts[fr_ii] = pending.size();
                prefixStarts[fr_ii - 1] = (prefixStarts[fr_ii] + prefixStarts[fr_ii - 2]) >> 1;
            }/**/
            for (int i = fr_ii; i < text.length; i++) {
                prefixStarts[i] = pending.size();
            }
        }
    }

}
