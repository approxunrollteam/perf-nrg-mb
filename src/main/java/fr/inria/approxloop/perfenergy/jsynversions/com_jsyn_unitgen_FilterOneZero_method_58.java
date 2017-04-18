package fr.inria.approxloop.perfenergy.jsynversions;

import fr.inria.approxloop.perfenergy.JsynLoopsMicroBenchs;

/**
 * Created by elmarce on 16/04/17.
 */
public class com_jsyn_unitgen_FilterOneZero_method_58 extends JsynLoopsMicroBenchs {

    public double a0v, a1v, x1;

    public void benchmark() {
        for (int i = 0; i < 2000; i++) {
            double x0 = inputs[i];
            outputs[i] = (a0v * x0) + (a1v * x1);
            x1 = x0;
        }
    }

    public void benchmark_MN34() {
        {
            double x0 = inputs[0];
            outputs[0] = (a0v * x0) + (a1v * x1);
            x1 = x0;
            for (int i = 4; i < 2000000; i++) {
                x0 = inputs[i];
                outputs[i] = (a0v * x0) + (a1v * x1);
                x1 = x0;
                outputs[i - 1] = (outputs[i] * 0.75f + outputs[i - 4] * 0.25f);
                outputs[i - 2] = (outputs[i] + outputs[i - 4]) * 0.5f;
                outputs[i - 3] = (outputs[i] * 0.25f + outputs[i - 4] * 0.75f);
            }
        }
    }

    public void benchmark_MN4() {
        for (int i = 0; i < 2000000; i++) {
            double x0 = inputs[i];
            outputs[i] = (a0v * x0) + (a1v * x1);
            x1 = x0;

            i++;
            x0 = inputs[i];
            outputs[i] = (a0v * x0) + (a1v * x1);
            x1 = x0;

            i += 2;
            x0 = inputs[i];
            outputs[i] = (a0v * x0) + (a1v * x1);
            x1 = x0;
            outputs[i - 1] = 0.5f * (outputs[i] + outputs[i - 2]);
        }
    }

    public void benchmark_NN34() {
        for (int i = 0; i < 2000000; i += 4) {
            double x0 = inputs[i];
            outputs[i] = (a0v * x0) + (a1v * x1);
            x1 = x0;
            outputs[i + 1] = outputs[i];
            outputs[i + 2] = outputs[i];
            outputs[i + 3] = outputs[i];
        }
    }

    public void benchmark_NN4() {
        for (int i = 0; i < 2000000; i += 2) {
            double x0 = inputs[i];
            outputs[i] = (a0v * x0) + (a1v * x1);
            x1 = x0;

            x0 = inputs[i];
            outputs[i] = (a0v * x0) + (a1v * x1);
            x1 = x0;

            x0 = inputs[i];
            outputs[i] = (a0v * x0) + (a1v * x1);
            x1 = x0;
            outputs[i + 1] = outputs[i];
        }
    }

}
