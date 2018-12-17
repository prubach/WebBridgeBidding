package pl.waw.rubach.points;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.points.bridgeExeption.InvalidNumberOfPointsException;
import pl.waw.rubach.points.bridgeExeption.InvalidParameterException;

import java.util.Map;

//todo make copy of this for  one before the second after
public class CalculatedImpPointsForOneDealBeforWePLay {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    protected boolean wePlay = true;
    protected boolean[] assumption = {false, false};
    protected int a = wePlay ? 1 : -1;

    protected MultiKeyMap<Float, Integer> testCountingPointsFitWeMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsFitTheyMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsFitBothMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsNoFitBothMap = new MultiKeyMap<>();


    /*  private MultiKeyMap<MultiKeyMap,Integer> testCountingPointsAssumptionNoFitMap = new MultiKeyMap<>();
    testCountingPointsAssumptionNoFitMap.put(testCountingPointsAssumptionNoFitMap, testCountingPointsAssumptionNoFitMap,11); */


    @Before
    public void fillTestPointsMap() {
        testCountingPointsNoFitBothMap.put(11f, 50f, 10);
        testCountingPointsNoFitBothMap.put(12f, -990f, -11);
        testCountingPointsNoFitBothMap.put(12f, -100f, 7);
        testCountingPointsNoFitBothMap.put(20f, 400f, 9);
        testCountingPointsNoFitBothMap.put(22f, 400f, 8);
        testCountingPointsNoFitBothMap.put(23f, 400f, 7);
        testCountingPointsNoFitBothMap.put(24.5f, 400f, 6);
        testCountingPointsNoFitBothMap.put(25f, 400f, 5);
        testCountingPointsNoFitBothMap.put(28f, 400f, 0);
        testCountingPointsNoFitBothMap.put(28f, 990f, 11);
        testCountingPointsNoFitBothMap.put(28f, 100f, -7);
        testCountingPointsNoFitBothMap.put(29f, -50f, -10);


//todo add more example
        testCountingPointsFitBothMap.put(12f, -100f, 8);
        testCountingPointsFitBothMap.put(28f, 100f, -8);
        testCountingPointsFitBothMap.put(19.5f, -100f, -1);
        testCountingPointsFitBothMap.put(20.5f, 100f, 1);
        testCountingPointsFitBothMap.put(15f, -100f, 6);
        testCountingPointsFitBothMap.put(25f, 100f, -6);

        testCountingPointsFitWeMap.put(11f, 50f, 10);
        testCountingPointsFitWeMap.put(12f, -990f, -11);
        testCountingPointsFitWeMap.put(12f, -100f, 7);
        testCountingPointsFitWeMap.put(20f, -50f, -3);
        testCountingPointsFitWeMap.put(20f, 0f, -2);
        testCountingPointsFitWeMap.put(20f, 50f, 0);
        testCountingPointsFitWeMap.put(20f, 100f, 2);

        testCountingPointsFitTheyMap.put(20f, -50f, 0);
        testCountingPointsFitTheyMap.put(20f, 0f, 2);
        testCountingPointsFitTheyMap.put(20f, 50f, 3);
        testCountingPointsFitTheyMap.put(20f, 100f, 4);
        testCountingPointsFitTheyMap.put(20f, 400f, 10);
        testCountingPointsFitTheyMap.put(22f, 400f, 8);
        testCountingPointsFitTheyMap.put(23f, 400f, 7);
        testCountingPointsFitTheyMap.put(24.5f, 400f, 6);
        testCountingPointsFitTheyMap.put(25f, 400f, 5);
        testCountingPointsFitTheyMap.put(28f, 400f, 0);
        testCountingPointsFitTheyMap.put(28f, 990f, 11);
        testCountingPointsFitTheyMap.put(28f, 100f, -7);
        testCountingPointsFitTheyMap.put(29f, -50f, -10);
    }


    protected String printAssumtion(boolean[] a) {
        return (a[0] && a[1]) ? " Obie Po " : (a[0] ? " My Po, Oni Przed " : (a[1] ? " My Przed, Oni Po " : " Obie Przed "));
    }

    protected String printFit(boolean fitWe, boolean fitThey) {
        return (fitWe && fitThey) ? " Obie Fit " : (fitWe ? " My Fit" : (fitThey ? " Oni Fit " : " Obie bez fitu "));
    }


    protected void testFunction1(MultiKeyMap<Float, Integer> map, int a, boolean wePlay, boolean[] assumption, boolean fitWe, boolean fitThey) throws InvalidNumberOfPointsException, InvalidParameterException {

        String des2 = printFit(fitWe, fitThey);
        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : map.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float contractScoringPointsFloat = entry.getKey().getKey(1);
            int contractScoringPoints = Math.round(contractScoringPointsFloat);
            Integer res = a * new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHands, contractScoringPoints, assumption[0], assumption[1], fitWe, fitThey).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + contractScoringPoints + " wynik jest " + res + " impów. " + printAssumtion(assumption) + des2);
            Assert.assertEquals(map.get(pointsInBothHands, contractScoringPointsFloat), res);
        }
    }

    protected void testFunction2(MultiKeyMap<Float, Integer> map, int a, boolean wePlay, boolean[] assumption, boolean fitWe, boolean fitThey) throws InvalidNumberOfPointsException, InvalidParameterException {

        String des2 = printFit(fitWe, fitThey);
        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : map.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            if (pointsInBothHands != 20 && fitWe && fitThey) {
                float contractScoringPointsFloat = entry.getKey().getKey(1);
                int contractScoringPoints = Math.round(contractScoringPointsFloat);
                Integer res = new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHands, contractScoringPoints, assumption[0], assumption[1], fitWe, fitThey).getResults();
                logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + contractScoringPoints + " wynik jest " + res + " impów. " + printAssumtion(assumption) + des2);
                Assert.assertEquals(map.get(pointsInBothHands, contractScoringPointsFloat), res);
                Integer resT = -new CalculatedImpPointsForOneDeal(!wePlay, pointsInBothHands, contractScoringPoints, assumption[0], assumption[1], fitWe, fitThey).getResults();
                Assert.assertEquals(map.get(pointsInBothHands, contractScoringPointsFloat), resT);
            }
        }

    }


    @Test
    public void testCountingPointsRes() throws InvalidNumberOfPointsException, InvalidParameterException {
        testFunction1(testCountingPointsNoFitBothMap, a, wePlay, assumption, false, false);
        testFunction2(testCountingPointsNoFitBothMap, a, wePlay, assumption, false, false);
    }

    @Test
    public void testCountingPointsFitWeRes() throws InvalidNumberOfPointsException, InvalidParameterException {
        testFunction1(testCountingPointsFitWeMap, a, wePlay, assumption, true, false);
        testFunction2(testCountingPointsFitWeMap, a, wePlay, assumption, true, false);
    }


    @Test
    public void testCountingPointsFitTheyRes() throws InvalidNumberOfPointsException, InvalidParameterException {
        testFunction1(testCountingPointsFitTheyMap, a, wePlay, assumption, false, true);
        testFunction2(testCountingPointsFitTheyMap, a, wePlay, assumption, false, true);
    }

    @Test
    public void testCountingPointsBothFitRes() throws InvalidNumberOfPointsException, InvalidParameterException {
        testFunction1(testCountingPointsFitBothMap, a, wePlay, assumption, true, true);
        testFunction2(testCountingPointsFitBothMap, a, wePlay, assumption, true, true);

    }

//pyt - zamiast tej pustej klasy- lepiej tak
    @Test
    public void testCountingPointsResThey() throws InvalidNumberOfPointsException, InvalidParameterException {
    wePlay = false;
     a = -1;

        testFunction1(testCountingPointsNoFitBothMap, a, wePlay, assumption, false, false);
        testFunction1(testCountingPointsFitWeMap, a, wePlay, assumption, true, false);
        testFunction1(testCountingPointsFitTheyMap, a, wePlay, assumption, false, true);
        testFunction1(testCountingPointsFitBothMap, a, wePlay, assumption, true, true);


    }




}
