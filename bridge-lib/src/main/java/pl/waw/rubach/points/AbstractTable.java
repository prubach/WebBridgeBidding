package pl.waw.rubach.points;

import java.util.*;

public abstract class AbstractTable {


    /**
     * Map of points (as key) and  result imp as ???
     */
    protected Map<Integer, Integer> ptsMap = new HashMap<>();

    /**
     * set of key (so points borders?
     */
    protected NavigableSet<Integer> ptsSet;


    //Getters ? (no setters because is not possible to change sth here ?

    /**
     * Function to give imps form points (map value from key?)
     *
     * @param points difference between expected and wined poinst in game
     * @return imp points
     */
    public int getPoints(int points) {
        Integer key = ptsSet.ceiling(points);
        return ptsMap.get(key);
    }

    /**
     * Function to get the whole map (poinst as key and imp as value)
     *
     * @return ImpTable value for all possibilitis
     */
    public Map<Integer, Integer> getPtsMap() {
        return ptsMap;
    }


    /**
     * Function to check condition if imput values are correct
     *
     * return boolean true if good false if wrong
     * */
    public boolean checkInputValue(int begin, int end, int value) {

        return value <= end && value >= begin;
    }

    /**
     *
     * @return string with the description of the table
     */
    public String toString() {
        String s=("*** Tabela impów *** \n");

        Map<Integer, Integer> map = getPtsMap();
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());
        int prev = 0;
        for (Integer key : ptsMapSet) {
            s = s+"\n "+(map.get(key)+ " imp " + (prev > 0 ? prev + 10 : 0)  + "-" + key);
            prev = key;
        }
        return s;
    }
}
