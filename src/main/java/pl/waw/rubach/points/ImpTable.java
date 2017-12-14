package pl.waw.rubach.points;

import java.util.*;

public class ImpTable extends AbstractTable{

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

    /**
     * Function to print table :) with
     */
    public static void printTable() {
        System.out.println("*** Tabela impów ***");

        Map<Integer, Integer> map = ImpTable.getInstance().getPtsMap();
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());
        int prev = 0;
        for (Integer key : ptsMapSet) {
            System.out.println(map.get(key)+ " imp " + (prev > 0 ? prev + 10 : 0)  + "-" + key);
            prev = key;
        }
    }

    public static String  giveTable() {
        String s=("*** Tabela impów *** \n");

        Map<Integer, Integer> map = ImpTable.getInstance().getPtsMap();
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());
        int prev = 0;
        for (Integer key : ptsMapSet) {
            s = s+"\n "+(map.get(key)+ " imp " + (prev > 0 ? prev + 10 : 0)  + "-" + key);
            prev = key;
        }
        return s;
    }




}
