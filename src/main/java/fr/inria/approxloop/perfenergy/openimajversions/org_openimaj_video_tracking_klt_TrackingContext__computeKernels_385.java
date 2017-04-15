package fr.inria.approxloop.perfenergy.openimajversions;

import fr.inria.approxloop.perfenergy.OpenImaJVersions;

/**
 * Created by elmarce on 15/04/17.
 */
public class org_openimaj_video_tracking_klt_TrackingContext__computeKernels_385 {

    public int MAX_KERNEL_WIDTH = 71;
    private final int halfsize = MAX_KERNEL_WIDTH / 2;


    public OpenImaJVersions.WithData gauss = new OpenImaJVersions.WithData(halfsize, halfsize, true);
    public OpenImaJVersions.WithData gaussderiv = new OpenImaJVersions.WithData(halfsize, halfsize, true);

    public void benchmark_org_openimaj_video_tracking_klt_TrackingContext__computeKernels_385() {
        int i;
        //@@LOOP BEGIN@@
        for (i = 0; i < gauss.width; i++)
            gaussderiv.data[i] = gaussderiv.data[(i + (((MAX_KERNEL_WIDTH) - gauss.width) / 2))];
    }

    public void benchmark_org_openimaj_video_tracking_klt_TrackingContext__computeKernels_385_PERF() {
        int i;
        //@@LOOP BEGIN@@
        for (i = 0; i < gauss.width; i += 2)
            gaussderiv.data[i] = gaussderiv.data[(i + (((MAX_KERNEL_WIDTH) - gauss.width) / 2))];
    }

    public void benchmark_org_openimaj_video_tracking_klt_TrackingContext__computeKernels_385_NN() {
        int i;
        //@@LOOP BEGIN@@
        for (i = 0; i < gauss.width - 1; i += 2) {
            gauss.data[i] = gauss.data[i + (MAX_KERNEL_WIDTH - gauss.width) / 2];
            gauss.data[i + 1] = gauss.data[i];
        }
        for (i = gauss.width - 1; i < gauss.width; i ++) {
            gauss.data[i] = gauss.data[i + (MAX_KERNEL_WIDTH - gauss.width) / 2];
        }
    }

    public void benchmark_org_openimaj_video_tracking_klt_TrackingContext__computeKernels_385_MN() {
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

}