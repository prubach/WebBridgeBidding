package pl.waw.rubach.points;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

//Original IMP table, 1949 -1962
public class OrginalImpTable extends AbstractTable{

    /**
     * one instance of ImpTable (as Singleton)??
     */
    private static OrginalImpTable instance;


    /**
     * private constructor because user cant use it only could call and then it is checkt it it exist..
     */
    private OrginalImpTable() {
        ptsMap.put(10, 0);
        ptsMap.put(40, 1);
        ptsMap.put(130, 2);
        ptsMap.put(210, 3);
        ptsMap.put(340, 4);
        ptsMap.put(490, 5);
        ptsMap.put(740, 6);
        ptsMap.put(990, 7);
        ptsMap.put(1240, 8);
        ptsMap.put(1490, 9);
        ptsMap.put(1990, 10);
        ptsMap.put(2490, 11);
        ptsMap.put(2990, 12);
        ptsMap.put(3490, 13);
        ptsMap.put(3990, 14);
        ptsMap.put(10000, 15);
        ptsSet = new TreeSet<>(ptsMap.keySet());
    }

    /**
     * magic function to check if it is instance of ImpTable and create it if it is not
     * (problem which I found somewhere is that two person cant use it in the same time?
     * @return instance of ImpTable
     */
    public static OrginalImpTable getInstance() {
        if (instance == null) instance = new OrginalImpTable();
        return instance;
    }

    public static String getTableAsString() {
        return OrginalImpTable.getInstance().toString();
    }
}
