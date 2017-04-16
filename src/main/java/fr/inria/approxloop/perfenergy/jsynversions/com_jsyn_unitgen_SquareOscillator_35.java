package fr.inria.approxloop.perfenergy.jsynversions;

import fr.inria.approxloop.perfenergy.JsynLoopsMicroBenchs;

/**
 * Created by elmarce on 16/04/17.
 */
public class com_jsyn_unitgen_SquareOscillator_35 extends JsynLoopsMicroBenchs {

    public void benchmark() {
        for (int i = 0; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            double ampl = amplitudes[i];
            outputs[i] = (phase < 0.0) ? -ampl : ampl;
        }
    }

    public void benchmark_NN34() {
        for (int i = 0; i < 2000000; i+=4) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            double ampl = amplitudes[i];
            outputs[i] = (phase < 0.0) ? -ampl : ampl;
            outputs[i + 1] = outputs[i];
            outputs[i + 2] = outputs[i];
            outputs[i + 3] = outputs[i];
        }
    }

    public void benchmark_NN4() {
        for (int i = 0; i < 2000000; i+=2) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            double ampl = amplitudes[i];
            outputs[i] = (phase < 0.0) ? -ampl : ampl;

            i++;
            t = (t0 + p * (double) (i)) * frequencies[i];
            phase = 2 * (t - Math.floor(t + 0.5));
            ampl = amplitudes[i];
            outputs[i] = (phase < 0.0) ? -ampl : ampl;

            i++;
            t = (t0 + p * (double) (i)) * frequencies[i];
            phase = 2 * (t - Math.floor(t + 0.5));
            ampl = amplitudes[i];
            outputs[i] = (phase < 0.0) ? -ampl : ampl;

            outputs[i + 1] = outputs[i];
        }
    }
}
