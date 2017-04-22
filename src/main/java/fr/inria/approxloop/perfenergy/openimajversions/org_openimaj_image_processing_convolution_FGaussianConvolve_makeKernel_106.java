package fr.inria.approxloop.perfenergy.openimajversions;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_openimaj_image_processing_convolution_FGaussianConvolve_makeKernel_106 {

    public float sigma = 0.7f;
    public float truncate = 4.0f;
    public float[] kernel = new float[7];;

    public float benchmark() {
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.0f;
        //@@LOOP BEGIN@@
        for (int i = 0; i < ksize; i++) {
            final float x = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x * x / (2.0 * sigma * sigma));
            sum += kernel[i];
        }
        return sum;
    }
   ///YEAH

    public float benchmark_MN34() {
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.0f;
        //@@LOOP BEGIN@@
        for (int i = 0; i < 1; i++) {
            final float x = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x * x / (2.0 * sigma * sigma));
            sum += kernel[i];
        }
        for (int i = 4; i < ksize - 4; i += 4) {
            final float x = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x * x / (2.0 * sigma * sigma));
            sum += kernel[i];
            kernel[i - 1] = kernel[i - 4] * 0.25f + kernel[i] * 0.75f;
            sum += kernel[i - 1];
            kernel[i - 2] = (kernel[i - 4] + kernel[i]) * 0.5f;
            sum += kernel[i - 2];
            kernel[i - 3] = kernel[i - 4] * 0.75f + kernel[i] * 0.25f;
            sum += kernel[i - 3];
            //System.out.print(".");
        }
        for (int i = ksize - 4; i < ksize; i++) {
            final float x = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x * x / (2.0 * sigma * sigma));
            sum += kernel[i];
        }
        return sum;
    }

    public float benchmark_NN34() {
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.0f;
        //@@LOOP BEGIN@@
        for (int i = 0; i < ksize - 4; i +=4) {
            final float x = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x * x / (2.0 * sigma * sigma));
            sum += kernel[i];
            kernel[i + 1] = kernel[i];
            sum += kernel[i];
            kernel[i + 2] = kernel[i];
            sum += kernel[i];
            kernel[i + 3] = kernel[i];
            sum += kernel[i];
            ////System.out.print(".");
        }
        for (int i = ksize - 4; i < ksize; i++) {
            final float x = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x * x / (2.0 * sigma * sigma));
            sum += kernel[i];
        }
        return sum;
    }

    public float benchmark_MN4() {
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.0f;
        //@@LOOP BEGIN@@
        for (int i = 0; i < ksize - 3; i++) {
            final float x1 = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x1 * x1 / (2.0 * sigma * sigma));
            sum += kernel[i];

            i++;
            final float x2 = i - ksize / 2;
            final float k2 = (float) Math.exp(-x2 * x2 / (2.0 * sigma * sigma));
            kernel[i] = k2;
            sum += kernel[i];

            i += 2;
            final float x4 = i - ksize / 2;
            final float k4 = (float) Math.exp(-x4 * x4 / (2.0 * sigma * sigma));
            kernel[i] = k4;
            sum += kernel[i];

            kernel[i - 1] = (k2 + k4) * 0.5f;
            sum += kernel[i];
        }
        for (int i = ksize - 3; i < ksize; i++) {
            final float x1 = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x1 * x1 / (2.0 * sigma * sigma));
            sum += kernel[i];
        }
        return sum;
    }

    public float benchmark_NN4() {
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.0f;
        //@@LOOP BEGIN@@
        for (int i = 0; i < ksize - 3; i++) {
            final float x1 = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x1 * x1 / (2.0 * sigma * sigma));
            sum += kernel[i];

            i++;
            final float x2 = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x2 * x2 / (2.0 * sigma * sigma));
            sum += kernel[i];

            i++;
            final float x3 = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x3 * x3 / (2.0 * sigma * sigma));
            sum += kernel[i];

            i++;
            kernel[i] = kernel[i];
            sum += kernel[i];
        }
        //@@LOOP BEGIN@@
        for (int i = ksize - 3; i < ksize; i++) {
            final float x1 = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x1 * x1 / (2.0 * sigma * sigma));
            sum += kernel[i];
        }
        return sum;
    }

    public float benchmark_PERF() {
        // build kernel
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.0f;

        //@@LOOP BEGIN@@
        for (int i = 0; i < ksize; i += 2) {
            final float x = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x * x / (2.0 * sigma * sigma));
            sum += kernel[i];
        }
        return sum;
    }

    public float benchmark_NN() {
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.0f;
        //@@LOOP BEGIN@@
        for (int i = 0; i < ksize - 1; i += 2) {
            final float x = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x * x / (2.0 * sigma * sigma));
            sum += kernel[i];
            kernel[i + 1] = kernel[i];
            sum += kernel[i];
        }
        for (int i = ksize - 1; i < ksize; i++) {
            final float x = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x * x / (2.0 * sigma * sigma));
            sum += kernel[i];
        }
        return sum;
    }

    public float benchmark_MN() {
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.0f;

        for (int i = 0; i < 1; i++) {
            final float xx = 0 - ksize / 2;
            kernel[0] = (float) Math.exp(-xx * xx / (2.0 * sigma * sigma));
            sum += kernel[0];
        }
        //@@LOOP BEGIN@@
        for (int i = 2; i < ksize - 1; i += 2) {
            final float x = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x * x / (2.0 * sigma * sigma));
            sum += kernel[i];
            kernel[i - 1] = 0.5f * (kernel[i] + kernel[i - 2]);
            sum += kernel[i];
        }
        for (int i = ksize - 1; i < ksize; i++) {
            final float x = i - ksize / 2;
            kernel[i] = (float) Math.exp(-x * x / (2.0 * sigma * sigma));
            sum += kernel[i];
        }
        return sum;
    }

}
