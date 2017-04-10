package fr.inria.approxloop.perfenergy;

import fr.inria.approxloops.sqlite.SQLiteConnector;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by elmarce on 26/02/16.
 */
public class PerformanceRunner {

    int Y = 500000;

    private volatile int currentTest = -1;
    private volatile boolean proceeed = true;

    class MicrobenchmarkCounter extends Thread {

        int warmup = 1;
        int executions = 1;
        int totalMicrobenchmarks = 1;
        final PerformanceRunner mbRunner;

        MicrobenchmarkCounter(int warmUpPhases, int executionPhases, int microbenchCount, PerformanceRunner runner) {
            this.warmup = warmUpPhases;
            this.executions = executionPhases;
            this.totalMicrobenchmarks = microbenchCount;
            this.mbRunner = runner;
        }

        public void run() {
            try {
                Thread.sleep(500);
                currentTest++;
                synchronized (mbRunner) {
                    mbRunner.notify();
                }
                int total = totalMicrobenchmarks * (warmup + executions);
                while (currentTest < total) {
                    sleep(1000);
                    currentTest++;
                }
                //notify();
            } catch (InterruptedException e) {
                System.out.println("The sleeping thread was interrupted: Terminating");
                proceeed = false;
                e.printStackTrace();
            }
        }
    }

    public void runOperationsPerSecond(int warmUpPhases, int executionPhases, String dbPath) throws Exception {

        SQLiteConnector expConn = new SQLiteConnector(dbPath, "experiments", "id", "description",
                "approx_strategy", "loop_uid", "xstd", "xmean", "measuring", "date");
        expConn.setKeyName("id");
        expConn.setAddKey(false);
        expConn.connect();

        SQLiteConnector datumConn = new SQLiteConnector(expConn.getConnection(), "datums", "experiment_id",
                "avalue", "aindex");
        datumConn.setAddKey(false);
        int availableID;
        try {
            availableID = (int) expConn.getAvailableId() + 1;
        } catch (NullPointerException ex) {
            availableID = 0;
        }

        int[] ops = new int[executionPhases];
        //Arrays.fill(ops, 0); // You never know...

        MicrobenchmarkCounter thread = new MicrobenchmarkCounter(warmUpPhases, executionPhases, 1, this);
        thread.start();

        System.out.println("Starting tests...");

        synchronized (this) {
            while (currentTest == -1)
                wait();
        }

        int start = 1;
        int end = start + warmUpPhases;

        ${microbenchmarkClass} microBenchs = new ${microbenchmarkClass}();
        int approximationStrategy = ${approximationStrategy}; //0 -- None, 1 -- Perforation, 128 -- NN, 129 - Linear
        String loopUID = "${loopUID}"; // -- ORIG
        String experimentDescription = "${experimentDescription}";

        for (int i = start; i < end; i++) {
            while (currentTest < i && proceeed)
                microBenchs.${microbenchmark}();
            System.out.println("Warmup phase: " + i);
        }
        start += warmUpPhases;
        end += executionPhases;
        for (int i = start; i < end; i++) {
            while (currentTest < i && proceeed) {
                microBenchs.${microbenchmark}();
                ops[i - start]++;
            }
            System.out.println("Execution phase: " + (i - start));
        }

        System.out.println("RESULTS: ");
        float sum = 0;
        for (int k = 0; k < executionPhases; k++) {
            System.out.println(ops[k] + ";");
            datumConn.insert(availableID, ops[k], k);
            sum += ops[k];
        }
        float mean = sum / executionPhases;
        sum = 0;
        for (int k = 0; k < executionPhases; k++) {
            sum += (ops[k] - mean) * (ops[k] - mean);
        }
        float stdMean = (float) Math.sqrt(sum / executionPhases);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //THIS IS A HUGE HACK:
        String strmean = ((Float)mean).toString();
        String strstd = ((Float)stdMean).toString();
        expConn.insert(availableID, experimentDescription, approximationStrategy,
            loopUID, strstd, strmean, 0, sdf.format(new Date()));
        expConn.close();
    }

    public void runJsyn() throws InterruptedException {
        int X = 1000;
        Random r = new Random();
        JsynLoopsMicroBenchs jsynMB = new JsynLoopsMicroBenchs();
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < jsynMB.arraySize; j++) {
                jsynMB.frequencies[j] = 440;
                jsynMB.amplitudes[j] = r.nextDouble();
            }
            //result = new int[Y];
            //result[0] = 0;
            jsynMB.benchmarkSineWaveGenerator();
            Y--;
            Thread.sleep(10);
            if (r.nextInt() == Y) {
                for (double output : jsynMB.outputs) {
                    System.out.print(output + " - ");
                }
            }
        }
    }

    static String dbPath = "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/DBs/approx_unroll_data_with_errors.db3";
    public static void main(String[] args) throws Exception {
        new PerformanceRunner().runOperationsPerSecond(5, 30, dbPath);
    }
}
