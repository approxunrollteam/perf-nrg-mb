package fr.inria.approxloop.perfenergy.jsynversions;

import fr.inria.approxloop.perfenergy.JsynLoopsMicroBenchs;

/**
 * Created by elmarce on 16/04/17.
 */
public class com_jsyn_unitgen_TriangleOscillator_42 extends JsynLoopsMicroBenchs {

    public void benchmark() {
        for (int i = 0; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            double triangle = (phase >= 0.0) ? (0.5 - phase) : (0.5 + phase);
            outputs[i] = triangle * 2.0 * amplitudes[i];
        }
    }
}
