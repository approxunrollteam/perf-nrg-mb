import fr.inria.approxloop.orm.ExprimentDAO;
import fr.inria.approxloop.perfenergy.MBRunner;
import fr.inria.approxloop.perfenergy.MBTimeThread;
import fr.inria.approxloop.perfenergy.OpenImaJVersions;
import fr.inria.approxloop.perfenergy.LuceneVersions;
import fr.inria.approxloop.perfenergy.JsynLoopsMicroBenchs;
import fr.inria.approxloop.perfenergy.smileversions.*;
import fr.inria.approxloop.perfenergy.luceneversions.*;
import fr.inria.approxloop.perfenergy.jsynversions.*;

/**
 * Created by elmarce on 26/02/16.
 */
public class PerformanceRunner extends MBRunner {

    public void runOperationsPerSecond(int warmUpPhases, int executionPhases, String dbPath) throws Exception {

        ${datum_init}

        MBTimeThread thread = new MBTimeThread(warmUpPhases, executionPhases, 1, this);
        thread.start();

        System.out.println("Starting tests...");
        synchronized (this) {
            while (currentTest == -1)
                wait();
        }

        int start = 1;
        int end = start + warmUpPhases;

        ${microbenchmarkClass} microBenchs = new ${microbenchmarkClass}();
        int approximationStrategy = ${approximationStrategy}; //0 -- None, 1 -- Perforation, 128 -- NN, 129 - Linear
        String loopUID = "${loopUID}"; // -- ORIG
        String experimentDescription = "${experimentDescription}";

        for (int i = start; i < end; i++) {
            while (currentTest < i && proceeed)
                microBenchs.${microbenchmark}();
            System.out.println("Warmup phase: " + i);
        }
        start += warmUpPhases;
        end += executionPhases;
        for (int i = start; i < end; i++) {
            ${before_run}
            while (currentTest < i && proceeed) {
                microBenchs.${microbenchmark}();
                ${after_mb}
            }
            ${after_run}
            System.out.println("Execution phase: " + (i - start));
        }

        if ( ${store_to_db?c} ) {
          ExprimentDAO dao = new ExprimentDAO(dbPath);
          dao.connnect();
          dao.writeExperiment(datums, loopUID, experimentDescription, approximationStrategy, ${measuring});
          dao.close();
        }
    }

    /*
public void runJsyn() throws InterruptedException {
int X = 1000;
Random r = new Random();
JsynLoopsMicroBenchs jsynMB = new JsynLoopsMicroBenchs();
for (int i = 0; i < Y; i++) {
for (int j = 0; j < jsynMB.arraySize; j++) {
jsynMB.frequencies[j] = 440;
jsynMB.amplitudes[j] = r.nextDouble();
}
//result = new int[Y];
//result[0] = 0;
jsynMB.benchmarkSineWaveGenerator();
Y--;
Thread.sleep(10);
if (r.nextInt() == Y) {
for (double output : jsynMB.outputs) {
System.out.print(output + " - ");
}
}
}
}
*/

    static String dbPath = "${dbpath}";
    public static void main(String[] args) throws Exception {
        new PerformanceRunner().runOperationsPerSecond(${warmupcount}, ${executioncount}, dbPath);
    }
}
