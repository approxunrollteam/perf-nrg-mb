package fr.inria.approxloop.perfenergy.jsynversions;

import fr.inria.approxloop.perfenergy.JsynLoopsMicroBenchs;

/**
 * Created by elmarce on 16/04/17.
 */
public class com_jsyn_unitgen_PhaseShifter_69 extends JsynLoopsMicroBenchs {

    public void benchmark() {

        double gain;

        for (int i = 0; i < 2000; i++) {
            double currentOffset = offsets[i];
            gain = 1.0 - (currentOffset * currentOffset);
            if (gain < -1.0) {
                gain = -1.0;
            }
            double x = inputs[i] + (zm1 * feedbacks[i]);
            for (int stage = 0; stage < xs.length; stage++) {
                double temp = ys[stage] = (gain * (ys[stage] - x)) + xs[stage];
                xs[stage] = x;
                x = temp;
            }
            zm1 = x;
            outputs[i] = inputs[i] + (x * depths[i]);
        }
    }

    public void benchmark_MN34() {
        double gain;
        for (int i = 0; i < 1; i++) {
            double currentOffset = offsets[i];
            gain = 1.0 - (currentOffset * currentOffset);
            if (gain < -1.0) {
                gain = -1.0;
            }
            double x = inputs[i] + (zm1 * feedbacks[i]);
            for (int stage = 0; stage < xs.length; stage++) {
                double temp = ys[stage] = (gain * (ys[stage] - x)) + xs[stage];
                xs[stage] = x;
                x = temp;
            }
            zm1 = x;
            outputs[i] = inputs[i] + (x * depths[i]);
        }

        for (int i = 4; i < 2000000; i++) {
            double currentOffset = offsets[i];
            gain = 1.0 - (currentOffset * currentOffset);
            if (gain < -1.0) {
                gain = -1.0;
            }
            double x = inputs[i] + (zm1 * feedbacks[i]);
            for (int stage = 0; stage < xs.length; stage++) {
                double temp = ys[stage] = (gain * (ys[stage] - x)) + xs[stage];
                xs[stage] = x;
                x = temp;
            }
            zm1 = x;
            outputs[i] = inputs[i] + (x * depths[i]);
            outputs[i - 1] = (outputs[i] * 0.75f + outputs[i - 4] * 0.25f);
            outputs[i - 2] = (outputs[i] + outputs[i - 4]) * 0.5f;
            outputs[i - 3] = (outputs[i] * 0.25f + outputs[i - 4] * 0.75f);
        }
    }

    public void benchmark_MN4() {
        double gain;
        for (int i = 0; i < 2000000; i++) {
            double currentOffset = offsets[i];
            gain = 1.0 - (currentOffset * currentOffset);
            if (gain < -1.0) {
                gain = -1.0;
            }
            double x = inputs[i] + (zm1 * feedbacks[i]);
            for (int stage = 0; stage < xs.length; stage++) {
                double temp = ys[stage] = (gain * (ys[stage] - x)) + xs[stage];
                xs[stage] = x;
                x = temp;
            }
            zm1 = x;
            outputs[i] = inputs[i] + (x * depths[i]);

            i++;
            currentOffset = offsets[i];
            gain = 1.0 - (currentOffset * currentOffset);
            if (gain < -1.0) {
                gain = -1.0;
            }
            x = inputs[i] + (zm1 * feedbacks[i]);
            for (int stage = 0; stage < xs.length; stage++) {
                double temp = ys[stage] = (gain * (ys[stage] - x)) + xs[stage];
                xs[stage] = x;
                x = temp;
            }
            zm1 = x;
            outputs[i] = inputs[i] + (x * depths[i]);

            i += 2;
            currentOffset = offsets[i];
            gain = 1.0 - (currentOffset * currentOffset);
            if (gain < -1.0) {
                gain = -1.0;
            }
            x = inputs[i] + (zm1 * feedbacks[i]);
            for (int stage = 0; stage < xs.length; stage++) {
                double temp = ys[stage] = (gain * (ys[stage] - x)) + xs[stage];
                xs[stage] = x;
                x = temp;
            }
            zm1 = x;
            outputs[i] = inputs[i] + (x * depths[i]);
            outputs[i - 1] = 0.5f * (outputs[i] + outputs[i - 2]);
        }
    }

    public void benchmark_NN34() {

        double gain;

        for (int i = 0; i < 2000; i+=4) {
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
            outputs[i+ 1] = outputs[i];
            outputs[i+ 2] = outputs[i];
            outputs[i+ 3] = outputs[i];
        }
    }

    public void benchmark_NN4() {

        double gain;

        for (int i = 0; i < 2000; i+=2) {
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

            i++;
            // Support audio rate modulation.
            currentOffset = offsets[i];

            // Prevent gain from exceeding 1.0.
            gain = 1.0 - (currentOffset * currentOffset);
            if (gain < -1.0) {
                gain = -1.0;
            }

            x = inputs[i] + (zm1 * feedbacks[i]);
            // Cascaded all-pass filters.
            for (int stage = 0; stage < xs.length; stage++) {
                double temp = ys[stage] = (gain * (ys[stage] - x)) + xs[stage];
                xs[stage] = x;
                x = temp;
            }
            zm1 = x;
            outputs[i] = inputs[i] + (x * depths[i]);

            i++;
            // Support audio rate modulation.
            currentOffset = offsets[i];

            // Prevent gain from exceeding 1.0.
            gain = 1.0 - (currentOffset * currentOffset);
            if (gain < -1.0) {
                gain = -1.0;
            }

            x = inputs[i] + (zm1 * feedbacks[i]);
            // Cascaded all-pass filters.
            for (int stage = 0; stage < xs.length; stage++) {
                double temp = ys[stage] = (gain * (ys[stage] - x)) + xs[stage];
                xs[stage] = x;
                x = temp;
            }
            zm1 = x;
            outputs[i] = inputs[i] + (x * depths[i]);
            outputs[i + 1] = outputs[i];
        }
    }

}
