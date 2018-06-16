package pl.waw.rubach.points;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CountingPointsForGameTest {

    private static Logger logger = LoggerFactory.getLogger(CountingPointsForGameTest.class);

    private Map<Integer, Integer> testPointsForMajorContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForMinorContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForNoTrumphContract = new HashMap<>();

    private Map<Integer, Integer> testPointsForMajorDoubleContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForMinorDoubleContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForNoTrumphDoubleContract = new HashMap<>();

    private Map<Integer, Integer> testPointsForMajorReDoubleContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForMinorReDoubleContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForNoTrumphReDoubleContract = new HashMap<>();


    @Before
    public void fillTestPointsMap() {
        //testPointsMap.put()
        testPointsForMajorContract.put(1, 30);
        testPointsForMajorContract.put(2, 60);
        testPointsForMajorContract.put(3, 90);
        testPointsForMajorContract.put(4, 120);
        testPointsForMajorContract.put(5, 150);
        testPointsForMajorContract.put(6, 180);
        testPointsForMajorContract.put(7, 210);

        testPointsForMinorContract.put(1, 20);
        testPointsForMinorContract.put(2, 40);
        testPointsForMinorContract.put(3, 60);
        testPointsForMinorContract.put(4, 80);
        testPointsForMinorContract.put(5, 100);
        testPointsForMinorContract.put(6, 120);
        testPointsForMinorContract.put(7, 140);

        testPointsForNoTrumphContract.put(1, 40);
        testPointsForNoTrumphContract.put(2, 70);
        testPointsForNoTrumphContract.put(3, 100);
        testPointsForNoTrumphContract.put(4, 130);
        testPointsForNoTrumphContract.put(5, 160);
        testPointsForNoTrumphContract.put(6, 190);
        testPointsForNoTrumphContract.put(7, 220);

        //example of double contract

        testPointsForMajorDoubleContract.put(1, 60);
        testPointsForMajorDoubleContract.put(2, 120);
        testPointsForMinorDoubleContract.put(1, 40);
        testPointsForMinorDoubleContract.put(2, 80);
        testPointsForNoTrumphDoubleContract.put(1, 80);
        testPointsForNoTrumphDoubleContract.put(2, 140);

        //example of redouble contract

        testPointsForMajorReDoubleContract.put(1, 120);
        testPointsForMajorReDoubleContract.put(2, 240);
        testPointsForMinorReDoubleContract.put(1, 80);
        testPointsForMinorReDoubleContract.put(2, 160);
        testPointsForNoTrumphReDoubleContract.put(1, 160);
        testPointsForNoTrumphReDoubleContract.put(2, 280);

    }


    @Test
    public void testMajorContractPoints() {
        for (int p : new TreeSet<Integer>(testPointsForMajorContract.keySet())) {
            Integer res = new PointsForContract(p,p,"s",false,false).getCalculatedPointsForContract();
         //   logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContract.get(p), res);
        }
    }

    @Test
    public void testMinorContractPoints() {
        for (int p : new TreeSet<Integer>(testPointsForMinorContract.keySet())) {
            Integer res = new PointsForContract(p,p,"d",false,false).getCalculatedPointsForContract();
       //     logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContract.get(p), res);
        }
    }


    @Test
    public void testNoTrumphContractPoints() {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphContract.keySet())) {
            Integer res = new PointsForContract(p,p,"n",false,false).getCalculatedPointsForContract();
         //   logger.info("Dla kontraktu o wysokości " + p + " w bez atu wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphContract.get(p), res);
        }
    }


    @Test
    public void testMajorDoubleContractPoints() {
        for (int p : new TreeSet<Integer>(testPointsForMajorDoubleContract.keySet())) {
            Integer res = new PointsForContract(p,p,"s",true,false).getCalculatedPointsForContract();
      //      logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym z kontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorDoubleContract.get(p), res);
        }
    }

    @Test
    public void testMinorDoubleContractPoints() {
        for (int p : new TreeSet<Integer>(testPointsForMinorDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p,"d",true,false).getCalculatedPointsForContract();
       //     logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z kotrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorDoubleContract.get(p), res);
        }
    }


    @Test
    public void testNoTrumphDoubleContractPoints() {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p,"n",true,false).getCalculatedPointsForContract();
       //     logger.info("Dla kontraktu o wysokości " + p + " w bez atu z kontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphDoubleContract.get(p), res);
        }
    }

    @Test
    public void testMajorReDoubleContractPoints() {
        for (int p : new TreeSet<Integer>(testPointsForMajorReDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p,"s",false,true).getCalculatedPointsForContract();
           logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym z rekontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorReDoubleContract.get(p), res);
        }
    }

    @Test
    public void testMinorReDoubleContractPoints() {
        for (int p : new TreeSet<Integer>(testPointsForMinorReDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p,"d",false,true).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z rekotrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorReDoubleContract.get(p), res);
        }
    }


    @Test
    public void testNoTrumphReDoubleContractPoints() {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphReDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p,"n",false,true).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu z rekontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphReDoubleContract.get(p), res);
        }
    }
}
