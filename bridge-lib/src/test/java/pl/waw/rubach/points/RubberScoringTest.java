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

public class RubberScoringTest {

    private static Logger logger = LoggerFactory.getLogger(RubberScoringTest.class);

    // private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
       // DuplicateBridgeScoring dbs = new DuplicateBridgeScoring(3, "nt", 1, false, 9);
       // CalculatedImpPointsForOneDeal ipr2 = new CalculatedImpPointsForOneDeal(true, 20, dbs.getContractScoringPoints(), false, false, false, false);
       // CalculatedImpPointsForOneDeal ipr1 = new CalculatedImpPointsForOneDeal(true, 20, 400, false, false, false, false);
       // CalculatedImpPointsForOneDeal ipr0 = new CalculatedImpPointsForOneDeal(true, 20, 3, "nt", 1, 9, false, false, false, false);
    private MultiKeyMap<Integer, Integer> testRubberFromReslts = new MultiKeyMap<>();

    @Before
    public void fillTestPointsMap() {
        //testPointsMap.put()

        //all equal normal contract
        testRubberFromReslts.put(1,2,3,4,10);
        testRubberFromReslts.put(6,10,-5,-3,8);
        testRubberFromReslts.put(3,5,-5,-3,0);
    }


    @Test
    public void testRubberScoringFromResults1() throws BridgeException {
        for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : testRubberFromReslts.entrySet()) {
            int result1 = entry.getKey().getKey(0);
            int result2 = entry.getKey().getKey(1);
            int result3 = entry.getKey().getKey(2);
            int result4 = entry.getKey().getKey(3);

            RubberScoring rS = new RubberScoring(result1,result2,result3,result4);
            Integer res = rS.getSumm();
            logger.info("Wynik dla całego robra z podanych wyników rozdań (" + result1+ ","+ result2+ ","+ result3 +","+ result4+ ") jest: "+ res +" \n");
            Assert.assertEquals(testRubberFromReslts.get(result1, result2, result3,result4), res);
        }
    }

    @Test
    public void testRubberScoringFromInputData() throws BridgeException {


    }


    @Test
    public void testRubberScoringTest() throws BridgeException {

        RubberScoring a = new RubberScoring(true, true, true, true, 1, "nt", 1, "nt", 3, "nt", 3, "nt", 20, 21, 22, 23, 7, 6, 8, 9, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
        logger.info("Wynik dla całego robra liczonengo na raz z podstawowych parametrów wejściowych jest: " + a.getSumm() + " \n");
        Assert.assertEquals(a.getSumm(), 6);

        RubberScoring a1 = new RubberScoring(true, true, true, true, 1, "nt", 1, "nt", 6, "nt", 6, "nt", 12, 28, 15, 35, 7, 6, 1, 12, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
        logger.info("Wynik dla całego robra  liczonengo na raz z podstawowych parametrów wejściowych jest: " + a1.getSumm() + " \n");
        Assert.assertEquals(a1.getSumm(), -10);

        RubberScoring a2 = new RubberScoring(true, true, true, true, 1, "nt", 1, "nt", 1, "nt", 1, "nt", 20, 21, 22, 23, 7, 6, 8, 9, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
        logger.info("Wynik dla całego robra  liczonengo na raz z podstawowych parametrów wejściowych  jest: " + a2.getSumm() + " \n");
        Assert.assertEquals(a2.getSumm(), 2);

        RubberScoring b = new RubberScoring(true, true, true, true, 20, 19, 18, 17, -110, -110, -110, -110, false, false, false, false, false, false, false, false);
        logger.info("Wynik dla całego robra  liczonengo na raz z punktów za rozdanie i na ręku  jest: " + b.getSumm() + " \n");
        Assert.assertEquals(b.getSumm(), -6);

        RubberScoring b1 = new RubberScoring(false, false, false, false, 20, 21, 22, 23, 110, 110, 110, 110, false, false, false, false, false, false, false, false);
        logger.info("Wynik dla całego robra  liczonengo na raz z punktów za rozdanie i na ręku  jest: " + b1.getSumm() + " \n");
        Assert.assertEquals(b1.getSumm(), 6);
    }



    @Test
    public void testRubberScorigFillingDeals() throws BridgeException {

        {
            RubberScoring rooG = new RubberScoring(15);
            int a = rooG.fillOneContractFrom4GameSet(1, true, 20, -110, false, false);
            Assert.assertEquals(a, -3);

            int b = rooG.fillOneContractFrom4GameSet(2, true, 19, -110, false, false);
            Assert.assertEquals(b, -2);

            int c = rooG.fillOneContractFrom4GameSet(3, true, 18, -110, false, false);
            Assert.assertEquals(c, -1);

            int d = rooG.fillOneContractFrom4GameSet(4, true, 17, -110, false, false);
            Assert.assertEquals(d, 0);

            int sum = rooG.getSumm();
            Assert.assertEquals(sum, -6);
            logger.info("Wynik dla całego robra wpisanego ręcznie (punkty i wyniki) rozdanie po rozdaniu jest: " + sum + ". ");
        }
        {
            RubberScoring rooG = new RubberScoring(15);
            int a = rooG.fillOneContractFrom4GameSet(1, true, 20, 110, false, false);
            Assert.assertEquals(a, 3);

            int b = rooG.fillOneContractFrom4GameSet(2, true, 21, 110, false, false);
            Assert.assertEquals(b, 2);

            int c = rooG.fillOneContractFrom4GameSet(3, true, 22, 110, false, false);
            Assert.assertEquals(c, 1);

            int d = rooG.fillOneContractFrom4GameSet(4, true, 23, 110, false, false);
            Assert.assertEquals(d, 0);

            int sum = rooG.getSumm();
            Assert.assertEquals(sum, 6);
            logger.info("Wynik dla całego robra wpisanego ręcznie (punkty i wyniki) rozdanie po rozdaniu jest:  " + sum + ". ");
        }

    }
}