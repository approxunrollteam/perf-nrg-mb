package fr.inria.approxloop.orm;

import fr.inria.approxloops.sqlite.SQLiteConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by elmarce on 06/03/17.
 */
public class ExprimentDAO {

    private final String db;

    private SQLiteConnector datumConn;
    private SQLiteConnector expConn;
    private int availableID;

    public ExprimentDAO(String db) {
        this.db = db;
    }

    public void connnect() throws Exception {
        expConn = new SQLiteConnector(db, "experiments", "id", "description",
                "approx_strategy", "loop_uid", "xstd", "xmean", "measuring", "date");
        expConn.setKeyName("id");
        expConn.setAddKey(false);
        expConn.connect();

        datumConn = new SQLiteConnector(expConn.getConnection(), "datums", "experiment_id",
                "avalue", "aindex");
        datumConn.setAddKey(false);
        try {
            availableID = (int) expConn.getAvailableId() + 1;
        } catch (NullPointerException ex) {
            availableID = 0;
        }
    }


    public void close() {
        if (expConn != null)
            expConn.close();
    }

    public void writeExperiment(double[] datums, String loopUID, String description,
                                int approximationStrategy, int measuring) {
        float sum = 0;
        for (int k = 0; k < datums.length; k++) {
            double d = datums[k];
            datumConn.insert(availableID, d, k);
            sum += d;
        }
        float mean = sum / datums.length;
        sum = 0;
        for (double d : datums)
            sum += (d - mean) * (d - mean);

        float stdMean = (float) Math.sqrt(sum / datums.length);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //THIS IS A HUGE HACK:
        String strmean = ((Float)mean).toString();
        String strstd = ((Float)stdMean).toString();
        expConn.insert(availableID, description, approximationStrategy,
                loopUID, strstd, strmean, measuring, sdf.format(new Date()));
        availableID++;
    }

    public void writeExperiment(int[] datums, String loopUID, String description,
                                int approximationStrategy, int measuring) {
        float sum = 0;
        for (int k = 0; k < datums.length; k++) {
            double d = datums[k];
            datumConn.insert(availableID, d, k);
            sum += d;
        }
        float mean = sum / datums.length;
        sum = 0;
        for (int d : datums)
            sum += (d - mean) * (d - mean);

        float stdMean = (float) Math.sqrt(sum / datums.length);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //THIS IS A HUGE HACK:
        String strmean = ((Float)mean).toString();
        String strstd = ((Float)stdMean).toString();
        expConn.insert(availableID, description, approximationStrategy,
                loopUID, strstd, strmean, measuring, sdf.format(new Date()));
        availableID++;
    }


    public void writeExperiment(List<Double> datums, String loopUID, String description,
                                int approximationStrategy, int measuring) {
        float sum = 0;
        for (int k = 0; k < datums.size(); k++) {
            Double d = datums.get(k);
            datumConn.insert(availableID, d, k);
            sum += d;
        }
        float mean = sum / datums.size();
        sum = 0;
        for (Double d : datums)
            sum += (d - mean) * (d - mean);

        float stdMean = (float) Math.sqrt(sum / datums.size());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //THIS IS A HUGE HACK:
        String strmean = ((Float)mean).toString();
        String strstd = ((Float)stdMean).toString();
        expConn.insert(availableID, description, approximationStrategy,
                loopUID, strstd, strmean, measuring, sdf.format(new Date()));
        availableID++;
    }

}
