package fr.inria.approxloop.perfenergy.openimajversions;

import fr.inria.approxloop.perfenergy.OpenImaJVersions;

/**
 * Created by elmarce on 15/04/17.
 */
public class org_openimaj_video_tracking_klt_TrackingContext__computeKernels_385 {

    public int MAX_KERNEL_WIDTH = 71;
    private final int halfsize = MAX_KERNEL_WIDTH / 2;

    public OpenImaJVersions.WithData gaussderiv = new OpenImaJVersions.WithData(halfsize, halfsize, true);

    public void benchmark() {
        int i;
        //@@LOOP BEGIN@@
        for (i = 0; i < gaussderiv.width; i++)
            gaussderiv.data[i] = gaussderiv.data[(i + (((MAX_KERNEL_WIDTH) - gaussderiv.width) / 2))];
    }

    public void benchmark_PERF() {
        int i;
        //@@LOOP BEGIN@@
        for (i = 0; i < gaussderiv.width; i += 2)
            gaussderiv.data[i] = gaussderiv.data[(i + (((MAX_KERNEL_WIDTH) - gaussderiv.width) / 2))];
    }

    public void benchmark_NN() {
        int i;
        //@@LOOP BEGIN@@
        for (i = 0; i < gaussderiv.width - 1; i += 2) {
            gaussderiv.data[i] = gaussderiv.data[(i + (((MAX_KERNEL_WIDTH) - gaussderiv.width) / 2))];
            gaussderiv.data[i + 1] = gaussderiv.data[i];
        }
        for (i = gaussderiv.width - 1; i < gaussderiv.width; i ++) {
            gaussderiv.data[i] = gaussderiv.data[i + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
        }
    }

    public void benchmark_MN() {
        int i;
        for (i = 0; i < 1; i++) {
            gaussderiv.data[0] = gaussderiv.data[(MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
        }
        //@@LOOP BEGIN@@
        for (i = 2; i < gaussderiv.width; i += 2) {
            gaussderiv.data[i] = gaussderiv.data[i + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
            gaussderiv.data[i - 1] = (gaussderiv.data[i] + gaussderiv.data[i - 2]) * 0.5f;
        }
    }

    public void benchmark_MN4() {
        //@@LOOP BEGIN@@
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < gaussderiv.width - 4; fr_ii += 2) {
                gaussderiv.data[fr_ii] = gaussderiv.data[fr_ii + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
                fr_ii++;
                gaussderiv.data[fr_ii] = gaussderiv.data[fr_ii + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
                fr_ii+=2;
                gaussderiv.data[fr_ii] = gaussderiv.data[fr_ii + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
                gaussderiv.data[fr_ii - 1] = (gaussderiv.data[fr_ii] + gaussderiv.data[fr_ii - 2]) * 0.5f;
            }
            for (int i = fr_ii ; fr_ii < gaussderiv.width; fr_ii += 2) {
                gaussderiv.data[i] = gaussderiv.data[i + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
            }
        }
    }

    public void benchmark_MN34() {
        //@@LOOP BEGIN@@
        {
            gaussderiv.data[0] = gaussderiv.data[0 + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
            int fr_ii;
            for (fr_ii = 4; fr_ii < gaussderiv.width - 4; fr_ii += 2) {
                gaussderiv.data[fr_ii] = gaussderiv.data[fr_ii + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
                gaussderiv.data[fr_ii - 1] = 0.75f * gaussderiv.data[fr_ii] + 0.25f * gaussderiv.data[fr_ii - 4];
                gaussderiv.data[fr_ii - 2] = 0.5f * (gaussderiv.data[fr_ii] + gaussderiv.data[fr_ii - 4]);
                gaussderiv.data[fr_ii - 3] = 0.25f * gaussderiv.data[fr_ii] + 0.75f * gaussderiv.data[fr_ii - 4];
            }
            for (int i = fr_ii ; fr_ii < gaussderiv.width; fr_ii += 2) {
                gaussderiv.data[i] = gaussderiv.data[i + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
            }
        }
    }

    public void benchmark_NN4() {
        //@@LOOP BEGIN@@
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < gaussderiv.width - 4; fr_ii += 2) {
                gaussderiv.data[fr_ii] = gaussderiv.data[fr_ii + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
                fr_ii++;
                gaussderiv.data[fr_ii] = gaussderiv.data[fr_ii + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
                fr_ii++;
                gaussderiv.data[fr_ii] = gaussderiv.data[fr_ii + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
                gaussderiv.data[fr_ii + 1] = gaussderiv.data[fr_ii];
            }
            for (int i = fr_ii; fr_ii < gaussderiv.width; fr_ii ++) {
                gaussderiv.data[i] = gaussderiv.data[i + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
            }
        }
    }

    public void benchmark_NN34() {
        //@@LOOP BEGIN@@
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < gaussderiv.width - 4; fr_ii ++) {
                float k = gaussderiv.data[fr_ii + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
                gaussderiv.data[fr_ii] = k;
                fr_ii++;
                gaussderiv.data[fr_ii] = k;
                fr_ii++;
                gaussderiv.data[fr_ii] = k;
                fr_ii++;
                gaussderiv.data[fr_ii] = k;
                fr_ii++;
            }
            for (int i = fr_ii; fr_ii < gaussderiv.width; fr_ii ++) {
                gaussderiv.data[i] = gaussderiv.data[i + (MAX_KERNEL_WIDTH - gaussderiv.width) / 2];
            }
        }
    }

}
