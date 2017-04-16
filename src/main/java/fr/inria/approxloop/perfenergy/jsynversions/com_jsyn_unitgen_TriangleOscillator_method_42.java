package fr.inria.approxloop.perfenergy.jsynversions;

import fr.inria.approxloop.perfenergy.JsynLoopsMicroBenchs;

/**
 * Created by elmarce on 16/04/17.
 */
public class com_jsyn_unitgen_TriangleOscillator_method_42 extends JsynLoopsMicroBenchs {

    public void benchmark() {
        for (int i = 0; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            double triangle = (phase >= 0.0) ? (0.5 - phase) : (0.5 + phase);
            outputs[i] = triangle * 2.0 * amplitudes[i];
        }
    }

    public void benchmark_MN34() {
        for (int i = 0; i < 1; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            double triangle = (phase >= 0.0) ? (0.5 - phase) : (0.5 + phase);
            outputs[i] = triangle * 2.0 * amplitudes[i];
        }

        for (int i = 4; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            double triangle = (phase >= 0.0) ? (0.5 - phase) : (0.5 + phase);
            outputs[i] = triangle * 2.0 * amplitudes[i];
            outputs[i - 1] = (outputs[i] * 0.75f + outputs[i - 4] * 0.25f);
            outputs[i - 2] = (outputs[i] + outputs[i - 4]) * 0.5f;
            outputs[i - 3] = (outputs[i] * 0.25f + outputs[i - 4] * 0.75f);
        }
    }

    public void benchmark_MN4() {
        for (int i = 0; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            double triangle = (phase >= 0.0) ? (0.5 - phase) : (0.5 + phase);
            outputs[i] = triangle * 2.0 * amplitudes[i];

            i++;
            t = (t0 + p * (double) (i)) * frequencies[i];
            phase = 2 * (t - Math.floor(t + 0.5));
            triangle = (phase >= 0.0) ? (0.5 - phase) : (0.5 + phase);
            outputs[i] = triangle * 2.0 * amplitudes[i];

            i += 2;
            t = (t0 + p * (double) (i)) * frequencies[i];
            phase = 2 * (t - Math.floor(t + 0.5));
            triangle = (phase >= 0.0) ? (0.5 - phase) : (0.5 + phase);
            outputs[i] = triangle * 2.0 * amplitudes[i];
            outputs[i - 1] = 0.5f * (outputs[i] + outputs[i - 2]);
        }
    }

    public void benchmark_NN34() {
        for (int i = 0; i < 2000000; i+=4) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            double triangle = (phase >= 0.0) ? (0.5 - phase) : (0.5 + phase);
            outputs[i] = triangle * 2.0 * amplitudes[i];
            outputs[i + 1] = outputs[i];
            outputs[i + 2] = outputs[i];
            outputs[i + 3] = outputs[i];
        }
    }

    public void benchmark_N4() {
        for (int i = 0; i < 2000000; i+=2) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            double triangle = (phase >= 0.0) ? (0.5 - phase) : (0.5 + phase);
            outputs[i] = triangle * 2.0 * amplitudes[i];

            t = (t0 + p * (double) (i)) * frequencies[i];
            phase = 2 * (t - Math.floor(t + 0.5));
            triangle = (phase >= 0.0) ? (0.5 - phase) : (0.5 + phase);
            outputs[i] = triangle * 2.0 * amplitudes[i];

            t = (t0 + p * (double) (i)) * frequencies[i];
            phase = 2 * (t - Math.floor(t + 0.5));
            triangle = (phase >= 0.0) ? (0.5 - phase) : (0.5 + phase);
            outputs[i] = triangle * 2.0 * amplitudes[i];
            outputs[i + 1] = outputs[i];
        }
    }
}