package fr.inria.approxloop.perfenergy.openimajversions;

/**
 * Created by elmarce on 15/04/17.
 */
public class org_openimaj_image_processing_convolution_FImageConvolveSeparable_convolveVertical_166 {

    public int height = 480 * 2;
    public int width = 640 * 2;
    public float[] kernel = new float[7];;
    float buffer[] = new float[height + kernel.length];


    public float[] benchmark_PERF() {
        int l = buffer.length - kernel.length;
        //@@LOOP BEGIN@@ 
        for (int i = 0; i < l; i += 2) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@ 
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
        }
        return buffer;
    }

    public float[] benchmark_NN() {
        int l = buffer.length - kernel.length;
        for (int i = 0; i < l - 1; i+=2) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
            buffer[i + 1] = sum;
        }
        for (int i = l - 1; i < l; i++) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
        }
        return buffer;
    }

    public float[] benchmark_MN() {
        final int l = buffer.length - kernel.length;
        //@@LOOP BEGIN@@
        for (int i = 0; i < 1; i++) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
        }
        for (int i = 2; i < l; i += 2) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
            buffer[i - 1] = (buffer[i] + buffer[i - 2]) * 0.5f;
        }
        return buffer;
    }

    public float[] benchmark_NN4() {
        final int l = buffer.length - kernel.length;
        //@@LOOP BEGIN@@ 
        for (int i = 0; i < l - 3; i++) {
            float sum1 = 0.0f;
            //@@LOOP BEGIN@@ 
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum1 += buffer[i + j] * kernel[jj];
            buffer[i] = sum1;

            i++;
            float sum2 = 0.0f;
            //@@LOOP BEGIN@@ 
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum2 += buffer[i + j] * kernel[jj];
            buffer[i] = sum2;

            i++;
            float sum3 = 0.0f;
            //@@LOOP BEGIN@@ 
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum3 += buffer[i + j] * kernel[jj];
            buffer[i] = sum3;

            i++;
            buffer[i] = sum3;
        }
        //@@LOOP BEGIN@@ 
        for (int i = l - 4; i < l; i++) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@ 
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
        }
        return buffer;
    }

    public float[] benchmark_MN4() {
        final int l = buffer.length - kernel.length;
        //@@LOOP BEGIN@@ 
        for (int i = 0; i < l - 3; i += 2) {
            float sum1 = 0.0f;
            //@@LOOP BEGIN@@ 
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum1 += buffer[i + j] * kernel[jj];
            buffer[i] = sum1;

            i++;
            float sum2 = 0.0f;
            //@@LOOP BEGIN@@ 
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum2 += buffer[i + j] * kernel[jj];
            buffer[i] = sum2;

            i++;
            float sum4 = 0.0f;
            //@@LOOP BEGIN@@ 
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum4 += buffer[i + j] * kernel[jj];
            buffer[i] = sum4;

            i++;
            buffer[i] = (sum4 + sum2) * 0.5f;

        }

        //@@LOOP BEGIN@@ 
        for (int i = l - 3; i < l; i += 2) {
            float sum1 = 0.0f;
            //@@LOOP BEGIN@@ 
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum1 += buffer[i + j] * kernel[jj];
            buffer[i] = sum1;
        }

        return buffer;
    }
    
}
