package fr.inria.approxloop.perfenergy.jsynversions;

/**
 * Created by elmarce on 16/04/17.
 */
public class com_jsyn_unitgen_Delay_47 {
    int arraySize = 2000;
    public double[] buffer = new double[arraySize];
    public double[] outputs = new double[arraySize];
    public double[] inputs = new double[arraySize];

    public void benchmark() {
        int cursor = 0;
        int numSamples = arraySize;
        for (int i = 0; i < 2000; i++) {
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
            cursor += 1;
            if (cursor >= numSamples) {
                cursor = 0;
            }
        }
    }


    public void benchmark_MN4() {
        int cursor = 0;
        int numSamples = arraySize;
        for (int i = 0; i < 2000; i++) {
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
            cursor += 1;
            if (cursor >= numSamples) {
                cursor = 0;
            }
            i++;
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
            cursor += 1;
            if (cursor >= numSamples) {
                cursor = 0;
            }

            i += 2;
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
            cursor += 1;
            if (cursor >= numSamples) {
                cursor = 0;
            }

            outputs[i - 1] = (outputs[i] + outputs[i - 2]) * 0.5f;
        }
    }

    public void benchmark_MN34() {
        int cursor = 0;
        int numSamples = arraySize;

        for (int i = 0; i < 1; i++) {
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
            cursor += 1;
            if (cursor >= numSamples) {
                cursor = 0;
            }
        }
        for (int i = 4; i < 2000; i+=4) {
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
            cursor += 1;
            if (cursor >= numSamples) {
                cursor = 0;
            }
            outputs[i - 1] = (outputs[i] * 0.75f + outputs[i - 4] * 0.25f);
            outputs[i - 2] = (outputs[i] + outputs[i - 4]) * 0.5f;
            outputs[i - 3] = (outputs[i] * 0.25f + outputs[i - 4] * 0.75f);
        }
    }


    public void benchmark_NN34() {
        int cursor = 0;
        int numSamples = arraySize;
        for (int i = 0; i < 2000; i++) {
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
            cursor += 1;
            if (cursor >= numSamples) {
                cursor = 0;
            }

            i++;
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];

            i++;
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];

            i++;
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
        }
    }

    public void benchmark_NN4() {
        int cursor = 0;
        int numSamples = arraySize;
        for (int i = 0; i < 2000; i++) {
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
            cursor += 1;
            if (cursor >= numSamples) {
                cursor = 0;
            }

            i++;
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
            cursor += 1;
            if (cursor >= numSamples) {
                cursor = 0;
            };

            i++;
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
            cursor += 1;
            if (cursor >= numSamples) {
                cursor = 0;
            }

            i++;
            outputs[i] = buffer[cursor];
            buffer[cursor] = (float) inputs[i];
        }
    }

}
