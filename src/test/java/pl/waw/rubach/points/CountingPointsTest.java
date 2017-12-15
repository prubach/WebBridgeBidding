package pl.waw.rubach.points;

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

    private Map<Float,Integer> testExpResBeforeFitMap = new HashMap<>();
    private Map<Float,Integer> testExpResAfterFitMap = new HashMap<>();

    private Map<Integer,Integer> testImpPointsMap = new HashMap<>();

    @Before
    public void fillTestPointsMap() {
        //testPointsMap.put()
        testImpPointsMap.put(50, 2);
        testImpPointsMap.put(120, 3);
        testImpPointsMap.put(450, 10);
        testImpPointsMap.put(1090, 14);
        testImpPointsMap.put(1200, 15);
        testImpPointsMap.put(2000, 19);

        testExpResBeforeFitMap.put(20f, 50);
        testExpResBeforeFitMap.put(20.5f, 60);
        testExpResBeforeFitMap.put(25f, 350);
        testExpResBeforeFitMap.put(23.5f, 215);
        testExpResBeforeFitMap.put(30f, 550);
        testExpResBeforeFitMap.put(35f, 1100);

        testExpResAfterFitMap.put(20f, 50);
        testExpResAfterFitMap.put(20.5f, 60);
        testExpResAfterFitMap.put(25f, 520);
        testExpResAfterFitMap.put(23.5f, 285);
        testExpResAfterFitMap.put(30f, 750);
        testExpResAfterFitMap.put(35f, 1650);

    }
    @Test
    public void testImpPoints() {
        for (int p : new TreeSet<Integer>(testImpPointsMap.keySet())) {
            Integer res = ImpTable.getInstance().getPoints(p);
            logger.info("Dla " + p + " pkt: " + res + " impów.");
            Assert.assertEquals(res, testImpPointsMap.get(p));
        }
    }

    @Test
    public void testExpRes() {
        for (float p : new TreeSet<Float>(testExpResBeforeFitMap.keySet())) {
            Integer res = ExpectedResultsTable.getInstance().getPoints(p, true,false);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Przed, Fit");
            Assert.assertEquals(res, testExpResBeforeFitMap.get(p));
        }
        for (float p : new TreeSet<Float>(testExpResAfterFitMap.keySet())) {
            Integer res = ExpectedResultsTable.getInstance().getPoints(p, true,true);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Po, Fit");
            Assert.assertEquals(res, testExpResAfterFitMap.get(p));
        }
    }

    @Test
    public void testCountingPoints() {
        ResultsOfOneGame a = new ResultsOfOneGame(24,100,true,true);
        System.out.println(a.getResults());
        System.out.println(ExpectedResultsTable.getInstance().getPoints(30,true,true));

        int[] testPoints = new int[]{50, 120, 450, 1090, 1200};
        for (int p : testPoints) {
            System.out.println("Dla " + p + " pkt: " + ImpTable.getInstance().getPoints(p)+ "impów.");
        }

        int[] testPoints1 = new int[]{20, 25, 30, 35};
        for (int p : testPoints1) {
            System.out.println("Dla " + p + " pkt: " + ExpectedResultsTable.getInstance().getPoints(p, true,true)+" oczekiwane.");
        }
    }

    @Test
    public void printExpResTables() {
        logger.info(ExpectedResultsTable.getTableAsString(false, false));
        logger.info(ExpectedResultsTable.getTableAsString(false, true));
        logger.info(ExpectedResultsTable.getTableAsString(true, false));
        logger.info(ExpectedResultsTable.getTableAsString(true, true));
    }
}
