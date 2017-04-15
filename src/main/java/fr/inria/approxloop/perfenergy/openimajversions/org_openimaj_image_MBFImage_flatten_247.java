package fr.inria.approxloop.perfenergy.openimajversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_openimaj_image_MBFImage_flatten_247 {

    public int width = 640 * 2;
    public int height = 480 * 2;
    public float[][] outp;
    public float[][] bnd;

    public org_openimaj_image_MBFImage_flatten_247() {
        Random r = new Random();
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                outp[y][x] = r.nextFloat();
                bnd[y][x] = r.nextFloat();
            }
    }

    public void benchmark() {
        int y = 0;
        //@@LOOP BEGIN@@
        for (int c = 0; c < width; c++) {
            bnd[y][c] += outp[y][c];
        }
    }

    public void benchmark_PERF() {
        int y = 0;
        //@@LOOP BEGIN@@
        for (int c = 0; c < width; c += 2) {
            bnd[y][c] += outp[y][c];
        }
    }

    public void benchmark_NN() {
        int y = 0;
        //@@LOOP BEGIN@@
        for (int c = 0; c < width - 1; c += 2) {
            bnd[y][c] += outp[y][c];
            bnd[y][c + 1] += bnd[y][c];
        }
        for (int c = width - 1; c < width; c++) {
            bnd[y][c] += outp[y][c];
        }
    }

    public void benchmark_MN() {
        int y = 0;
        for (int cccc = 0; cccc < 1; cccc++) {
            bnd[y][0] += outp[y][0];
        }
        //@@LOOP BEGIN@@
        for (int c = 2; c < width; c += 2) {
            bnd[y][c] += outp[y][c];
            bnd[y][c - 1] = (bnd[y][c] + bnd[y][c - 2]) * 0.5f;
        }
    }

}
