package fr.inria.approxloop.perfenergy.openimajversions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_openimaj_image_MBFImage_flatten_257 {

    public int width = 640 * 2;
    public int height = 480 * 2;
    public float[][] outp = new float[height][width];
    public float[][] bnd = new float[height][width];;
    public float norm = 0.8f;

    public org_openimaj_image_MBFImage_flatten_257() {
        Random r = new Random();
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                outp[y][x] = r.nextFloat();
                bnd[y][x] = r.nextFloat();
            }
    }

    /*
    * for (int x = 0; x < width; x++) {
    outp[y][x] = ((outp[y][x]) + (bnd[y][x])) * norm;
}*/

    public void benchmark() {
        int y = 0;
        //@@LOOP BEGIN@@
        for (int x = 0; x < width; x++) {
            outp[y][x] = ((outp[y][x]) + (bnd[y][x])) * norm;
        }
    }

    public void benchmark_PERF() {
        int y = 0;
        //@@LOOP BEGIN@@
        for (int x = 0; x < width; x+=2) {
            outp[y][x] = ((outp[y][x]) + (bnd[y][x])) * norm;
        }
    }

    public void benchmark_NN() {
        int y = 0;
        //@@LOOP BEGIN@@
        int fr_ii;
        for (fr_ii = 0; fr_ii < width - 1; fr_ii+=2) {
            outp[y][fr_ii] = ((outp[y][fr_ii]) + (bnd[y][fr_ii])) * norm;
            outp[y][fr_ii + 1] += outp[y][fr_ii];
        }
        for (int x = fr_ii; x < width; x++) {
            outp[y][x] = ((outp[y][x]) + (bnd[y][x])) * norm;
        }
    }

    public void benchmark_NN34() {
        int y = 0;
        //@@LOOP BEGIN@@
        {
        int fr_ii;
        for (fr_ii = 0; fr_ii < width - 1; fr_ii += 4) {
            outp[y][fr_ii] = ((outp[y][fr_ii]) + (bnd[y][fr_ii])) * norm;
            outp[y][fr_ii + 1] += outp[y][fr_ii];
            outp[y][fr_ii + 2] += outp[y][fr_ii];
            outp[y][fr_ii + 3] += outp[y][fr_ii];
        }
        for (int x = fr_ii; x < width; x++) {
            outp[y][x] = ((outp[y][x]) + (bnd[y][x])) * norm;
        }
    }
    }

    public void benchmark_NN4() {
        int y = 0;
        //@@LOOP BEGIN@@
        int fr_ii;
        for (fr_ii = 0; fr_ii < width - 1; fr_ii+=2) {
            outp[y][fr_ii] = ((outp[y][fr_ii]) + (bnd[y][fr_ii])) * norm;
            fr_ii++;
            outp[y][fr_ii] = ((outp[y][fr_ii]) + (bnd[y][fr_ii])) * norm;
            fr_ii++;
            outp[y][fr_ii] = ((outp[y][fr_ii]) + (bnd[y][fr_ii])) * norm;
            outp[y][fr_ii + 1] += outp[y][fr_ii];
        }
        for (int x = fr_ii; x < width; x++) {
            outp[y][x] = ((outp[y][x]) + (bnd[y][x])) * norm;
        }
    }


    public void benchmark_MN() {
        int y = 0;
        {
            outp[y][0] = ((outp[y][0]) + (bnd[y][0])) * norm;
            //@@LOOP BEGIN@@
            int fr_ii;
            for (fr_ii = 2; fr_ii < width; fr_ii+=2) {
                outp[y][fr_ii] = ((outp[y][fr_ii]) + (bnd[y][fr_ii])) * norm;
                outp[y][fr_ii - 1] = 0.5f * (outp[y][fr_ii] + outp[y][fr_ii - 2]);
            }
            for (int x = fr_ii; x < width; x++) {
                outp[y][x] = ((outp[y][x]) + (bnd[y][x])) * norm;
            }
        }
    }


    public void benchmark_MN34() {
        int y = 0;
        {
            outp[y][0] = ((outp[y][0]) + (bnd[y][0])) * norm;
            //@@LOOP BEGIN@@
            int fr_ii;
            for (fr_ii = 4; fr_ii < width; fr_ii+=2) {
                outp[y][fr_ii] = ((outp[y][fr_ii]) + (bnd[y][fr_ii])) * norm;
                outp[y][fr_ii - 1] = (outp[y][fr_ii] * 0.75f + outp[y][fr_ii - 4] * 0.25f);
                outp[y][fr_ii - 2] = (outp[y][fr_ii] + outp[y][fr_ii - 4]) * 0.5f;
                outp[y][fr_ii - 3] = (outp[y][fr_ii] * 0.25f + outp[y][fr_ii - 4] * 0.75f);
            }
            for (int x = fr_ii; x < width; x++) {
                outp[y][x] = ((outp[y][x]) + (bnd[y][x])) * norm;
            }
        }
    }

    public void benchmark_MN4() {
        int y = 0;
        {
            //@@LOOP BEGIN@@
            int fr_ii;
            for (fr_ii = 0; fr_ii < width; fr_ii+=2) {
                outp[y][fr_ii] = ((outp[y][fr_ii]) + (bnd[y][fr_ii])) * norm;
                fr_ii++;
                outp[y][fr_ii] = ((outp[y][fr_ii]) + (bnd[y][fr_ii])) * norm;
                fr_ii += 2;
                outp[y][fr_ii] = ((outp[y][fr_ii]) + (bnd[y][fr_ii])) * norm;
                outp[y][fr_ii - 1] = 0.5f * (outp[y][fr_ii] + outp[y][fr_ii - 2]);
            }
            for (int x = fr_ii; x < width; x++) {
                outp[y][x] = ((outp[y][x]) + (bnd[y][x])) * norm;
            }
        }
    }

}
