package fr.inria.approxloop.perfenergy.openimajversions;

import fr.inria.approxloop.perfenergy.OpenImaJVersions;

import java.util.Random;

/**
 * Created by elmarce on 15/04/17.
 */
public class org_openimaj_image_processing_convolution_FImageConvolveSeparable_convolveVertical_157 {

    private final float[] buffer;
    public int MAX_KERNEL_WIDTH = 71;
    private final int halfsize = MAX_KERNEL_WIDTH / 2;
    public OpenImaJVersions.WithData image = new OpenImaJVersions.WithData(640*2,480*2, true);

    public org_openimaj_image_processing_convolution_FImageConvolveSeparable_convolveVertical_157() {
        buffer= new float[image.width * image.height];
        Random r = new Random();
        for (int y = 0; y < image.height*image.width; y++)
            buffer[y] = r.nextFloat();
    }

    public void benchmark() {
        int c = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize; i++)
            buffer[i] = image.pixels[0][c];
    }

    public void benchmark_PERF() {
        int c = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize; i += 2)
            buffer[i] = image.pixels[0][c];
    }

    public void benchmark_NN() {
        int c = 5;
        //@@LOOP BEGIN@@
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < halfsize - 1; fr_ii += 2) {
                buffer[fr_ii] = image.pixels[0][c];
                buffer[fr_ii + 1] = buffer[fr_ii];
            }
            for (int i = fr_ii; i < halfsize; i++) {
                buffer[i] = image.pixels[0][c];
            }
        }
    }

    public void benchmark_NN4() {
        int c = 5;
        //@@LOOP BEGIN@@
        {
            int fr_ii;
            for (fr_ii = 0; fr_ii < halfsize - 4; fr_ii += 2) {
                buffer[fr_ii] = image.pixels[0][c];
                fr_ii++;
                buffer[fr_ii] = image.pixels[0][c];
                fr_ii++;
                buffer[fr_ii] = image.pixels[0][c];
                buffer[fr_ii + 1] = buffer[fr_ii];
            }
            for (int i = fr_ii; i < halfsize; i++) {
                buffer[i] = image.pixels[0][c];
            }
        }
    }

    public void benchmark_NN34() {
        int c = 5;
        //@@LOOP BEGIN@@
        {
        int fr_ii;
        for (fr_ii = 0; fr_ii < halfsize - 4; fr_ii++) {
            float k = image.pixels[0][c];
            buffer[fr_ii] = k;
            fr_ii++;
            buffer[fr_ii] = k;
            fr_ii++;
            buffer[fr_ii] = k;
            fr_ii++;
            buffer[fr_ii] = k;
            ;
        }
        for (int i = fr_ii; i < halfsize; i++) {
            buffer[i] = image.pixels[0][c];
        }
    }
    }

    public void benchmark_MN() {
        int c = 5;
        for (int i = 0; i < 1; i++)
            buffer[0] = image.pixels[0][0];
        //@@LOOP BEGIN@@
        for (int i = 2; i < halfsize; i += 2) {
            buffer[i] = image.pixels[0][c];
            buffer[i - 1] = 0.5f * (buffer[i] + buffer[i - 2]);
        }
    }

    public void benchmark_MN34() {
        int c = 5;
        {
            for (int i = 0; i < 1; i++)
                buffer[0] = image.pixels[0][c];
            //@@LOOP BEGIN@@
            int fr_ii;
            for (fr_ii = 4; fr_ii < halfsize - 4; fr_ii += 2) {
                buffer[fr_ii] = image.pixels[0][c];
                buffer[fr_ii - 1] = 0.75f * buffer[fr_ii] + 0.25f * buffer[fr_ii - 4];
                buffer[fr_ii - 2] = 0.5f * (buffer[fr_ii] + buffer[fr_ii - 4]);
                buffer[fr_ii - 3] = 0.25f * buffer[fr_ii] + 0.75f * buffer[fr_ii - 4];
            }
            for (int i = fr_ii; i < halfsize; i++)
                buffer[0] = image.pixels[0][c];
        }
    }

    public void benchmark_MN4() {
        int c = 5;
        {
            for (int i = 0; i < 1; i++)
                buffer[0] = image.pixels[0][c];
            //@@LOOP BEGIN@@
            int fr_ii;
            for (fr_ii = 4; fr_ii < halfsize - 4; fr_ii += 2) {
                buffer[fr_ii] = image.pixels[0][c];
                fr_ii++;
                buffer[fr_ii] = image.pixels[0][c];
                fr_ii += 2;
                buffer[fr_ii] = image.pixels[0][c];
                buffer[fr_ii - 1] = 0.5f * (buffer[fr_ii] + buffer[fr_ii - 2]);
            }
            for (int i = fr_ii; i < halfsize; i++)
                buffer[0] = image.pixels[0][c];
        }
    }

}
