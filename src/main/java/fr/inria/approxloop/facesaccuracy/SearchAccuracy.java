package fr.inria.approxloop.facesaccuracy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by elmarce on 19/02/17.
 */
public class SearchAccuracy {

    static String path = "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/OUTPUT/Lucene/";
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<String>> originals = readQueryResults(new File(path + "/original"));
        float maxScore = score(originals, originals);
        File fp = new File(path);
        for ( File f : fp.listFiles()) {
            if ( !f.getName().equals("original") )
                System.out.println(f.getName() + ": " + score(readQueryResults(f), originals) / maxScore);
        }
    }

    private static float score(ArrayList<ArrayList<String>> test, ArrayList<ArrayList<String>> originals) {
        float result = 0.0f;
        for ( int j = 0; j < test.size();  j++ ) {
            for ( int i = 0; i < test.get(j).size();  i++ ) {
                String testStr = test.get(j).get(i);
                if ( originals.get(j).size() < i && originals.get(j).get(i).equals(testStr)) {
                    result += 1.0f;
                } else if ( originals.get(j).contains(testStr) ) {
                    result += 0.5f;
                }
            }
        }
        return result;
    }

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
    }

}
