package fr.inria.approxloop.orm;

//import com.j256.ormlite.field.DatabaseField;
//import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by elmarce on 01/03/17.
 */
//@DatabaseTable(tableName = "datum")
public class Datum {

    //@DatabaseField(columnName = "experiment_id")
    private int experimentId;

    //@DatabaseField(columnName = "value")
    private float value;

    //@DatabaseField(columnName = "index")
    private int index;

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
