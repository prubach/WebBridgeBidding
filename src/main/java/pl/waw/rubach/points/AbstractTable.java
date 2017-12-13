package pl.waw.rubach.points;

import java.util.*;

public abstract class AbstractTable {

    /**
     * one instance of ImpTable (as Singleton)??
     */
    protected static AbstractTable instance;

    /**
     * Map of points (as key) and  result imp as ???
     */
    protected Map<Integer, Integer> ptsMap = new HashMap<>();

    /**
     * set of key (so points borders?
     */
    protected NavigableSet<Integer> ptsSet;

    /**
     * private constructor because user cant use it only could call and then it is checkt it it exist..
     */
    protected AbstractTable() {

    }

    /**
     * magic function to check if it is instance of ImpTable and create it if it is not
     * (problem which I found somewhere is that two person cant use it in the same time?
     * @return instance of ImpTable
     */
    public static AbstractTable getInstance() {
       // if (instance == null) instance = new AbstractTable();
        return instance;
    }

    /**
     * Function to print table :) with
     */
    public static void printTable() {
        Map<Integer, Integer> map = ImpTable.getInstance().getPtsMap();
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());
        int prev = 0;
        for (Integer key : ptsMapSet) {
         //   System.out.println(map.get(key)+ " imp " + (prev > 0 ? prev + 10 : 0)  + "-" + key);
            prev = key;
        }
    }

    //Getters ? (no setters because is not possible to change sth here ?
    /**
     * Function to give imps form points (map value from key?)
     * @param points difference between expected and wined poinst in game
     * @return imp points
     */
    public int getPoints(int points) {
        Integer key = ptsSet.higher(points);
        return ptsMap.get(key);
    }

    /**
     * Function to get the whole map (poinst as key and imp as value)
     * @return ImpTable value for all possibilitis
     */
    public Map<Integer, Integer> getPtsMap() {
        return ptsMap;
    }


}
