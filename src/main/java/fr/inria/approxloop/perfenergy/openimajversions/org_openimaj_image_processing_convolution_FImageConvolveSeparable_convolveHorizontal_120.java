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

    public void benchmark() {
        int r = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize; i++)
            buffer[(halfsize + i)] = image.pixels[r][i];
    }

}
