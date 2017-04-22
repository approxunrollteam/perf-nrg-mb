package fr.inria.approxloop.perfenergy.openimajversions;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_openimaj_image_processing_convolution_FImageConvolveSeparable_convolveHorizontal_127 {

    float[] kernel = new float[7];
    public int width = 640 * 2;
    float buffer[] = new float[width + kernel.length];

    public float[] benchmark() {
        final int l = buffer.length - kernel.length;
        //@@LOOP BEGIN@@
        for (int i = 0; i < l; i++) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
        }
        return buffer;
    }
   //YEAH
    public float[] benchmark_MN34() {
        final int l = buffer.length - kernel.length;
        //@@LOOP BEGIN@@
        for (int i = 0; i < 1; i++) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
        }
        for (int i = 4; i < l - 4; i += 4) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
            buffer[i - 1] = (buffer[i - 4]) * 0.25f + (buffer[i]) * 0.75f;
            buffer[i - 2] = (buffer[i - 4] + buffer[i]) * 0.5f;
            buffer[i - 3] = (buffer[i - 4]) * 0.75f + (buffer[i]) * 0.25f;
            //System.out.print(".");
        }
        for (int i = l - 4; i < l; i++) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
        }
        return buffer;
    }

    public float[] benchmark_NN34() {
        final int l = buffer.length - kernel.length;
        //@@LOOP BEGIN@@
        for (int i = 0; i < l - 4; i += 4) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
            buffer[i + 1] = sum;
            buffer[i + 2] = sum;
            buffer[i + 3] = sum;
            //System.out.print(".");
        }
        for (int i = l - 4; i < l; i++) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
        }
        return buffer;
    }

    public float[] benchmark_PERF() {
        final int l = buffer.length - kernel.length;

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
        final int l = buffer.length - kernel.length;
        //@@LOOP BEGIN@@
        for (int i = 0; i < l - 1; i += 2) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
            buffer[i + 1] = buffer[i] = sum;
        }
        for (int i = l - 1; i < l; i += 2) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
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

        //final float buffer[] = new float[width + kernel.length];
        //final int l = buffer.length - kernel.length;
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

            i += 2;
            float sum4 = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum4 += buffer[i + j] * kernel[jj];
            buffer[i] = sum4;

            buffer[i] = (sum4 + sum2) * 0.5f;
        }

        //final float buffer[] = new float[width + kernel.length];
        //final int l = buffer.length - kernel.length;
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

    public float[] benchmark_MN() {
        final int l = buffer.length - kernel.length;
        for (int i = 0; i < 1; i++) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[j] * kernel[jj];
            buffer[0] = sum;
            buffer[1] = buffer[0] = sum;
        }
        //@@LOOP BEGIN@@
        for (int i = 2; i < l; i += 2) {
            float sum = 0.0f;
            //@@LOOP BEGIN@@
            for (int j = 0, jj = kernel.length - 1; j < kernel.length; j++, jj--)
                sum += buffer[i + j] * kernel[jj];
            buffer[i] = sum;
            buffer[i - 1] = 0.5f * (buffer[i] + buffer[i - 2]);
        }/**/
        return buffer;
    }



}
