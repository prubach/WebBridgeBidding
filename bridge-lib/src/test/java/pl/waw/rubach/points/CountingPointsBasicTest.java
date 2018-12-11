package pl.waw.rubach.points;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CountingPointsBasicTest {

    private static Logger logger = LoggerFactory.getLogger(CountingPointsBasicTest.class);


    private MultiKeyMap<Float, Integer> testCountingPointsBothAfterFitWeMap = new MultiKeyMap<>();
    private MultiKeyMap<Float, Integer> testCountingPointsBothAfterFitTheyMap = new MultiKeyMap<>();

    private MultiKeyMap<Float, Integer> testCountingPointsBothAfterFitBoth = new MultiKeyMap<>();
    private MultiKeyMap<Float, Integer> testCountingPointsBothAfterNoFitBothMap = new MultiKeyMap<>();


    private MultiKeyMap<Float, Integer> testCountingPointsBothBeforNoFitMap = new MultiKeyMap<>();
  //
    //

    /*  private MultiKeyMap<MultiKeyMap,Integer> testCountingPointsAssumptionNoFitMap = new MultiKeyMap<>();
    testCountingPointsAssumptionNoFitMap.put(testCountingPointsAssumptionNoFitMap, testCountingPointsAssumptionNoFitMap,11); */
//pyt czy można zrobić mapę której jednym kluczem jest boolean a kolejnymi tak jak jest wtedy można by zrobic jeden test i tylko zmieniać założenia przy wartościach?  czy np mapa map i czy to się opłaca


    @Before
    public void fillTestPointsMap() {
//todo make copy of this for both before :) and one before the second after
        testCountingPointsBothBeforNoFitMap.put(28f, 990f, 11);
        testCountingPointsBothBeforNoFitMap.put(12f, -990f, -11);


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
      //  testCountingPointsBothAfterFitBoth.put(20f, 0f, 2);  //gra problem who should play (how to see who have spades ) - probably is better in this case marke fit to spads and no fit to hearts???
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
            Integer res = new ResultsOfOneGame(pointsInBothHands, pointsOfContract, false, false, false, false).getResults();
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
            Integer res = new ResultsOfOneGame(pointsInBothHands, pointsOfContract, true, true, true, false).getResults();
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
            Integer res = new ResultsOfOneGame(pointsInBothHands, pointsOfContract, true, true, false, true).getResults();
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
            Integer res = new ResultsOfOneGame(pointsInBothHands, pointsOfContract, true, true, true, true).getResults();
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
            Integer res = new ResultsOfOneGame(pointsInBothHands, pointsOfContract, true, true, false, false).getResults();
            logger.info("Dla " + pointsInBothHands + " pkt:  oraz ugranych " + pointsOfContract + " wynik jest " + res + " impów. Obie po, Obe bez fitu");
            Assert.assertEquals(testCountingPointsBothAfterNoFitBothMap.get(pointsInBothHands, pointsOfContractFloat), res);

        }


    }

/* dobre testy ale nieładnie zapisane (kopia wyżej)
    @Test
    public void testCountingPoints() throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
        // System.out.println("Dla " + 24 + " pkt: " + ExpectedResultsTable.getInstance().getPoints(24, true, false,true) + " oczekiwane.");

        ResultsOfOneGame a = new ResultsOfOneGame(24, 500, true, false, true, false);
        System.out.println("Wynik gry dla 24PC i ugranych 500 pkt przy założeniach po i Fit jest: " + a.getResults());
        Assert.assertEquals(2, a.getResults());

        ResultsOfOneGame b = new ResultsOfOneGame(24, 300, true, false, true, false);
        System.out.println("Wynik gry dla 24PC i ugranych 300 pkt przy założeniach po i Fit jest: " + b.getResults());
        Assert.assertEquals(-4, b.getResults());

        ResultsOfOneGame c = new ResultsOfOneGame(24, 100, true, false, true, false);
        System.out.println("Wynik gry dla 24PC i ugranych +100 pkt przy założeniach po i Fit jest: " + c.getResults());
        Assert.assertEquals(-8, c.getResults());

        ResultsOfOneGame d = new ResultsOfOneGame(24, -100, true, false, true, false);
        System.out.println("Wynik gry dla 24PC i ugranych -100 pkt przy założeniach po i Fit jest: " + d.getResults());
        Assert.assertEquals(-11, d.getResults());

        ResultsOfOneGame e = new ResultsOfOneGame(24, -500, true, false, true, false);
        System.out.println("Wynik gry dla 24PC i ugranych -100 pkt przy założeniach po i Fit jest: " + e.getResults());
        Assert.assertEquals(-14, e.getResults());


        ResultsOfOneGame f = new ResultsOfOneGame(20, 0, true, false, true, false);
        System.out.println("Wynik gry dla 20PC i ugranych 0 pkt przy założeniach po i my Fit jest: " + f.getResults());
        Assert.assertEquals(-2, f.getResults());

        ResultsOfOneGame g = new ResultsOfOneGame(20, 0, true, false, false, true);
        System.out.println("Wynik gry dla 20PC i ugranych 0 pkt przy założeniach po i oni Fit jest: " + g.getResults());
        Assert.assertEquals(2, g.getResults());


        ResultsOfOneGame h = new ResultsOfOneGame(10, -660, true, true, false, false);
        System.out.println("Wynik gry dla 10PC i ugranych -660 pkt przy założeniach po i oni bez fit jest: " + h.getResults());
        Assert.assertEquals(0, h.getResults());


    }

*/
}
