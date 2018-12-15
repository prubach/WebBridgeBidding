package pl.waw.rubach.points;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.points.bridgeExeption.InvalidNumberOfPointsException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ImpExpTableTest {

    private static Logger logger = LoggerFactory.getLogger(ImpExpTableTest.class);

    private Map<Float, Integer> testExpResBeforeFitMap = new HashMap<>();
    private Map<Float, Integer> testExpResAfterFitMap = new HashMap<>();

    private Map<Float, Integer> testExpResBeforeNoFitMap = new HashMap<>();
    private Map<Float, Integer> testExpResAfterNoFitMap = new HashMap<>();


    private Map<Integer, Integer> testImpPointsMap = new HashMap<>();

    @Before
    public void fillTestPointsMap() {
        //testPointsMap.put()
        //  testImpPointsMap.put(-1, 0);
        testImpPointsMap.put(0, 0);
        testImpPointsMap.put(20, 1);
        testImpPointsMap.put(25, 1);
        testImpPointsMap.put(50, 2);
        testImpPointsMap.put(120, 3);
        testImpPointsMap.put(450, 10);
        testImpPointsMap.put(1090, 14);
        testImpPointsMap.put(1200, 15);
        testImpPointsMap.put(2000, 19);
        testImpPointsMap.put(6000, 24);
        testImpPointsMap.put(10000, 24);
        testImpPointsMap.put(430 + 50, 10);
        // testImpPointsMap.put(20000, 24);


//test expected results if diferent assumption (fit/no fit)
  //      testExpResBeforeFitMap.put(0.5f, -1400);
        testExpResBeforeFitMap.put(10f, -550);
        testExpResBeforeFitMap.put(12f, -460);
        testExpResBeforeFitMap.put(15f, -350);
        testExpResBeforeFitMap.put(16.5f, -215);
        testExpResBeforeFitMap.put(19.5f, -60);
        testExpResBeforeFitMap.put(20f, 50);
        testExpResBeforeFitMap.put(20.5f, 60);
        testExpResBeforeFitMap.put(25f, 350);
        testExpResBeforeFitMap.put(23.5f, 215);
        testExpResBeforeFitMap.put(30f, 550);
        testExpResBeforeFitMap.put(35f, 1100);
        testExpResBeforeFitMap.put(39.5f, 1400);
        testExpResBeforeFitMap.put(40f, 1400);

        testExpResBeforeNoFitMap.put(11f, -430);
        testExpResBeforeNoFitMap.put(20f, 0);
        testExpResBeforeNoFitMap.put(20.5f, 25);
        testExpResBeforeNoFitMap.put(25f, 200);
        testExpResBeforeNoFitMap.put(23.5f, 120);
        testExpResBeforeNoFitMap.put(30f, 460);
        testExpResBeforeNoFitMap.put(35f, 1000);
        testExpResBeforeNoFitMap.put(39.5f, 1400);

        testExpResAfterFitMap.put(10f, -750);
        testExpResAfterFitMap.put(15f, -520);
        testExpResAfterFitMap.put(16.5f, -285);
        testExpResAfterFitMap.put(19.5f, -60);
        testExpResAfterFitMap.put(20f, 50);
        testExpResAfterFitMap.put(20.5f, 60);
        testExpResAfterFitMap.put(25f, 520);
        testExpResAfterFitMap.put(23.5f, 285);
        testExpResAfterFitMap.put(30f, 750);
        testExpResAfterFitMap.put(35f, 1650);
        testExpResAfterFitMap.put(39.5f, 2100);
        testExpResAfterFitMap.put(40f, 2100);

        testExpResAfterNoFitMap.put(10f, -660);
        testExpResAfterNoFitMap.put(20f, 0);
        testExpResAfterNoFitMap.put(20.5f, 25);
        testExpResAfterNoFitMap.put(25f, 290);
        testExpResAfterNoFitMap.put(23.5f, 120);
        testExpResAfterNoFitMap.put(30f, 660);
        testExpResAfterNoFitMap.put(35f, 1500);
        testExpResAfterNoFitMap.put(39.5f, 2100);
        testExpResAfterNoFitMap.put(40f, 2100);

    }

    @Test
    public void testImpPoints() {
        for (int p : new TreeSet<Integer>(testImpPointsMap.keySet())) {
            //   if(ImpTable.getInstance().checkImputValue(0,10000,p)){
            Integer res = ImpTable.getInstance().getPoints(p);
            logger.info("Dla " + p + " pkt: " + res + " impów.");
            Assert.assertEquals(testImpPointsMap.get(p), res);
        }
        //  }
    }


    @Test
    public void testExpResSHORT() throws InvalidNumberOfPointsException {
        for (float p : new TreeSet<Float>(testExpResBeforeFitMap.keySet())) {
            Integer res = ExpectedResultsTable.getInstance().getPoints(p, true, false);
        //    else res = ExpectedResultsTable.getInstance().getPoints(p, true, false);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Przed, Fit");
            Assert.assertEquals(testExpResBeforeFitMap.get(p), res);

        }

        for (float p : new TreeSet<Float>(testExpResAfterFitMap.keySet())) {
            Integer res = ExpectedResultsTable.getInstance().getPoints(p, true, true);
        //    else res = ExpectedResultsTable.getInstance().getPoints( p, true, true);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Po, Fit");
            Assert.assertEquals(testExpResAfterFitMap.get(p), res);
        }

        for (float p : new TreeSet<Float>(testExpResBeforeNoFitMap.keySet())) {
            Integer  res = ExpectedResultsTable.getInstance().getPoints(p, false, false);
            // else  res = ExpectedResultsTable.getInstance().getPoints(p, false, false);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Przed, Bez Fitu");
            Assert.assertEquals(testExpResBeforeNoFitMap.get(p), res);
        }

        for (float p : new TreeSet<Float>(testExpResAfterNoFitMap.keySet())) {
            Integer res = ExpectedResultsTable.getInstance().getPoints(p, false, true);
            //  else  res =ExpectedResultsTable.getInstance().getPoints(p, false, true);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Po, Bez Fitu");
            Assert.assertEquals(testExpResAfterNoFitMap.get(p), res);
        }


    }



    @Test
    public void testExpResFit() throws InvalidNumberOfPointsException {
        for (float p : new TreeSet<Float>(testExpResBeforeFitMap.keySet())) {
            Integer res;
            res = ExpectedResultsTable.getInstance().getPoints(p, true, true, false,false);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie Przed, Obie Fit");
            Assert.assertEquals(testExpResBeforeFitMap.get(p), res);

        }

        for (float p : new TreeSet<Float>(testExpResBeforeFitMap.keySet())) {
            Integer res;
            if(p>=20) {res = ExpectedResultsTable.getInstance().getPoints(p, true, true, false,true);
                logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Przed, Oni Po,  Obie Fit");
            }
            //Mamy mniej punktów czyli trzeba sprawdzić dla nich - wynik będzie dla nich czyli jes odwrócony
            else {res = ExpectedResultsTable.getInstance().getPoints(p, true, true,true, false);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Po, Oni Przed,  Obie Fit");}
            Assert.assertEquals(testExpResBeforeFitMap.get(p), res);

        }


        for (float p : new TreeSet<Float>(testExpResAfterFitMap.keySet())) {
            Integer res;
            if(p>=20) {
                res = ExpectedResultsTable.getInstance().getPoints(p, true, true, true, false);
                logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Po, Oni Przed, Obie  Fit");
            }
                else {res = ExpectedResultsTable.getInstance().getPoints( p, true, true, false, true );
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Przed Oni Po, Obie Fit");
            Assert.assertEquals(testExpResAfterFitMap.get(p), res);
        }}

        for (float p : new TreeSet<Float>(testExpResBeforeFitMap.keySet())) {
            Integer res;
            res = ExpectedResultsTable.getInstance().getPoints(p, true, true, false,false);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie  Przed, Obie  Fit");
            Assert.assertEquals(testExpResBeforeFitMap.get(p), res);

        }
    }

    @Test
    public void testExpResNoFit() throws InvalidNumberOfPointsException {
        for (float p : new TreeSet<Float>(testExpResBeforeNoFitMap.keySet())) {
            Integer  res;
            res = ExpectedResultsTable.getInstance().getPoints(p, false, false, false, false);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie Przed, Bez Fitu");
            Assert.assertEquals(testExpResBeforeNoFitMap.get(p), res);
        }

        for (float p : new TreeSet<Float>(testExpResAfterNoFitMap.keySet())) {
            Integer res;
            if(p>=20) {
                res = ExpectedResultsTable.getInstance().getPoints(p, false, false, true, false);
                logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Po, Oni Przed, Bez Fitu");
            }  else  {res =ExpectedResultsTable.getInstance().getPoints(p, false, false , false,true);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Przed, Oni Po, Bez Fitu");}
            Assert.assertEquals(testExpResAfterNoFitMap.get(p), res);
        }

        for (float p : new TreeSet<Float>(testExpResBeforeNoFitMap.keySet())) {
            Integer  res;
            if(p>=20) {res = ExpectedResultsTable.getInstance().getPoints(p, false, false, false, true);
                logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Przed, Oni Po, Bez Fitu");}
            else  {res = ExpectedResultsTable.getInstance().getPoints(p, false,  false, true,false);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Po, Oni Przed, Bez Fitu");}
            Assert.assertEquals(testExpResBeforeNoFitMap.get(p), res);
        }

        for (float p : new TreeSet<Float>(testExpResAfterNoFitMap.keySet())) {
            Integer res;
            res = ExpectedResultsTable.getInstance().getPoints(p, false, false, true, true);
            logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie  Po, Bez Fitu");
            Assert.assertEquals(testExpResAfterNoFitMap.get(p), res);
        }


    }

    @Test
    public void testExpResFitOLD() throws InvalidNumberOfPointsException {
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

        //for they play (points is 40 -p and results is minus - excluding 20 pc because it is previour case)
        for (float p : new TreeSet<Float>(testExpResBeforeFitMap.keySet())) {
            if (p != 20) {
                Integer res = -ExpectedResultsTable.getInstance().getPoints(40 - p, true, false, false);
                logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Przed, Fit");
                Assert.assertEquals(testExpResBeforeFitMap.get(p), res);
            }
        }
        for (float p : new TreeSet<Float>(testExpResAfterFitMap.keySet())) {
            if (p != 20) {
                Integer res = -ExpectedResultsTable.getInstance().getPoints(40 - p, true, true, true);
                logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Po, Fit");
                Assert.assertEquals(testExpResAfterFitMap.get(p), res);
            }
        }
    }

    @Test
    public void testExpResNoFitOLD() throws InvalidNumberOfPointsException {
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
        //for they play (points is 40 -p and results is minus - excluding 20 pc because it is previour case)
        for (float p : new TreeSet<Float>(testExpResBeforeNoFitMap.keySet())) {
            if (p != 20) {
                Integer res = -ExpectedResultsTable.getInstance().getPoints(40 - p, false, false, false);
                logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Przed, Bez Fitu");
                Assert.assertEquals(testExpResBeforeNoFitMap.get(p), res);
            }
        }

        for (float p : new TreeSet<Float>(testExpResAfterNoFitMap.keySet())) {
            if (p != 20) {
                Integer res = -ExpectedResultsTable.getInstance().getPoints(40 - p, false, true, true);
                logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Po, Bez Fitu");
                Assert.assertEquals(testExpResAfterNoFitMap.get(p), res);
            }

        }}
/*
    @Test
    public void printExpResTables() {
        logger.info(ExpectedResultsTable.getTableAsString(false, false));
        logger.info(ExpectedResultsTable.getTableAsString(false, true));
        logger.info(ExpectedResultsTable.getTableAsString(true, false));
        logger.info(ExpectedResultsTable.getTableAsString(true, true));
    }


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

}
