package fr.inria.approxloop.accuracy;

import fr.inria.approxloop.orm.ExprimentDAO;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by elmarce on 14/02/17.
 */
public class OpenImaJAccuracyMain {
    public static void main(String[] args) throws Exception {

        boolean useLoopNames = false;

        String dbPath = "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/DBs/approx_unroll_data_with_errors.db3";
        String path = "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/OUTPUT/OpenIMaJ";
        String originalName = "original";
        String lpSEP = ".";

        String prefixes[] = {
                "PERF", "NN", "MN", "NN4", "MN4", "NN34", "MN34"
        };

/*        String loopNames[] = {
                "NN4.org.openimaj.image.processing.convolution.FGaussianConvolve.makeKernel_106",
                "NN4.org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_127",
                "NN4.org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_166", // -- ORIG
                "NN4.org.openimaj.video.tracking.klt.TrackingContext._computeKernels_366", // -- ORIG
                "NN4.org.openimaj.image.ImageUtilities.Unknown_117",

                "MN4.org.openimaj.image.processing.convolution.FGaussianConvolve.makeKernel_106",
                "MN4.org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_127",
                "MN4.org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_166", // -- ORIG
                "MN4.org.openimaj.video.tracking.klt.TrackingContext._computeKernels_366", // -- ORIG
                "MN4.org.openimaj.image.ImageUtilities.Unknown_117",

                "PERF_org.openimaj.image.MBFImage.flatten_247",
                "PERF_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_137",
                "PERF_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_118",
                "PERF_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_120",
                "PERF_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_120",
                "PERF_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_157",
                "PERF_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_159",
                "PERF_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_161",
                "PERF_org.openimaj.image.processing.convolution.FGaussianConvolve.makeKernel_113",

                "NN_org.openimaj.image.MBFImage.flatten_247",
                "NN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_137",
                "NN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_118",
                "NN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_120",
                "NN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_120",
                "NN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_157",
                "NN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_159",
                "NN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_161",
                "NN_org.openimaj.image.processing.convolution.FGaussianConvolve.makeKernel_113",

                "MN_org.openimaj.image.MBFImage.flatten_247",
                "MN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_137",
                "MN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_118",
                "MN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_120",
                "MN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_120",
                "MN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_157",
                "MN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_159",
                "MN_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_161",
                "MN_org.openimaj.image.processing.convolution.FGaussianConvolve.makeKernel_113",
*/
/*
                "MN.org.openimaj.image.FImage.extractROI",
                "MN.org.openimaj.image.FImage.multiplyInplace",//org.openimaj.image.FImage.multiplyInplace_1199
                "MN.org.openimaj.image.ImageUtilities.LUT", //org.openimaj.image.ImageUtilities.Unknown_117
                "MN.org.openimaj.image.MBFImage.flatten.258",//org.openimaj.image.MBFImage.flatten_257
                "MN.org.openimaj.image.processing.convolution.FGaussianConvolve.makeKernel",//org.openimaj.image.processing.convolution.FGaussianConvolve.makeKernel_106
                "MN.org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal",//org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_127
                "MN.org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical",//org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_166
                "MN.org.openimaj.video.tracking.klt.TrackingContext.computeKernels", //org.openimaj.video.tracking.klt.TrackingContext._computeKernels_366
                "MN.org.openimaj.video.tracking.klt.TrackingContext.computeKernelsShift1", //org.openimaj.video.tracking.klt.TrackingContext._computeKernels_383
                "MN.org.openimaj.video.tracking.klt.TrackingContext.computeKernelsShift2",//org.openimaj.video.tracking.klt.TrackingContext._computeKernels_385

                "NN.org.openimaj.image.FImage.extractROI",
                "NN.org.openimaj.image.FImage.multiplyInplace",
                "NN.org.openimaj.image.ImageUtilities.LUT",
                "NN.org.openimaj.image.MBFImage.flatten.258",
                "NN.org.openimaj.image.processing.convolution.FGaussianConvolve.makeKernel",
                "NN.org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal",
                "NN.org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical",
                "NN.org.openimaj.video.tracking.klt.TrackingContext.computeKernels",
                "NN.org.openimaj.video.tracking.klt.TrackingContext.computeKernelsShift1",
                "NN.org.openimaj.video.tracking.klt.TrackingContext.computeKernelsShift2",

                "PERF_org.openimaj.image.FImage.extractROI",
                "PERF_org.openimaj.image.FImage.multiplyInplace",
                "PERF_org.openimaj.image.ImageUtilities.LUT",
                "PERF_org.openimaj.image.MBFImage.flatten",
                "PERF_org.openimaj.image.processing.convolution.FGaussianConvolve.makeKernel",
                "PERF_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal",
                "PERF_org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical",
                "PERF_org.openimaj.video.tracking.klt.TrackingContext._computeKernels1_2",
                "PERF_org.openimaj.video.tracking.klt.TrackingContext._computeKernels_shift_1",
                "PERF_org.openimaj.video.tracking.klt.TrackingContext._computeKernels_shift_2"

        };*/

        File dir = new File(path);
        OpenImaJFaceRectIntersection o = new OpenImaJFaceRectIntersection();
        HashMap<String, List<Double>> result = new HashMap<>();
        for (File f : dir.listFiles()) {
            if (f.isFile() && !f.getName().startsWith(originalName)) {
                try {
                    String name = f.getName();
                    int indexOf = name.lastIndexOf('_');
                    String videoName = name.substring(indexOf + 1, name.length());
                    String loopName = name.substring(0, indexOf);
                    if (result.containsKey(loopName))
                        result.get(loopName).addAll(o.run(path + "/original_" + videoName, f.getAbsolutePath()));
                    else
                        result.put(loopName, o.run(path + "/original_" + videoName, f.getAbsolutePath()));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        ExprimentDAO dao = new ExprimentDAO(dbPath);
        dao.connnect();
        for (String loopName : result.keySet()) {
            try {
                System.out.println(" ABOUT TO STORE: " + loopName);
                int approxStrategy = getApproxStrategy(loopName);
                String loopUID = loopName.substring(loopName.indexOf(lpSEP) + lpSEP.length());
                dao.writeExperiment(result.get(loopName), loopUID,
                        "Using VideoFrame - Dice original face rect of the original loop vs approx one",
                        approxStrategy, 1);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        dao.close();
    }

    private static int indexOfNth(String source, char c, int k) {

        if (k < 0) {
            int result = source.length() - 1;
            while (k > 0) {
                if (result == 0) return -1;
                if (source.charAt(result) == c)
                    k++;
                result--;
            }
            return result;
        } else {
            int result = 0;
            while (k > 0) {
                result = source.indexOf(c, result);
                k--;
            }
            return result;
        }
    }

    private static int getApproxStrategy(String loopName) {
        if (loopName.startsWith("PERF")) return 1;
        else if (loopName.startsWith("NN4")) return 130;
        else if (loopName.startsWith("MN4")) return 131;
        else if (loopName.startsWith("NN34")) return 132;
        else if (loopName.startsWith("MN34")) return 133;
        else if (loopName.startsWith("NN")) return 128;
        else if (loopName.startsWith("MN")) return 129;
        else throw new RuntimeException("Unknown strategy");
    }
}
