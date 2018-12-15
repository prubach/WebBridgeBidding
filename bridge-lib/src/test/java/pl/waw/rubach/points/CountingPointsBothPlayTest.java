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

public class CountingPointsBothPlayTest {

    private static Logger logger = LoggerFactory.getLogger(CountingPointsBothPlayTest.class);



    private MultiKeyMap<Integer, Integer> testCountingPointsNTBothBeforeBothNoFit = new MultiKeyMap<>();
    private Map<Integer, Integer> testCountingPoints3NTBothBeforeBothNoFit = new HashMap<>();

   // private MultiKeyMap<Float, Integer> testCountingPointsBothAfterFitBoth = new MultiKeyMap<>();
  //  private MultiKeyMap<Float, Integer> testCountingPointsBothAfterNoFitBothMap = new MultiKeyMap<>();


  //  private MultiKeyMap<Float, Integer> testCountingPointsBothBeforNoFitMap = new MultiKeyMap<>();
  //
    //

    /*  private MultiKeyMap<MultiKeyMap,Integer> testCountingPointsAssumptionNoFitMap = new MultiKeyMap<>();
    testCountingPointsAssumptionNoFitMap.put(testCountingPointsAssumptionNoFitMap, testCountingPointsAssumptionNoFitMap,11); */
//pyt czy można zrobić mapę której jednym kluczem jest boolean a kolejnymi tak jak jest wtedy można by zrobic jeden test i tylko zmieniać założenia przy wartościach?
// czy np mapa map i czy to się opłaca


    @Before
    public void fillTestPointsMap() {
        //                          key1: liczba puntków  key2: wysokość key3 liczba lew
        testCountingPointsNTBothBeforeBothNoFit.put( 28,6,12, 11); //990
        testCountingPointsNTBothBeforeBothNoFit.put( 20,6,12, 14); //990
       testCountingPointsNTBothBeforeBothNoFit.put( 20, 3,9, 9); //400
        testCountingPointsNTBothBeforeBothNoFit.put( 22,3,9, 8); //400
        testCountingPointsNTBothBeforeBothNoFit.put( 26,3,9, 3); //400

    }

//    RubberScoring a1 = new RubberScoring(1, "nt", 1,"nt", 6,"nt",6,"nt",12,28,15,35,7,6,1,12,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
    @Test
    public void testCountingPointsA() throws BridgeException {
 // ResultsOfOneGame(float pointsInBothHands, int contractLevel, int numberOfTrickTaken, String contractSuit, boolean doubleGame, boolean redoubleGame, boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)

        ResultsOfOneGame a = new ResultsOfOneGame(20f,3,9,"nt",false,false,false,false,false,false);
        Assert.assertEquals(a.getResults(), 9);

        ResultsOfOneGame a2 = new ResultsOfOneGame(20f,3,9,"nt",false,false,false,false,false,false);
        Assert.assertEquals(a2.getResults(), 9);

    }

    @Test
    public void testCountingPointsBothBeforeRes() throws BridgeException {

         for (Map.Entry<MultiKey<? extends Integer>, Integer> entry :  testCountingPointsNTBothBeforeBothNoFit.entrySet()) {

            int pointsInBothHands = entry.getKey().getKey(0);
            int contractLevel = entry.getKey().getKey(1);
            int numberOfTricksTaken = entry.getKey().getKey(2);

  ResultsOfOneGame roog = new ResultsOfOneGame(pointsInBothHands,contractLevel,numberOfTricksTaken,"nt",false,false,false,false,false,false);

//             ResultsOfOneGame roog = new ResultsOfOneGame(pointsInBothHands,400,false,false,false,false);
            Integer res = roog.getResults();
            //    Integer res = new ResultsOfOneGame(28f, contractLevel,"nt",numberOfTricksTaken, false, false, false, false).getResults();
           logger.info("Dla "+ pointsInBothHands+" pkt.   przy kontrakcie " + contractLevel + "NT i zebranych "+numberOfTricksTaken+ " lewach - wynik jest " + res + " impów. Obie przed, obie bez fitu");
            Assert.assertEquals( testCountingPointsNTBothBeforeBothNoFit.get(pointsInBothHands,contractLevel, numberOfTricksTaken), res);
          //  Assert.assertEquals(roog.getPointsForContract(),400 );


        }


    }


}
