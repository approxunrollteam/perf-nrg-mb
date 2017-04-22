package fr.inria.approxloop.perfenergy.openimajversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_openimaj_image_MBFImage_flatten_247 {

    public int width = 640 * 2;
    public int height = 480 * 2;
    public float[][] outp = new float[height][width];
    public float[][] bnd = new float[height][width];;

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

    public void benchmark_NN34() {
        int y = 0;
        //@@LOOP BEGIN@@
        {
            int fr_cc;
            for (fr_cc = 0; fr_cc < width - 4; fr_cc += 2) {
                float k = bnd[y][fr_cc] + outp[y][fr_cc];
                bnd[y][fr_cc] = k;
                fr_cc++;
                bnd[y][fr_cc] = k;
                fr_cc++;
                bnd[y][fr_cc] = k;
                fr_cc++;
                bnd[y][fr_cc ] = k;
            }
            for (int c = fr_cc; c < width; c++) {
                bnd[y][c] += outp[y][c];
            }
        }
    }

    public void benchmark_NN4() {
        int y = 0;
        //@@LOOP BEGIN@@
        {
            int fr_cc;
            for (fr_cc = 0; fr_cc < width - 4; fr_cc += 2) {
                bnd[y][fr_cc] += outp[y][fr_cc];
                fr_cc++;
                bnd[y][fr_cc] += outp[y][fr_cc];
                fr_cc+=2;
                bnd[y][fr_cc] += outp[y][fr_cc];
                bnd[y][fr_cc + 1] = bnd[y][fr_cc];
            }
            for (int c = fr_cc; c < width; c++) {
                bnd[y][c] += outp[y][c];
            }
        }
    }

    public void benchmark_MN() {
        int y = 0;
        {
            bnd[y][0] += outp[y][0];
            //@@LOOP BEGIN@@
            int fr_cc;
            for (fr_cc = 2; fr_cc < width; fr_cc += 2) {
                bnd[y][fr_cc] += outp[y][fr_cc];
                bnd[y][fr_cc - 1] = (bnd[y][fr_cc] + bnd[y][fr_cc - 2]) * 0.5f;
            }
            for (int c = fr_cc; c < width; c ++) {
                bnd[y][c] += outp[y][c];
            }
        }
    }

    public void benchmark_MN4() {
        int y = 0;
        {
            //@@LOOP BEGIN@@
            int fr_cc;
            for (fr_cc = 2; fr_cc < width -4; fr_cc += 2) {
                bnd[y][fr_cc] += outp[y][fr_cc];
                fr_cc++;
                bnd[y][fr_cc] += outp[y][fr_cc];
                fr_cc+=2;
                bnd[y][fr_cc] += outp[y][fr_cc];
                bnd[y][fr_cc - 1] = (bnd[y][fr_cc] + bnd[y][fr_cc - 2]) * 0.5f;
            }
            for (int c = fr_cc; c < width; c ++) {
                bnd[y][c] += outp[y][c];
            }
        }
    }

    public void benchmark_MN34() {
        int y = 0;
        {
            bnd[y][0] += outp[y][0];
            //@@LOOP BEGIN@@
            int fr_cc;
            for ( fr_cc = 4; fr_cc < width - 4; fr_cc +=4) {
                bnd[y][fr_cc] += outp[y][fr_cc];
                bnd[y][fr_cc - 1] = (bnd[y][fr_cc]* 0.75f + bnd[y][fr_cc - 4]) * 0.25f;
                bnd[y][fr_cc - 2] = (bnd[y][fr_cc] + bnd[y][fr_cc - 4]) * 0.5f;
                bnd[y][fr_cc - 3] = (bnd[y][fr_cc]* 0.25f + bnd[y][fr_cc - 4]) * 0.75f;
            }
            for (int c = fr_cc; c < width; c ++) {
                bnd[y][c] += outp[y][c];
            }
        }
    }

}
