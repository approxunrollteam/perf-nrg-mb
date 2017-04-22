package fr.inria.approxloop.perfenergy.openimajversions;

import fr.inria.approxloop.perfenergy.OpenImaJVersions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_openimaj_image_processing_convolution_FImageConvolveSeparable_convolveHorizontal_120 {

    private final int halfsize;
    private final float[] buffer;
    public OpenImaJVersions.WithData image;
    public int MAX_KERNEL_WIDTH = 71;

    public org_openimaj_image_processing_convolution_FImageConvolveSeparable_convolveHorizontal_120() {
        halfsize = MAX_KERNEL_WIDTH / 2;
        image = new OpenImaJVersions.WithData(640*2, 480*2);
        Random r = new Random();
        //@@LOOP BEGIN@@
        for (int y = 0; y < image.height; y++)
            //@@LOOP BEGIN@@
            for (int x = 0; x < image.width; x++) {
                image.pixels[x][y] = r.nextFloat();
            }
        buffer = new float[image.width * image.height];

    }

    public void benchmark_PERF() {
        int r = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize; i += 2)
            buffer[(halfsize + i)] = image.pixels[r][i];
    }

    public void benchmark_NN() {
        int r = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize; i += 2) {
            buffer[(halfsize + i)] = image.pixels[r][i];
            buffer[(halfsize + i) + 1] = buffer[(halfsize + i)];
        }
        for (int i = halfsize; i < halfsize - 1; i++) {
            buffer[(halfsize + i)] = image.pixels[r][i];
        }
    }

    public void benchmark_NN34() {
        int r = 5;
        //@@LOOP BEGIN@@
        int fr_ii;
        {
        for (fr_ii = 0; fr_ii < halfsize - 4; fr_ii += 4) {
            buffer[(halfsize + fr_ii)] = image.pixels[r][fr_ii];
            buffer[(halfsize + fr_ii) + 1] = buffer[(halfsize + fr_ii)];
            buffer[(halfsize + fr_ii) + 2] = buffer[(halfsize + fr_ii)];
            buffer[(halfsize + fr_ii) + 3] = buffer[(halfsize + fr_ii)];
        }
        for (int i = fr_ii; i < halfsize - 1; i++) {
            buffer[(halfsize + i)] = image.pixels[r][i];
        }
    }
    }

    public void benchmark_NN4() {
        int r = 5;
        //@@LOOP BEGIN@@
        {
        int fr_ii;
        for (fr_ii = 0; fr_ii < halfsize - 4; fr_ii++) {
            buffer[(halfsize + fr_ii)] = image.pixels[r][fr_ii];
            fr_ii++;
            buffer[(halfsize + fr_ii)] = image.pixels[r][fr_ii];
            fr_ii++;
            buffer[(halfsize + fr_ii)] = image.pixels[r][fr_ii];
            fr_ii++;
            buffer[(halfsize + fr_ii) + 1] = buffer[(halfsize + fr_ii)];
        }
        for (int i = fr_ii; i < halfsize - 1; i++) {
            buffer[(halfsize + i)] = image.pixels[r][i];
        }
    }
    }

    public void benchmark_MN() {
        int r = 5;
        for (int i = 0; i < 1; i++) {
            buffer[(halfsize)] = image.pixels[r][0];
        }
        //@@LOOP BEGIN@@
        for (int i = 2; i < halfsize; i += 2) {
            buffer[(halfsize + i)] = image.pixels[r][i];
            buffer[(halfsize + i) - 1] = (buffer[(halfsize + i)] - buffer[(halfsize + i) - 2]) * 0.5f;
        }
    }


    public void benchmark_MN34() {
        int r = 5;
        {
            buffer[(halfsize)] = image.pixels[r][0];
            //@@LOOP BEGIN@@
            int fr_ii;
            for (fr_ii = 4; fr_ii < halfsize - 4; fr_ii += 4) {
                int k = halfsize + fr_ii;
                buffer[k] = image.pixels[r][fr_ii];
                buffer[k - 1] = buffer[k] * 0.75f + buffer[k - 4] * 0.25f;
                buffer[k - 2] = (buffer[k] + buffer[k - 4]) * 0.5f;
                buffer[k - 3] = buffer[k] * 0.25f + buffer[k - 4] * 0.75f;
            }
            for (int i = fr_ii; fr_ii < halfsize; fr_ii ++) {
                buffer[halfsize + i] = image.pixels[r][fr_ii];
            }
        }
    }

    public void benchmark_MN4() {
        int r = 5;
        {
            //@@LOOP BEGIN@@
            int fr_ii;
            for (fr_ii = 0; fr_ii < halfsize - 4; fr_ii += 2) {
                buffer[(halfsize + fr_ii)] = image.pixels[r][fr_ii];
                fr_ii++;
                buffer[(halfsize + fr_ii)] = image.pixels[r][fr_ii];
                fr_ii += 2;
                buffer[(halfsize + fr_ii)] = image.pixels[r][fr_ii];
                buffer[(halfsize + fr_ii) - 1] = (buffer[(halfsize + fr_ii)] - buffer[(halfsize + fr_ii) - 2]) * 0.5f;
            }
            for (int i = fr_ii; i < 1; i++) {
                buffer[(halfsize)] = image.pixels[r][0];
            }
        }
    }

    public void benchmark() {
        int r = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize; i++)
            buffer[(halfsize + i)] = image.pixels[r][i];
    }

}
