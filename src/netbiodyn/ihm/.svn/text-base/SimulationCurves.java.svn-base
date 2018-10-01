/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netbiodyn.ihm;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import netbiodyn.Entity;
import netbiodyn.util.FileSaverLoader;

/**
 *
 * @author riviere
 */
public class SimulationCurves {

    private ArrayList<CurveElement> points;
    private ArrayList<CurveElement> only_selected;
    private String absc = "time";
    private JProgressBar waiter;
    private boolean processing = false;

    public SimulationCurves() {
        points = new ArrayList<>();
        only_selected = new ArrayList<>();
    }

    public void addPoint(String name, Color color, Double x, Double y) {
        setProcessing(true);
        CurveElement e = new CurveElement(name, new Point2D.Double(x, y), color);
        if (x.compareTo(0.) == 0) {
            if (points.contains(e)) {
                points.remove(e);
            }
        }
        points.add(e);
        if (x.compareTo(89999.) >= 0) {
            System.err.println("Processing !" + Thread.currentThread().toString());
        }
        setProcessing(false);
        if (x.compareTo(89999.) >= 0) {
            System.err.println("Done Processing" + Thread.currentThread().toString());
        }
    }

    public void clear() {
        points = new ArrayList<>();
        only_selected = new ArrayList<>();
    }

    public void clearEntity(String name) {
        ArrayList<CurveElement> copy = (ArrayList<CurveElement>) points.clone();
        for (int i = copy.size() - 1; i >= 0; i--) {
            if (copy.get(i).getStr().equals(name)) {
                points.remove(i);
            }
        }
    }

    public Double getMaxInSelected(ArrayList<String> entitesSelected) {
        Double max = 0.0;
        only_selected = new ArrayList<>();
        for (CurveElement elt : points) {
            if (entitesSelected.contains(elt._str)) {
                only_selected.add(elt);
            }
        }

        for (CurveElement elt : only_selected) {
            if (elt._pt.y > max) {
                max = elt.getPt().y;
            }
        }

        return max;
    }

    public Double getMaxAbsc() {
        Double max = 0.0;
        if (absc.equals("time")) {
            for (CurveElement elt : points) {
                if (elt.getPt().x > max) {
                    max = elt.getPt().x;
                }
            }
        } else {
            ArrayList<CurveElement> ab = getByName(absc);
            for (CurveElement elt : ab) {
                if (elt.getPt().y > max) {
                    max = elt.getPt().y;
                }
            }
        }
        return max;
    }

    public BufferedImage buildOnlySelectedCurves(BufferedImage bmp, double scale_x, double scale_y, int height) {
        if (!absc.equals("time")) {
            ArrayList<CurveElement> ab = getByName(absc);
            for (CurveElement c : ab) {
                double time = c.getPt().x;
                double x = c.getPt().y * scale_x;
                ArrayList<CurveElement> elmts = getByAbscissa(time, only_selected);
                for (CurveElement elt : elmts) {
                    double y = elt.getPt().y * scale_y;
                    bmp.setRGB(1 + (int) x, -4 + height - 1 - (int) y, elt._col.getRGB());
                    bmp.setRGB(1 + (int) x - 1, -4 + height - 1 - (int) y, elt._col.getRGB());
                    bmp.setRGB(1 + (int) x + 1, -4 + height - 1 - (int) y, elt._col.getRGB());
                    bmp.setRGB(1 + (int) x, -4 + height - 1 - (int) y + 1, elt._col.getRGB());
                    bmp.setRGB(1 + (int) x, -4 + height - 1 - (int) y - 1, elt._col.getRGB());
                }
            }
        } else {
            for (CurveElement elt : only_selected) {
                double x = elt.getPt().x * scale_x;
                double y = elt.getPt().y * scale_y;
                bmp.setRGB(1 + (int) x, -4 + height - 1 - (int) y, elt._col.getRGB());
                bmp.setRGB(1 + (int) x - 1, -4 + height - 1 - (int) y, elt._col.getRGB());
                bmp.setRGB(1 + (int) x + 1, -4 + height - 1 - (int) y, elt._col.getRGB());
                bmp.setRGB(1 + (int) x, -4 + height - 1 - (int) y + 1, elt._col.getRGB());
                bmp.setRGB(1 + (int) x, -4 + height - 1 - (int) y - 1, elt._col.getRGB());
            }
        }
        return bmp;
    }

    /**
     * Change the name of an edited entity
     *
     * @param oldName of the edited entity
     * @param newName of the edited entity
     */
    public void changeName(String oldName, String newName) {
        for (CurveElement curve : points) {
            if (curve.getStr().equals(oldName)) {
                curve.setStr(newName);
            }
        }
    }

    /**
     * Change the color of an edited entity
     *
     * @param name of the edited entity
     * @param newColor of the edited entity
     */
    public void changeColor(String name, Color newColor) {
        for (CurveElement curve : points) {
            if (curve.getStr().equals(name)) {
                curve.setCol(newColor);
            }
        }
    }

    /**
     * Put an entity as abscissa of the curves
     *
     * @param name = time by default
     */
    public void changeAbsc(String name) {
        absc = name;
    }

    /**
     * Export points in CSV or R format. Called from Controller.
     *
     * @param path is the name of recording file
     * @param names of entities
     * @param waiter
     * @return true if export went right
     */
    public boolean export(String path, ArrayList<String> names, JProgressBar waiter) throws Exception {
        while (isProcessing()) {
            try {
                System.err.println("Waiting (still processing)" + Thread.currentThread().toString());
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimulationCurves.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.setWaiter(waiter);
        if (path.endsWith(".csv")) {
            return exportToCSV(path, names);
        } else {
            return exportToR(path, names);
        }
    }

    private Double[][] getFastData(ArrayList<String> names, int nbrLine) {
        int stepW;
        if (nbrLine > 40) {
            stepW = (int) (nbrLine / 40);
        } else {
            stepW = (int) (40 / nbrLine);
        }

        Double[][] tab = new Double[nbrLine][names.size()];
        for (int k = 0; k < points.size(); k++) {
            CurveElement c = points.get(k);
            int i = new Double(c.getPt().x).intValue();
            int j = names.indexOf(c.getStr());
            tab[i][j] = c.getPt().y;

            if (k % stepW == 0) {
                waiter.setValue(waiter.getValue() + 1);
            }

        }
        return tab;
    }

    /**
     * Export to CSV format
     *
     * @param path is the name of recording file
     * @param names of entities
     */
    private boolean exportToCSV(String path, ArrayList<String> names) {
        String mem = absc;
        changeAbsc("time");
        boolean error = false;
        Double max = getMaxAbsc();
        try {
            FileWriter testSave = new FileWriter(path);
            try (BufferedWriter out = new BufferedWriter(testSave)) {
                String first = "\"\";\"time\"";
                for (String name : names) {
                    first = first.concat(";\"" + name + "\"");
                }
                out.write(first + "\n");
                waiter.setValue(30);

                Double[][] tab = getFastData(names, max.intValue() + 1);

                int stepW = 1;
                if (max > 30) {
                    stepW = (int) (max / 30);
                } else {
                    stepW = (int) (30 / max);
                }

                for (int i = 0; i <= max; i++) {
                    String line = "\"" + i + "\";" + i;
                    for (int j = 0; j < names.size(); j++) {
                        line = line.concat(";" + tab[i][j]);
                    }
                    if (i % stepW == 0) {
                        waiter.setValue(waiter.getValue() + 1);
                    }
                    out.write(line + "\n");
                }
            }

        } catch (Exception e) {
            System.err.println(e.toString());
            error = true;
        }

        changeAbsc(mem);
        return error;
    }

    public void appendToCsv(String path, ArrayList<String> names) {
        String mem = absc;
        changeAbsc("time");
        HashMap<String, ArrayList<CurveElement>> curvesByName = new HashMap<>();
        ArrayList<String> toExport = new ArrayList<>();
        String first = "";
        for (String name : names) {
            first = first.concat(";\"" + name + "\"");
            curvesByName.put(name, getByName(name));
        }
        toExport.add(first);

        Double max = getMaxAbsc();
        for (int i = 0; i <= max; i++) {
            String line = "";
            for (String name : names) {
                CurveElement c = getByAbscissa(i, name);
                if (c != null) {
                    line = line.concat(";" + c.getPt().y);
                }
            }
            toExport.add(line);
        }

        System.out.println(toExport.toString());
        FileSaverLoader.appendCurves(path, toExport);
        changeAbsc(mem);
    }

    /**
     * Export points to R format
     *
     * @param path is the name of recording file
     * @param names of entities
     */
    private boolean exportToR(String path, ArrayList<String> names) {
        String mem = absc;
        changeAbsc("time");
        ArrayList<String> toExport = new ArrayList<>();
        ArrayList<Double> time = new ArrayList<>();
        String df = "df=data.frame(time,";
        waiter.setValue(30);
        int size = names.size();
        int stepW = 1;
        if (size > 50) {
            stepW = (int) (size / 50);
        } else {
            stepW = (int) (50 / size);
        }

        for (String name : names) {
            df = df.concat(name + ",");
            ArrayList<CurveElement> elmt = getByName(name);
            String export = name + " <- c(";
            for (CurveElement c : elmt) {
                export = export.concat(c.getPt().y + ",");
                Double t = c.getPt().x;
                if (!time.contains(t)) {
                    time.add(t);
                }
            }
            export = export.substring(0, export.length() - 1);
            export = export.concat(")");
            toExport.add(export);
            waiter.setValue(waiter.getValue() + stepW);
        }
        df = df.substring(0, df.length() - 1);
        df = df.concat(")");
        String timeToExport = time.toString();
        timeToExport = timeToExport.replaceAll(", ", ",");
        timeToExport = timeToExport.replace('[', '(');
        timeToExport = timeToExport.replace(']', ')');
        timeToExport = "time <- c" + timeToExport;
        toExport.add(timeToExport);
        toExport.add(df);
        boolean error = FileSaverLoader.exportCurves(path, toExport);
        changeAbsc(mem);
        waiter.setValue(100);
        return error;
    }

    /**
     * Import points from CSV datas
     *
     * @param path of the csv file
     * @param entities we are looking for datas
     */
    public void importFromCSV(String path, ArrayList<Entity> entities) {
        clear();

        ArrayList<String> existingNames = new ArrayList<>();
        for (Entity r : entities) {
            existingNames.add(r.getEtiquettes());
        }

        ArrayList<String> lines = FileSaverLoader.importCurves(path);

        ArrayList<Double> times = extractColumn("time", lines);
        if (times.isEmpty()) {
            times = extractColumn("", lines);
            if (times.isEmpty()) {
                for (int i = 0; i < lines.size(); i++) {
                    times.add(i * 1.0);
                }
            }
        }

        HashMap<String, ArrayList<Double>> allValues = new HashMap<>();
        for (String name : existingNames) {
            ArrayList<Double> v = extractColumn(name, lines);
            if (!v.isEmpty()) {
                allValues.put(name, v);
            }
        }

        for (String name : allValues.keySet()) {
            ArrayList<Double> v = allValues.get(name);
            for (int i = 0; i < v.size(); i++) {
                addPoint(name, getColor(entities, name), times.get(i), v.get(i));
            }
        }
    }

    /**
     * Get the Color of a specific entity
     *
     * @param entities list of existing entities
     * @param name of the specific entity
     * @return the entity Color, or the default Color.Black
     */
    private Color getColor(ArrayList<Entity> entities, String name) {
        for (Entity p : entities) {
            if (p.getEtiquettes().equals(name)) {
                return p.Couleur;
            }
        }
        return Color.BLACK;
    }

    /**
     * Extract in an array built from CSV datas the column of values of the
     * entity called name
     *
     * @param name of the sought entity
     * @param lines array red from a CSV file
     * @return an array of number of entity's instances
     */
    private ArrayList<Double> extractColumn(String name, ArrayList<String> lines) {
        int column = -1;
        ArrayList<Double> values = new ArrayList<>();
        if (lines.size() > 0) {
            String first = lines.get(0);
            first = first.replaceAll("\"", "");
            String[] parts = first.split(";");
            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                if (part.equalsIgnoreCase(name)) {
                    column = i;
                }
            }
            if (column >= 0) {
                for (int i = 1; i < lines.size(); i++) {
                    String line = lines.get(i);
                    line = line.replaceAll("\"", "");
                    parts = line.split(";");
                    String v = parts[column];
                    values.add(Double.valueOf(v));
                }
            }
        }
        return values;
    }

    /**
     * Get all points related to the entity called name
     *
     * @param name of the sought entity
     * @return an array of points of the entity name
     */
    private ArrayList<CurveElement> getByName(String name) {
        ArrayList<CurveElement> elmt = new ArrayList<>();
        for (CurveElement c : points) {
            if (c.getStr().equals(name)) {
                elmt.add(c);
            }
        }
        return elmt;
    }

    private CurveElement getByAbscissa(double abs, String name) {
        for (CurveElement c : points) {
            if (c.getStr().equals(name) && (c.getPt().x == abs)) {
                return c;
            }
        }
        return null;
    }

    private ArrayList<CurveElement> getByAbscissa(double abs, ArrayList<CurveElement> entity) {
        ArrayList<CurveElement> elmts = new ArrayList<>();
        for (CurveElement c : entity) {
            if (c.getPt().x == abs) {
                elmts.add(c);
            }
        }
        return elmts;
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public void setWaiter(JProgressBar waiter) {
        this.waiter = waiter;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

    private class CurveElement {

        private Point2D.Double _pt;
        private String _str;
        private Color _col;

        public CurveElement(String str, Point2D.Double pt, Color col) {
            _pt = pt;
            _str = str;
            _col = col;
        }

        public Point2D.Double getPt() {
            return _pt;
        }

        public void setPt(Point2D.Double _pt) {
            this._pt = _pt;
        }

        public String getStr() {
            return _str;
        }

        public void setStr(String _str) {
            this._str = _str;
        }

        public Color getCol() {
            return _col;
        }

        public void setCol(Color _col) {
            this._col = _col;
        }

        @Override
        public String toString() {
            return getStr() + " " + _pt.x + "*" + _pt.y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof CurveElement) {
                CurveElement c = (CurveElement) o;
                return ((c._str.equals(this._str)) && (c._pt.x == this._pt.x));
            }
            return false;
        }
    }
}
