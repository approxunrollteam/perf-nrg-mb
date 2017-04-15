package fr.inria.approxloop.perfenergy.openimajversions;

import fr.inria.approxloop.perfenergy.OpenImaJVersions;

import java.util.Random;

/**
 * Created by elmarce on 15/04/17.
 */
public class org_openimaj_image_processing_convolution_FImageConvolveSeparable_convolveVertical_159 {

    private final float[] buffer;
    public int MAX_KERNEL_WIDTH = 71;
    private final int halfsize = MAX_KERNEL_WIDTH / 2;
    public OpenImaJVersions.WithData image = new OpenImaJVersions.WithData(640*2,480*2, true);

    public org_openimaj_image_processing_convolution_FImageConvolveSeparable_convolveVertical_159() {
        buffer= new float[image.width * image.height];
        Random r = new Random();
        for (int y = 0; y < image.height*image.width; y++)
            buffer[y] = r.nextFloat();
    }

    public void benchmark() {
        int c = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize; i++)
            buffer[(halfsize + i)] = image.pixels[i][c];
    }

    public void benchmark_PERF() {
        int c = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize; i += 2)
            buffer[(halfsize + i)] = image.pixels[i][c];
    }

    public void benchmark_NN() {
        int c = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize - 1; i += 2) {
            buffer[(halfsize + i)] = image.pixels[i][c];
            buffer[(halfsize + i) + 1] = buffer[(halfsize + i)];
        }
        for (int i = halfsize; i < halfsize - 1; i++) {
            buffer[(halfsize + i)] = image.pixels[i][c];
        }
    }

    public void benchmark_MN() {
        int c = 5;
        for (int i = 0; i < 1; i++)
            buffer[(halfsize + 0)] = image.pixels[0][c];
        //@@LOOP BEGIN@@
        for (int i = 2; i < halfsize; i += 2) {
            buffer[(halfsize + i)] = image.pixels[i][c];
            buffer[(halfsize + i) - 1] = 0.5f * (buffer[(halfsize + i)] + buffer[(halfsize + i) - 2]);
        }
    }


}
