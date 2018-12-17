package pl.waw.rubach.points;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.points.bridgeExeption.BridgeException;

import java.util.HashMap;
import java.util.Map;

public class CalculatedImpPointsForOneDealAdvanceTest {

    private static Logger logger = LoggerFactory.getLogger(CalculatedImpPointsForOneDealAdvanceTest.class);

    protected boolean weBeforeAfter = false;
    protected boolean theyBeforeAfter = false;

    private MultiKeyMap<Integer, Integer> testCountingPointsNTBothBeforeBothNoFit = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> testCountingPointsNTBothBeforeBothNoFitThey = new MultiKeyMap<>();
    private Map<Integer, Integer> testCountingPoints3NTBothBeforeBothNoFit = new HashMap<>();

   // private MultiKeyMap<Float, Integer> testCountingPointsBothAfterFitBothMap = new MultiKeyMap<>();
  //  private MultiKeyMap<Float, Integer> testCountingPointsBothAfterNoFitBothMap = new MultiKeyMap<>();


  //  private MultiKeyMap<Float, Integer> testCountingPointsNoFitBothMap = new MultiKeyMap<>();
  //
    //

    /*  private MultiKeyMap<MultiKeyMap,Integer> testCountingPointsAssumptionNoFitMap = new MultiKeyMap<>();
    testCountingPointsAssumptionNoFitMap.put(testCountingPointsAssumptionNoFitMap, testCountingPointsAssumptionNoFitMap,11); */
//pyt czy można zrobić mapę której jednym kluczem jest boolean a kolejnymi tak jak jest wtedy można by zrobic jeden test i tylko zmieniać założenia przy wartościach?
// czy np mapa map i czy to się opłaca może lepiej tak jak było w CalculatedImpPointsForOneDealBeforWePlay



    @Before
    public void fillTestPointsMap() {
        //                          key1: liczba puntków  key2: wysokość key3 liczba lew

        testCountingPointsNTBothBeforeBothNoFit.put( 20, 3,9, 9); //400
        testCountingPointsNTBothBeforeBothNoFit.put( 20,6,12, 14); //990
        testCountingPointsNTBothBeforeBothNoFit.put( 29,6,11, -10); //990
        testCountingPointsNTBothBeforeBothNoFit.put( 28,6,12, 11); //990
        testCountingPointsNTBothBeforeBothNoFit.put( 22,3,9, 8); //400
        testCountingPointsNTBothBeforeBothNoFit.put( 26,3,9, 3); //400


    }

    @Test
    public void testCountingPointsBothBeforeResBoth() throws BridgeException {



        for (Map.Entry<MultiKey<? extends Integer>, Integer> entry :  testCountingPointsNTBothBeforeBothNoFit.entrySet()) {

            int pointsInBothHands = entry.getKey().getKey(0);
            int contractLevel = entry.getKey().getKey(1);
            int numberOfTricksTaken = entry.getKey().getKey(2);

            CalculatedImpPointsForOneDeal roog = new CalculatedImpPointsForOneDeal(true,pointsInBothHands,contractLevel, "nt", 1, numberOfTricksTaken, weBeforeAfter,theyBeforeAfter,false,false);
            Integer res = roog.getResults();

            CalculatedImpPointsForOneDeal roogR = new CalculatedImpPointsForOneDeal(false,40-pointsInBothHands,contractLevel, "nt", 1, 13-numberOfTricksTaken,  theyBeforeAfter,weBeforeAfter,false,false);
            Integer resR = roogR.getResults();

            logger.info("Dla "+ pointsInBothHands+" pkt.   przy kontrakcie " + contractLevel + "NT i zebranych "+numberOfTricksTaken+ " lewach - wynik jest " + res + " impów. Obie przed, obie bez fitu");
            Assert.assertEquals( testCountingPointsNTBothBeforeBothNoFit.get(pointsInBothHands,contractLevel, numberOfTricksTaken), res);

            Assert.assertEquals( res, resR);

        }



    }


    @Test
    public void testCountingPointsA() throws BridgeException {

        CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(20f,3, "nt", false, false, 9, false,false,false,false);
        Assert.assertEquals(a.getResults(), 9);

        CalculatedImpPointsForOneDeal a2 = new CalculatedImpPointsForOneDeal(20f,3, "nt", false, false, 9, false,false,false,false);
        Assert.assertEquals(a2.getResults(), 9);

    }

    @Test
    public void testCountingPointsBothBeforeRes() throws BridgeException {

         for (Map.Entry<MultiKey<? extends Integer>, Integer> entry :  testCountingPointsNTBothBeforeBothNoFit.entrySet()) {

            int pointsInBothHands = entry.getKey().getKey(0);
            int contractLevel = entry.getKey().getKey(1);
            int numberOfTricksTaken = entry.getKey().getKey(2);

  CalculatedImpPointsForOneDeal roog = new CalculatedImpPointsForOneDeal(pointsInBothHands,contractLevel, "nt", false, false, numberOfTricksTaken, weBeforeAfter,theyBeforeAfter,false,false);

//             CalculatedImpPointsForOneDeal roog = new CalculatedImpPointsForOneDeal(pointsInBothHands,400,false,false,false,false);
            Integer res = roog.getResults();
            //    Integer res = new CalculatedImpPointsForOneDeal(28f, contractLevel,"nt",numberOfTricksTaken, false, false, false, false).getResults();
           logger.info("Dla "+ pointsInBothHands+" pkt.   przy kontrakcie " + contractLevel + "NT i zebranych "+numberOfTricksTaken+ " lewach - wynik jest " + res + " impów. Obie przed, obie bez fitu");
            Assert.assertEquals( testCountingPointsNTBothBeforeBothNoFit.get(pointsInBothHands,contractLevel, numberOfTricksTaken), res);
          //  Assert.assertEquals(roog.getContractScoringPoints(),400 );


        }


    }


}
