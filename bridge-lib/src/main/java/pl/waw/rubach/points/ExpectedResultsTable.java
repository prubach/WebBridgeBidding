package pl.waw.rubach.points;

import org.apache.commons.collections4.map.MultiKeyMap;
import pl.waw.rubach.points.exceptions.InvalidNumberOfPointsException;
import pl.waw.rubach.points.exceptions.InvalidParameterException;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * ZAPIS MILTONOWY ZMODYFIKOWANY (WEDŁUG Ł. INIARSKIEGO)
 * Poniższa tabelka uwzględnia nie tylko PC (punkty za figury) ale także sfitowanie kolorów starszych oraz krótkości. Kolor sfitowany to kolor co najmniej 8-mio kartowy
 */
public class ExpectedResultsTable {

    /**
     * one instance of ExpectedResultsTable (as Singleton)
     */
    private static ExpectedResultsTable instance;
    private final int[] BEFORE_FIT = new int[]{50, 70, 110, 130, 300, 350, 400, 430, 460, 490, 550, 600, 700, 900,
            1000, 1100, 1200, 1400, 1400, 1400, 1400};
    private final int[] AFTER_FIT = new int[]{50, 70, 110, 130, 440, 520, 600, 630, 660, 690, 750, 800, 1050, 1350,
            1500, 1650, 1800, 2100, 2100, 2100, 2100};
    private final int[] BEFORE_NOFIT = new int[]{0, 50, 70, 110, 130, 200, 300, 350, 400, 430, 460, 520, 600, 700,
            900, 1000, 1100, 1200, 1400, 1400, 1400};
    private final int[] AFTER_NOFIT = new int[]{0, 50, 70, 110, 130, 290, 440, 520, 600, 630, 660, 720, 800, 1050,
            1350, 1500, 1650, 1800, 2100, 2100, 2100};
    // Keep Maps of points to Imp Points, for Fit (true|false), After (true|false)
    private MultiKeyMap<Boolean, Map> pointsMap = new MultiKeyMap<>();

    private ExpectedResultsTable() {
        pointsMap.put(true, false, fillMap(BEFORE_FIT));
        pointsMap.put(true, true, fillMap(AFTER_FIT));
        pointsMap.put(false, false, fillMap(BEFORE_NOFIT));
        pointsMap.put(false, true, fillMap(AFTER_NOFIT));
    }

    /**
     * magic function to check if it is instance of ExpectedTable and create it if it is not
     * (problem which I found somewhere is that two person cant use it in the same time?
     * (PR: that was due to an incorrect implementation. Shouldn't be the case anymore)
     *
     * @return instance of ImpTable
     */
    public static ExpectedResultsTable getInstance() {
        if (instance == null)
            instance = new ExpectedResultsTable();
        return instance;
    }

    /**
     * Prepare a string description of a table of bonus points
     *
     * @param fitInOlderColor   - if fit in older color (beginning with 30 points any color)
     * @param auctionAssumption - assumption After (true) or Before (false)
     * @return description of bonus points table
     */
    public static String getTableAsString(boolean fitInOlderColor, boolean auctionAssumption) {
        StringBuilder s = new StringBuilder(("*** Oczekiwane  punkty dla koloru " + (fitInOlderColor ? "sfitowanego" : "niesfitowanego") + " i " + (auctionAssumption ? "po partii" : "przed partią" + ".***" + "\n")));

        Map<Integer, Integer> map = ExpectedResultsTable.getInstance().getPtsMap(fitInOlderColor, auctionAssumption);
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());

        for (Integer key : ptsMapSet) {
            s.append("\n dla ").append(key).append(" PC oczekiwane ").append(map.get(key)).append(" punktów");

        }
        return s.toString();
    }

    /**
     * Helper method to enable printing the bonus points table as a string
     *
     * @param fitInOlderColor   - if fit in older color (beginning with 30 points any color)
     * @param auctionAssumption - assumption After (true) or Before (false)
     * @return map of points in hand to bonus points
     */
    public Map<Integer, Integer> getPtsMap(boolean fitInOlderColor, boolean auctionAssumption) {
        return pointsMap.get(fitInOlderColor, auctionAssumption);
    }

    /**
     * Fill the map of bonus points with data from an array of ints and return it.
     * Points in hand start at 20 and finish at 40 (for all cases!)
     *
     * @param pointsTab - array of ints with bonus points for subsequent points in hand
     * @return map of points in hand to bonus points
     */
    private Map<Integer, Integer> fillMap(int[] pointsTab) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pointsTab.length; i++) {
            map.put(i + 20, pointsTab[i]);
        }
        return map;
    }

    /**
     * Calculate the bonus points for a certain contract
     *
     * @param points                - points in both our  (person who calulate points)  hands
     * @param fitInOlderColorWe     - if we hace  fit in older color (beginning with 30 points any color) true, else false
     * @param fitInOlderColorThey   -  if they have fit as above
     *                              Attantion if both have exactly 20 points and both fit in older color mark only spades (for hearts mark no fit)
     * @param auctionAssumptionWe   - assumption After (true) or Before (false)
     * @param auctionAssumptionThey - vunerable or not as above
     * @return bonus points
     */
    int getPoints(float points, boolean fitInOlderColorWe, boolean fitInOlderColorThey, boolean auctionAssumptionWe, boolean auctionAssumptionThey)
            throws InvalidNumberOfPointsException, InvalidParameterException {

        //test if points value is correct if not print Exeption
        if (points < 0 || points > 40) throw new InvalidNumberOfPointsException(points);

        if (points == 20 && fitInOlderColorThey && fitInOlderColorWe)
            throw new InvalidParameterException(points, fitInOlderColorWe, fitInOlderColorThey);

        //if 1 we  so points are for us, if -1 they and points for us is minus that for they
        int whoShouldPlay= 1;
        boolean auctionAssumption = auctionAssumptionWe;
        boolean fit = fitInOlderColorWe;
        if (points < 20) {
            points = (40 - points);
            whoShouldPlay = -1;
            auctionAssumption = auctionAssumptionThey;
            fit = fitInOlderColorThey;
        }
        else if(points == 20) {
            if(fitInOlderColorThey) {
                whoShouldPlay = -1;
                auctionAssumption = auctionAssumptionThey;
                fit = fitInOlderColorThey;
            }
        }

        return  calculateExpectedPoins(points,whoShouldPlay,fit,auctionAssumption);
    }


    //******************************************************************************************************************

    /**
     * SHORT VERSION of getPoints
     * Attention - probably also good but ask for assumption and fit pair who have more points, and not the same as points etc
     *
     * @param points            - points in both hands for those who have more
     * @param fit               - of pair who have more points
     * @param auctionAssumption of pair who have more points
     * @return expected number of points if other pair should play is less then zero
     * @throws InvalidNumberOfPointsException if number of points is not correct
     */
    int getPoints(float points, boolean fit, boolean auctionAssumption) throws InvalidNumberOfPointsException {

        //test if points value is correct if not print Exeption
        if (points < 0 || points > 40) throw new InvalidNumberOfPointsException(points);
        int whoShouldPlay = 1;
        if (points < 20) {
            points = 40 - points;
            whoShouldPlay = -1;
        }

      return  calculateExpectedPoins(points,whoShouldPlay,fit,auctionAssumption);
    }

 private int calculateExpectedPoins(float points, int whoShouldPlay, boolean fit, boolean auctionAssumption) throws InvalidNumberOfPointsException{

     Map<Integer, Integer> map = getPtsMap(fit, auctionAssumption);
     int pointsInt = Math.round(points * 2);
     if (pointsInt % 2 == 0) {
         return whoShouldPlay * map.get(pointsInt / 2);
     } else {
         int up = Math.round((pointsInt / 2) + 0.5f);
         int down = Math.round((pointsInt / 2) - 0.5f);
         return whoShouldPlay * ((map.get(up) + map.get(down)) / 2);
     }
 }


}
