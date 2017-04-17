package fr.inria.approxloop.orm;

/**
 * Created by elmarce on 23/03/17.
 */
public class Loop {
    private String code;
    private String uid;
    private String pos;
    private int strategy;

    public Loop(String uid, String code, int strategy, String pos) {
        this(uid, code, strategy);
        this.setPos(pos);
    }

    public Loop(String uid, String code, int strategy) {
        this.setUid(uid);
        this.setCode(code);
        this.setStrategy(strategy);
    }

    @Override
    public String toString() {
        return getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getStrategy() {
        return strategy;
    }

    public void setStrategy(int strategy) {
        this.strategy = strategy;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
