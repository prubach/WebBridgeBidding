package pl.waw.rubach.points;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;

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


}
