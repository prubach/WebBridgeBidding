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

public class FourGameImpScorringTest {

    private static Logger logger = LoggerFactory.getLogger(FourGameImpScorringTest.class);


    private MultiKeyMap<Integer, Integer> inputDataPointsInHands6 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> inputDataPointsInHands2 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> inputDataPointsInHands3 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> inputDataPointsInHands4 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> inputDataPointsInHands5 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> inputDataPointsInHands1 = new MultiKeyMap<>();


    private MultiKeyMap<Integer, Integer> inputDataPointsForContract1 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> inputDataPointsForContract2 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> inputDataPointsForContract3 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> inputDataPointsForContract4 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> inputDataPointsForContract5 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> inputDataPointsForContract6 = new MultiKeyMap<>();


    private MultiKeyMap<Integer, Integer> whoPlay1 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> whoPlay2 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> whoPlay3 = new MultiKeyMap<>();
    private MultiKeyMap<Integer, Integer> whoPlay4 = new MultiKeyMap<>();

    //opcje                 wynik
    private MultiKeyMap<MultiKeyMap<Integer, Integer>, Integer> testRubberFromPoints2MultiKey = new MultiKeyMap<>();

    @Before
    public void fillTestPointsMap() {

        whoPlay1.put(1, 1, 1, 1, 4);
        whoPlay4.put(0, 0, 0, 0, 0);

        inputDataPointsInHands1.put(20, 19, 18, 14, 1); //value is symetry
        inputDataPointsForContract1.put(-440, -440, -440, -440, 0);
        testRubberFromPoints2MultiKey.put(inputDataPointsInHands1, inputDataPointsForContract1, whoPlay1, -28);

        //we playde -symetric
        inputDataPointsInHands4.put(28, 28, 28, 28, 1);
        inputDataPointsForContract4.put(400, 600, 400, 600, 0);
        testRubberFromPoints2MultiKey.put(inputDataPointsInHands4, inputDataPointsForContract4, whoPlay1, 0);

        //they played - symetric
        inputDataPointsInHands5.put(12, 12, 12, 12, 1);
        inputDataPointsForContract5.put(-400, -600, -400, -600, 0);
        testRubberFromPoints2MultiKey.put(inputDataPointsInHands5, inputDataPointsForContract5, whoPlay4, 0);


        // not symetric
        inputDataPointsInHands2.put(12, 15, 12, 13, 0);
        inputDataPointsForContract2.put(-400, -400, -400, -400, 0);
        testRubberFromPoints2MultiKey.put(inputDataPointsInHands2, inputDataPointsForContract2, whoPlay1, 3);

        inputDataPointsInHands3.put(28, 25, 28, 27, 0);
        inputDataPointsForContract3.put(400, 400, 400, 400, 0);
        testRubberFromPoints2MultiKey.put(inputDataPointsInHands3, inputDataPointsForContract3, whoPlay1, 0);

        //others - repetition
        inputDataPointsInHands6.put(20, 19, 18, 14, 0); //value is NOT  symetry
        testRubberFromPoints2MultiKey.put(inputDataPointsInHands6, inputDataPointsForContract2, whoPlay1, -24);
        testRubberFromPoints2MultiKey.put(inputDataPointsInHands6, inputDataPointsForContract3, whoPlay1, 42);
        testRubberFromPoints2MultiKey.put(inputDataPointsInHands6, inputDataPointsForContract4, whoPlay1, 45);


    }

    @Test
    public void testRubberScoringFromInputDataMultiKeyMaps() throws BridgeException {


        helpFunctionMultiKey(inputDataPointsInHands1, inputDataPointsForContract1, whoPlay1);
        helpFunctionMultiKey(inputDataPointsInHands2, inputDataPointsForContract2, whoPlay1);
        helpFunctionMultiKey(inputDataPointsInHands3, inputDataPointsForContract3, whoPlay1);
        helpFunctionMultiKey(inputDataPointsInHands4, inputDataPointsForContract4, whoPlay1);
        helpFunctionMultiKey(inputDataPointsInHands5, inputDataPointsForContract5, whoPlay4);
        helpFunctionMultiKey(inputDataPointsInHands6, inputDataPointsForContract6, whoPlay4);

        helpFunctionMultiKey(inputDataPointsInHands6, inputDataPointsForContract2, whoPlay1);
        helpFunctionMultiKey(inputDataPointsInHands6, inputDataPointsForContract3, whoPlay1);
        helpFunctionMultiKey(inputDataPointsInHands6, inputDataPointsForContract4, whoPlay1);


    }

    public void helpFunctionMultiKey(MultiKeyMap<Integer, Integer> inputDataPointsInHands,
                                     MultiKeyMap<Integer, Integer> inputDataPointsForContract,
                                     MultiKeyMap<Integer, Integer> whoPlay

    )
            throws BridgeException {

        for (Map.Entry<MultiKey<? extends Integer>, Integer> entryWp : whoPlay.entrySet()) {
            boolean wp1 = (entryWp.getKey().getKey(0) == 1);
            boolean wp2 = (entryWp.getKey().getKey(1) == 1);
            boolean wp3 = (entryWp.getKey().getKey(2) == 1);
            boolean wp4 = (entryWp.getKey().getKey(3) == 1);


            for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : inputDataPointsInHands.entrySet()) {
                int ph1 = entry.getKey().getKey(0);
                int ph2 = entry.getKey().getKey(1);
                int ph3 = entry.getKey().getKey(2);
                int ph4 = entry.getKey().getKey(3);
                for (Map.Entry<MultiKey<? extends Integer>, Integer> entry2 : inputDataPointsForContract.entrySet()) {
                    int pfc1 = entry2.getKey().getKey(0);
                    int pfc2 = entry2.getKey().getKey(1);
                    int pfc3 = entry2.getKey().getKey(2);
                    int pfc4 = entry2.getKey().getKey(3);

                    {
                        FourGameImpScorring rS = new FourGameImpScorring(wp1, wp2, wp3, wp4,
                                ph1, ph2, ph3, ph4, pfc1, pfc2, pfc3, pfc4,
                                false, false, false, false, false, false, false, false);
                        Integer res = rS.getSumm();
                        logger.info("Wynik dla całego robra z podanych punktów na ręku/wynik ("
                                + ph1 + "/" + pfc1 + "," + ph2 + "/" + pfc2 + "," + ph3 + "/" + pfc3 + "," + ph4 + "/" + pfc4 + ") jest: " + res + " \n");
                        Assert.assertEquals(testRubberFromPoints2MultiKey.get(inputDataPointsInHands, inputDataPointsForContract, whoPlay), res);
                    }
                    {
                        FourGameImpScorring rSfromPart = new FourGameImpScorring(22);
                        int result1 = rSfromPart.fillOneContract(1, wp1, ph1, pfc1, false, false);
                        int result2 = rSfromPart.fillOneContract(2, wp2, ph2, pfc2, false, false);
                        int result3 = rSfromPart.fillOneContract(3, wp3, ph3, pfc3, false, false);
                        int result4 = rSfromPart.fillOneContract(4, wp4, ph4, pfc4, false, false);
                        Integer summ = result1 + result2 + result3 + result4;
                        Assert.assertEquals(testRubberFromPoints2MultiKey.get(inputDataPointsInHands, inputDataPointsForContract, whoPlay), summ);
                    }


                }
            }


            for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : inputDataPointsInHands.entrySet()) {
                int ph1 = 40 - entry.getKey().getKey(0);
                int ph2 = 40 - entry.getKey().getKey(1);
                int ph3 = 40 - entry.getKey().getKey(2);
                int ph4 = 40 - entry.getKey().getKey(3);
                for (Map.Entry<MultiKey<? extends Integer>, Integer> entry2 : inputDataPointsForContract.entrySet()) {
                    int pfc1 = -entry2.getKey().getKey(0);
                    int pfc2 = -entry2.getKey().getKey(1);
                    int pfc3 = -entry2.getKey().getKey(2);
                    int pfc4 = -entry2.getKey().getKey(3);

                    if (entry.getValue().equals(Integer.parseInt("1"))) {
                        {
                            FourGameImpScorring rS = new FourGameImpScorring(wp1, wp2, wp3, wp4,
                                    ph1, ph2, ph3, ph4, pfc1, pfc2, pfc3, pfc4,
                                    false, false, false, false, false, false, false, false);
                            Integer res = -rS.getSumm();
                            logger.info("Wynik (rewers) dla całego robra z podanych punktów na ręku/wynik ("
                                    + ph1 + "/" + pfc1 + "," + ph2 + "/" + pfc2 + "," + ph3 + "/" + pfc3 + "," + ph4 + "/" + pfc4 + ") jest: " + res + " \n");
                            Assert.assertEquals(testRubberFromPoints2MultiKey.get(inputDataPointsInHands, inputDataPointsForContract, whoPlay), res);
                        }
                        {
                            FourGameImpScorring rSfromPart = new FourGameImpScorring(22);
                            int result1 = rSfromPart.fillOneContract(1, wp1, ph1, pfc1, false, false);
                            int result2 = rSfromPart.fillOneContract(2, wp2, ph2, pfc2, false, false);
                            int result3 = rSfromPart.fillOneContract(3, wp3, ph3, pfc3, false, false);
                            int result4 = rSfromPart.fillOneContract(4, wp4, ph4, pfc4, false, false);
                            Integer summ = -(result1 + result2 + result3 + result4);
                            Assert.assertEquals(testRubberFromPoints2MultiKey.get(inputDataPointsInHands, inputDataPointsForContract, whoPlay), summ);
                        }
                    }

                }
            }

        }
    }


}