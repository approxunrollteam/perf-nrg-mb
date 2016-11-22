package fr.inria.approxloop.perfenergy;

/*
import fr.inria.approximated.Approximated;
import jdk.internal.vm.annotation.DontInline;
*/

/**
 * Created by elmarce on 28/10/16.
 */
public class JsynLoopsMicroBenchs {


    int arraySize = 2000000;

    double[] outputs      = new double[arraySize];
    double[] frequencies  = new double[arraySize];
    double[] amplitudes   = new double[arraySize];

    double[] inputs     = new double[arraySize];
    double[] feedbacks  = new double[arraySize];
    double[] depths     = new double[arraySize];
    double[] offsets    = new double[arraySize];
    double[] buffer    = new double[arraySize];

    public double t0 = 0.0;
    public double t = t0;
    public double p = 1.0 / 44100.0;

    private double zm1;
    private double[] xs = new double[6];
    private double[] ys = new double[6];

    //@Approximated
    //@DontInline
    public void benchmarkSineWaveGenerator() {
        long a = System.nanoTime();
        for (int i = 0; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];
        }
        long b = System.nanoTime() - a;
        System.out.println("Result:" + b);
    }

    //@DontInline
    public void benchmarkSawtoothGenerator() {
        long a = System.nanoTime();
        for (int i = 0; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = phase * amplitudes[i];
        }
        long b = System.nanoTime() - a;
        System.out.println("Result:" + b);
    }

    //@Approximated
    //@DontInline
    public void benchmarkSquareGenerator() {
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
    //@DontInline
    public void benchmarkTriangleGenerator() {
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

    double a0_jsyn, a1_jsyn, a2_jsyn, b1_jsyn, b2_jsyn;
    double x0_jsyn, x1_jsyn, x2_jsyn, y1_jsyn, y2_jsyn;

    //@Approximated
    //@DontInline
    public void benchFilter() {
        for (int i = 0; i < 2000000; i += 2)
        {
            x0_jsyn = inputs[i];
            y2_jsyn = (a0_jsyn * x0_jsyn) + (a1_jsyn * x1_jsyn) + (a2_jsyn * x2_jsyn)
                    - (b1_jsyn * y1_jsyn) - (b2_jsyn * y2_jsyn);

            outputs[i] = amplitudes[i] * y2_jsyn;

            x2_jsyn = inputs[i + 1];
            y1_jsyn = (a0_jsyn * x2_jsyn) + (a1_jsyn * x0_jsyn) + (a2_jsyn * x1_jsyn)
                    - (b1_jsyn * y2_jsyn) - (b2_jsyn * y1_jsyn);

            outputs[i + 1] = amplitudes[i + 1] * y1_jsyn;

            x1_jsyn = x2_jsyn;
            x2_jsyn = x0_jsyn;
        }
    }

    public void benchmarkDelay() {
        long a = System.nanoTime();

        int cursor = 0;
        int numSamples = arraySize;
        for (int i = 0; i < 1200000; i++) {
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
            cursor += 1;
            if (cursor >= numSamples) {
                cursor = 0;
            }
        }

        long b = System.nanoTime() - a;
        System.out.println(b);
    }

    public void benchmarkMixer() {
        for (int i = 0; i < 2000000; i++) {
            double sum = 0;
            for (int n = 0; n < 3; n++) {
                double[] inputs = input.getValues(n);
                double[] gains = gain.getValues(n);
                sum += inputs[i] * gains[i];
            }
            outputs[i] = sum * amplitudes[i];
        }
    }

    //@Approximated
    //@DontInline
    public void benchmarkPhaseShifter() {

        long a = System.nanoTime();

        double gain;

        for (int i = 0; i < 2000000; i++) {
            // Support audio rate modulation.
            double currentOffset = offsets[i];

            // Prevent gain from exceeding 1.0.
            gain = 1.0 - (currentOffset * currentOffset);
            if (gain < -1.0) {
                gain = -1.0;
            }

            double x = inputs[i] + (zm1 * feedbacks[i]);
            // Cascaded all-pass filters.
            for (int stage = 0; stage < xs.length; stage++) {
                double temp = ys[stage] = (gain * (ys[stage] - x)) + xs[stage];
                xs[stage] = x;
                x = temp;
            }
            zm1 = x;
            outputs[i] = inputs[i] + (x * depths[i]);
        }


        long b = System.nanoTime() - a;
        System.out.println(b);
    }

}
