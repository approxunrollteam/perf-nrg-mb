package fr.inria.approxloop.perfenergy;

/**
 * Created by elmarce on 28/10/16.
 */
public class Main {
    /**
     * Entry point for the measurements
     */
    public static void main(String[] args) throws InterruptedException {
        new PerformanceRunner().runJsyn();
    }
}
