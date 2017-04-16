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
