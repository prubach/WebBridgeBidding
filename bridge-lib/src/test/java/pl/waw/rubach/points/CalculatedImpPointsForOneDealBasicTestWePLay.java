package pl.waw.rubach.points;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.points.bridgeExeption.InvalidNumberOfPointsException;
import pl.waw.rubach.points.bridgeExeption.PointsDiferentLessThenZeroException;

import java.util.Map;

public class CalculatedImpPointsForOneDealBasicTestWePLay {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
    protected boolean whoPlay=true;

    protected MultiKeyMap<Float, Integer> testCountingPointsBothAfterFitWeMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsBothAfterFitTheyMap = new MultiKeyMap<>();

    protected MultiKeyMap<Float, Integer> testCountingPointsBothAfterFitBoth = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsBothAfterNoFitBothMap = new MultiKeyMap<>();


    protected MultiKeyMap<Float, Integer> testCountingPointsBothBeforNoFitMap = new MultiKeyMap<>();
  //
    //

    /*  private MultiKeyMap<MultiKeyMap,Integer> testCountingPointsAssumptionNoFitMap = new MultiKeyMap<>();
    testCountingPointsAssumptionNoFitMap.put(testCountingPointsAssumptionNoFitMap, testCountingPointsAssumptionNoFitMap,11); */


    @Before
    public void fillTestPointsMap() {
//todo make copy of this for both before :) and one before the second after
        testCountingPointsBothBeforNoFitMap.put(28f, 990f, 11);
        testCountingPointsBothBeforNoFitMap.put(29f, -50f, -10);
        testCountingPointsBothBeforNoFitMap.put(11f, 50f, 10);

        testCountingPointsBothBeforNoFitMap.put(12f, -990f, -11);
        testCountingPointsBothBeforNoFitMap.put(20f, 400f, 9);
        testCountingPointsBothBeforNoFitMap.put(22f, 400f, 8);
        testCountingPointsBothBeforNoFitMap.put(23f, 400f, 7);
        testCountingPointsBothBeforNoFitMap.put(28f, 400f, 0);



        //Fit only we - they no fit
        testCountingPointsBothAfterFitWeMap.put(20f, 0f, -2);

        testCountingPointsBothAfterFitWeMap.put(24f, 500f, 2);
        testCountingPointsBothAfterFitWeMap.put(24f, 300f, -4);
        testCountingPointsBothAfterFitWeMap.put(24f, 100f, -8);
        testCountingPointsBothAfterFitWeMap.put(24f, -100f, -11);
        testCountingPointsBothAfterFitWeMap.put(24f, -500f, -14);

        testCountingPointsBothAfterFitWeMap.put(30f, 100f, -12);
        testCountingPointsBothAfterFitWeMap.put(12f, -1660f, -14);
        testCountingPointsBothAfterFitWeMap.put(28f, 1660f, 14);

        testCountingPointsBothAfterFitWeMap.put(28f, 1660f, 14);
        testCountingPointsBothAfterFitWeMap.put(12f, -1660f, -14);

        //both fit
      //  testCountingPointsBothAfterFitBoth.put(20f, 0f, 2);  //not such case if both have 20PC and fit in major color mark only spades (one have fit)
      //  testCountingPointsBothAfterFitBoth.put(24f, -100f, -6);
      //  testCountingPointsBothAfterFitBoth.put(16f, 100f, 6);

        testCountingPointsBothAfterFitBoth.put(30f, 750f, 0);
        testCountingPointsBothAfterFitBoth.put(10f, -750f, 0);
        testCountingPointsBothAfterFitBoth.put(30f, 1250f, 11);
        testCountingPointsBothAfterFitBoth.put(10f, -1250f, -11);

        //both no fit
        testCountingPointsBothAfterNoFitBothMap.put(20f, 0f, 0);

        testCountingPointsBothAfterNoFitBothMap.put(10f, -660f, 0);
        testCountingPointsBothAfterNoFitBothMap.put(10f, -100f, 11);
        testCountingPointsBothAfterNoFitBothMap.put(30f, 660f, 0);
        testCountingPointsBothAfterNoFitBothMap.put(30f, 100f, -11);

        //Fit only they  - we not fit
        testCountingPointsBothAfterFitTheyMap.put(20f, 0f, 2);

        testCountingPointsBothAfterFitTheyMap.put(16f, -500f, -2);
        testCountingPointsBothAfterFitTheyMap.put(16f, -300f, 4);
        testCountingPointsBothAfterFitTheyMap.put(16f, -100f, 8);
        testCountingPointsBothAfterFitTheyMap.put(16f, 100f, 11);
        testCountingPointsBothAfterFitTheyMap.put(16f, 500f, 14);

        testCountingPointsBothAfterFitTheyMap.put(10f, -100f, 12);
        testCountingPointsBothAfterFitTheyMap.put(28f, 1660f, 14);
        testCountingPointsBothAfterFitTheyMap.put(12f, -1660f, -14);

        testCountingPointsBothAfterFitTheyMap.put(28f, -1660f, -20);
        testCountingPointsBothAfterFitTheyMap.put(12f, 1660f, 20);
    }


    @Test
    public void testCountingPointsBothBeforeRes() throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsBothBeforNoFitMap.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res = new CalculatedImpPointsForOneDeal(whoPlay, pointsInBothHands, pointsOfContract, false, false, false, false).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. Obie przed, obie bez fitu");
            Assert.assertEquals(testCountingPointsBothBeforNoFitMap.get(pointsInBothHands, pointsOfContractFloat), res);

        }


    }


    @Test
    public void testCountingPointsBothAfterFitWeRes() throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {


        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsBothAfterFitWeMap.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res = new CalculatedImpPointsForOneDeal(whoPlay,pointsInBothHands, pointsOfContract, true, true, true, false).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. Obie po, My Fit");
            Assert.assertEquals(testCountingPointsBothAfterFitWeMap.get(pointsInBothHands, pointsOfContractFloat), res);

        }
    }

    @Test
    public void testCountingPointsBothAfterFitTheyRes() throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {


        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsBothAfterFitTheyMap.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res = new CalculatedImpPointsForOneDeal(whoPlay,pointsInBothHands, pointsOfContract, true, true, false, true).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. Obie po, Oni Fit");
            Assert.assertEquals(testCountingPointsBothAfterFitTheyMap.get(pointsInBothHands, pointsOfContractFloat), res);

        }
    }

    @Test
    public void testCountingPointsBothAfterBothFitRes() throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {


        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsBothAfterFitBoth.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res = new CalculatedImpPointsForOneDeal(whoPlay, pointsInBothHands, pointsOfContract, true, true, true, true).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. Obie po, My Fit");
            Assert.assertEquals(testCountingPointsBothAfterFitBoth.get(pointsInBothHands, pointsOfContractFloat), res);

        }
    }

    @Test
    public void testCountingPointsBothNoFitAfterRes() throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsBothAfterNoFitBothMap.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res = new CalculatedImpPointsForOneDeal(whoPlay, pointsInBothHands, pointsOfContract, true, true, false, false).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. Obie po, Obe bez fitu");
            Assert.assertEquals(testCountingPointsBothAfterNoFitBothMap.get(pointsInBothHands, pointsOfContractFloat), res);

        }


    }


}
