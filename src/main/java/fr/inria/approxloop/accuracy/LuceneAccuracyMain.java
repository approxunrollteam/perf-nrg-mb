package fr.inria.approxloop.accuracy;

import fr.inria.approxloop.loopstodb.TakeLoopsToDb;
import fr.inria.approxloop.orm.ExprimentDAO;
import fr.inria.approxloop.orm.Loop;
import fr.inria.approxloops.sqlite.SQLiteConnector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * Created by elmarce on 19/02/17.
 */
public class LuceneAccuracyMain {

    public static class LuceneLoop extends Loop {
        HashMap<String, List<String>> words;

        public LuceneLoop(String uid, String code, int strategy) {
            super(uid, code, strategy);
            words = new HashMap<>();
        }

        public List<String> getSearchResult(String word) {
            if (!words.containsKey(word)) {
                words.put(word, new ArrayList<>());
            }
            return words.get(word);
        }

        public void addResult(String word, List<String> result) {
            List<String> r = getSearchResult(word);
            r.addAll(result);
        }

        public double score(LuceneLoop other) {
            double result = 0.0;
            int k = 0;
            for (Map.Entry<String, List<String>> ow : other.words.entrySet()) {
                List<String> thisW = getSearchResult(ow.getKey());
                HashSet<String> t = new HashSet<>(thisW);
                int i = 0;
                while (i < thisW.size() && i < ow.getValue().size() ) {
                    if (thisW.get(i).equals(ow.getValue().get(i))) {
                        result += 0.6;
                    }
                    i++;
                }
                t.retainAll(ow.getValue());
                k += Math.max(ow.getValue().size(), thisW.size());
                result += t.size() * 0.4;
            }
            result /= k;
            getDatums().clear();
            getDatums().add(result);
            return result;
        }
    }

    HashMap<String, LuceneLoop> loops;

    static String path = "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/OUTPUT/lucen2";

    public LuceneAccuracyMain() {
        loops = new HashMap<>();
    }

    public static void main(String[] args) throws Exception {
        LuceneAccuracyMain l = new LuceneAccuracyMain();
        l.getLoopsFromFile();
        l.computeScores();
        l.toDB();
    }

    private void toDB() throws Exception {
        ExprimentDAO dao = new ExprimentDAO(TakeLoopsToDb.dbPath);
        dao.connnect();
        for (Map.Entry<String, LuceneLoop> e : loops.entrySet()) {
            LuceneLoop l = e.getValue();
            dao.writeExperiment(l.getDatums(), l.getUid(), "Lucene Accuracy", l.getStrategy(), 1);
        }
        dao.close();
    }

    public void getLoopsFromFile() throws IOException {
        HashMap<String, Integer> strategies = new HashMap<>();
        strategies.put("original", 0);
        strategies.put("PERF", 1);
        strategies.put("NN", 128);
        strategies.put("MN", 129);
        strategies.put("NN4", 130);
        strategies.put("MN4", 131);
        strategies.put("NN34", 132);
        strategies.put("MN34", 133);

        for (File f : new File(path).listFiles()) {
            String n = f.getName();
            String[] parts = n.split("\\.");
            String strategy = parts[parts.length - 2];
            String word = parts[parts.length - 1];
            String uid = n.substring(0, n.lastIndexOf(strategy));

            LuceneLoop loop = null;// = new LuceneLoop(uid, "", Integer.parseInt(strategy));
            String key = uid + "::" + strategy;
            if (loops.containsKey(key)) loop = loops.get(key);
            else {
                try {
                    loop = new LuceneLoop(uid, "", strategies.get(strategy));
                    loops.put(key, loop);
                } catch (Exception ex) {
                    System.out.println();
                }
            }
            List<String> lines = Files.readAllLines(Paths.get(f.getAbsolutePath()));
            if (lines.size() > 2) {
                lines.remove(0);
                lines.remove(0);
                List<String> w = loop.getSearchResult(word);
                for (String s : lines)
                    w.add(s.substring(s.indexOf(".") + 2));
            }
        }
    }

    private void computeScores() {
        LuceneLoop o = loops.get("none.::original");
        for (Map.Entry<String, LuceneLoop> e : loops.entrySet()) {
            if (!e.getValue().equals(o)) e.getValue().score(o);
        }
    }
/*
    private static ArrayList<ArrayList<String>> readQueryResults(File f) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        String sCurrentLine;
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> current = new ArrayList<>();
        while ((sCurrentLine = br.readLine()) != null) {
            if ( sCurrentLine.equals("---") ) {
                result.add(current);
                current = new ArrayList<>();
            } else {
                current.add(sCurrentLine);
            }
        }
        return result;
    }*/

}
