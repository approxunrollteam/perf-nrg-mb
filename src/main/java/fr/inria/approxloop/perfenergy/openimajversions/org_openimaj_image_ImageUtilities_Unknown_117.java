package fr.inria.approxloop.perfenergy.openimajversions;

/**
 * Created by elmarce on 15/04/17.
 */
public class org_openimaj_image_ImageUtilities_Unknown_117 {

    float[] BYTE_TO_FLOAT_LUT = new float[256];

    public void benchmark() {
        //@@LOOP BEGIN@@ 
        for (int i = 0; i < BYTE_TO_FLOAT_LUT.length; i++) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
        }
    }

    public void benchmark_NN34() {
        //@@LOOP BEGIN@@
        for (int i = 0; i < 1; i++) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
        }
        for (int i = 4; i < BYTE_TO_FLOAT_LUT.length - 4; i += 4) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
            BYTE_TO_FLOAT_LUT[i - 1] = BYTE_TO_FLOAT_LUT[i];
            BYTE_TO_FLOAT_LUT[i - 2] = BYTE_TO_FLOAT_LUT[i];
            BYTE_TO_FLOAT_LUT[i - 3] = BYTE_TO_FLOAT_LUT[i];
            //System.out.print(".");
        }
        for (int i = BYTE_TO_FLOAT_LUT.length - 4; i < BYTE_TO_FLOAT_LUT.length; i++) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
        }
    }

    public void benchmark_MN34() {
        //@@LOOP BEGIN@@
        for (int i = 0; i < 1; i ++) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
        }
        for (int i = 4; i < BYTE_TO_FLOAT_LUT.length -4; i += 4) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
            BYTE_TO_FLOAT_LUT[i - 1] = BYTE_TO_FLOAT_LUT[i] * 0.75f + BYTE_TO_FLOAT_LUT[i - 4] * 0.25f;
            BYTE_TO_FLOAT_LUT[i - 2] = (BYTE_TO_FLOAT_LUT[i] + BYTE_TO_FLOAT_LUT[i - 4]) * 0.5f;
            BYTE_TO_FLOAT_LUT[i - 3] = BYTE_TO_FLOAT_LUT[i] * 0.25f + BYTE_TO_FLOAT_LUT[i - 4] * 0.75f;
            //System.out.print(".");
        }
        for (int i = BYTE_TO_FLOAT_LUT.length - 4; i < BYTE_TO_FLOAT_LUT.length; i++) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
        }
    }

    public void benchmark_PERF() {
        //@@LOOP BEGIN@@ 
        for (int i = 0; i < BYTE_TO_FLOAT_LUT.length; i += 2) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
        }
    }

    public void benchmark_NN4() {
        //@@LOOP BEGIN@@ 
        for (int i = 0; i < BYTE_TO_FLOAT_LUT.length - 3; i++) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
            i++;
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
            i++;
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
            i++;
            BYTE_TO_FLOAT_LUT[i] = BYTE_TO_FLOAT_LUT[i - 1];
        }
        //@@LOOP BEGIN@@ 
        for (int i = BYTE_TO_FLOAT_LUT.length - 3; i < BYTE_TO_FLOAT_LUT.length; i++) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
        }
    }

    public void benchmark_MN4() {
        //@@LOOP BEGIN@@ 
        for (int i = 0; i < BYTE_TO_FLOAT_LUT.length - 3; i++) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
            i++;
            float k2 = i / 255f;
            BYTE_TO_FLOAT_LUT[i] = k2;
            i += 2;
            float k4 = i / 255f;
            BYTE_TO_FLOAT_LUT[i] = k4;
            BYTE_TO_FLOAT_LUT[i - 1] = (k4 + k2) * 0.5f;

        }
        //@@LOOP BEGIN@@ 
        for (int i = BYTE_TO_FLOAT_LUT.length - 3; i < BYTE_TO_FLOAT_LUT.length; i++) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
        }
    }

    public void benchmark_NN() {
        //@@LOOP BEGIN@@ 
        for (int i = 0; i < BYTE_TO_FLOAT_LUT.length - 1; i += 2) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
            BYTE_TO_FLOAT_LUT[i + 1] = BYTE_TO_FLOAT_LUT[i];
        }
        for (int i = 0; i < BYTE_TO_FLOAT_LUT.length; i++) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
        }
    }

    public void benchmark_MN() {
        for (int i = 0; i < 1; i++) {
            BYTE_TO_FLOAT_LUT[0] = 0;
        }
        //@@LOOP BEGIN@@ 
        for (int i = 2; i < BYTE_TO_FLOAT_LUT.length; i += 2) {
            BYTE_TO_FLOAT_LUT[i] = i / 255f;
            BYTE_TO_FLOAT_LUT[i - 1] = 0.5f * (BYTE_TO_FLOAT_LUT[i] + BYTE_TO_FLOAT_LUT[i - 2]);
        }
    }
    
}
