package fr.inria.approxloop.perfenergy.jsynversions;

import fr.inria.approxloop.perfenergy.JsynLoopsMicroBenchs;

/**
 * Created by elmarce on 16/04/17.
 */
public class com_jsyn_unitgen_FilterBiquad_117 extends JsynLoopsMicroBenchs {

    public void benchmark() {
        for (int i = 0; i < 20000; i +=2)
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

    public void benchmark_NN34() {
        for (int i = 0; i < 20000; i +=2)
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


            outputs[i + 1] = outputs[i - 2];
            outputs[i + 2] = outputs[i - 1];

            outputs[i + 3] = outputs[i - 2];
            outputs[i + 4] = outputs[i - 1];

            outputs[i + 5] = outputs[i - 2];
            outputs[i + 6] = outputs[i - 1];

        }
    }

    public void benchmark_NN4() {
        for (int i = 0; i < 20000; i +=2)
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

            i++;
            outputs[i + 1] = outputs[i - 2];
            outputs[i + 2] = outputs[i - 1];
        }
    }

}
