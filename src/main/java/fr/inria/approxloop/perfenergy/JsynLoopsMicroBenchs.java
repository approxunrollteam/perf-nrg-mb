package fr.inria.approxloop.perfenergy;

import fr.inria.approximated.Approximated;
import jdk.internal.vm.annotation.DontInline;

/**
 * Created by elmarce on 28/10/16.
 */
public class JsynLoopsMicroBenchs {


    int arraySize = 2000000;

    double[] outputs  = new double[arraySize];
    double[] frequencies  = new double[arraySize];
    double[] amplitudes  = new double[arraySize];

    public double t0 = 0.0;
    public double t = t0;
    public double p = 1.0 / 44100.0;

    //@Approximated
    @DontInline
    public void testSineWaveGenerator() {
        long a = System.nanoTime();
        for (int i = 0; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];
        }
        long b = System.nanoTime() - a;
        System.out.println("Result:" + b);
    }

    @DontInline
    public void testSawtoothGenerator() {
        long a = System.nanoTime();
        for (int i = 0; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = phase * amplitudes[i];
        }
        long b = System.nanoTime() - a;
        System.out.println("Result:" + b);
    }

    @Approximated
    @DontInline
    public void testSquareGenerator() {
        long a = System.nanoTime();
        for (int i = 0; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            double ampl = amplitudes[i];
            outputs[i] = (phase < 0.0) ? -ampl : ampl;
        }
        long b = System.nanoTime() - a;
        System.out.println("Result:" + b);
    }

    //@Approximated
    @DontInline
    public void testTriangleGenerator() {
        long a = System.nanoTime();
        for (int i = 0; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            double triangle = (phase >= 0.0) ? (0.5 - phase) : (0.5 + phase);
            outputs[i] = triangle * 2.0 * amplitudes[i];
        }
        long b = System.nanoTime() - a;
        System.out.println("Result:" + b);
    }

}
