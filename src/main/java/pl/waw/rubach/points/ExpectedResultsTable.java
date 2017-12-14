package pl.waw.rubach.points;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class ExpectedResultsTable extends AbstractTable {


    /**
     * one instance of ImpTable (as Singleton)??
     */
    private static ExpectedResultsTable instance;


    private boolean fitInOlderColor;
    private boolean auctionAssumption;


    private ExpectedResultsTable(boolean fitInOlderColor, boolean auctionAssumption) {
        this.fitInOlderColor = fitInOlderColor;
        this.auctionAssumption = auctionAssumption;

        //TODO find what to do if there is half point (calculate two and divide etc? not in table?
        // it shoud be in the middle betwen two next to it

        ptsMap.put(20, (fitInOlderColor ? 50 : 0));
        ptsMap.put(21, (fitInOlderColor ? 70 : 50));
        ptsMap.put(22, (fitInOlderColor ? 110 : 70));
        ptsMap.put(23, (fitInOlderColor ? 130 : 110));
        ptsMap.put(24, (fitInOlderColor ? (auctionAssumption ? 440 : 300) : 130));
        ptsMap.put(25, (fitInOlderColor ? (auctionAssumption ? 520 : 350) : (auctionAssumption ? 290 : 200)));
        ptsMap.put(26, (fitInOlderColor ? (auctionAssumption ? 600 : 400) : (auctionAssumption ? 440 : 300)));
        ptsMap.put(27, (fitInOlderColor ? (auctionAssumption ? 630 : 430) : (auctionAssumption ? 520 : 350)));
        ptsMap.put(28, (fitInOlderColor ? (auctionAssumption ? 660 : 460) : (auctionAssumption ? 600 : 400)));
        ptsMap.put(29, (fitInOlderColor ? (auctionAssumption ? 690 : 490) : (auctionAssumption ? 630 : 430)));
        ptsMap.put(30, (fitInOlderColor ? (auctionAssumption ? 750 : 550) : (auctionAssumption ? 660 : 460)));
        ptsMap.put(31, (fitInOlderColor ? (auctionAssumption ? 800 : 600) : (auctionAssumption ? 720 : 520)));
        ptsMap.put(32, (fitInOlderColor ? (auctionAssumption ? 1050 : 700) : (auctionAssumption ? 800 : 600)));
        ptsMap.put(33, (fitInOlderColor ? (auctionAssumption ? 1350 : 900) : (auctionAssumption ? 1050 : 700)));
        ptsMap.put(34, (fitInOlderColor ? (auctionAssumption ? 1500 : 1000) : (auctionAssumption ? 1350 : 1000)));
        ptsMap.put(35, (fitInOlderColor ? (auctionAssumption ? 1650 : 1100) : (auctionAssumption ? 1500 : 1100)));
        ptsMap.put(36, (fitInOlderColor ? (auctionAssumption ? 1800 : 1200) : (auctionAssumption ? 1650 : 1200)));
        ptsMap.put(37, (fitInOlderColor ? (auctionAssumption ? 2100 : 1400) : (auctionAssumption ? 1800 : 1400)));
        ptsMap.put(38, (fitInOlderColor ? (auctionAssumption ? 2200 : 1500) : (auctionAssumption ? 2100 : 1400)));

        ptsSet = new TreeSet<>(ptsMap.keySet());

    }


    /**
     * magic function to check if it is instance of ExpectedTable and create it if it is not
     * (problem which I found somewhere is that two person cant use it in the same time?
     *
     * @return instance of ImpTable
     */
    public static ExpectedResultsTable getInstance(boolean fitInOlderColor, boolean auctionAssumption) {
        if (instance == null || (instance.fitInOlderColor != fitInOlderColor || instance.auctionAssumption != auctionAssumption))
            instance = new ExpectedResultsTable(fitInOlderColor, auctionAssumption);
        return instance;
    }

    /**
     * Function to print table :) with
     */
    public static void printTable(boolean fitInOlderColor, boolean auctionAssumption) {
        System.out.println("*** Oczekiwane  punkty dla koloru " + (fitInOlderColor ? "sfitowanego" : "niesfitowanego") + " i " + (auctionAssumption ? "po partii" : "przed partią" + ".***"));
        Map<Integer, Integer> map = ExpectedResultsTable.getInstance(fitInOlderColor, auctionAssumption).getPtsMap();
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());

        for (Integer key : ptsMapSet) {
            System.out.println("dla " + key + " PC oczekiwane " + map.get(key) + " punktów");

        }
    }


    public static String giveTable(boolean fitInOlderColor, boolean auctionAssumption) {
        String s = ("*** Oczekiwane  punkty dla koloru " + (fitInOlderColor ? "sfitowanego" : "niesfitowanego") + " i " + (auctionAssumption ? "po partii" : "przed partią" + ".***" + "\n"));

        Map<Integer, Integer> map = ExpectedResultsTable.getInstance(fitInOlderColor, auctionAssumption).getPtsMap();
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());

        for (Integer key : ptsMapSet) {
            s = s + "\n" + "dla " + key + " PC oczekiwane " + map.get(key) + " punktów";

        }
        return s;
    }

}
