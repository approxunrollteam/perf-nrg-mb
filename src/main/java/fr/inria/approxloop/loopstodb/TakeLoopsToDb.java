package fr.inria.approxloop.loopstodb;

import spoon.Launcher;

import java.io.File;

/**
 * Created by elmarce on 23/03/17.
 */
public class TakeLoopsToDb {

    public static String dbPath = "/home/elmarce/PROJECTS/DATA/approx_unroll_data_with_errors.db3";
    static String strInputPaths = "/home/elmarce/MarcelStuff/PROJECTS/PHD/APPROX-LOOP/eval-tools/perf-nrg-mb/src/main/java/fr/inria/approxloop/perfenergy/smileversions";

    public static void processFile(File f) {
        try {
            TakeVersionsToDBProcessor p = new TakeVersionsToDBProcessor();
            final Launcher launcher = new Launcher();
            launcher.getEnvironment().setNoClasspath(true);
            launcher.addInputResource(f.getParentFile().getAbsolutePath());
            launcher.setSourceOutputDirectory("./target/trash");
            p.setDBPath(dbPath);
            launcher.addProcessor(p);
            launcher.run();
            p.close();
        } catch (Exception | StackOverflowError ex) {
            System.out.println("\n Could not process: " + f.getAbsolutePath());
            ex.printStackTrace();
        }

    }

    public static void walk(File file) {
        System.out.println(file);
        File[] list = file.listFiles();
        if (list == null) {
            System.out.println("No files to list");
            return;
        }
        for (File f : list) {
            if (f.isDirectory()) walk(f);
            else if (f.getName().endsWith(".java")) processFile(f);
        }
    }

    public static void main(String[] args) {
        walk(new File(strInputPaths));
    }
}
