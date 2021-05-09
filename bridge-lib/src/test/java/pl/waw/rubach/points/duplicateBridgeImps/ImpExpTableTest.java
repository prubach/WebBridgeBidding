package pl.waw.rubach.points.duplicateBridgeImps;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.points.exceptions.BridgeException;
import pl.waw.rubach.points.exceptions.InvalidNumberOfPointsException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ImpExpTableTest {

  private static final Logger logger = LoggerFactory.getLogger(ImpExpTableTest.class);

  private final Map<Float, Integer> testExpResBeforeFitMap = new HashMap<>();
  private final Map<Float, Integer> testExpResAfterFitMap = new HashMap<>();

  private final Map<Float, Integer> testExpResBeforeNoFitMap = new HashMap<>();
  private final Map<Float, Integer> testExpResAfterNoFitMap = new HashMap<>();


  private final Map<Integer, Integer> testImpPointsMap = new HashMap<>();

  @Before
  public void fillTestPointsMap() {
    //testPointsMap.put()
    testImpPointsMap.put(-20, -1);
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
    testImpPointsMap.put(20000, 24);


    //test expected results if diferent assumption (fit/no fit) without 20 pc - special case!!
    testExpResBeforeFitMap.put(0.5f, -1400);
    testExpResBeforeFitMap.put(10f, -550);
    testExpResBeforeFitMap.put(12f, -460);
    testExpResBeforeFitMap.put(15f, -350);
    testExpResBeforeFitMap.put(16.5f, -215);
    testExpResBeforeFitMap.put(19.5f, -60);
    testExpResBeforeFitMap.put(20.5f, 60);
    testExpResBeforeFitMap.put(25f, 350);
    testExpResBeforeFitMap.put(23.5f, 215);
    testExpResBeforeFitMap.put(30f, 550);
    testExpResBeforeFitMap.put(35f, 1100);
    testExpResBeforeFitMap.put(39.5f, 1400);
    testExpResBeforeFitMap.put(40f, 1400);

    testExpResBeforeNoFitMap.put(0.5f, -1400);
    testExpResBeforeNoFitMap.put(5f, -1000);
    testExpResBeforeNoFitMap.put(10f, -460);
    testExpResBeforeNoFitMap.put(11f, -430);
    testExpResBeforeNoFitMap.put(19.5f, -25);
    testExpResBeforeNoFitMap.put(20.5f, 25);
    testExpResBeforeNoFitMap.put(25f, 200);
    testExpResBeforeNoFitMap.put(23.5f, 120);
    testExpResBeforeNoFitMap.put(30f, 460);
    testExpResBeforeNoFitMap.put(35f, 1000);
    testExpResBeforeNoFitMap.put(39.5f, 1400);
    testExpResBeforeNoFitMap.put(40f, 1400);

    testExpResAfterFitMap.put(10f, -750);
    testExpResAfterFitMap.put(15f, -520);
    testExpResAfterFitMap.put(16.5f, -285);
    testExpResAfterFitMap.put(19.5f, -60);
    testExpResAfterFitMap.put(20.5f, 60);
    testExpResAfterFitMap.put(23.5f, 285);
    testExpResAfterFitMap.put(25f, 520);
    testExpResAfterFitMap.put(23.5f, 285);
    testExpResAfterFitMap.put(30f, 750);
    testExpResAfterFitMap.put(35f, 1650);
    testExpResAfterFitMap.put(39.5f, 2100);
    testExpResAfterFitMap.put(40f, 2100);

    testExpResAfterNoFitMap.put(0.5f, -2100);
    testExpResAfterNoFitMap.put(5f, -1500);
    testExpResAfterNoFitMap.put(10f, -660);
    testExpResAfterNoFitMap.put(19.5f, -25);
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
      if (ImpTable.getInstance().checkInputValue(0, 10000, p)) {
        Integer res = ImpTable.getInstance().getImpPoints(p);
        logger.info("Dla " + p + " pkt: " + res + " impów.");
        Assert.assertEquals(testImpPointsMap.get(p), res);
      }
    }
  }

  @Test
  public void reversTestImpPoints() {
    for (int p : new TreeSet<Integer>(testImpPointsMap.keySet())) {
      if (ImpTable.getInstance().checkInputValue(0, 10000, p)) {
        int imps = testImpPointsMap.get(p);
        int resMin = ImpTable.findingDifferenceFromImp(imps)[0];
        int resMax = ImpTable.findingDifferenceFromImp(imps)[1];
        logger.info("Dla " + imps + " impów różnica punków musi być między " + resMin + " a " + resMax + ".");
        Assert.assertEquals(imps, ImpTable.getInstance().getImpPoints(p));
      }
    }
  }


  @Test
  public void testExpResSpecialCase20() throws BridgeException {
    check(true, true);
    check(false, false);
    check(true, false);
    check(false, true);
  }

  private void check(boolean asWe, boolean asThey) throws BridgeException {
    int p = 20, res1, res2, res3;
    String a = asWe ? "My Po," : "My Przed,";
    String b = asThey ? "Oni Po," : "Oni Przed, ";
    res1 = ExpectedResultsTable.getInstance().getPoints(p, true, false, asWe, asThey);
    logger.info("Dla " + p + " pkt: " + res1 + " oczekiwane. " + a + b + "  My Fit");
    Assert.assertEquals(50, res1);

    res2 = ExpectedResultsTable.getInstance().getPoints(p, false, true, asWe, asThey);
    logger.info("Dla " + p + " pkt: " + res2 + " oczekiwane. " + a + b + "  Oni  Fit");
    Assert.assertEquals(-50, res2);

    res3 = ExpectedResultsTable.getInstance().getPoints(40 - p, false, false, asWe, asThey);
    logger.info("Dla " + p + " pkt: " + res3 + " oczekiwane.  " + a + b + " Obie bez fitu");
    Assert.assertEquals(0, res3);
  }


  @Test
  public void testExpResSHORT() throws InvalidNumberOfPointsException {
    for (float p : new TreeSet<Float>(testExpResBeforeFitMap.keySet())) {
      Integer res = ExpectedResultsTable.getInstance().getPoints(p, true, false);
      //    else res = ExpectedResultsTable.getInstance().getImpPoints(p, true, false);
      logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Przed, Fit");
      Assert.assertEquals(testExpResBeforeFitMap.get(p), res);

    }

    for (float p : new TreeSet<Float>(testExpResAfterFitMap.keySet())) {
      Integer res = ExpectedResultsTable.getInstance().getPoints(p, true, true);
      //    else res = ExpectedResultsTable.getInstance().getImpPoints( p, true, true);
      logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Po, Fit");
      Assert.assertEquals(testExpResAfterFitMap.get(p), res);
    }

    for (float p : new TreeSet<Float>(testExpResBeforeNoFitMap.keySet())) {
      Integer res = ExpectedResultsTable.getInstance().getPoints(p, false, false);
      // else  res = ExpectedResultsTable.getInstance().getImpPoints(p, false, false);
      logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Przed, Bez Fitu");
      Assert.assertEquals(testExpResBeforeNoFitMap.get(p), res);
    }

    for (float p : new TreeSet<Float>(testExpResAfterNoFitMap.keySet())) {
      Integer res = ExpectedResultsTable.getInstance().getPoints(p, false, true);
      //  else  res =ExpectedResultsTable.getInstance().getImpPoints(p, false, true);
      logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Po, Bez Fitu");
      Assert.assertEquals(testExpResAfterNoFitMap.get(p), res);
    }


  }


  @Test
  public void testExpResFit() throws BridgeException {
    //p !=20 because for this case is not possible have both fit in major color - if so count only spades and hearst fit mark as no fit

    for (float p : new TreeSet<Float>(testExpResBeforeFitMap.keySet())) {
      if (p != 20) {
        Integer res, res2;
        res = ExpectedResultsTable.getInstance().getPoints(p, true, true, false, false);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie Przed, Obie Fit");
        Assert.assertEquals(testExpResBeforeFitMap.get(p), res);
        res2 = -ExpectedResultsTable.getInstance().getPoints(40 - p, true, true, false, false);
        Assert.assertEquals(testExpResBeforeFitMap.get(p), res2);
      }
      //       }
      //  }
//    @Test
//    public void testExpResFit1() throws InvalidNumberOfPointsException, InvalidParameterException {
      //    for (float p : new TreeSet<Float>(testExpResBeforeFitMap.keySet())) {
      Integer res;
      if (p != 20) {
        if (p > 20) {
          res = ExpectedResultsTable.getInstance().getPoints(p, true, true, false, true);
          logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Przed, Oni Po,  Obie Fit");
        }
        //Mamy mniej punktów czyli trzeba sprawdzić dla nich - wynik będzie dla nich czyli jes odwrócony
        else {
          res = ExpectedResultsTable.getInstance().getPoints(p, true, true, true, false);
          logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Po, Oni Przed,  Obie Fit");
        }
        Assert.assertEquals(testExpResBeforeFitMap.get(p), res);
      }
    }
//    }
//    @Test
//    public void testExpResFit2() throws InvalidNumberOfPointsException, InvalidParameterException {


    for (float p : new TreeSet<Float>(testExpResAfterFitMap.keySet())) {
      if (p != 20) {
        Integer res;
        if (p > 20) {
          res = ExpectedResultsTable.getInstance().getPoints(p, true, true, true, false);
          logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Po, Oni Przed, Obie  Fit");
        } else {
          res = ExpectedResultsTable.getInstance().getPoints(p, true, true, false, true);
          logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Przed Oni Po, Obie Fit");
          Assert.assertEquals(testExpResAfterFitMap.get(p), res);
        }
      }
//        }
//    }
//    @Test
//    public void testExpResFit3() throws InvalidNumberOfPointsException, InvalidParameterException {
      //       for (float p : new TreeSet<Float>(testExpResAfterFitMap.keySet())) {
      if (p != 20) {
        Integer res1, res2;
        res1 = ExpectedResultsTable.getInstance().getPoints(p, true, true, true, true);
        logger.info("Dla " + p + " pkt: " + res1 + " oczekiwane. Obie  Po, Obie  Fit");
        Assert.assertEquals(testExpResAfterFitMap.get(p), res1);
        res2 = -ExpectedResultsTable.getInstance().getPoints(40 - p, true, true, true, true);
        Assert.assertEquals(testExpResAfterFitMap.get(p), res2);
      }
    }
  }

  @Test
  public void testExpResNoFit() throws BridgeException {
    for (float p : new TreeSet<Float>(testExpResBeforeNoFitMap.keySet())) {
      Integer res;
      if (p >= 20) {
        res = ExpectedResultsTable.getInstance().getPoints(p, false, false, false, true);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Przed, Oni Po, Bez Fitu");
      } else {
        res = ExpectedResultsTable.getInstance().getPoints(p, false, false, true, false);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Po, Oni Przed, Bez Fitu");
      }
      Assert.assertEquals(testExpResBeforeNoFitMap.get(p), res);

      Integer res1, res2;
      res1 = ExpectedResultsTable.getInstance().getPoints(p, false, false, false, false);
      logger.info("Dla " + p + " pkt: " + res1 + " oczekiwane. Obie Przed, Bez Fitu");
      Assert.assertEquals(testExpResBeforeNoFitMap.get(p), res1);
      res2 = -ExpectedResultsTable.getInstance().getPoints(40 - p, false, false, false, false);
      Assert.assertEquals(testExpResBeforeNoFitMap.get(p), res2);


    }

    for (float p : new TreeSet<Float>(testExpResAfterNoFitMap.keySet())) {
      Integer res;
      if (p >= 20) {
        res = ExpectedResultsTable.getInstance().getPoints(p, false, false, true, false);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Po, Oni Przed, Bez Fitu");
      } else {
        res = ExpectedResultsTable.getInstance().getPoints(p, false, false, false, true);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Przed, Oni Po, Bez Fitu");
      }
      Assert.assertEquals(testExpResAfterNoFitMap.get(p), res);

      Integer res1, res2;
      res1 = ExpectedResultsTable.getInstance().getPoints(p, false, false, true, true);
      logger.info("Dla " + p + " pkt: " + res1 + " oczekiwane. Obie  Po, Bez Fitu");
      Assert.assertEquals(testExpResAfterNoFitMap.get(p), res1);
      res2 = -ExpectedResultsTable.getInstance().getPoints(40 - p, false, false, true, true);
      Assert.assertEquals(testExpResAfterNoFitMap.get(p), res2);
    }


  }

  @Test
  public void testExpResOnlyOneFitBoth() throws BridgeException {


    for (float p : new TreeSet<Float>(testExpResAfterFitMap.keySet())) {
      Integer res;
      if (p >= 20) {
        res = ExpectedResultsTable.getInstance().getPoints(p, true, false, true, true);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie Po, My Fit");
      } else {
        res = ExpectedResultsTable.getInstance().getPoints(p, false, true, true, true);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie Po,  Oni Fit");
      }
      Assert.assertEquals(testExpResAfterFitMap.get(p), res);
    }


    for (float p : new TreeSet<Float>(testExpResAfterNoFitMap.keySet())) {
      Integer res;
      if (p >= 20) {
        res = ExpectedResultsTable.getInstance().getPoints(p, false, true, true, true);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie Po, Oni Fit");
      } else {
        res = ExpectedResultsTable.getInstance().getPoints(p, true, false, true, true);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie Po,  My Fit");
      }
      Assert.assertEquals(testExpResAfterNoFitMap.get(p), res);
    }


    for (float p : new TreeSet<Float>(testExpResBeforeFitMap.keySet())) {
      Integer res;
      if (p >= 20) {
        res = ExpectedResultsTable.getInstance().getPoints(p, true, false, false, false);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie Przed, My Fit");
      } else {
        res = ExpectedResultsTable.getInstance().getPoints(p, false, true, false, false);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie Przed,  Oni Fit");
      }
      Assert.assertEquals(testExpResBeforeFitMap.get(p), res);
    }


    for (float p : new TreeSet<Float>(testExpResBeforeNoFitMap.keySet())) {
      Integer res;
      if (p >= 20) {
        res = ExpectedResultsTable.getInstance().getPoints(p, false, true, false, false);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie Przed, Oni Fit");
      } else {
        res = ExpectedResultsTable.getInstance().getPoints(p, true, false, false, false);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Obie Przed,  My Fit");
      }
      Assert.assertEquals(testExpResBeforeNoFitMap.get(p), res);
    }
  }


  @Test
  public void testExpResOnlyOneFitMix() throws BridgeException {
    for (float p : new TreeSet<Float>(testExpResBeforeFitMap.keySet())) {
      Integer res;
      if (p >= 20) {
        res = ExpectedResultsTable.getInstance().getPoints(p, true, false, false, true);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Przed, Oni Po, My Fit");
      } else {
        res = ExpectedResultsTable.getInstance().getPoints(p, false, true, true, false);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Oni Przed, My Po,  Oni Fit");
      }
      Assert.assertEquals(testExpResBeforeFitMap.get(p), res);
    }


    for (float p : new TreeSet<Float>(testExpResBeforeNoFitMap.keySet())) {
      Integer res;
      if (p >= 20) {
        res = ExpectedResultsTable.getInstance().getPoints(p, false, true, false, true);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Przed, Oni Po, Oni Fit");
      } else {
        res = ExpectedResultsTable.getInstance().getPoints(p, true, false, true, false);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Oni Przed, My Po,  My Fit");
      }
      Assert.assertEquals(testExpResBeforeNoFitMap.get(p), res);
    }

    for (float p : new TreeSet<Float>(testExpResAfterFitMap.keySet())) {
      Integer res;
      if (p >= 20) {
        res = ExpectedResultsTable.getInstance().getPoints(p, true, false, true, false);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Po, Oni Przed, My Fit");
      } else {
        res = ExpectedResultsTable.getInstance().getPoints(p, false, true, false, true);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Oni Po, My Przed,  Oni Fit");
      }
      Assert.assertEquals(testExpResAfterFitMap.get(p), res);
    }


    for (float p : new TreeSet<Float>(testExpResAfterNoFitMap.keySet())) {
      Integer res;
      if (p >= 20) {
        res = ExpectedResultsTable.getInstance().getPoints(p, false, true, true, false);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. My Po,Oni Przed, Oni Fit");
      } else {
        res = ExpectedResultsTable.getInstance().getPoints(p, true, false, false, true);
        logger.info("Dla " + p + " pkt: " + res + " oczekiwane. Oni Po, My Przed,  My Fit");
      }
      Assert.assertEquals(testExpResAfterNoFitMap.get(p), res);
    }


  }


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
            System.out.println("Dla " + p + " pkt: " + ImpTable.getInstance().getImpPoints(p) + "impów.");
        }

        int[] testPoints1 = new int[]{10, 15, 20, 25, 30, 35};
        for (int p : testPoints1) {
            System.out.println("Dla " + p + " pkt: " + ExpectedResultsTable.getInstance().getImpPoints(p, true, false, true) + " oczekiwane.");
        }

    }
*/

}
