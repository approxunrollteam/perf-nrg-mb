package fr.inria.approxloop.perfenergy.jsynversions;

import fr.inria.approxloop.perfenergy.JsynLoopsMicroBenchs;

/**
 * Created by elmarce on 16/04/17.
 */
public class com_jsyn_unitgen_MixerMono_58 extends JsynLoopsMicroBenchs {

    private double[] getValues(int n) {
        if (n == 0)
            return inputs;
        else if ( n == 1 ) return amplitudes;
        else return offsets;
    }

    private double[] getValues2(int n) {
        if (n == 0)
            return amplitudes;
        else if ( n == 1 ) return offsets;
        else return inputs;
    }

    public void benchmark() {
        for (int i = 0; i < 2000; i++) {
            double sum = 0;
            for (int n = 0; n < 3; n++) {
                double[] inputs = getValues(n);
                double[] gains = getValues2(n);
                sum += inputs[i] * gains[i];
            }
            outputs[i] = sum * amplitudes[i];
        }
    }

    public void benchmark_NN34() {
        for (int i = 0; i < 2000; i+=4) {
            double sum = 0;
            for (int n = 0; n < 3; n++) {
                double[] inputs = getValues(n);
                double[] gains = getValues2(n);
                sum += inputs[i] * gains[i];
            }
            outputs[i] = sum * amplitudes[i];
            outputs[i + 1] = outputs[i];
            outputs[i + 2] = outputs[i];
            outputs[i + 3] = outputs[i];
        }
    }

    public void benchmark_NN4() {
        for (int i = 0; i < 2000; i+=2) {
            double sum = 0;
            for (int n = 0; n < 3; n++) {
                double[] inputs = getValues(n);
                double[] gains = getValues2(n);
                sum += inputs[i] * gains[i];
            }
            outputs[i] = sum * amplitudes[i];

            i++;
            sum = 0;
            for (int n = 0; n < 3; n++) {
                double[] inputs = getValues(n);
                double[] gains = getValues2(n);
                sum += inputs[i] * gains[i];
            }
            outputs[i] = sum * amplitudes[i];

            i++;
            sum = 0;
            for (int n = 0; n < 3; n++) {
                double[] inputs = getValues(n);
                double[] gains = getValues2(n);
                sum += inputs[i] * gains[i];
            }
            outputs[i] = sum * amplitudes[i];
            outputs[i + 1] = outputs[i];
        }
    }
}
