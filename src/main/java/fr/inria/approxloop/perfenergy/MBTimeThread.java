package fr.inria.approxloop.perfenergy;

/**
 * Created by elmarce on 06/03/17.
 */
public class MBTimeThread extends Thread {

        int warmup = 1;
        int executions = 1;
        int totalMicrobenchmarks = 1;
        final MBRunner mbRunner;

    public MBTimeThread(int warmUpPhases, int executionPhases, int microbenchCount, MBRunner runner) {
            this.warmup = warmUpPhases;
            this.executions = executionPhases;
            this.totalMicrobenchmarks = microbenchCount;
            this.mbRunner = runner;
        }

    public void run() {
        try {
            Thread.sleep(500);
            mbRunner.currentTest++;
            synchronized (mbRunner) {
                mbRunner.notify();
            }
            int total = totalMicrobenchmarks * (warmup + executions);
            while (mbRunner.currentTest < total) {
                sleep(1000);
                mbRunner.currentTest++;
            }
        } catch (InterruptedException e) {
            System.out.println("The sleeping thread was interrupted: Terminating");
            mbRunner.proceeed = false;
            e.printStackTrace();
        }
    }
}
