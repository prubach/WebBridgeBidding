package pl.waw.rubach.points.duplicateBridgeImps;

import java.util.*;

public class ImpTable extends AbstractImpTable {

    /**
     * one instance of ImpTable (as Singleton)??
     */
    private static ImpTable instance;


    /**
     * private constructor because user cant use it only could call and then it is checkt it it exist..
     */
    private ImpTable() {
        ptsMap.put(10, 0);
        ptsMap.put(40, 1);
        ptsMap.put(80, 2);
        ptsMap.put(120, 3);
        ptsMap.put(160, 4);
        ptsMap.put(210, 5);
        ptsMap.put(260, 6);
        ptsMap.put(310, 7);
        ptsMap.put(360, 8);
        ptsMap.put(420, 9);
        ptsMap.put(490, 10);
        ptsMap.put(590, 11);
        ptsMap.put(740, 12);
        ptsMap.put(890, 13);
        ptsMap.put(1090, 14);
        ptsMap.put(1220, 15);
        ptsMap.put(1490, 16);
        ptsMap.put(1740, 17);
        ptsMap.put(1990, 18);
        ptsMap.put(2240, 19);
        ptsMap.put(2490, 20);
        ptsMap.put(2990, 21);
        ptsMap.put(3490, 22);
        ptsMap.put(3990, 23);
        ptsMap.put(10000, 24);
        ptsSet = new TreeSet<>(ptsMap.keySet());
    }

    /**
     * magic function to check if it is instance of ImpTable and create it if it is not
     * (problem which I found somewhere is that two person cant use it in the same time?
     * @return instance of ImpTable
     */
    public static ImpTable getInstance() {
        if (instance == null) instance = new ImpTable();
        return instance;
    }

    public static String getTableAsString() {
        return ImpTable.getInstance().toString();
    }
}
