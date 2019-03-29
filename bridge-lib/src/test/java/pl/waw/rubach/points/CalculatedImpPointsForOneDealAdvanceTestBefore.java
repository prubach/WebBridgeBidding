package pl.waw.rubach.points;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.points.exceptions.BridgeException;

import java.util.Map;

public class CalculatedImpPointsForOneDealAdvanceTestBefore {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    protected boolean weBeforeAfter = false;
    protected boolean theyBeforeAfter = false;
    protected boolean weFit = false;
    protected boolean theyFit = false;


    private MultiKeyMap<Integer, Integer> testCountingPointsNTBothBeforeBothNoFit = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> testCountingPointsNTBothBeforeBothFit = new MultiKeyMap<>();


//todo czy można zrobić mapę której jednym kluczem jest boolean a kolejnymi tak jak jest wtedy można by zrobic jeden test i tylko zmieniać założenia przy wartościach?
// czy np mapa map i czy to się opłaca może lepiej tak jak było w CalculatedImpPointsForOneDealBeforWePlay
//odp trochę to skomplikowane, ale jak chcesz to nie ma problemu, może być np.  Map<Boolean,MultiKeyMap>


    @Before
    public void fillTestPointsMap() {
        //                          key1: liczba puntków  key2: wysokość key3 liczba lew
        testCountingPointsNTBothBeforeBothNoFit.put(20, 3, 9, 9); //400
        testCountingPointsNTBothBeforeBothNoFit.put(21, 3, 9, 8); //990
        testCountingPointsNTBothBeforeBothNoFit.put(22, 3, 9, 8); //400
        testCountingPointsNTBothBeforeBothNoFit.put(23, 3, 9, 7); //400
        testCountingPointsNTBothBeforeBothNoFit.put(24, 3, 9, 7); //400
        testCountingPointsNTBothBeforeBothNoFit.put(24, 3, 9, 7); //400
        testCountingPointsNTBothBeforeBothNoFit.put(25, 3, 9, 5); //400
        testCountingPointsNTBothBeforeBothNoFit.put(26, 3, 9, 3); //400
        testCountingPointsNTBothBeforeBothNoFit.put(27, 3, 9, 2); //400
        testCountingPointsNTBothBeforeBothNoFit.put(28, 3, 9, 0); //400
        testCountingPointsNTBothBeforeBothNoFit.put(29, 3, 9, -1); //400
        testCountingPointsNTBothBeforeBothNoFit.put(30, 3, 9, -2); //400
        testCountingPointsNTBothBeforeBothNoFit.put(31, 3, 9, -3); //400
        testCountingPointsNTBothBeforeBothNoFit.put(32, 3, 9, -5); //400

        testCountingPointsNTBothBeforeBothNoFit.put(20, 6, 12, 14); //990
        testCountingPointsNTBothBeforeBothNoFit.put(28, 6, 12, 11); //990
        testCountingPointsNTBothBeforeBothNoFit.put(29, 6, 12, 11); //990
        testCountingPointsNTBothBeforeBothNoFit.put(29, 6, 13, 11); //990 +30 = 1020 //jedna lepiej tu nic nie zmienia
        testCountingPointsNTBothBeforeBothNoFit.put(29, 6, 11, -10); //-50         //bez jednej


        testCountingPointsNTBothBeforeBothNoFit.put(21, 1, 7, 1); //90
        testCountingPointsNTBothBeforeBothNoFit.put(24, 1, 7, -1); //90
        testCountingPointsNTBothBeforeBothNoFit.put(27, 1, 7, -6); //90
        testCountingPointsNTBothBeforeBothNoFit.put(21, 1, 8, 2); //140


        //todo add more example for tests
     //   testCountingPointsNTBothBeforeBothFit.put(26,1,7,-7);
        testCountingPointsNTBothBeforeBothFit.put(26,3,9,0);
     //   testCountingPointsNTBothBeforeBothFit.put(25,3,9,-2);
    }


    private void testHelperFunction( MultiKeyMap<Integer, Integer> map, String opis) throws BridgeException {
        for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : map.entrySet()) {

            int pointsInBothHands = entry.getKey().getKey(0);
            int contractLevel = entry.getKey().getKey(1);
            int numberOfTricksTaken = entry.getKey().getKey(2);

            CalculatedImpPointsForOneDeal roog = new CalculatedImpPointsForOneDeal(true, pointsInBothHands,
                    contractLevel, "nt", 1, numberOfTricksTaken, weBeforeAfter, theyBeforeAfter, weFit, theyFit);
//            Integer res = roog.getDeclarerResluts();
            Integer res = roog.getResultsWe(true);

            logger.info("My gramy: Dla " + pointsInBothHands + " pkt.   przy kontrakcie " + contractLevel + "NT i zebranych " + numberOfTricksTaken + " lewach - wynik jest " + res + " impów. Obie przed, "+ opis +".");
            logger.info("Punktów za kontrakt jest : "+ roog.getDeclarerContractScoringPoints() +" Róznica jest: " +roog.getPointDifferent());
            Assert.assertEquals(map.get(pointsInBothHands, contractLevel, numberOfTricksTaken), res);

            CalculatedImpPointsForOneDeal roogR = new CalculatedImpPointsForOneDeal(false, 40 - pointsInBothHands,
                    contractLevel, "nt", 1, 13 - numberOfTricksTaken, theyBeforeAfter, weBeforeAfter, weFit, theyFit);
          //  Integer resR = roogR.getDeclarerResluts();
            Integer resR = roogR.getResultsWe(false);

            logger.info("Oni grają: Dla " + (40-pointsInBothHands)+" pkt. na ich ręku  przy kontrakcie " + contractLevel + "NT i zebranych " +  (13-numberOfTricksTaken) + "przez nich lewach - wynik jest " + resR + "  impów dla nas. Obie przed, "+ opis +".");
            logger.info("Punktów za kontrakt jest : "+ roogR.getDeclarerContractScoringPoints() +" Róznica jest: " +roogR.getPointDifferent());
//fixme finwhy test does not passed
       //     Assert.assertEquals(map.get(pointsInBothHands, contractLevel, numberOfTricksTaken), resR);

            //to check - should be the same always...
       //     Assert.assertEquals(res, resR);

            FourGameImpScorring aa =new FourGameImpScorring();
            Integer imp = aa.fillOneContract(1,roog);
            Assert.assertEquals(res,imp);
            Integer imp2 = aa.fillOneContract(1,roogR);
            Assert.assertEquals(resR,imp2);

        }
    }

    @Test
    public void testCountingPointsBothBeforeBoth() throws BridgeException {
    testHelperFunction(testCountingPointsNTBothBeforeBothNoFit, "Obie bez fitu");
    }


    @Test
    public void testCountingPointsBothBeforeBothBothFit() throws BridgeException {
        weFit =true;
        theyFit = true;
        testHelperFunction(testCountingPointsNTBothBeforeBothFit, "Obie fit");
    }


    @Test
    public void testCountingPointsA() throws BridgeException {

        CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(true,20f,
                3, "nt", 1, 9, false, false, false, false);
        Assert.assertEquals(a.getDeclarerResluts(), 9);

        CalculatedImpPointsForOneDeal a2 = new CalculatedImpPointsForOneDeal(true,20f,
                3, "nt", 1, 9, false, false, false, false);
        Assert.assertEquals(a2.getDeclarerResluts(), 9);
    }
}



