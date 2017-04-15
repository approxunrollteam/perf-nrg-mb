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
            buffer[i] = image.pixels[r][0];
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
        for (int i = 0; i < 1; i++)
            buffer[0] = image.pixels[r][0];
        //@@LOOP BEGIN@@
        for (int i = 2; i < halfsize; i += 2) {
            buffer[i] = image.pixels[r][0];
            buffer[i - 1] = 0.5f * (buffer[i] + buffer[i - 2]);
        }
    }

    public void benchmark() {
        int r = 5;
        //@@LOOP BEGIN@@
        for (int i = 0; i < halfsize; i++)
            buffer[i] = image.pixels[r][0];
    }

}
