package pl.waw.rubach.points;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CountingPointsTest {

    private static Logger logger = LoggerFactory.getLogger(CountingPointsTest.class);

    private Map<Float, Integer> testExpResBeforeFitMap = new HashMap<>();
    private Map<Float, Integer> testExpResAfterFitMap = new HashMap<>();

    private Map<Float, Integer> testExpResBeforeNoFitMap = new HashMap<>();
    private Map<Float, Integer> testExpResAfterNoFitMap = new HashMap<>();

    private MultiKeyMap<Float, Integer> testCountingPointsBothAfterFitMap = new MultiKeyMap<>();

    private MultiKeyMap<Float, Integer> testCountingPointsBothAfterNoFitMap = new MultiKeyMap<>();


    private Map<Integer, Integer> testImpPointsMap = new HashMap<>();

    @Before
    public void fillTestPointsMap() {
        //testPointsMap.put()
        testImpPointsMap.put(25, 1);
        testImpPointsMap.put(50, 2);
        testImpPointsMap.put(120, 3);
        testImpPointsMap.put(450, 10);
        testImpPointsMap.put(1090, 14);
        testImpPointsMap.put(1200, 15);
        testImpPointsMap.put(2000, 19);

        //TODO add case of 20 Points on hand (fit in older etc...
        // testExpResBeforeFitMap.put(20f, -50);
        testExpResBeforeFitMap.put(19.5f, -60);
        testExpResBeforeFitMap.put(15f, -350);
        testExpResBeforeFitMap.put(16.5f, -215);
        testExpResBeforeFitMap.put(10f, -550);

        testExpResBeforeFitMap.put(20f, 50);
        testExpResBeforeFitMap.put(20.5f, 60);
        testExpResBeforeFitMap.put(25f, 350);
        testExpResBeforeFitMap.put(23.5f, 215);
        testExpResBeforeFitMap.put(30f, 550);
        testExpResBeforeFitMap.put(35f, 1100);
        testExpResBeforeFitMap.put(39.5f, 1400);
        testExpResBeforeFitMap.put(40f, 1400);

        testExpResBeforeNoFitMap.put(20f, 0);
        testExpResBeforeNoFitMap.put(20.5f, 25);
        testExpResBeforeNoFitMap.put(25f, 200);
        testExpResBeforeNoFitMap.put(23.5f, 120);
        testExpResBeforeNoFitMap.put(30f, 460);
        testExpResBeforeNoFitMap.put(35f, 1000);
        testExpResBeforeNoFitMap.put(39.5f, 1400);

        //  testExpResAfterFitMap.put(20f, 50);
        testExpResAfterFitMap.put(19.5f, -60);
        testExpResAfterFitMap.put(15f, -520);
        testExpResAfterFitMap.put(16.5f, -285);
        testExpResAfterFitMap.put(10f, -750);


        testExpResAfterFitMap.put(20f, 50);
        testExpResAfterFitMap.put(20.5f, 60);
        testExpResAfterFitMap.put(25f, 520);
        testExpResAfterFitMap.put(23.5f, 285);
        testExpResAfterFitMap.put(30f, 750);
        testExpResAfterFitMap.put(35f, 1650);
        testExpResAfterFitMap.put(39.5f, 2100);
        testExpResAfterFitMap.put(40f, 2100);

        testExpResAfterNoFitMap.put(20f, 0);
        testExpResAfterNoFitMap.put(20.5f, 25);
        testExpResAfterNoFitMap.put(25f, 290);
        testExpResAfterNoFitMap.put(23.5f, 120);
        testExpResAfterNoFitMap.put(30f, 660);
        testExpResAfterNoFitMap.put(35f, 1500);
        testExpResAfterNoFitMap.put(39.5f, 2100);
        testExpResAfterNoFitMap.put(40f, 2100);


        testCountingPointsBothAfterFitMap.put(24f, 500f, 2);
        testCountingPointsBothAfterFitMap.put(24f, 300f, -4);
        testCountingPointsBothAfterFitMap.put(24f, 100f, -8);
        testCountingPointsBothAfterFitMap.put(24f, -100f, -11);
        testCountingPointsBothAfterFitMap.put(24f, -500f, -14);

        testCountingPointsBothAfterFitMap.put(20f, 0f, -2);
        testCountingPointsBothAfterNoFitMap.put(20f, 0f, 2);


    }

    @Test
    public void testImpPoints() {
        for (int p : new TreeSet<Integer>(testImpPointsMap.keySet())) {
            Integer res = ImpTable.getInstance().getPoints(p);
            logger.info("Dla " + p + " pkt: " + res + " impów.");
            Assert.assertEquals(testImpPointsMap.get(p), res);
        }
    }


    @Test
    public void testExpResFit() throws InvalidNumberOfPointsException {
        for (float p : new TreeSet<Float>(testExpResBeforeFitMap.keySet())) {
            Integer res = ExpectedResultsTable.getInstance().getPoints(p, true, false, false);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Przed, Fit");
            Assert.assertEquals(testExpResBeforeFitMap.get(p), res);
        }
        for (float p : new TreeSet<Float>(testExpResAfterFitMap.keySet())) {
            Integer res = ExpectedResultsTable.getInstance().getPoints(p, true, true, true);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Po, Fit");
            Assert.assertEquals(testExpResAfterFitMap.get(p), res);
        }
    }

    @Test
    public void testExpResNoFit() throws InvalidNumberOfPointsException {
        for (float p : new TreeSet<Float>(testExpResBeforeNoFitMap.keySet())) {
            Integer res = ExpectedResultsTable.getInstance().getPoints(p, false, false, false);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Przed, Bez Fitu");
            Assert.assertEquals(testExpResBeforeNoFitMap.get(p), res);
        }
        for (float p : new TreeSet<Float>(testExpResAfterNoFitMap.keySet())) {
            Integer res = ExpectedResultsTable.getInstance().getPoints(p, false, true, true);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Po, Bez Fitu");
            Assert.assertEquals(testExpResAfterNoFitMap.get(p), res);
        }
    }


    @Test
    public void printExpResTables() {
        logger.info(ExpectedResultsTable.getTableAsString(false, false));
        logger.info(ExpectedResultsTable.getTableAsString(false, true));
        logger.info(ExpectedResultsTable.getTableAsString(true, false));
        logger.info(ExpectedResultsTable.getTableAsString(true, true));
    }

/*
    @Test
    public void testingTable() throws InvalidNumberOfPointsException {
        int[] testPoints = new int[]{50, 120, 450, 1090, 1200};
        for (int p : testPoints) {
            System.out.println("Dla " + p + " pkt: " + ImpTable.getInstance().getPoints(p) + "impów.");
        }

        int[] testPoints1 = new int[]{10, 15, 20, 25, 30, 35};
        for (int p : testPoints1) {
            System.out.println("Dla " + p + " pkt: " + ExpectedResultsTable.getInstance().getPoints(p, true, false, true) + " oczekiwane.");
        }

    }
*/
    @Test
    public void testCountingPointsRes() throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {


        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsBothAfterFitMap.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res = new ResultsOfOneGame(pointsInBothHands, pointsOfContract, true, false, true, false).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. My po, Fit");
            Assert.assertEquals(testCountingPointsBothAfterFitMap.get(pointsInBothHands, pointsOfContractFloat), res);

        }

        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsBothAfterNoFitMap.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res = new ResultsOfOneGame(pointsInBothHands, pointsOfContract, true, false, false, true).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. My po, Fit");
            Assert.assertEquals(testCountingPointsBothAfterNoFitMap.get(pointsInBothHands, pointsOfContractFloat), res);

        }


    }

/*
    @Test
    public void testCountingPoints() throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
        // System.out.println("Dla " + 24 + " pkt: " + ExpectedResultsTable.getInstance().getPoints(24, true, false,true) + " oczekiwane.");

        ResultsOfOneGame a = new ResultsOfOneGame(24, 500, true, false, true, false);
        System.out.println("Wynik gry dla 24PC i ugranych 500 pkt przy założeniach po i Fit jest: " + a.getResults());
        Assert.assertEquals(2, a.getResults());

        ResultsOfOneGame b = new ResultsOfOneGame(24, 300, true, false, true, false);
        System.out.println("Wynik gry dla 24PC i ugranych 300 pkt przy założeniach po i Fit jest: " + b.getResults());
        Assert.assertEquals(-4, b.getResults());

        ResultsOfOneGame c = new ResultsOfOneGame(24, 100, true, false, true, false);
        System.out.println("Wynik gry dla 24PC i ugranych +100 pkt przy założeniach po i Fit jest: " + c.getResults());
        Assert.assertEquals(-8, c.getResults());

        ResultsOfOneGame d = new ResultsOfOneGame(24, -100, true, false, true, false);
        System.out.println("Wynik gry dla 24PC i ugranych -100 pkt przy założeniach po i Fit jest: " + d.getResults());
        Assert.assertEquals(-11, d.getResults());

        ResultsOfOneGame e = new ResultsOfOneGame(24, -500, true, false, true, false);
        System.out.println("Wynik gry dla 24PC i ugranych -100 pkt przy założeniach po i Fit jest: " + e.getResults());
        Assert.assertEquals(-14, e.getResults());


        ResultsOfOneGame f = new ResultsOfOneGame(20, 0, true, false, true, false);
        System.out.println("Wynik gry dla 20PC i ugranych 0 pkt przy założeniach po i my Fit jest: " + f.getResults());
        Assert.assertEquals(-2, f.getResults());

        ResultsOfOneGame g = new ResultsOfOneGame(20, 0, true, false, false, true);
        System.out.println("Wynik gry dla 20PC i ugranych 0 pkt przy założeniach po i oni Fit jest: " + g.getResults());
        Assert.assertEquals(2, g.getResults());


    }
*/

}
