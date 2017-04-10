package smile.demo.classification;

import smile.classification.AdaBoost;
import smile.data.AttributeDataset;
import smile.data.NominalAttribute;
import smile.data.parser.DelimitedTextParser;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Created by elmarce on 07/04/17.
 */
public class AdaBoostUSPS {

    public static void main(String[] args) {
        System.out.println("USPS");
        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setResponseIndex(new NominalAttribute("class"), 0);
        try {
            AttributeDataset train = parser.parse("USPS Train", smile.data.parser.IOUtils.getTestDataFile("usps/zip.train"));
            AttributeDataset test = parser.parse("USPS Test", smile.data.parser.IOUtils.getTestDataFile("usps/zip.test"));

            double[][] x = train.toArray(new double[train.size()][]);
            int[] y = train.toArray(new int[train.size()]);
            double[][] testx = test.toArray(new double[test.size()][]);
            int[] testy = test.toArray(new int[test.size()]);

            for (int i = 0; i < y.length; i++) {
                if (y[i] != 0) y[i] = 1;
            }
            for (int i = 0; i < testy.length; i++) {
                if (testy[i] != 0) testy[i] = 1;
            }

            AdaBoost forest = new AdaBoost(x, y, 100, 6);

            int error = 0;
            for (int i = 0; i < testx.length; i++) {
                if (forest.predict(testx[i]) != testy[i]) {
                    error++;
                }
            }

            System.out.println("AdaBoost error = " + error);
            System.out.format("USPS error rate = %.2f%%%n", 100.0 * error / testx.length);

            FileWriter fw = new FileWriter("${strategy}.${loop_uid}");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(error + "; " + 100.0 * error / testx.length);
            bw.flush();
            bw.close();

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

}
