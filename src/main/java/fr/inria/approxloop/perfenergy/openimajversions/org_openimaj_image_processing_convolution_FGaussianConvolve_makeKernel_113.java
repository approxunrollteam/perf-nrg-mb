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

}
