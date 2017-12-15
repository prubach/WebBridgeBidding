package pl.waw.rubach.points;

import org.apache.commons.collections4.map.MultiKeyMap;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class ExpectedResultsTable {

    /**
     * one instance of ImpTable (as Singleton)??
     */
    private static ExpectedResultsTable instance;

    // Keep Maps of points to Imp Points, for Fit (true|false), After (true|false)
    private MultiKeyMap<Boolean,Map> pointsMap = new MultiKeyMap<>();

    private final int[] BEFORE_FIT = new int[] {50, 70, 110, 130, 300, 350, 400, 430, 460, 490, 550, 600, 700, 900,
            1000, 1100, 1200, 1400};
    private final int[] AFTER_FIT = new int[] {50, 70, 110, 130, 440, 520, 600, 630, 660, 690, 750, 800, 1050, 1350,
            1500, 1650, 1800, 2100};
    private final int[] BEFORE_NOFIT = new int[] {0, 50, 70, 110, 130, 200, 300, 350, 400, 430, 460, 520, 600, 700,
            900, 1000, 1100, 1200, 1400 };
    private final int[] AFTER_NOFIT = new int[] {0, 50, 70, 110, 130, 290, 440, 520, 600, 630, 660, 720, 800, 1050,
            1350, 1500, 1650, 1800, 2100 };

    private ExpectedResultsTable() {
        pointsMap.put(true,false,fillMap(BEFORE_FIT));
        pointsMap.put(true,true,fillMap(AFTER_FIT));
        pointsMap.put(false,false,fillMap(BEFORE_NOFIT));
        pointsMap.put(false,true,fillMap(AFTER_NOFIT));
    }

    private Map<Integer, Integer> fillMap(int[] pointsTab) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<pointsTab.length;i++) {
            map.put(i+20, pointsTab[i]);
        }
        return map;
    }

    public int getPoints(float points, boolean fitInOlderColor, boolean auctionAssumption) {
        Map<Integer,Integer> map = getPtsMap(fitInOlderColor, auctionAssumption);
        int pointsInt = Math.round(points*2);
        if (pointsInt % 2==0) {
            return map.get(pointsInt / 2);
        } else {
            int up = Math.round((pointsInt/2)+0.5f);
            int down = Math.round((pointsInt/2)-0.5f);
            return (map.get(up) + map.get(down))/2;
        }
    }

    public Map<Integer, Integer> getPtsMap(boolean fitInOlderColor, boolean auctionAssumption) {
        return pointsMap.get(fitInOlderColor, auctionAssumption);
    }

    /**
     * magic function to check if it is instance of ExpectedTable and create it if it is not
     * (problem which I found somewhere is that two person cant use it in the same time?
     *
     * @return instance of ImpTable
     */
    public static ExpectedResultsTable getInstance() {
        if (instance == null)
            instance = new ExpectedResultsTable();
        return instance;
    }

    public static String getTableAsString(boolean fitInOlderColor, boolean auctionAssumption) {
        String s = ("*** Oczekiwane  punkty dla koloru " + (fitInOlderColor ? "sfitowanego" : "niesfitowanego") + " i " + (auctionAssumption ? "po partii" : "przed partią" + ".***" + "\n"));

        Map<Integer, Integer> map = ExpectedResultsTable.getInstance().getPtsMap(fitInOlderColor, auctionAssumption);
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());

        for (Integer key : ptsMapSet) {
            s = s + "\n" + "dla " + key + " PC oczekiwane " + map.get(key) + " punktów";

        }
        return s;
    }

}
