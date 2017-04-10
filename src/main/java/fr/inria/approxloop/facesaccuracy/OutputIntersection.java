package fr.inria.approxloop.facesaccuracy;

import org.w3c.dom.Document;
import org.w3c.dom.css.Rect;

import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * Created by elmarce on 13/02/17.
 */
public class OutputIntersection {

    static class RectFrame {
        int frame = 0;
        Rectangle2D[] rect = null;

        public RectFrame() {

        }

        public RectFrame(int frame, Rectangle2D[] rect) {
            this.frame = frame;
            this.rect = rect;
        }

        public String toString() {
            return this.frame + ": " + rect.toString();
        }

        /*
        private double lerp(double x, double y, float t) {
            return (1 - t) * x + t * y;
        }


        public void interpolate(int frame, RectFrame r1, RectFrame r2) {
            if (r1.frame == r2.frame) {
                this.frame = r1.frame;
                rect = r1.rect;
            } else {
                float t;
                if (r1.frame > r2.frame) {
                    t = Math.abs(r1.frame - r2.frame) / Math.abs(frame - r2.frame);
                } else {
                    t = Math.abs(r2.frame - r1.frame) / Math.abs(frame - r1.frame);
                }
                double x = lerp(r1.rect.getMinX(), r2.rect.getMinX(), t);
                double y = lerp(r1.rect.getMinY(), r2.rect.getMinX(), t);
                double X = lerp(r1.rect.getMaxX(), r2.rect.getMaxX(), t);
                double Y = lerp(r1.rect.getMaxY(), r2.rect.getMaxY(), t);
                rect = new Rectangle2D.Double(x, y, X - x, Y - y);
            }
        }*/
    }

    private RectFrame readRectanglesFromLine(String line) throws IllegalArgumentException {
        String[] strs = line.split(";");
        if (strs.length > 4) {
            Rectangle2D[] result = new Rectangle2D[(strs.length - 1) / 4];
            for (int i = 0, k = 0; i < strs.length - 4; k++, i += 4) {
                result[k] = new Rectangle2D.Float(
                        Float.parseFloat(strs[i + 1]), Float.parseFloat(strs[i + 2]),
                        Float.parseFloat(strs[i + 3]), Float.parseFloat(strs[i + 4]));
            }
            return new RectFrame(Integer.parseInt(strs[0]), result);
        } else {
            throw new IllegalArgumentException("Empty line");
        }
    }

    private List<RectFrame> readRectangles(File original) throws IOException {
        try {
            List<RectFrame> result = new ArrayList<>();
            BufferedReader brOr = new BufferedReader(new FileReader(original));
            String line;
            //Read one line of each file at a time
            while ((line = brOr.readLine()) != null) {
                try {
                    RectFrame r = readRectanglesFromLine(line);
                    if (r != null) result.add(r);
                } catch (IllegalArgumentException e) {
                    System.out.println("Empty line");
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Double> run(String original, String approx) throws IOException {
        List<RectFrame> or = readRectangles(new File(original));
        File a = new File(approx);
        List<RectFrame> nn = readRectangles(a);

        List<Double> result = new ArrayList<>();
        int i;
        int size = Math.min(or.size(), nn.size());
        for (i = 0; i < size; i++) {
            double maxM = Float.MIN_VALUE;
            for (int k = 0; k < or.get(i).rect.length; k++) {
                int ii = 0;
                int or_frame = or.get(i).frame;
                while (ii< nn.size() && nn.get(ii).frame < or_frame) ii++;
                if ( ii < nn.size() ) {
                    if (ii > 0 && Math.abs(nn.get(ii).frame - or_frame) > Math.abs(nn.get(ii - 1).frame - or_frame)) {
                        ii--;
                    }
                    if (or.get(i).frame != nn.get(ii).frame) {
                        System.out.println("OR: " + original + " AP: " + approx);
                        System.out.println("OR: " + or.get(i).frame + " AP: " + nn.get(ii).frame);
                    }
                    for (int n = 0; n < nn.get(ii).rect.length; n++)
                        maxM = Math.max(maxM, diceMetric(or.get(i).rect[k], nn.get(ii).rect[n]));
                }
            }
            if (maxM > 0.0000001) {
                result.add(maxM);
            }
        }
        return result;
    }

    private double diceMetric(Rectangle2D or, Rectangle2D approx) {
        Rectangle2D r = or.createIntersection(approx);
        double intersectionArea = (r.getWidth() * r.getHeight());
        if (r.getWidth() < 0 || r.getHeight() < 0) return 0;
        double unionArea = (or.getWidth() * or.getHeight()) +
                (approx.getWidth() * approx.getHeight());
        double res = intersectionArea * 2 / unionArea;
        if ( res > 1.0 ) {
            throw new RuntimeException("Impossible!");
        }
        return res;
        /*
        int minLenght = Math.min(or.length, approx.length);

        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (int i = 0; i < minLenght; i++) {
            if (minX > or[i].getMinX()) minX = (int) Math.round(or[i].getMinX());
            if (minX > approx[i].getMinX()) minX = (int) Math.round(approx[i].getMinX());
            if (maxX > or[i].getMaxX()) maxX = (int) Math.round(or[i].getMinX());
            if (maxX > approx[i].getMaxX()) maxX = (int) Math.round(approx[i].getMinX());

            if (minY > or[i].getMinY()) minY = (int) Math.round(or[i].getMinY());
            if (minY > approx[i].getMinY()) minY = (int) Math.round(approx[i].getMinY());
            if (maxY > or[i].getMaxY()) maxY = (int) Math.round(or[i].getMinY());
            if (maxY > approx[i].getMaxY()) maxY = (int) Math.round(approx[i].getMinY());
        }

        int union = 0;
        int intersection = 0;
        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                boolean inOr = false;
                boolean inAp = false;
                for (int i = 0; i < minLenght; i++) {
                    Rectangle2D o = or[i];
                    Rectangle2D a = approx[i];
                    inOr |= x >= o.getMinX() && x <= o.getMaxX() && y >= o.getMinY() && y <= o.getMaxY();
                    inAp |= x >= a.getMinX() && x <= a.getMaxX() && y >= a.getMinY() && y <= a.getMaxY();
                    if (inOr && inAp) {
                        intersection++;
                        break;
                    }
                }
                if (inOr || inAp) union++;
            }
        }

        return (float) intersection * 2 / (float)union;*/
    }

}
