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

public class CalculatedImpPointsForOneDealBeforeWePLay {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
    protected boolean whoPlay=true;
    protected boolean[] assumption = {false, false};
    protected int a = whoPlay ? 1 :-1;

    protected MultiKeyMap<Float, Integer> testCountingPointsFitWeMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsFitTheyMap = new MultiKeyMap<>();

    protected MultiKeyMap<Float, Integer> testCountingPointsFitBothMap = new MultiKeyMap<>();


    protected MultiKeyMap<Float, Integer> testCountingPointsNoFitBothMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsBothBeforFitBothMap = new MultiKeyMap<>();
  //
    //

    /*  private MultiKeyMap<MultiKeyMap,Integer> testCountingPointsAssumptionNoFitMap = new MultiKeyMap<>();
    testCountingPointsAssumptionNoFitMap.put(testCountingPointsAssumptionNoFitMap, testCountingPointsAssumptionNoFitMap,11); */


    @Before
    public void fillTestPointsMap() {
//todo make copy of this for  one before the second after
        testCountingPointsNoFitBothMap.put(28f, 990f, 11);
        testCountingPointsNoFitBothMap.put(28f, 100f, -7);
        testCountingPointsNoFitBothMap.put(29f, -50f, -10);
        testCountingPointsNoFitBothMap.put(11f, 50f, 10);
        testCountingPointsNoFitBothMap.put(12f, -990f, -11);
        testCountingPointsNoFitBothMap.put(12f, -100f, 7);
        testCountingPointsNoFitBothMap.put(20f, 400f, 9);
        testCountingPointsNoFitBothMap.put(22f, 400f, 8);
        testCountingPointsNoFitBothMap.put(23f, 400f, 7);
        testCountingPointsNoFitBothMap.put(24.5f, 400f, 6);
        testCountingPointsNoFitBothMap.put(25f, 400f, 5);
        testCountingPointsNoFitBothMap.put(28f, 400f, 0);


        testCountingPointsBothBeforFitBothMap.put(12f,-100f,8);
        testCountingPointsBothBeforFitBothMap.put(28f,100f,-8);




    }
    protected String printAssumtion(boolean[] a) {
        String des;
        if (a[0] && a[1]) des =" Obie Po, ";
        else if( a[0] && !a[1]) des = " My Po, Oni Przed, ";
        else if( !a[0] && a[1]) des = " My Przed, Oni Po, ";
        else if( !a[0] && !a[1]) des = " Obie Przed, ";
        else des = " Jakiś błąd" ;
        return des;
    }



    @Test
    public void testCountingPointsRes() throws InvalidNumberOfPointsException, InvalidParameterException {

        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsNoFitBothMap.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res = a* new CalculatedImpPointsForOneDeal(whoPlay, pointsInBothHands, pointsOfContract,  assumption[0],  assumption[1], false, false).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. " + printAssumtion(assumption) +", Obie bez fitu");
            Assert.assertEquals(testCountingPointsNoFitBothMap.get(pointsInBothHands, pointsOfContractFloat), res);
        }
    }

    @Test
    public void testCountingPointsFitWeRes() throws InvalidNumberOfPointsException, InvalidParameterException {


        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsFitWeMap.entrySet()) {
            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res =a* new CalculatedImpPointsForOneDeal(whoPlay,pointsInBothHands, pointsOfContract, assumption[0],  assumption[1], true, false).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest -" + res + " impów. " + printAssumtion(assumption) +",My Fit");
            Assert.assertEquals(testCountingPointsFitWeMap.get(pointsInBothHands, pointsOfContractFloat), res);
        }
    }


    @Test
    public void testCountingPointsFitTheyRes() throws InvalidNumberOfPointsException, InvalidParameterException {

        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsFitTheyMap.entrySet()) {
            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res = a* new CalculatedImpPointsForOneDeal(whoPlay,pointsInBothHands, pointsOfContract,  assumption[0],  assumption[1], false, true).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. " + printAssumtion(assumption) +", Oni Fit");
            Assert.assertEquals(testCountingPointsFitTheyMap.get(pointsInBothHands, pointsOfContractFloat), res);
        }
    }

    @Test
    public void testCountingPointsBothFitRes() throws InvalidNumberOfPointsException,  InvalidParameterException {

        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsFitBothMap.entrySet()) {
            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res = a*new CalculatedImpPointsForOneDeal(whoPlay, pointsInBothHands, pointsOfContract,  assumption[0],  assumption[1], true, true).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. " + printAssumtion(assumption) +",, My Fit");
            Assert.assertEquals(testCountingPointsFitBothMap.get(pointsInBothHands, pointsOfContractFloat), res);
        }
    }


}
