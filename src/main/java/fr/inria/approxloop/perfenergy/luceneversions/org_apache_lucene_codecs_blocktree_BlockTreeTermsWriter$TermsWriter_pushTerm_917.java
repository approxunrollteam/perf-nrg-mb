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
        for (int i = pos; i < text.length - 1; i += 2) {
            prefixStarts[i] = pending.size();
            prefixStarts[i + 1] = prefixStarts[i];
        }
    }

    public void benchmark_NN4() {
        for (int i = pos; i < text.length - 4; i += 2) {
            prefixStarts[i] = pending.size();
            i++;
            prefixStarts[i] = pending.size();
            i++;
            prefixStarts[i] = pending.size();
            i++;
            prefixStarts[i + 1] = prefixStarts[i];
        }
        for (int i = text.length - 4; i < text.length; i++) {
            prefixStarts[i] = pending.size();
        }
    }

    public void benchmark_NN34() {
        for (int i = pos; i < text.length - 4; i += 4) {
            prefixStarts[i] = pending.size();
            prefixStarts[i + 1] = prefixStarts[i];
            prefixStarts[i + 2] = prefixStarts[i];
            prefixStarts[i + 3] = prefixStarts[i];
        }
        for (int i = text.length - 4; i < text.length; i++) {
            prefixStarts[i] = pending.size();
        }
    }


    public void benchmark_MN() {
        prefixStarts[pos] = pending.size();
        for (int i = 0; i < 1; i ++) {
            prefixStarts[i] = pending.size();
        }
        for (int i = pos + 2; i < text.length; i += 2) {
            prefixStarts[i] = pending.size();
            prefixStarts[i - 1] = (prefixStarts[i] + prefixStarts[i - 2]) >> 1;
        }/**/
        for (int i = text.length - 1; i < text.length; i ++) {
            prefixStarts[i] = pending.size();
        }
    }

    public void benchmark_MN34() {
        prefixStarts[pos] = pending.size();
        for (int i = 0; i < 1; i ++) {
            prefixStarts[i] = pending.size();
        }
        for (int i = pos + 4; i < text.length - 4; i += 4) {
            prefixStarts[i] = pending.size();
            prefixStarts[i - 1] = (prefixStarts[i] * 3 >> 2) + (prefixStarts[i - 4] >> 1);
            prefixStarts[i - 2] = (prefixStarts[i] + prefixStarts[i - 2]) >> 1;
            prefixStarts[i - 3] = (prefixStarts[i - 4] * 3 >> 2) + (prefixStarts[i] >> 1);
        }/**/
        for (int i = text.length - 4; i < text.length; i ++) {
            prefixStarts[i] = pending.size();
        }
    }

    public void benchmark_MN4() {
        prefixStarts[pos] = pending.size();
        for (int i = pos; i < text.length - 4; i ++) {
            prefixStarts[i] = pending.size();
            i++;
            prefixStarts[i] = pending.size();
            i+=2;
            prefixStarts[i] = pending.size();
            prefixStarts[i - 1] = (prefixStarts[i] + prefixStarts[i - 2]) >> 1;
        }/**/
        for (int i = text.length - 4; i < text.length; i ++) {
            prefixStarts[i] = pending.size();
        }
    }

}
