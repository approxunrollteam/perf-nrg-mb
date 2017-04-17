package fr.inria.approxloop;

import fr.inria.approxloop.loopstodb.TakeLoopsToDb;
import fr.inria.approxloop.orm.Loop;
import fr.inria.approxloop.perfenergy.CodeGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by elmarce on 23/03/17.
 */
public class AccuracyGenerator extends CodeGenerator {

    static String inputProjectPath = "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/lucene-solr-master/lucene/";
    static String path = "/home/elmarce/MarcelStuff/PROJECTS/PHD/APPROX-LOOP/eval-tools/perf-nrg-mb/src/main/java/fr/inria/approxloop/perfenergy/luceneversions";
    static String project = "lucene";
    static String[] ouputFiles = {
            inputProjectPath + "/core/src/java/org/apache/lucene/codecs/blocktree/BlockTreeTermsWriter.java",
            inputProjectPath + "/core/src/java/org/apache/lucene/codecs/blocktree/SegmentTermsEnumFrame.java",
            inputProjectPath + "/core/src/java/org/apache/lucene/codecs/compressing/CompressingStoredFieldsWriter.java",
            inputProjectPath + "/core/src/java/org/apache/lucene/index/TermsHashPerField.java",
            inputProjectPath + "/core/src/java/org/apache/lucene/util/fst/BytesStore.java",
            inputProjectPath + "/core/src/java/org/apache/lucene/util/fst/ReverseBytesReader.java",
            inputProjectPath + "/core/src/java/org/apache/lucene/util/packed/Packed64SingleBlock.java",
            inputProjectPath + "/core/src/java/org/apache/lucene/util/bkd/BKDWriter.java",
            inputProjectPath + "/queryparser/src/java/org/apache/lucene/queryparser/classic/QueryParserTokenManager.java"
            /*
            "/media/elmarce/Windows/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/smile-master/core/src/main/java/smile/classification/AdaBoost.java",
            "/media/elmarce/Windows/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/smile-master/core/src/main/java/smile/classification/DecisionTree.java",
            "/media/elmarce/Windows/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/smile-master/data/src/main/java/smile/data/Dataset.java",
            "/media/elmarce/Windows/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/smile-master/math/src/main/java/smile/math/random/UniversalGenerator.java",
            "/media/elmarce/Windows/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/smile-master/core/src/main/java/smile/util/SmileUtils.java",
            "/media/elmarce/Windows/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/smile-master/demo/src/main/java/smile/demo/classification/AdaBoostUSPS.java"
             */           /*
            "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/openimaj-master/demos/approxdemos/src/main/java/org/openimaj/approxdemos/KLTHaarFaceTrackerDemoApproxEval.java",
            "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/openimaj-master/core/core-image/src/main/java/org/openimaj/image/FImage.java",
            "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/openimaj-master/core/core-image/src/main/java/org/openimaj/image/ImageUtilities.java",
            "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/openimaj-master/core/core-image/src/main/java/org/openimaj/image/MBFImage.java",
            "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/openimaj-master/image/image-processing/src/main/java/org/openimaj/image/processing/convolution/FGaussianConvolve.java",
            "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/openimaj-master/image/image-processing/src/main/java/org/openimaj/image/processing/convolution/FImageConvolveSeparable.java",
            "/home/elmarce/MarcelStuff/DATA/APPROX-UNROLL/INPUT_PROGRAMS/SOURCE/openimaj-master/thirdparty/klt-tracker/src/main/java/org/openimaj/video/tracking/klt/TrackingContext.java"*/

    };
/*
    static String[] loopUIDs = {
            "org.openimaj.image.MBFImage.flatten_247", // -- ORIG
            "org.openimaj.image.MBFImage.flatten_257", // -- ORIG -- PERF
            "org.openimaj.image.processing.convolution.FGaussianConvolve.makeKernel_106", // -- ORIG -- PERF
            "org.openimaj.image.processing.convolution.FGaussianConvolve.makeKernel_113",// -- ORIG
            "org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_118", // -- ORIG
            "org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_120", // -- ORIG
            "org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_127", // -- ORIG
            "org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveHorizontal_137", // -- ORIG
            "org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_157", // -- ORIG
            "org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_159", // -- ORIG
            "org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_161", // -- ORIG
            "org.openimaj.image.processing.convolution.FImageConvolveSeparable.convolveVertical_166", // -- ORIG
            "org.openimaj.video.tracking.klt.TrackingContext._computeKernels_366", // -- ORIG
            "org.openimaj.video.tracking.klt.TrackingContext._computeKernels_383",// -- ORIG
            "org.openimaj.video.tracking.klt.TrackingContext._computeKernels_385",// -- ORIG
            "org.openimaj.image.ImageUtilities.Unknown_117",
            "org.openimaj.image.FImage.multiplyInplace_1199"
    };
*/
    private static String template_dir = "lucene_templates";
    private static String commandName = "./run_accuracy_lucene.sh";
    private static HashMap<Integer, String> strategies;


    /*
    private  HashMap<String, String> getLoopFiles(String[] loops, String[] files) {
        HashMap<String, List<String>> result = new HashMap<>();
        for (String loop : loops) {
            String loop1 = loop.substring(0, loop.lastIndexOf('.'));
            for (String file : ouputFiles) {
                String file1 = file.substring(file.indexOf("src/main/java/")+ "src/main/java/".length(), file.indexOf(".java"));
                file1 = file1.replace("/", ".");
                if (file1.equals(loop1)) {
                    List<String> ll = result.containsKey(file) ? result.get(file) : new ArrayList<String>();
                    ll.add(loop);
                    result.put(file, ll);
                }
            }
        }
        return null;
    }*/

    private String getTemplateNameFromFile(String file) {
        file = file.substring(file.lastIndexOf('/') + 1, file.indexOf(".java"));
        return file;
    }

    private void generateAndRun(HashMap<String, Loop> approximate, int init) {
        int  i = -1;
        for (Map.Entry<String, Loop> e : approximate.entrySet()) {
            String uid = e.getKey().replace(".", "_");
            Loop loop = e.getValue();
            i++;
            if ( i < init ) {
                continue;
            }

            HashMap<String, Object> input = new HashMap<>();
            //input.putAll(originals);
            input.put(uid, loop);
            try {
                input.put("strategy", strategies.get(loop.getStrategy()));
            } catch (NullPointerException ex) {
                System.out.println(loop.getStrategy());
            }
            input.put("loop_uid", loop.getUid());

            System.out.println("=================================");
            System.out.println(i + " - " + loop.getUid() + " :: " + loop.getCode());
            System.out.println("=================================");
            for (String outputFile : ouputFiles) {
                String templateName = getTemplateNameFromFile(outputFile);
                generate(input, templateName, outputFile, true);
            }
            run(e.getValue().getUid());
        }
    }

    private void run(String loop) {
        executeCommand(commandName, loop);
    }

    public static void main(String[] params) throws Exception {

        //HashMap<String, List<String>> loopsPerFile = getLoopFiles(loopUIDs, ouputFiles);
        TakeLoopsToDb.walk(new File(path));
        AccuracyGenerator gen = new AccuracyGenerator();
        gen.initialize(new File(gen.getClass().getClassLoader().getResource(template_dir).toURI()));

        //String project = "OpenImaJ";

        
        //originals = gen.getLoops(project, 0);
        HashMap<String, Loop> nn = gen.getLoops(project, 128);
        HashMap<String, Loop> mn = gen.getLoops(project, 129);
        HashMap<String, Loop> nn_4 = gen.getLoops(project, 130);
        HashMap<String, Loop> mn_4 = gen.getLoops(project, 131);
        HashMap<String, Loop> nn_34 = gen.getLoops(project, 132);
        HashMap<String, Loop> mn_34 = gen.getLoops(project, 133);
        HashMap<String, Loop> perf = gen.getLoops(project, 1);

        strategies = new HashMap<>();
        strategies.put(0, "original");
        strategies.put(1, "PERF");
        strategies.put(128, "NN");
        strategies.put(129, "MN");
        strategies.put(130, "NN4");
        strategies.put(131, "MN4");
        strategies.put(132, "NN34");
        strategies.put(133, "MN34");

        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("           LINEAR INTERPOLATION 3/4");
        System.out.println("*****************************************");
        System.out.println("*****************************************");

        //Loop 4 does not executes the innter loop

        gen.generateAndRun(mn_34, 0);

        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("           NEAREST 3/4 ");
        System.out.println("*****************************************");
        System.out.println("*****************************************");

        gen.generateAndRun(nn_34, 0);

        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("           LINEAR INTERPOLATION ");
        System.out.println("*****************************************");
        System.out.println("*****************************************");

        //gen.generateAndRun(mn, 3);

        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("           NEAREST ");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        //gen.generateAndRun(nn, 0);

        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("           LINEAR INTERPOLATION 4");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        gen.generateAndRun(mn_4, 0);

        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("           NEAREST 4");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        gen.generateAndRun(nn_4, 0);

        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("           PERFORATION");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        //gen.generateAndRun(perf, 0);

        //Collect original data first
        //gen.run("original");
    }


}
