package fr.inria.approxloop.perfenergy.jsynversions;

import fr.inria.approxloop.perfenergy.JsynLoopsMicroBenchs;

/**
 * Created by elmarce on 16/04/17.
 */
public class com_jsyn_unitgen_SineOscillator_method_47 extends JsynLoopsMicroBenchs {

    public void benchmark() {
        for (int i = 0; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];
        }
    }

    public void benchmark_PERF() {
        for (int i = 0; i < 2000000; i+=2) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];
        }
    }

    public void benchmark_NN() {
        for (int i = 0; i < 2000000; i+=2) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];
            outputs[i + 1] = outputs[i];
        }
    }

    public void benchmark_MN() {
        t = (t0 + p * (double) (0)) * frequencies[0];
        double phase = 2 * (t - Math.floor(t + 0.5));
        outputs[0] = Math.sin(phase * Math.PI) * amplitudes[0];
        for (int i = 2; i < 2000000; i+=2) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];
            outputs[i - 1] = (outputs[i] + outputs[i - 2]) * 0.5f;
        }
    }

    public void benchmark_MN34() {
        for (int i = 0; i < 1; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];
        }

        for (int i = 4; i < 2000000; i+=4) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];
            outputs[i - 1] = (outputs[i] * 0.75f + outputs[i - 4] * 0.25f);
            outputs[i - 2] = (outputs[i] + outputs[i - 4]) * 0.5f;
            outputs[i - 3] = (outputs[i] * 0.25f + outputs[i - 4] * 0.75f);
        }
    }

    public void benchmark_MN4() {
        for (int i = 0; i < 2000000; i++) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];

            i++;
            t = (t0 + p * (double) (i)) * frequencies[i];
            phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];

            i += 2;
            t = (t0 + p * (double) (i)) * frequencies[i];
            phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];
            outputs[i - 1] = 0.5f * (outputs[i] + outputs[i - 2]);
        }
    }

    public void benchmark_NN34() {
        for (int i = 0; i < 2000000; i+=4) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];
            outputs[i + 1] = outputs[i];
            outputs[i + 2] = outputs[i];
            outputs[i + 3] = outputs[i];
        }
    }


    public void benchmark_NN4() {
        for (int i = 0; i < 2000000; i+=2) {
            t = (t0 + p * (double) (i)) * frequencies[i];
            double phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];

            i++;
            t = (t0 + p * (double) (i)) * frequencies[i];
            phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];

            i++;
            t = (t0 + p * (double) (i)) * frequencies[i];
            phase = 2 * (t - Math.floor(t + 0.5));
            outputs[i] = Math.sin(phase * Math.PI) * amplitudes[i];
            outputs[i + 1] = outputs[i];
        }
    }
}
