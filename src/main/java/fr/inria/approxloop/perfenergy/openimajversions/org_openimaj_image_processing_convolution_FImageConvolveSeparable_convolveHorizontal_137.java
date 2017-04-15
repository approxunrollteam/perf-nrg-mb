package fr.inria.approxloop.perfenergy.openimajversions;

import fr.inria.approxloop.perfenergy.OpenImaJVersions;

import java.util.Random;

/**
 * Created by elmarce on 14/04/17.
 */
public class org_openimaj_image_processing_convolution_FImageConvolveSeparable_convolveHorizontal_137 {
    public int MAX_KERNEL_WIDTH = 71;
    private final int halfsize = MAX_KERNEL_WIDTH / 2;
    public OpenImaJVersions.WithData image = new OpenImaJVersions.WithData(640 * 2, 480 * 2, true);
    float[] buffer;

    public org_openimaj_image_processing_convolution_FImageConvolveSeparable_convolveHorizontal_137() {
        buffer= new float[image.width * image.height];
        Random r = new Random();
        for (int y = 0; y < image.height*image.width; y++)
            buffer[y] = r.nextFloat();
    }

    public void benchmark() {
        int r = 5;
        //@@LOOP BEGIN@@
        for (int c = 0; c < halfsize; c++)
            image.pixels[r][c] = buffer[c];

    }

    public void benchmark_PERF() {
        int r = 5;
        //@@LOOP BEGIN@@
        for (int c = 0; c < halfsize; c += 2)
            image.pixels[r][c] = buffer[c];
    }

    public void benchmark_MN() {
        int r = 5;
        for (int i = 0; i < 1; i++)
            image.pixels[r][0] = buffer[0];
        //@@LOOP BEGIN@@
        for (int c = 2; c < halfsize; c += 2) {
            image.pixels[r][c] = buffer[c];
            image.pixels[r][c - 1] = 0.5f * (image.pixels[r][c] - image.pixels[r][c - 2]);
        }
    }

    public void benchmark_NN() {
        int r = 5;
        //@@LOOP BEGIN@@
        for (int c = 0; c < halfsize - 1; c += 2) {
            image.pixels[r][c] = buffer[c];
            image.pixels[r][c + 1] = image.pixels[r][c];
        }
        for (int c = halfsize - 1; c < halfsize; c++) {
            image.pixels[r][c] = buffer[c];
        }
    }
}
