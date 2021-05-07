package pl.waw.rubach.points.rubberBridge;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.points.exceptions.BridgeException;

import java.util.Map;

public class CalculatedRubberPointsForOneDealBefore {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    protected boolean wePlay = true;
    protected boolean[] assumption = {false, false};

    protected MultiKeyMap<Integer, Integer> testCountingPointsDeclarerNoTrumph = new MultiKeyMap<>();
    protected MultiKeyMap<Integer, Integer> testCountingPointsDeclarerMajor = new MultiKeyMap<>();


    @Before
    public void fillTestPointsMap() {
        testCountingPointsDeclarerMajor.put(1,7,1,0,30);
        testCountingPointsDeclarerMajor.put(2,7,1,-50,0);
        testCountingPointsDeclarerMajor.put(3,9,1,0,90);
        testCountingPointsDeclarerMajor.put(3,8,1,-50,0);
        testCountingPointsDeclarerMajor.put(3,7,1,-100,0);
        testCountingPointsDeclarerMajor.put(4,10,1,0,120);
        testCountingPointsDeclarerMajor.put(4,12,1,60,120);
        testCountingPointsDeclarerMajor.put(4,12,2,250,240);


        testCountingPointsDeclarerNoTrumph.put(1, 9, 1, 60,40);
        testCountingPointsDeclarerNoTrumph.put(2, 9,  1,30,70);
        testCountingPointsDeclarerNoTrumph.put(3, 9, 1,0,100);
        testCountingPointsDeclarerNoTrumph.put(3, 8,  1,-50,0);
        testCountingPointsDeclarerNoTrumph.put(3, 7,  1,-100,0);
        testCountingPointsDeclarerNoTrumph.put(3, 10, 1,30,100);
        testCountingPointsDeclarerNoTrumph.put(4, 10, 1,0,130);
        testCountingPointsDeclarerNoTrumph.put(5, 11, 1,0,160);
        testCountingPointsDeclarerNoTrumph.put(6, 12, 1,500,190);
        testCountingPointsDeclarerNoTrumph.put(7, 13, 1,1000,220);

        testCountingPointsDeclarerNoTrumph.put(1, 9, 2,250,80);
        testCountingPointsDeclarerNoTrumph.put(2, 9,  2,150,140);
        testCountingPointsDeclarerNoTrumph.put(3, 9, 2,50,200);
        testCountingPointsDeclarerNoTrumph.put(3, 8,  2,-100,0);
        testCountingPointsDeclarerNoTrumph.put(3, 7,  2,-300,0);
        testCountingPointsDeclarerNoTrumph.put(3, 6,  2,-500,0);
        testCountingPointsDeclarerNoTrumph.put(3, 10, 2,150,200);
        testCountingPointsDeclarerNoTrumph.put(4, 10, 2,50,260);
        testCountingPointsDeclarerNoTrumph.put(5, 11, 2,50, 320);
        testCountingPointsDeclarerNoTrumph.put(6, 12, 2,550,380);
        testCountingPointsDeclarerNoTrumph.put(7, 13, 2,1050,440);

        testCountingPointsDeclarerNoTrumph.put(1, 9, 4,500,160);
        testCountingPointsDeclarerNoTrumph.put(2, 9,  4,300,280);

    }


    private String printAssumtion(boolean[] a) {
        return (a[0] && a[1]) ? " Obie Po " : (a[0] ? " My Po, Oni Przed " : (a[1] ? " My Przed, Oni Po " : " Obie Przed "));
    }
    private String printDoubleRedouble(int sig) {
        return (sig==2 ? " z kontrą " : (sig ==4  ? " z rekontrą " : " "));
    }


    private void testFunctionWePlay(MultiKeyMap<Integer, Integer> map, boolean[] assumption, String  suit) throws BridgeException {

         for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : map.entrySet()) {

            int contractLevel = entry.getKey().getKey(0);
             int tricksTaken = entry.getKey().getKey(1);
             int ndrSig = entry.getKey().getKey(2);
             int overPoints = entry.getKey().getKey(3);
             CalculatedOneDealRubberScoring we = new CalculatedOneDealRubberScoring( contractLevel,suit,ndrSig, assumption[0],tricksTaken);
             Integer resUnder = we.getDeclarerUnderPoints();
             int resOver = we.getDeclarerOverPoints();
             int scorrint = resOver + resUnder;
                logger.info("Dla  kontraktu " + contractLevel + "bez atu  "+printDoubleRedouble(ndrSig)+  printAssumtion(assumption)
                     +" oraz " + tricksTaken + " wziętych przez nas lew,"
                     + "  wynik pod kreską jest " + resUnder + " punktów, a nad kreską  "+ resOver
                     + " całkowity wynik dla Nas jest: "+ scorrint);

             Assert.assertEquals(overPoints, resOver);
             Assert.assertEquals(map.get(contractLevel, tricksTaken,ndrSig,overPoints), resUnder);
             Assert.assertEquals(scorrint, we.getContractScoringPointsWe());

         }
    }

    @Test
    public void testCountingPointsRes() throws BridgeException {
        testFunctionWePlay(testCountingPointsDeclarerNoTrumph, assumption,"nt");
        testFunctionWePlay(testCountingPointsDeclarerMajor, assumption,"s");
        testFunctionWePlay(testCountingPointsDeclarerMajor, assumption,"h");
    }

    private void testFunctionTheyPlay(MultiKeyMap<Integer, Integer> map, boolean wePlay,boolean[] assumption, String suit ) throws BridgeException {

        for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : map.entrySet()) {

            int contractLevel = entry.getKey().getKey(0);
            int tricksTaken = entry.getKey().getKey(1);
            int ndrSig = entry.getKey().getKey(2);
            int overPoints = entry.getKey().getKey(3);
            CalculatedOneDealRubberScoring they = new CalculatedOneDealRubberScoring( wePlay,contractLevel,suit,ndrSig, assumption[0],assumption[1],13-tricksTaken);
            Integer resUnder = they.getDeclarerUnderPoints();
            int resOver = they.getDeclarerOverPoints();
            int scorrint =-(resOver + resUnder);
              logger.info("Oni grają : Dla  kontraktu " + contractLevel + "bez atu  "+printDoubleRedouble(ndrSig)+  printAssumtion(assumption)
                    +" oraz " + tricksTaken + " wziętych przez nich lew,"
                    + " ich wynik pod kreską jest " + resUnder + " punktów, a nad kreską  "+ resOver
                     + " całkowity wynik dla Nas jest: "+ scorrint);

            Assert.assertEquals(overPoints, resOver);
            Assert.assertEquals(map.get(contractLevel, tricksTaken,ndrSig,overPoints), resUnder);
            Assert.assertEquals(scorrint, they.getContractScoringPointsWe());

        }
    }



    @Test
    public void testCountingPointsResThey() throws BridgeException {
        wePlay = false;

        testFunctionTheyPlay(testCountingPointsDeclarerNoTrumph, wePlay,assumption, "nt");
        testFunctionTheyPlay(testCountingPointsDeclarerMajor,wePlay, assumption,"s");
        testFunctionTheyPlay(testCountingPointsDeclarerMajor,wePlay, assumption,"h");


    }


}
