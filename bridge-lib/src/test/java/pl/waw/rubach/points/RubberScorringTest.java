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

import static org.junit.Assert.*;

public class RubberScorringTest {


    private static Logger logger = LoggerFactory.getLogger(FourGameImpScorringFirstTest.class);



    private MultiKeyMap<Integer, Integer> testRubberFromPoints = new MultiKeyMap<>();

    private MultiKeyMap<Integer, Integer> testRubberFromPoints1 = new MultiKeyMap<>();

    @Before
    public void fillTestPointsMap() throws Exception {

        testRubberFromPoints.put(3, 9, 4, 9, 100+500);
        testRubberFromPoints.put(3, 9, 1, 9, 290+500);

        testRubberFromPoints1.put(3, 9, 4, 9, 150+500);
        testRubberFromPoints1.put(3, 9, 1, 9, 290+500);

    }


    @Test
    public void fillOneContract() throws BridgeException {
        for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : testRubberFromPoints.entrySet()) {
            int ph1 = entry.getKey().getKey(0);
            int ph2 = entry.getKey().getKey(1);
            int ph3 = entry.getKey().getKey(2);
            int ph4 = entry.getKey().getKey(3);
//            int cS = entry.getKey().getKey(4);

            RubberScorring aa = new RubberScorring((2)); //uwaga istotna jest kolejność wpisów a nie numer!! TODO poprawić?

         int a1=   aa.fillOneContract(1,true,ph1,"n",false,false,ph2);
            Integer res1 = aa.getWiningScorring();//aa
         int a2=   aa.fillOneContract(2,true,ph3,"s",false,false,ph2);
            Integer res2 = aa.getWiningScorring();//aa
         int a3=   aa.fillOneContract(3,true,ph1,"n",false,false,ph2);

            Integer res3 = aa.getWiningScorring();//aa.getSumm();
            logger.info("Wynik dla robra z dwóch rozdań (wysokość/liczba lew) (" + ph1 + "/" + ph2 + "," + ph3 + "/" + ph4 + ") " +
                    "i wyniku rozdania : " +a1+" " + a2 + "  " +a3 +" "+ +res1 + ", "+ res2+ " ,"+ res3 + " \n");
            Assert.assertEquals(testRubberFromPoints.get(ph1, ph2, ph3, ph4), res3);

            RubberScorring bb = new RubberScorring((4));

            int b2 = bb.fillOneContract(2, true, ph3, "s", false, false, ph2);
            Integer res2b = bb.getWiningScorring();//aa

            int b1 = bb.fillOneContract(1, true, ph1, "n", false, false, ph2);
            Integer res1b = bb.getWiningScorring();//aa
            int b3 = bb.fillOneContract(3, true, ph1, "n", false, false, ph2);

            Integer res3b = bb.getWiningScorring();//aa.getSumm();
            logger.info("Wynik dla robra z dwóch rozdań (wysokość/liczba lew) (" + ph1 + "/" + ph2 + "," + ph3 + "/" + ph4 + ") " +
                    "i wyniku rozdania : " + b1 + " " + b2 + "  " + b3 + " " + +res1b + ", " + res2b + " ," + res3b + " \n");
            Assert.assertEquals(testRubberFromPoints1.get(ph1, ph2, ph3, ph4), res3b);


        }}

    @Test
    public void fillOneContract1() throws BridgeException {
        for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : testRubberFromPoints.entrySet()) {
            int ph1 = entry.getKey().getKey(0);
            int ph2 = entry.getKey().getKey(1);
            int ph3 = entry.getKey().getKey(2);
            int ph4 = entry.getKey().getKey(3);

        }
    }
     /*  @Test
    public void getGameType() {
    }

    @Test
    public void setGameType() {
    }

    @Test
    public void getGameID() {
    }

    @Test
    public void setGameID() {
    }

    @Test
    public void getRubberSpecialDescription() {
    }

    @Test
    public void setRubberSpecialDescription() {
    }

    @Test
    public void getResultsDescription() {
    }

    @Test
    public void setResultsDescription() {
    }

    @Test
    public void getSumm() {
    }

    @Test
    public void setSumm() {
    }
*/

/*
    @Test
    public void getScorringForOneGame() {
    }

    @Test
    public void setSumm1() {
    }

    @Test
    public void getAboveWe() {
    }

    @Test
    public void getAboveThey() {
    }

    @Test
    public void getUnderWe() {
    }

    @Test
    public void getUnderThey() {
    }

    @Test
    public void getAboveWeSumm() {
    }

    @Test
    public void getAboveTheySumm() {
    }

    @Test
    public void getUnderWeSumm() {
    }

    @Test
    public void getUnderTheySumm() {
    }

    @Test
    public void isAreWeBefore() {
    }

    @Test
    public void setAreWeBefore() {
    }

    @Test
    public void isAreTheyBefore() {
    }

    @Test
    public void setAreTheyBefore() {
    }

    @Test
    public void isWinWe() {
    }

    @Test
    public void setWinWe() {
    }

    @Test
    public void isWinThey() {
    }

    @Test
    public void setWinThey() {
    }
    */
}