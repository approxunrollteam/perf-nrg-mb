package fr.inria.approxloop.perfenergy.openimajversions;

import fr.inria.approxloop.perfenergy.OpenImaJVersions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_openimaj_image_processing_convolution_FImageConvolveSeparable_convolveHorizontal_118 {

    private final int halfsize;
    private final float[] buffer;
    public OpenImaJVersions.WithData image;
    public int MAX_KERNEL_WIDTH = 71;

    public org_openimaj_image_processing_convolution_FImageConvolveSeparable_convolveHorizontal_118() {
        halfsize = MAX_KERNEL_WIDTH / 2;
        image = new OpenImaJVersions.WithData(640 * 2, 480 * 2);
        Random r = new Random();
        //@@LOOP BEGIN@@
        for (int y = 0; y < image.height; y++)
            //@@LOOP BEGIN@@
            for (int x = 0; x < image.width; x++) {
                image.pixels[y][x] = r.nextFloat();
            }
        buffer = new float[image.width * image.height];

    }

    public void benchmark_PERF() {
        int r = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize; i += 2)
            buffer[i] = image.pixels[r][0];
    }

    public void benchmark_NN4() {
        {
            int r = 5;
            //@@LOOP BEGIN@@
            int fr_ii = 0;
            for (fr_ii = 0; fr_ii < halfsize - 4; fr_ii += 2) {
                buffer[fr_ii] = image.pixels[r][0];
                fr_ii++;
                buffer[fr_ii] = image.pixels[r][0];
                fr_ii++;
                buffer[fr_ii] = image.pixels[r][0];
                buffer[fr_ii + 1] = buffer[fr_ii];
            }
            for (int i = fr_ii; i < halfsize; i++) {
                buffer[i] = image.pixels[r][0];
            }
        }
    }

    public void benchmark_NN34() {
        {
            int r = 5;
            //@@LOOP BEGIN@@
            int fr_ii = 0;
            for (fr_ii = 0; fr_ii < halfsize - 4; fr_ii ++) {
                float k = image.pixels[r][0];
                buffer[fr_ii] = k;
                fr_ii++;
                buffer[fr_ii] = k;
                fr_ii++;
                buffer[fr_ii] = k;
                fr_ii++;
                buffer[fr_ii] = k;
            }
            for (int i = fr_ii; i < halfsize; i++) {
                buffer[i] = image.pixels[r][0];
            }
        }
    }

    public void benchmark_NN() {
        int r = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize - 1; i += 2) {
            buffer[i] = image.pixels[r][0];
            buffer[i + 1] = buffer[i];
        }
        for (int i = halfsize; i < halfsize; i++) {
            buffer[i] = image.pixels[r][0];
        }
    }

    public void benchmark_MN() {
        int r = 5;
        {
            for (int i = 0; i < 1; i++)
                buffer[0] = image.pixels[r][0];
            //@@LOOP BEGIN@@
            for (int i = 2; i < halfsize; i += 2) {
                buffer[i] = image.pixels[r][0];
                buffer[i - 1] = 0.5f * (buffer[i] + buffer[i - 2]);
            }
        }
    }

    public void benchmark_MN4() {
        int r = 5;
        {
            //@@LOOP BEGIN@@
            int fr_ii;
            for (fr_ii = 0; fr_ii < halfsize -4; fr_ii++) {
                buffer[fr_ii] = image.pixels[r][0];
                fr_ii++;
                buffer[fr_ii] = image.pixels[r][0];
                fr_ii+=2;
                buffer[fr_ii] = image.pixels[r][0];
                buffer[fr_ii - 1] = 0.5f * (buffer[fr_ii] + buffer[fr_ii - 2]);
            }
            for (int i = fr_ii; i < halfsize; i += 2) {
                buffer[i] = image.pixels[r][0];
            }
        }
    }

    public void benchmark_MN34() {
        int r = 5;
        {
            for (int i = 0; i < 1; i += 2) {
                buffer[i] = image.pixels[r][0];
            }
            int fr_ii;
            for (fr_ii = 4; fr_ii < halfsize - 4; fr_ii +=4) {
                buffer[fr_ii] = image.pixels[r][0];
                buffer[fr_ii - 1] = 0.75f * buffer[fr_ii] + 0.25f* buffer[fr_ii - 4];
                buffer[fr_ii - 2] = 0.5f * (buffer[fr_ii] + buffer[fr_ii - 2]);
                buffer[fr_ii - 3] = 0.25f * buffer[fr_ii] + 0.75f* buffer[fr_ii - 4];
            }
            for (int i = fr_ii; i < halfsize; i += 2) {
                buffer[i] = image.pixels[r][0];
            }
        }
    }

    public void benchmark() {
        int r = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize; i++)
            buffer[i] = image.pixels[r][0];
    }

}
