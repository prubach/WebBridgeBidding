package pl.waw.rubach.points.duplicateBridgeImps;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.points.exceptions.BridgeException;

import java.util.Map;

/**
 * TEST which use points on both our hands and our scorring for contract calculate imps for one deal
 */
public class CalculatedImpPointsForOneDealFirstTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    protected MultiKeyMap<Float, Integer> testCountingPointsBothAfterFitWeMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsBothAfterFitTheyMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsBothAfterFitBothMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsBothAfterNoFitBothMap = new MultiKeyMap<>();


    protected MultiKeyMap<Float, Integer> testCountingPointsBothBeforNoFitBothMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsBothBeforFitBothMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsBothBeforFitWeMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsBothBeforFitTheyMap = new MultiKeyMap<>();


    protected MultiKeyMap<Float, Integer> testCountingPointsWeBeforTheyAfterFitBothMap = new MultiKeyMap<>();
    protected MultiKeyMap<Float, Integer> testCountingPointsWeAfterTheyBeforeFitBothMap = new MultiKeyMap<>();
  //
    //

    /*  private MultiKeyMap<MultiKeyMap,Integer> testCountingPointsAssumptionNoFitMap = new MultiKeyMap<>();
    testCountingPointsAssumptionNoFitMap.put(testCountingPointsAssumptionNoFitMap, testCountingPointsAssumptionNoFitMap,11); */


    @Before
    public void fillTestPointsMap() {
                                                //points in hand points for contract imps
        testCountingPointsBothBeforNoFitBothMap.put(28f, 990f, 11);
        testCountingPointsBothBeforNoFitBothMap.put(28f, 100f, -7);
        testCountingPointsBothBeforNoFitBothMap.put(29f, -50f, -10);
        testCountingPointsBothBeforNoFitBothMap.put(11f, 50f, 10);

        testCountingPointsBothBeforNoFitBothMap.put(12f, -990f, -11);
        testCountingPointsBothBeforNoFitBothMap.put(12f, -100f, 7);
        testCountingPointsBothBeforNoFitBothMap.put(20f, 400f, 9);
        testCountingPointsBothBeforNoFitBothMap.put(22f, 400f, 8);
        testCountingPointsBothBeforNoFitBothMap.put(23f, 400f, 7);
        testCountingPointsBothBeforNoFitBothMap.put(24.5f, 400f, 6);
        testCountingPointsBothBeforNoFitBothMap.put(25f, 400f, 5);
        testCountingPointsBothBeforNoFitBothMap.put(28f, 400f, 0);



        testCountingPointsBothBeforFitWeMap.put(28f, 990f, 11);
        testCountingPointsBothBeforFitWeMap.put(28f, 100f, -8);
        testCountingPointsBothBeforFitWeMap.put(29f, 50f, -10);

        testCountingPointsBothBeforFitTheyMap.put(12f, -990f, -11);
        testCountingPointsBothBeforFitTheyMap.put(12f, -100f, 8);
        testCountingPointsBothBeforFitTheyMap.put(11f, -50f, 10);

        testCountingPointsBothBeforFitBothMap.put(12f,-100f,8);
        testCountingPointsBothBeforFitBothMap.put(12f,-400f,2);
        testCountingPointsBothBeforFitBothMap.put(28f,100f,-8);
        testCountingPointsBothBeforFitBothMap.put(28f,400f,-2);



        testCountingPointsWeBeforTheyAfterFitBothMap.put(28f,400f,-2);
        testCountingPointsWeAfterTheyBeforeFitBothMap.put(28f,400f,-6); //400 - exp 660 = -260

        //Fit only we - they no fit
        testCountingPointsBothAfterFitWeMap.put(20f, 0f, -2);

        testCountingPointsBothAfterFitWeMap.put(24f, 500f, 2);
        testCountingPointsBothAfterFitWeMap.put(24f, 300f, -4);
        testCountingPointsBothAfterFitWeMap.put(24f, 100f, -8);
        testCountingPointsBothAfterFitWeMap.put(24f, -100f, -11);
        testCountingPointsBothAfterFitWeMap.put(24f, -500f, -14);

        testCountingPointsBothAfterFitWeMap.put(30f, -100f, -13);
        testCountingPointsBothAfterFitWeMap.put(30f, 100f, -12);

        testCountingPointsBothAfterFitWeMap.put(12f, -1660f, -14);
        testCountingPointsBothAfterFitWeMap.put(28f, 1660f, 14);

        testCountingPointsBothAfterFitWeMap.put(28f, 1660f, 14);
        testCountingPointsBothAfterFitWeMap.put(12f, -1660f, -14);

        //both fit
      //  testCountingPointsBothAfterFitBothMap.put(20f, 0f, 2);  //not such case if both have 20PC and fit in major color mark only spades (one have fit)
        testCountingPointsBothAfterFitBothMap.put(24f, -100f, -11);
        testCountingPointsBothAfterFitBothMap.put(16f, 100f, 11);
        testCountingPointsBothAfterFitBothMap.put(30f, 750f, 0);
        testCountingPointsBothAfterFitBothMap.put(10f, -750f, 0);
        testCountingPointsBothAfterFitBothMap.put(30f, 1250f, 11);
        testCountingPointsBothAfterFitBothMap.put(10f, -1250f, -11);
        testCountingPointsBothAfterFitBothMap.put(28f, 400f, -6); //400 - exp 660 = -260


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
        testCountingPointsBothAfterFitTheyMap.put(10f, 100f, 13);

        testCountingPointsBothAfterFitTheyMap.put(28f, 1660f, 14);
        testCountingPointsBothAfterFitTheyMap.put(12f, -1660f, -14);

        testCountingPointsBothAfterFitTheyMap.put(30f, -100f, -13);
        testCountingPointsBothAfterFitTheyMap.put(28f, -1660f, -20);
        testCountingPointsBothAfterFitTheyMap.put(12f, 1660f, 20);
    }

    private void helpfunctionBothPlay(MultiKeyMap<Float, Integer> map, boolean assWe, boolean assThey, boolean fitWe, boolean fitThey, String des ) throws BridgeException{
        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : map.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res = new CalculatedImpPointsForOneDeal(true,pointsInBothHands, pointsOfContract, assWe, assThey, fitWe, fitThey).getDeclarerResluts();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " imp."+ des);
            Assert.assertEquals(map.get(pointsInBothHands, pointsOfContractFloat), res);
            Integer resT = -new CalculatedImpPointsForOneDeal(false,pointsInBothHands, pointsOfContract, assWe, assThey, fitWe, fitThey).getDeclarerResluts();
            Assert.assertEquals(map.get(pointsInBothHands, pointsOfContractFloat), resT);
        }
        }

    @Test
    public void testCountingPointsBothPlayOnlySymetric() throws BridgeException {
        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsBothBeforNoFitBothMap.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            Integer res = new CalculatedImpPointsForOneDeal(true,pointsInBothHands, pointsOfContract, false, false, false, false).getDeclarerResluts();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. Obie przed, obie bez fitu");
            Assert.assertEquals(testCountingPointsBothBeforNoFitBothMap.get(pointsInBothHands, pointsOfContractFloat), res);
            Integer resT = -new CalculatedImpPointsForOneDeal(false,pointsInBothHands, pointsOfContract, false, false, false, false).getDeclarerResluts();
            Assert.assertEquals(testCountingPointsBothBeforNoFitBothMap.get(pointsInBothHands, pointsOfContractFloat), resT);
        }

        helpfunctionBothPlay(testCountingPointsBothBeforNoFitBothMap,false,false,false,false," Obie przed, obie bez fitu.");

        helpfunctionBothPlay(testCountingPointsBothAfterNoFitBothMap,true,true,false,false, "Obie po, Obie bez fitu");

        helpfunctionBothPlay(testCountingPointsBothAfterFitBothMap,true,true,true,true, " Obie po, Obie fit. ");

        helpfunctionBothPlay(testCountingPointsBothBeforFitBothMap,false,false,true,true, " Obie przed, Obie fit. ");

    }

    private void helpfunctionONLYWePlayAllPoints(MultiKeyMap<Float, Integer> map, boolean assDec, boolean assOp, boolean fitDe, boolean fitOp, String des ) throws BridgeException{
        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : map.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            CalculatedImpPointsForOneDeal cpfod = new CalculatedImpPointsForOneDeal(pointsInBothHands, pointsOfContract, assDec, assOp, fitDe, fitOp);
            Integer res = cpfod.getDeclarerResluts();
            logger.info("Rozgrywający ma fit: "+ cpfod.isDeclarerFit()+ " a powinno być " + fitDe);
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. " +des);
            Assert.assertEquals(map.get(pointsInBothHands, pointsOfContractFloat), res);

        }
    }

    private void helpfunctionONLYWePlayNOTSymetric(MultiKeyMap<Float, Integer> map, MultiKeyMap<Float, Integer> map2, boolean assDec, boolean assOp, boolean fitDe, boolean fitOp, String des ) throws BridgeException{
        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : map.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            if(pointsInBothHands>20.0){
                CalculatedImpPointsForOneDeal cpfod = new CalculatedImpPointsForOneDeal(pointsInBothHands, pointsOfContract, assDec, assOp, fitDe, fitOp);
                Integer res = cpfod.getDeclarerResluts();
                logger.info("Rozgrywający ma fit: " + cpfod.isDeclarerFit() + " a powinno być " + fitDe);
                logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. " + des);
              Assert.assertEquals(map.get(pointsInBothHands, pointsOfContractFloat), res);
              }}

        for (Map.Entry<MultiKey<? extends Float>, Integer> entry : map2.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int pointsOfContract = Math.round(pointsOfContractFloat);
            if(pointsInBothHands<20.0){
                CalculatedImpPointsForOneDeal cpfod = new CalculatedImpPointsForOneDeal(pointsInBothHands, pointsOfContract, assDec, assOp, fitDe, fitOp);
                Integer res = cpfod.getDeclarerResluts();
                logger.info("Rozgrywający ma fit: " + cpfod.isDeclarerFit() + " a powinno być " + fitDe);
                logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. " + des);
                Assert.assertEquals(map2.get(pointsInBothHands, pointsOfContractFloat), res);
            }}
    }


    @Test
    public void testCountingPointsONLYWePlayOLD() throws BridgeException {

       helpfunctionONLYWePlayAllPoints(testCountingPointsBothBeforNoFitBothMap,false,false,false,false,"Obie przed, obie bez fitu");

       helpfunctionONLYWePlayAllPoints(testCountingPointsBothBeforFitBothMap,false,false,true,true, "Obie przed, obie fit");

       helpfunctionONLYWePlayAllPoints(testCountingPointsBothBeforFitWeMap,false,false,true,false, "Obie przed, My fit");

       helpfunctionONLYWePlayAllPoints(testCountingPointsBothBeforFitTheyMap,false,false,false,true, "Obie przed, Oni fit");



       helpfunctionONLYWePlayAllPoints(testCountingPointsWeBeforTheyAfterFitBothMap,false,true,true,true, "My przed, Oni Po, Obie fit");
        helpfunctionONLYWePlayNOTSymetric(testCountingPointsBothBeforFitBothMap, testCountingPointsBothAfterFitBothMap,
                false,true,true,true, "My przed, Oni dowolnie, My fit, oni dowolnie");
        helpfunctionONLYWePlayNOTSymetric(testCountingPointsBothBeforFitWeMap,testCountingPointsBothAfterFitTheyMap,
                false,true,true,true, "My przed, Oni dowolnie, My fit, oni dowolnie");


       helpfunctionONLYWePlayAllPoints(testCountingPointsWeAfterTheyBeforeFitBothMap,true,false,true,true, "My po, Oni przed, Obie fit");
       helpfunctionONLYWePlayNOTSymetric(testCountingPointsBothAfterFitBothMap, testCountingPointsBothBeforFitBothMap,
               true,false,true,true, "My po, Oni dowolnie, My fit, oni dowolnie");
       helpfunctionONLYWePlayNOTSymetric(testCountingPointsBothAfterFitWeMap,testCountingPointsBothBeforFitTheyMap,
               true,false,true,true, "My po, Oni dowolnie, My fit, oni dowolnie");



       helpfunctionONLYWePlayAllPoints(testCountingPointsBothAfterFitWeMap,true,true,true, false, " Obie po, My fit.");

       helpfunctionONLYWePlayAllPoints(testCountingPointsBothAfterFitTheyMap, true, true, false, true, "Obie po, Oni fit.");

       helpfunctionONLYWePlayAllPoints(testCountingPointsBothAfterFitBothMap, true, true, true, true, "Obie po obie fit.");

       helpfunctionONLYWePlayAllPoints(testCountingPointsBothAfterNoFitBothMap, true, true, false, false, "Obie po obie bez fitu.");



    }
    @Test
    public void testCountingPointsManual() throws BridgeException {

        CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(true,20f,
                3, "nt", 1, 9, false, false, false, false);
        Assert.assertEquals(a.getDeclarerResluts(), 9);
        Assert.assertEquals(a.getResultsWe(true), 9);

        CalculatedImpPointsForOneDeal a2 = new CalculatedImpPointsForOneDeal(true,20f,
                3, "nt", 1, 9, true, false, false, false);
        Assert.assertEquals(a2.getDeclarerResluts(), 12);
        Assert.assertEquals(a2.getResultsWe(true), 12);

        CalculatedImpPointsForOneDeal a1 = new CalculatedImpPointsForOneDeal(false,20f,
                3, "nt", 1, 4, false, true, false, false);
        Assert.assertEquals(a1.getDeclarerResluts(), 12);
        Assert.assertEquals(a1.getResultsWe(false), -12);

        CalculatedImpPointsForOneDeal a12 = new CalculatedImpPointsForOneDeal(false,20f,
                3, "nt", 1, 4, true, true, false, false);
        Assert.assertEquals(a12.getDeclarerResluts(), 12);
        Assert.assertEquals(a12.getResultsWe(false), -12);


    }

    @Test
    public void testCountingPointsManual2() throws BridgeException {

        CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(true,20f,
                3, "nt", 1, 9, false, false, false, false);
        Assert.assertEquals(a.getDeclarerResluts(), 9);
        Assert.assertEquals(a.getResultsWe(true), 9);

        CalculatedImpPointsForOneDeal a2 = new CalculatedImpPointsForOneDeal(false,20f,
                3, "nt", 1, 4, true, false, false, false);
        Assert.assertEquals(a2.getDeclarerResluts(), 9);
        Assert.assertEquals(a2.getResultsWe(false), -9);

        CalculatedImpPointsForOneDeal a1 = new CalculatedImpPointsForOneDeal(false,20f,
                3, "nt", 1, 4, false, true, false, false);
        Assert.assertEquals(a1.getDeclarerResluts(), 12);
        Assert.assertEquals(a1.getResultsWe(false), -12);

        CalculatedImpPointsForOneDeal a12 = new CalculatedImpPointsForOneDeal(true,20f,
                3, "nt", 1, 9, true, true, false, false);
        Assert.assertEquals(a12.getDeclarerResluts(), 12);
        Assert.assertEquals(a12.getResultsWe(true), 12);


    }
}
