package fr.inria.approxloop.orm;

//import com.j256.ormlite.field.DatabaseField;
//import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by elmarce on 01/03/17.
 */
//@DatabaseTable(tableName = "experiments")
public class Experiment {

    private enum ApproximationStrategy {NONE, PERFORATION, NEAREST_NEIGHBOR, LINEAR}

    private enum Measuing {PERFORMANCE, ACCURACY, ENERGY}

    //@DatabaseField(columnName = "id")
    private int id;

    //@DatabaseField(columnName = "description")
    private String description;

    //@DatabaseField(columnName = "approx_strategy")
    private int approximationStrategy;

    //@DatabaseField(columnName = "measuring")
    private int measuring;

    //@DatabaseField(columnName = "loop_uid")
    private String loopUID;

    //@DatabaseField(columnName = "xmean")
    private float xMean;

    //@DatabaseField(columnName = "xstd")
    private float xstd;

    //@DatabaseField(columnName = "date")
    private String date;

    public Experiment() {

    }

    public Measuing getMeasuring() {
        switch (measuring) {
            case 0:
                return Measuing.PERFORMANCE;
            case 1:
                return Measuing.ACCURACY;
            case 2:
                return Measuing.ENERGY;
            default:
                throw new RuntimeException("Invalid approximation strategy");
        }
    }

    public void setMeasuring(Measuing val) {
        switch (val) {
            case PERFORMANCE:
                measuring = 0;
                break;
            case ACCURACY:
                measuring = 1;
                break;
            case ENERGY:
                measuring = 2;
                break;
        }
    }

    public ApproximationStrategy getApproximationStrategy() {
        switch (approximationStrategy) {
            case 0:
                return ApproximationStrategy.NONE;
            case 1:
                return ApproximationStrategy.PERFORATION;
            case 128:
                return ApproximationStrategy.LINEAR;
            case 129:
                return ApproximationStrategy.NEAREST_NEIGHBOR;
            default:
                throw new RuntimeException("Invalid approximation strategy");
        }
    }

    public void setApproximationStrategy(ApproximationStrategy val) {
        switch (val) {
            case NONE:
                approximationStrategy = 0;
                break;
            case PERFORATION:
                approximationStrategy = 1;
                break;
            case LINEAR:
                approximationStrategy = 128;
                break;
            case NEAREST_NEIGHBOR:
                approximationStrategy = 129;
                break;
            default:
                throw new RuntimeException("Invalid approximation strategy");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLoopUID() {
        return loopUID;
    }

    public void setLoopUID(String loopUID) {
        this.loopUID = loopUID;
    }

    public float getxMean() {
        return xMean;
    }

    public void setxMean(float xMean) {
        this.xMean = xMean;
    }

    public float getXstd() {
        return xstd;
    }

    public void setXstd(float xstd) {
        this.xstd = xstd;
    }
}
