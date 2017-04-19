package fr.inria.approxloop.perfenergy.jsynversions;

import fr.inria.approxloop.perfenergy.JsynLoopsMicroBenchs;

/**
 * Created by elmarce on 16/04/17.
 */
public class com_jsyn_unitgen_MixerMono_method_58 extends JsynLoopsMicroBenchs {

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

    public void benchmark_PERF() {
        for (int i = 0; i < 2000; i+=2) {
            double sum = 0;
            for (int n = 0; n < 3; n++) {
                double[] inputs = getValues(n);
                double[] gains = getValues2(n);
                sum += inputs[i] * gains[i];
            }
            outputs[i] = sum * amplitudes[i];
        }
    }

    public void benchmark_NN() {
        for (int i = 0; i < 2000; i+=2) {
            double sum = 0;
            for (int n = 0; n < 3; n++) {
                double[] inputs = getValues(n);
                double[] gains = getValues2(n);
                sum += inputs[i] * gains[i];
            }
            outputs[i] = sum * amplitudes[i];
            outputs[i + 1] = outputs[i];
        }
    }

    public void benchmark_MN() {
        double sum = 0;
        for (int n = 0; n < 3; n++) {
            double[] inputs = getValues(n);
            double[] gains = getValues2(n);
            sum += inputs[0] * gains[0];
        }
        outputs[0] = sum * amplitudes[0];
        for (int i = 2; i < 2000; i+=2) {
            sum = 0;
            for (int n = 0; n < 3; n++) {
                double[] inputs = getValues(n);
                double[] gains = getValues2(n);
                sum += inputs[i] * gains[i];
            }
            outputs[i] = sum * amplitudes[i];
            outputs[i - 1] = (outputs[i] + outputs[i - 2] ) * 0.5f;
        }
    }

    public void benchmark_MN34() {
        for (int i = 0; i < 1; i++) {
            double sum = 0;
            for (int n = 0; n < 3; n++) {
                double[] inputs = getValues(n);
                double[] gains = getValues2(n);
                sum += inputs[i] * gains[i];
            }
            outputs[i] = sum * amplitudes[i];
        }

        for (int i = 4; i < 2000000; i++) {
            double sum = 0;
            for (int n = 0; n < 3; n++) {
                double[] inputs = getValues(n);
                double[] gains = getValues2(n);
                sum += inputs[i] * gains[i];
            }
            outputs[i] = sum * amplitudes[i];
            outputs[i - 1] = (outputs[i] * 0.75f + outputs[i - 4] * 0.25f);
            outputs[i - 2] = (outputs[i] + outputs[i - 4]) * 0.5f;
            outputs[i - 3] = (outputs[i] * 0.25f + outputs[i - 4] * 0.75f);
        }
    }

    public void benchmark_MN4() {
        for (int i = 0; i < 2000000; i++) {
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

            i += 2;
            sum = 0;
            for (int n = 0; n < 3; n++) {
                double[] inputs = getValues(n);
                double[] gains = getValues2(n);
                sum += inputs[i] * gains[i];
            }
            outputs[i] = sum * amplitudes[i];
            outputs[i - 1] = 0.5f * (outputs[i] + outputs[i - 2]);
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
