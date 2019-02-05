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

    private MultiKeyMap<Integer, Integer> testRubberFromResults = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> testRubberFromPoints = new MultiKeyMap<>();

    private MultiKeyMap<Float, Integer> testRubberFromPointsFloat = new MultiKeyMap<>();

    @Before
    public void fillTestPointsMap() {
        //testPointsMap.put()

        //all equal normal contract
        testRubberFromResults.put(1,2,3,4,10);
        testRubberFromResults.put(6,10,-5,-3,8);
        testRubberFromResults.put(3,5,-5,-3,0);

        //pyt is posible more then 4 keys?
        //odp nie używałem tych MultiKeyMap więc trzeba by sprawdzić
        testRubberFromPoints.put(20,19,18,17,-110,-6);
        testRubberFromPoints.put(20,21,22,23,110,6);
        testRubberFromPoints.put(20,21,22,26,440,28); //3nt PRZED
        testRubberFromPoints.put(20,19,18,14,-440,-28); //3nt PRZED
        testRubberFromPoints.put(28,25,28,27,400,0); //3nt PRZED

        testRubberFromPointsFloat.put(20f,19f,18f,17f,-110f,-6);
        testRubberFromPointsFloat.put(20f,21f,22f,23f,110f,6);
        testRubberFromPointsFloat.put(20f,21f,22f,26f,440f,28); //3nt PRZED
        testRubberFromPointsFloat.put(20f,19f,18f,14f,-440f,-28); //3nt PRZED

    }


    @Test
    public void testRubberScoringFromResults1() throws BridgeException {
        for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : testRubberFromResults.entrySet()) {
            int result1 = entry.getKey().getKey(0);
            int result2 = entry.getKey().getKey(1);
            int result3 = entry.getKey().getKey(2);
            int result4 = entry.getKey().getKey(3);

            RubberScoring rS = new RubberScoring(result1,result2,result3,result4);
            Integer res = rS.getSumm();
            logger.info("Wynik dla całego robra z podanych wyników rozdań (" + result1+ ","+ result2+ ","+ result3 +","+ result4+ ") jest: "+ res +" \n");
            Assert.assertEquals(testRubberFromResults.get(result1, result2, result3,result4), res);
        }
    }
    @Test
    public void testRubberScoringFromInputDataMirrorFloat() throws BridgeException {

           for (Map.Entry<MultiKey<? extends Float>, Integer> entry : testRubberFromPointsFloat.entrySet()) {
           float ph1 = entry.getKey().getKey(0);
            float ph2 = entry.getKey().getKey(1);
            float ph3 = entry.getKey().getKey(2);
            float  ph4 = entry.getKey().getKey(3);
            float cSFloat = entry.getKey().getKey(4);
            int cS = Math.round(cSFloat);

            RubberScoring rS = new RubberScoring(true,true,true,true,ph1,ph2,ph3,ph4,cS,cS,cS,cS,false,false,false,false,false,false,false,false);
            Integer res = rS.getSumm();
            logger.info("Wynik dla całego robra z podanych punktów na ręku (" + ph1+ ","+ ph2+ ","+ ph3 +","+ ph4+ ") i wyniku każdego rozdania "+ cS+" jest: "+ res +" \n");
          //  Assert.assertEquals(testRubberFromPoints.get(ph1, ph2, ph3,ph4, cSFloat), res);

            RubberScoring rSRevers = new RubberScoring(false,false,false,false,40-ph1,40-ph2,40-ph3,40-ph4,-cS,-cS,-cS,-cS,false,false,false,false,false,false,false,false);
            //     Integer resRevers = -rSRevers.getSumm();

            //    Assert.assertEquals(res,resRevers);
        }


    }

    @Test
    public void testRubberScoringFromInputDataMirror() throws BridgeException {

        for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : testRubberFromPoints.entrySet()) {
            int ph1 = entry.getKey().getKey(0);
            int ph2 = entry.getKey().getKey(1);
            int ph3 = entry.getKey().getKey(2);
            int ph4 = entry.getKey().getKey(3);
            int cS = entry.getKey().getKey(4);

            RubberScoring rS = new RubberScoring(true,true,true,true,ph1,ph2,ph3,ph4,cS,cS,cS,cS,false,false,false,false,false,false,false,false);
            Integer res = rS.getSumm();
            logger.info("Wynik dla całego robra z podanych punktów na ręku (" + ph1+ ","+ ph2+ ","+ ph3 +","+ ph4+ ") i wyniku każdego rozdania "+ cS+" jest: "+ res +" \n");
            Assert.assertEquals(testRubberFromPoints.get(ph1, ph2, ph3,ph4, cS), res);

            RubberScoring rSRevers = new RubberScoring(false,false,false,false,40-ph1,40-ph2,40-ph3,40-ph4,-cS,-cS,-cS,-cS,false,false,false,false,false,false,false,false);
       //     Integer resRevers = -rSRevers.getSumm();

        //    Assert.assertEquals(res,resRevers);
        }


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
            logger.info("Wynik dla całego robra wpisanego ręcznie (punkty i wyniki) rozdanie po rozdaniu jest: " + sum + ". ");
            Assert.assertEquals(sum, -6);
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
            logger.info("Wynik dla całego robra wpisanego ręcznie (punkty i wyniki) rozdanie po rozdaniu jest:  " + sum + ". ");
            Assert.assertEquals(sum, 6);
        }

        {
            RubberScoring rooG = new RubberScoring(15);
            int a = rooG.fillOneContractFrom4GameSet(1, true, 28, 400, false, false);
            Assert.assertEquals(a, 0);
            int i=  new CalculatedImpPointsForOneDeal(true,28,400,false,false,false,false).getResults();
             Assert.assertEquals(a,i);
            int b = rooG.fillOneContractFrom4GameSet(2, true, 25, 400, false, false);
            Assert.assertEquals(b, 3);
            int ib=  new CalculatedImpPointsForOneDeal(true,25,400,true,false,false,false).getResults();
            Assert.assertEquals(b,ib);

            int c = rooG.fillOneContractFrom4GameSet(3, true, 28, 400, false, false);
            Assert.assertEquals(c, 0);
            int ic=  new CalculatedImpPointsForOneDeal(true,28,400,false,true,false,false).getResults();
            Assert.assertEquals(c,ic);

            int d = rooG.fillOneContractFrom4GameSet(4, true, 27, 400, false, false);
            Assert.assertEquals(d, -3);
            int id=  new CalculatedImpPointsForOneDeal(true,27,400,true,true,false,false).getResults();
            Assert.assertEquals(d,id);

            int sum = rooG.getSumm();
            logger.info("Wynik dla całego robra wpisanego ręcznie (punkty i wyniki) rozdanie po rozdaniu jest:  " + sum + ". ");
            Assert.assertEquals(sum, 0);
            }

    }
}