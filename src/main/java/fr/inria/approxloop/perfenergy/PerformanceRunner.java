package fr.inria.approxloop.perfenergy;

import java.util.Random;

/**
 * Created by elmarce on 26/02/16.
 */
public class PerformanceRunner {

    int Y = 500000;

    public void runJsyn() throws InterruptedException {
        int X = 1000;
        Random r = new Random();
        JsynLoopsMicroBenchs jsynMB = new JsynLoopsMicroBenchs();
        for (int i = 0; i < Y; i++ ) {
            for (int j = 0; j < jsynMB.arraySize; j++ ) {
                jsynMB.frequencies[j] = 440;
                jsynMB.amplitudes[j] = r.nextDouble();
            }
            //result = new int[Y];
            //result[0] = 0;
            jsynMB.testTriangleGenerator();
            Y--;
            Thread.sleep(10);
            if ( r.nextInt() == Y ) {
                for (double output : jsynMB.outputs) {
                    System.out.print(output + " - ");
                }
            }
        }
    }



}
