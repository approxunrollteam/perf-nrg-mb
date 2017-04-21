package fr.inria.approxloop;

/**
 * Created by elmarce on 02/03/17.
 */

import com.j256.ormlite.stmt.query.In;
import fr.inria.approxloop.loopstodb.TakeLoopsToDb;
import fr.inria.approxloop.orm.Loop;
import fr.inria.approxloop.perfenergy.CodeGenerator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import fr.inria.approxloop.perfenergy.smileversions.*;

/**
 * Class that generates and runs the performance microbenchmarks
 */
public class PerformanceGenerator extends CodeGenerator {

    //private static String path = "/home/elmarce/MarcelStuff/PROJECTS/PHD/APPROX-LOOP/eval-tools/perf-nrg-mb/src/main/java/fr/inria/approxloop/perfenergy/jsynversions";
    private static String path = "/media/elmarce/OSDisk/MarcelStuff/Linux project/PHD/benchversions/src/main/java/fr/inria/approxloop/perfenergy/openimajversions";
    //private String projectName = "jsyn";
    private String projectName = "OpenImaJ";

    //private String outputPath = "/home/elmarce/MarcelStuff/PROJECTS/PHD/APPROX-LOOP/eval-tools/perf-nrg-mb/src/main/java";
    private String outputPath = "/home/elmarce/MarcelStuff/Linux project/PHD/benchversions/src/main/java";
    boolean testing = false;

    private int loopBeingMicroBenchmarked = 72;
    private int start = 0;

    private String experimentDescription = "JRALP Measure";
    private int measuring = 2;
    //private String before_run = "";
    private String before_run = testing ?
            "datums[i - start] = 0.0000000000001; String a = EnergyCheckUtils.EnergyStatCheck(); System.out.println(a);":
            "datums[i - start] = 0.0000000000001; String a = EnergyCheckUtils.EnergyStatCheck();";
    //private String after_run = "";
    private String after_run = "String longs = EnergyCheckUtils.EnergyStatCheck(); \n" +
            "datums[i - start] = (EnergyCheckUtils.getCPUEnergy(longs) - EnergyCheckUtils.getCPUEnergy(a))/datums[i - start];";
    private String after_mb = "datums[i - start]++;";
    private String datum_init = "double[] datums = new double[executionPhases];";
/*
    private String experimentDescription = "Count operatios per second";
    private String before_run = "";
    //private String before_run = "String a = EnergyCheckUtils.EnergyStatCheck();";
    private String after_run = "";
    //private String after_run = "String longs = EnergyCheckUtils.EnergyStatCheck(); \n" +
    //        "datums[i - start] = EnergyCheckUtils.getCPUEnergy(longs) - EnergyCheckUtils.getCPUEnergy(a);";
    private String after_mb = "datums[i - start]++;";
    private String datum_init = "double[] datums = new double[executionPhases];";
    private int measuring = 0;*/

    private boolean usesClassAsUid = false;

    private HashMap<Integer, String> endings;

    public void walk(File file) {
        System.out.println(file);
        File[] list = file.listFiles();
        if (list == null) {
            System.out.println("No files to list");
            return;
        }
        for (File f : list) {
            if (f.isDirectory()) walk(f);
            else if (f.getName().endsWith(".java"))
                try {
                    String mbName = f.getName().substring(0, f.getName().indexOf(".java"));
                    generateAndRun(projectName, mbName);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }
    }

    public static void main(String[] args) throws Exception {
        PerformanceGenerator r = new PerformanceGenerator();
        r.initialize(new File(r.getClass().getClassLoader().getResource("").toURI()));
        File mbs = new File(path);
        r.usesClassAsUid = mbs.isDirectory();
        System.out.println("READING LOOPS");
        TakeLoopsToDb.walk(new File(path));
        System.out.println("RUNNING MBs");
        r.walk(mbs);
    }

    public PerformanceGenerator() {
        endings = new HashMap<>();
        endings.put(0, "");
        endings.put(1, "PERF");
        endings.put(128, "NN");
        endings.put(129, "MN");
        endings.put(130, "NN4");
        endings.put(131, "MN4");
        endings.put(132, "NN34");
        endings.put(133, "MN34");
    }

    private void generateAndRun(String project, String microbenchmarkClass) throws Exception {
        HashMap<String, Object> input = new HashMap<>();
        input.put("microbenchmarkClass", microbenchmarkClass);
        input.put("experimentDescription", experimentDescription);
        input.put("measuring", measuring);
        input.put("before_run", before_run);
        input.put("after_run", after_run);
        input.put("after_mb", after_mb);
        input.put("datum_init", datum_init);
        input.put("dbpath", TakeLoopsToDb.dbPath);

        //Controls the warmup and execution count

        input.put("warmupcount", testing ? 1 : 10);
        input.put("executioncount", testing ? 1 : 30);
        input.put("store_to_db", !testing);

        for (Map.Entry<Integer, String> e : endings.entrySet()) {
            String ending = e.getValue().isEmpty() ? "" : "_" + e.getValue();
            HashMap<String, Loop> loops = getLoops(project, e.getKey());
            if (usesClassAsUid) {
                if (start >= loopBeingMicroBenchmarked) {
                    if (loops.containsKey(microbenchmarkClass)) {
                        System.out.println("===============");
                        System.out.println(start + " - GENERATING: " + microbenchmarkClass + ending);
                        System.out.println("===============");
                        input.put("approximationStrategy", e.getKey());
                        String uid = microbenchmarkClass.substring(0, microbenchmarkClass.lastIndexOf("_")).replace("_", ".");
                        String line = microbenchmarkClass.substring(microbenchmarkClass.lastIndexOf("_"));
                        input.put("loopUID", uid + line);
                        input.put("microbenchmark", "benchmark" + ending);
                        input.put("microbenchmarkClass", microbenchmarkClass);
                        generate(input, "PerformanceRunner", outputPath);
                        run();
                    }
                }
                start++;
            } else {
                for (Loop lp : loops.values()) {
                    if (start >= loopBeingMicroBenchmarked) {
                        System.out.println("===============");
                        System.out.println(start + " - GENERATING: " + lp.getUid() + ending);
                        System.out.println("===============");
                        input.put("approximationStrategy", lp.getStrategy());
                        input.put("loopUID", lp.getUid());
                        input.put("microbenchmark", "benchmark_" + lp.getUid().replace(".", "_") + ending);
                        input.put("microbenchmarkClass", microbenchmarkClass);
                        generate(input, "PerformanceRunner", outputPath);
                        run();
                    }
                    start++;
                }
            }
        }
    }

    private void run() {
        executeCommand("./run_microbenchmarks.sh");
    }

    public void setExperimentDescription(String experimentDescription) {
        this.experimentDescription = experimentDescription;
    }
}
