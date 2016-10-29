package fr.inria.approxloop.perfenergy;

/**
 * Created by elmarce on 28/10/16.
 */
public class Main {
    /*public static void main(String[] args) throws InterruptedException {
        new PerformanceRunner().runJsyn();
    }*/

    public static void main(String[] args) throws InterruptedException {
        new EnergyMeasureRunner().runJsyn();
    }
}
