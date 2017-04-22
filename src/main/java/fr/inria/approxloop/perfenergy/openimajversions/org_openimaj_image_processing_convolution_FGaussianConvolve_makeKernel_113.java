package fr.inria.approxloop.perfenergy.openimajversions;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_openimaj_image_processing_convolution_FGaussianConvolve_makeKernel_113 {

    public float sigma = 0.7f;
    public float truncate = 4.0f;
    public float[] kernel = new float[7];;

    public void benchmark() {
        // build kernel
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.8f;
        //@@LOOP BEGIN@@
        for (int i = 0; i < ksize; i++) {
            kernel[i] /= sum;
        }
    }

    public void benchmark_MN() {
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.8f;
        kernel[0] /= sum;
        //@@LOOP BEGIN@@
        for (int i = 0; i < 1; i++) {
            kernel[i] /= sum;
        }
        for (int i = 2; i < ksize; i += 2) {
            kernel[i] /= sum;
            kernel[i - 1] = 0.5f * (kernel[i] + kernel[i - 2]);
        }
    }

    public void benchmark_PERF() {
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.8f;
        //@@LOOP BEGIN@@
        for (int i = 0; i < ksize; i += 2) {
            kernel[i] /= sum;
        }
    }

    public void benchmark_NN() {
        // build kernel
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.8f;
        //@@LOOP BEGIN@@
        for (int i = 0; i < ksize - 1; i += 2) {
            kernel[i] /= sum;
            kernel[i + 1] = kernel[i];
        }
        for (int i = ksize - 1; i < ksize; i++)
            kernel[i] /= sum;
    }

    public void benchmark_MN4() {
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.8f;
        {
           //@@LOOP BEGIN@@
            int fr_ii;
            for (fr_ii = 0; fr_ii < ksize; fr_ii ++) {
                kernel[fr_ii] /= sum;
                fr_ii++;
                kernel[fr_ii] /= sum;
                fr_ii+=2;
                kernel[fr_ii] /= sum;
                kernel[fr_ii - 1] = 0.5f * (kernel[fr_ii] + kernel[fr_ii - 2]);
            }
            for (int i = fr_ii; i < ksize; i += 2) {
                kernel[i] /= sum;
            }
        }
    }

    public void benchmark_MN34() {
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.8f;
        {
            kernel[0] /= sum;
            int fr_ii;
            for (fr_ii = 4; fr_ii < ksize; fr_ii += 4) {
                kernel[fr_ii] /= sum;
                kernel[fr_ii - 1] = kernel[fr_ii] * 0.75f + kernel[fr_ii - 4] * 0.25f;
                kernel[fr_ii - 2] = (kernel[fr_ii] + kernel[fr_ii - 4]) * 0.5f;
                kernel[fr_ii - 3] = kernel[fr_ii] * 0.25f + kernel[fr_ii - 4] * 0.75f;
            }
            for (int i = fr_ii; i < ksize; i += 2) {
                kernel[i] /= sum;
            }
        }
    }    

    public void benchmark_NN34() {
        // build kernel
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.8f;
        //@@LOOP BEGIN@@
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < ksize - 4; fr_ii ++) {
                float k = kernel[fr_ii] / sum;
                kernel[fr_ii] = k;
                fr_ii++;
                kernel[fr_ii] = k;
                fr_ii++;
                kernel[fr_ii] = k;
                fr_ii++;
                kernel[fr_ii] = k;
                fr_ii++;
            }
            for (int i = fr_ii; i < ksize; i++)
                kernel[i] /= sum;
        }
    }

    public void benchmark_NN4() {
        // build kernel
        int ksize = (int) (2.0f * truncate * sigma + 1.0f);
        float sum = 0.8f;
        //@@LOOP BEGIN@@
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < ksize - 4; fr_ii += 2) {
                kernel[fr_ii] /= sum;
                fr_ii++;
                kernel[fr_ii] /= sum;
                fr_ii++;
                kernel[fr_ii] /= sum;
                kernel[fr_ii + 1] = kernel[fr_ii];
            }
            for (int i = fr_ii; i < ksize; i++)
                kernel[i] /= sum;
        }
    }

}
