package fr.inria.approxloop.perfenergy.openimajversions;

import fr.inria.approxloop.perfenergy.OpenImaJVersions;

/**
 * Created by elmarce on 15/04/17.
 */
public class org_openimaj_video_tracking_klt_TrackingContext__computeKernels_383 {

    public OpenImaJVersions.WithData gauss = new OpenImaJVersions.WithData(480*2, 640*2, true);
    public int MAX_KERNEL_WIDTH = 71;

    public org_openimaj_video_tracking_klt_TrackingContext__computeKernels_383() {
        gauss.width = 71/2;
    }

    public void benchmark() {
        int i;
        //@@LOOP BEGIN@@
        for (i = 0; i < gauss.width; i++)
            gauss.data[i] = gauss.data[i + (MAX_KERNEL_WIDTH - gauss.width) / 2];
    }

    public void benchmark_PERF() {
        int i;
        //@@LOOP BEGIN@@
        for (i = 0; i < gauss.width; i += 2)
            gauss.data[i] = gauss.data[i + (MAX_KERNEL_WIDTH - gauss.width) / 2];
    }

    public void benchmark_NN() {
        int i;
        //@@LOOP BEGIN@@
        for (i = 0; i < gauss.width - 1; i += 2) {
            gauss.data[i] = gauss.data[i + (MAX_KERNEL_WIDTH - gauss.width) / 2];
            gauss.data[i + 1] = gauss.data[i];
        }
        for (i = gauss.width - 1; i < gauss.width; i += 2) {
            gauss.data[i] = gauss.data[i + (MAX_KERNEL_WIDTH - gauss.width) / 2];
        }
    }

    public void benchmark_NN4() {
        //@@LOOP BEGIN@@
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < gauss.width - 4; fr_ii += 2) {
                gauss.data[fr_ii] = gauss.data[fr_ii + (MAX_KERNEL_WIDTH - gauss.width) / 2];
                fr_ii++;
                gauss.data[fr_ii] = gauss.data[fr_ii + (MAX_KERNEL_WIDTH - gauss.width) / 2];
                fr_ii++;
                gauss.data[fr_ii] = gauss.data[fr_ii + (MAX_KERNEL_WIDTH - gauss.width) / 2];
                gauss.data[fr_ii + 1] = gauss.data[fr_ii];
            }
            for (int i = fr_ii; fr_ii < gauss.width; fr_ii ++) {
                gauss.data[i] = gauss.data[i + (MAX_KERNEL_WIDTH - gauss.width) / 2];
            }
        }
    }

    public void benchmark_NN34() {
        //@@LOOP BEGIN@@
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < gauss.width - 4; fr_ii ++) {
                float k = gauss.data[fr_ii + (MAX_KERNEL_WIDTH - gauss.width) / 2];
                gauss.data[fr_ii] = k;
                fr_ii++;
                gauss.data[fr_ii] = k;
                fr_ii++;
                gauss.data[fr_ii] = k;
                fr_ii++;
                gauss.data[fr_ii] = k;
                fr_ii++;
            }
            for (int i = fr_ii; fr_ii < gauss.width; fr_ii ++) {
                gauss.data[i] = gauss.data[i + (MAX_KERNEL_WIDTH - gauss.width) / 2];
            }
        }
    }

    public void benchmark_MN() {
        int i;
        for (i = 0; i < 1; i++) {
            gauss.data[0] = gauss.data[(MAX_KERNEL_WIDTH - gauss.width) / 2];
        }
        //@@LOOP BEGIN@@
        for (i = 2; i < gauss.width; i += 2) {
            gauss.data[i] = gauss.data[i + (MAX_KERNEL_WIDTH - gauss.width) / 2];
            gauss.data[i - 1] = (gauss.data[i] + gauss.data[i - 2]) * 0.5f;
        }
    }

    public void benchmark_MN4() {
        //@@LOOP BEGIN@@
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < gauss.width - 4; fr_ii += 2) {
                gauss.data[fr_ii] = gauss.data[fr_ii + (MAX_KERNEL_WIDTH - gauss.width) / 2];
                fr_ii++;
                gauss.data[fr_ii] = gauss.data[fr_ii + (MAX_KERNEL_WIDTH - gauss.width) / 2];
                fr_ii+=2;
                gauss.data[fr_ii] = gauss.data[fr_ii + (MAX_KERNEL_WIDTH - gauss.width) / 2];
                gauss.data[fr_ii - 1] = (gauss.data[fr_ii] + gauss.data[fr_ii - 2]) * 0.5f;
            }
            for (int i = fr_ii ; fr_ii < gauss.width; fr_ii += 2) {
                gauss.data[i] = gauss.data[i + (MAX_KERNEL_WIDTH - gauss.width) / 2];
            }
        }
    }

    public void benchmark_MN34() {
        //@@LOOP BEGIN@@
        {
            gauss.data[0] = gauss.data[0 + (MAX_KERNEL_WIDTH - gauss.width) / 2];
            int fr_ii;
            for (fr_ii = 4; fr_ii < gauss.width - 4; fr_ii += 2) {
                gauss.data[fr_ii] = gauss.data[fr_ii + (MAX_KERNEL_WIDTH - gauss.width) / 2];
                gauss.data[fr_ii - 1] = 0.75f * gauss.data[fr_ii] + 0.25f * gauss.data[fr_ii - 4];
                gauss.data[fr_ii - 2] = 0.5f * (gauss.data[fr_ii] + gauss.data[fr_ii - 4]);
                gauss.data[fr_ii - 3] = 0.25f * gauss.data[fr_ii] + 0.75f * gauss.data[fr_ii - 4];
            }
            for (int i = fr_ii ; fr_ii < gauss.width; fr_ii += 2) {
                gauss.data[i] = gauss.data[i + (MAX_KERNEL_WIDTH - gauss.width) / 2];
            }
        }
    }
}
