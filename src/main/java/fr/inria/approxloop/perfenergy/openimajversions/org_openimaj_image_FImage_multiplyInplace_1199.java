package fr.inria.approxloop.perfenergy.openimajversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_openimaj_image_FImage_multiplyInplace_1199 {

    public int height = 480 * 2;
    public int width = 640 * 2;
    public float fnum = 5.0f;
    public float[][] pixels = new float[height][width];

    public org_openimaj_image_FImage_multiplyInplace_1199() {
        Random r = new Random();
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                pixels[y][x] = r.nextFloat();


    }

    public void benchmark() {
        int r = 0;
        //@@LOOP BEGIN@@
        for (int c = 0; c < width; c++) {
            pixels[r][c] *= fnum;
        }
    }

    public void benchmark_PERF() {
        int r = 0;
        for (int c = 0; c < width; c += 2) {
            pixels[r][c] *= fnum;
        }
    }

    public void benchmark_NN() {
        int r = 0;
        for (int c = 0; c < width - 1; c += 2) {
            pixels[r][c] *= fnum;
            pixels[r][c + 1] = pixels[r][c];
        }
        for (int c = width - 1; c < width; c += 2) {
            pixels[r][c] *= fnum;
        }
    }

    public void benchmark_MN() {
        int r = 0;
        pixels[r][0] *= fnum;
        for (int c = 2; c < width - 1; c += 2) {
            pixels[r][c] *= fnum;
            pixels[r][c - 1] = (pixels[r][c] + pixels[r][c - 2]) * 0.5f;
        }
    }



}
