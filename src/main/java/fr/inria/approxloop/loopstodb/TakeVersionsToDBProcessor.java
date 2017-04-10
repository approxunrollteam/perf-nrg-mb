package fr.inria.approxloop.loopstodb;

import fr.inria.approxloop.orm.Loop;
import fr.inria.approxloops.sqlite.SQLiteConnector;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtLoop;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by elmarce on 23/03/17.
 */
public class TakeVersionsToDBProcessor extends AbstractProcessor<CtFor> {

    private String dbPath;
    private HashMap<String, Loop> loops = new HashMap<>();
    private static String suffixes[] = {"NN34", "MN34", "NN4", "MN4", "PERF", "NN", "MN"};
    private static int strategies[] = {132, 133, 130, 131, 1, 128, 129, 0};
    SQLiteConnector connector = null;

    private int getStrategyFromName(String name) {
        int strategy = 0;
        int i = 0;
        while (i < suffixes.length && !name.endsWith(suffixes[i])) i++;
        if (i < suffixes.length)
            strategy = strategies[i];
        return strategy;
    }

    private String getUidFromName(String name) {
        //Get the line number and the strategy
        int line = -1;
        try {
            line = Integer.parseInt(name.substring(name.lastIndexOf('_') + 1));
        } catch (NumberFormatException ex) {
            line = -1;
        }
        if (line == -1) {
            name = name.substring(0, name.lastIndexOf("_"));
            line = Integer.parseInt(name.substring(name.lastIndexOf('_') + 1));
        }
        name = name.substring(0, name.lastIndexOf("_"));
        name = name.replace("_", ".");
        name = name.replace("..", "._");
        //Finally build the ui
        name += "_" + line;
        return name;
    }


    private void addLoop(String uid, int strategy, CtFor ctFor) {
        //Now the code
        String key = uid + "::" + strategy;
        if (loops.containsKey(key)) {
            Loop l = loops.get(key);
            l.setCode(l.getCode() + "\n" + ctFor.toString());
        } else {
            loops.put(key, new Loop(uid, ctFor.toString(), strategy));
        }
    }

    @Override
    public void process(CtFor ctFor) {
        CtMethod m = ctFor.getParent(new TypeFilter<>(CtMethod.class));
        if (m == null) return;

        // get only outermost loops
        CtElement p = ctFor.getParent();
        while (p != m) {
            p = p.getParent();
            if (p instanceof CtLoop)
                return;
        }
        String mName = m.getSimpleName();
        try {
            CtClass cl = ctFor.getParent(new TypeFilter<>(CtClass.class));
            if (cl.getSimpleName().endsWith("Versions")) {
                int strategy = getStrategyFromName(mName);
                addLoop(getUidFromName(mName), strategy, ctFor);
            } else {
                addLoop(getUidFromName(cl.getSimpleName()), getStrategyFromName(mName), ctFor);
            }
        } catch (Exception ex) {
            System.out.println("Cannot process: " + ctFor.toString());
            System.out.println("UID: " + mName);
        }
    }

    public String getDBPath() {
        return dbPath;
    }

    public void setDBPath(String dxbPath) {
        this.dbPath = dxbPath;
    }

    public void close() {
        if (connector == null)
            connector = new SQLiteConnector(dbPath, "loop_versions", "uid", "code", "strategy");
        try {
            connector.connect();
            connector.getConnection().prepareStatement("DELETE FROM loop_versions").execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Loop> e : loops.entrySet()) {
            connector.write(e.getValue().getUid(), e.getValue().getCode(), e.getValue().getStrategy());
        }

        if (connector != null)
            connector.close();
    }
}
