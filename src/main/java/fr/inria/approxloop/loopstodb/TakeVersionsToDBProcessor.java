package fr.inria.approxloop.loopstodb;

import fr.inria.approxloop.orm.Loop;
import fr.inria.approxloops.sqlite.SQLiteConnector;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtLoop;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by elmarce on 23/03/17.
 */
public class TakeVersionsToDBProcessor extends AbstractProcessor<CtFor> {

    private String dbPath;
    private HashMap<String, Loop> elementsToStore = new HashMap<>();
    private static String suffixes[] = {"NN34", "MN34", "NN4", "MN4", "PERF", "NN", "MN"};
    private static int strategies[] = {132, 133, 130, 131, 1, 128, 129, 0};
    SQLiteConnector connector = null;
    HashSet<String> isBlock = new HashSet<>();

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


    private void addLoop(String uid, int strategy, CtElement e) {
        //Now the code
        String key = uid + "::" + strategy;
        if ( isBlock.contains(key) ) {
            return;
        } else {
            if (e instanceof CtBlock) isBlock.add(key);

            if (elementsToStore.containsKey(key)) {
                Loop l = elementsToStore.get(key);
                l.setCode(l.getCode() + "\n" + e.toString());
            } else {
                elementsToStore.put(key, new Loop(uid, e.toString(), strategy));
            }
        }
    }

    /**
     * If the loop is inside a block that is not the block of the method, take the block
     * @param ctFor
     * @return
     */
    private CtElement elementToStore(CtFor ctFor, CtMethod m) {
        CtElement p = ctFor.getParent();
        while (p != m) {
            if (p instanceof CtLoop) return null;
            else if (p instanceof CtBlock && !p.equals(m.getBody())) return p;
            p = p.getParent();
        }
        return ctFor;
    }

    @Override
    public void process(CtFor ctFor) {
        CtMethod m = ctFor.getParent(new TypeFilter<>(CtMethod.class));
        if (m == null) return;

        // get only outermost elementsToStore
        CtElement e = elementToStore(ctFor, m);
        if (e == null) return;

        String mName = m.getSimpleName();
        try {
            CtClass cl = ctFor.getParent(new TypeFilter<>(CtClass.class));
            if (cl.getSimpleName().endsWith("Versions")) {
                int strategy = getStrategyFromName(mName);
                addLoop(getUidFromName(mName), strategy, e);
            } else {
                addLoop(getUidFromName(cl.getSimpleName()), getStrategyFromName(mName), e);
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

        for (Map.Entry<String, Loop> e : elementsToStore.entrySet()) {
            connector.write(e.getValue().getUid(), e.getValue().getCode(), e.getValue().getStrategy());
        }

        if (connector != null)
            connector.close();
    }
}
