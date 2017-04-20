package fr.inria.approxloop.perfenergy.openimajversions;

import fr.inria.approxloop.perfenergy.OpenImaJVersions;

/**
 * Created by elmarce on 15/04/17.
 */
public class org_openimaj_video_tracking_klt_TrackingContext__computeKernels_366 {

    public OpenImaJVersions.WithData gauss;
    public OpenImaJVersions.WithData gaussderiv;
    public float sigma = 0.7f;

    public void benchmark() {
        int i;
        final int hw = 71 / 2;
        //@@LOOP BEGIN@@ 
        for (i = -hw; i <= hw; i++) {
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);
        }
    }

    //CONCEPTUALLY, THIS IS WHAT THE LOOP SHOULD DO WHEN in MEAN:
    public void benchmark_MN() {
        int i;
        final int hw = 71 / 2;
        for (i = -hw; i < -hw + 1; i++) {
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);
        }
        //@@LOOP BEGIN@@
        for (i = -hw + 2; i <= hw - 1; i += 2) {
            int k = i + hw;
            gauss.data[k] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gauss.data[k - 1] = (gauss.data[k] + gauss.data[k - 2]) * 0.5f;

            gaussderiv.data[k] = (-i) * (gauss.data[(k)]);
            gaussderiv.data[k - 1] = (gaussderiv.data[k] + gaussderiv.data[k - 2]) * 0.5f;

        }
        for ( i = hw - 1; i <= hw; i++ ) {
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);
        }
    }

    public void benchmark_MN34() {
        int i;
        final int hw = 71 / 2;
        //@@LOOP BEGIN@@
        for (i = -hw; i <= -hw + 1; i++) {
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);
        }
        for (i = -hw + 4; i <= hw - 4; i+=4) {
            float k = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gauss.data[i + hw] = k;
            gauss.data[i + hw - 3] = k * 0.25f + gauss.data[i + hw - 4] * 0.75f;
            gauss.data[i + hw - 2] = (k + gauss.data[i + hw - 4]) * 0.25f;
            gauss.data[i + hw - 1] = (k * 0.75f + gauss.data[i + hw - 4] * 0.25f);
            float kk = (-i) * (gauss.data[(i + hw)]);
            gaussderiv.data[i + hw] = kk;
            gaussderiv.data[i + hw - 3] = kk  * 0.25f + gaussderiv.data[i + hw - 4] * 0.75f;
            gaussderiv.data[i + hw - 2] = (kk + gaussderiv.data[i + hw - 4]) * 0.5f;
            gaussderiv.data[i + hw - 1] = kk  * 0.75f + gaussderiv.data[i + hw - 4] * 0.25f;
            //System.out.print(".");
        }
        for (i = hw - 4; i <= hw; i++) {
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);
        }
    }

    public void benchmark_NN34() {
        int i;
        final int hw = 71 / 2;
        //@@LOOP BEGIN@@
        for (i = -hw; i <= hw - 4; i += 4) {
            float k = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gauss.data[i + hw] = k;
            gauss.data[i + hw] = k;
            gauss.data[i + hw] = k;
            gauss.data[i + hw] = k;
            float kk = (-i) * (gauss.data[(i + hw)]);
            gaussderiv.data[i + hw] = kk;
            gaussderiv.data[i + hw] = kk;
            gaussderiv.data[i + hw] = kk;
            gaussderiv.data[i + hw] = kk;
            //System.out.print(".");
        }
        for (i = hw - 4; i <= hw; i++) {
            float k = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gauss.data[i + hw] = k;
            float kk = (-i) * (gauss.data[(i + hw)]);
            gaussderiv.data[i + hw] = kk;
        }
    }

    public void benchmark_MN4() {
        int i;
        final int hw = 71 / 2;
        //@@LOOP BEGIN@@ 
        for (i = -hw; i <= hw - 3; i++) {
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);

            i++;
            float gd2 = (float) Math.exp(-i * i / (2 * sigma * sigma));
            float gdd2 = (-i) * (gauss.data[(i + hw)]);
            gauss.data[i + hw] = gd2;
            gaussderiv.data[i + hw] = gdd2;

            i += 2;
            float gd4 = (float) Math.exp(-i * i / (2 * sigma * sigma));
            float gdd4 = (-i) * (gauss.data[(i + hw)]);
            gauss.data[i + hw] = gd4;
            gaussderiv.data[i + hw] = gdd4;

            gauss.data[i + hw - 1] = (gd4 + gd2) * 0.5f;
            gaussderiv.data[i + hw - 1] = (gdd4 + gdd2) * 0.5f;
        }
        //@@LOOP BEGIN@@ 
        for (i = hw - 3; i <= hw; i++) {
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);
        }
    }

    public void benchmark_NN4() {
        int i;
        final int hw = 71 / 2;
        //@@LOOP BEGIN@@ 
        for (i = -hw; i <= hw - 3; i++) {
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);

            i++;
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);

            i++;
            float fg = (float) Math.exp(-i * i / (2 * sigma * sigma));
            float fg1 = (-i) * (gauss.data[(i + hw)]);
            gauss.data[i + hw] = fg;
            gaussderiv.data[i + hw] = fg1;

            i++;
            gauss.data[i + hw] = fg;
            gaussderiv.data[i + hw] = fg1;
        }
        //@@LOOP BEGIN@@ 
        for (i = hw - 3; i <= hw; i++) {
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);
        }
    }


    public void benchmark_PERF() {
        int i;
        final int hw = 71 / 2;
        //@@LOOP BEGIN@@ 
        for (i = -hw; i <= hw; i += 2) {
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);
        }
    }

    public void benchmark_NN() {
        int i;
        final int hw = 71 / 2;
        //@@LOOP BEGIN@@ 
        for (i = -hw; i < hw - 1; i += 2) {
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gauss.data[i + hw + 1] = gauss.data[i + hw];
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);
            gaussderiv.data[i + hw + 1] = gaussderiv.data[i + hw];
        }
        for (i = hw - 1; i < hw; i++) {
            gauss.data[i + hw] = (float) Math.exp(-i * i / (2 * sigma * sigma));
            gaussderiv.data[i + hw] = (-i) * (gauss.data[(i + hw)]);
        }
    }
    
}
