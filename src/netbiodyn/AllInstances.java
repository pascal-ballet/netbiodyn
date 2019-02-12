/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netbiodyn;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author riviere
 */
public class AllInstances extends ArrayList<InstanceAgent> implements Cloneable{

    private InstanceAgent[][][] matrix; // position (acces rapides) des instances d'entites
    private HashMap<String, Integer> entitiesBook; // Nombre d'entités par type
    private int X;
    private int Y;
    private int Z;

    public AllInstances(int x, int y, int z) {
        super();
        X = x;
        Y = y;
        Z = z;
        matrix = new InstanceAgent[x][y][z];
        entitiesBook = new HashMap<>();
    }

    public AllInstances(ArrayList<InstanceAgent> l, InstanceAgent[][][] m, int x, int y, int z) {
        super(l);
        this.matrix = m;
        X = x;
        Y = y;
        Z = z;
        this.updateBook();
    }

    public AllInstances(AllInstances other) {
        super(other.getList());
        X = other.getX();
        Y = other.getY();
        Z = other.getZ();
        matrix = other.getMatrix();
        updateBook();
    }

    private void updateBook() {
        entitiesBook = new HashMap<>();
        for (InstanceAgent c : this) {
            if (entitiesBook.containsKey(c.getNom()) == false) {
                entitiesBook.put(c.getNom(), 1);
            } else {
                int nbr = entitiesBook.get(c.getNom());
                nbr += 1;
                entitiesBook.put(c.getNom(), nbr);
            }
        }
    }

    private void addInBook(String type) {
        if (!entitiesBook.containsKey(type)) {
            entitiesBook.put(type, 1);
        } else {
            int nbr = entitiesBook.get(type);
            nbr += 1;
            entitiesBook.put(type, nbr);
        }
    }

    private void removeInBook(InstanceAgent reaxel) {
        String type = reaxel.getNom();
        if (entitiesBook.containsKey(type)) {
            int nbr = entitiesBook.get(type);
            if (nbr > 0) {
                nbr -= 1;
            }
            entitiesBook.put(type, nbr);
        }
    }

    public HashMap<String, Integer> getBook() {
        return (HashMap<String, Integer>) entitiesBook.clone();
    }

    public int getInstancesNbr(String name) {
        if (entitiesBook.get(name) == null) {
            return 0;
        } else {
            return entitiesBook.get(name);
        }
    }
    
    public ArrayList<InstanceAgent> getByName(String name){
         ArrayList<InstanceAgent> entities = new ArrayList<>();
        for (InstanceAgent r : this) {
            if (r.getNom().equals(name)) {
                entities.add(r);
            }
        }
            return entities;
    }

    public int getSize() {
        return this.size();
    }

    public InstanceAgent getInList(int pos) {
        if (pos < getSize()) {
            return this.get(pos);
        } else {
            return null;
        }
    }

    public InstanceAgent getFast(int x, int y, int z) {
        return matrix[x][y][z];
    }

    public boolean addReaxel(InstanceAgent reaxel) {
        int x = reaxel.getX();
        int y = reaxel.getY();
        int z = reaxel.getZ();
        if (matrix[x][y][z] == null) {
            this.add(reaxel);
            matrix[x][y][z] = reaxel;
            addInBook(reaxel.getNom());
            return true;
        }
        return false;
    }

    public void editReaxels(Agent entity, String old_name) {
        ArrayList<InstanceAgent> copy = getList();
        for (InstanceAgent reaxel : copy) {
            if (reaxel.getNom().equals(old_name)) {
                removeReaxel(reaxel);
                InstanceAgent newReaxel = InstanceAgent.CreerReaxel(entity);
                newReaxel.setX(reaxel.getX());
                newReaxel.setY(reaxel.getY());
                newReaxel.setZ(reaxel.getZ());
                addReaxel(newReaxel);
            }
        }
    }

    public void select(int x, int y, int z) {
        matrix[x][y][z].setSelectionne(true);
    }

    public void unselect(int x, int y, int z) {
        matrix[x][y][z].setSelectionne(false);
    }

    public boolean removeReaxel(InstanceAgent reaxel) {
        int x = reaxel.getX();
        int y = reaxel.getY();
        int z = reaxel.getZ();
        if (matrix[x][y][z] != null) {
            this.remove(reaxel);
            matrix[x][y][z] = null;
            removeInBook(reaxel);
            return true;
        }
        return false;
    }

    public boolean removeReaxel(int x, int y, int z) {
        InstanceAgent reaxel = matrix[x][y][z];
        if (reaxel != null) {
            this.remove(reaxel);
            matrix[x][y][z] = null;
            removeInBook(reaxel);
            return true;
        }
        return false;
    }
    
    public void removeEntityType(String etiquettes) {
        removeByName(etiquettes);
        if(entitiesBook.containsKey(etiquettes)){
            entitiesBook.remove(etiquettes);
        }
    }

    public void removeByName(String nom) {
        ArrayList<InstanceAgent> copy = getList();
        for (InstanceAgent reaxel : copy) {
            if (reaxel.getNom().equals(nom)) {
                removeReaxel(reaxel);
            }
        }
    }

    public void removeOnlyCleanable(int x, int y, int z) {
        InstanceAgent c = matrix[x][y][z];
        if (c != null) {
            if ((this.contains(c)) && (c.isVidable())) {
                removeReaxel(c);
            }
        }
    }

    public void setMatrixAndList(ArrayList<InstanceAgent> l) {
        for (InstanceAgent rea : l) {
            addReaxel(rea.clone());
        }
        this.updateBook();
    }

    public InstanceAgent[][][] getMatrix() {
        InstanceAgent[][][] copy = new InstanceAgent[getX()][getY()][getZ()];
        for (InstanceAgent r : this) {
            copy[r.getX()][r.getY()][r.getZ()] = r.clone();
        }
        return copy;
    }

    public ArrayList<InstanceAgent> getList() {
        ArrayList<InstanceAgent> copy = new ArrayList<>();
        for (InstanceAgent r : this) {
            copy.add(r.clone());
        }
        return copy;
    }

    @Override
    public AllInstances clone() {
        return new AllInstances(getList(), getMatrix(), getX(), getY(), getZ());
    }

    public void clear() {
        entitiesBook.clear();
        matrix=null;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getZ() {
        return Z;
    }

    public void setZ(int Z) {
        this.Z = Z;
    }


}
