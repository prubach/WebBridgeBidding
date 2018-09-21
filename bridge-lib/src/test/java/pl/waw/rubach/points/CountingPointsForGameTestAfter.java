package pl.waw.rubach.points;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CountingPointsForGameTestAfter {

    private static Logger logger = LoggerFactory.getLogger(CountingPointsForGameTestAfter.class);

    boolean beforeAfter = true;

    private Map<Integer, Integer> testPointsForMajorContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForMinorContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForNoTrumphContract = new HashMap<>();

    private Map<Integer, Integer> testPointsForMajorDoubleContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForMinorDoubleContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForNoTrumphDoubleContract = new HashMap<>();

    private Map<Integer, Integer> testPointsForMajorReDoubleContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForMinorReDoubleContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForNoTrumphReDoubleContract = new HashMap<>();

    private Map<Integer, Integer> testPointsForMajorContractOneMore = new HashMap<>();

    private Map<Integer, Integer> testPointsForMinorContractOneMoreDouble = new HashMap<>();

    private Map<Integer, Integer> testPointsForMajorContractMinusOne = new HashMap<>();
    private Map<Integer, Integer> testPointsForNoTrumphContractMinusOneDouble = new HashMap<>();
    private Map<Integer, Integer> testNoTrumphContractPointsOneMoreDouble = new HashMap<>();
    private Map<Integer, Integer> testPointsForMajorContractMinusMore = new HashMap<>();

    private Map<Integer, Integer>  testPointsForMajorContractMinusMoreDouble = new HashMap<>();

    @Before
    public void fillTestPointsMap() {
        //testPointsMap.put()
        int karaZaKontre = 50;
        int premiaZaCzesciowke = 50;
        int premiaZaKoncowke = 500;
        int premiaZaSzlemika = 750;
        int premiaZaSzlema = 1500;
        int nadrobkaZKontra = 200;

        testPointsForMajorContract.put(1, 30 + premiaZaCzesciowke);
        testPointsForMajorContract.put(2, 60 + premiaZaCzesciowke);
        testPointsForMajorContract.put(3, 90 + premiaZaCzesciowke);
        testPointsForMajorContract.put(4, 120 + premiaZaKoncowke );
        testPointsForMajorContract.put(5, 150 + premiaZaKoncowke);
        testPointsForMajorContract.put(6, 180 + premiaZaKoncowke + premiaZaSzlemika );
        testPointsForMajorContract.put(7, 210+ premiaZaKoncowke + premiaZaSzlema);

        testPointsForMinorContract.put(1, 20 + premiaZaCzesciowke);
        testPointsForMinorContract.put(2, 40 + premiaZaCzesciowke);
        testPointsForMinorContract.put(3, 60 + premiaZaCzesciowke);
        testPointsForMinorContract.put(4, 80 + premiaZaCzesciowke);
        testPointsForMinorContract.put(5, 100 + premiaZaKoncowke);
        testPointsForMinorContract.put(6, 120 + premiaZaKoncowke + premiaZaSzlemika );
        testPointsForMinorContract.put(7, 140 + premiaZaKoncowke + premiaZaSzlema);

        testPointsForNoTrumphContract.put(1, 40 + premiaZaCzesciowke);
        testPointsForNoTrumphContract.put(2, 70 + premiaZaCzesciowke);
        testPointsForNoTrumphContract.put(3, 100 + premiaZaKoncowke);
        testPointsForNoTrumphContract.put(4, 130 + premiaZaKoncowke);
        testPointsForNoTrumphContract.put(5, 160 + premiaZaKoncowke);
        testPointsForNoTrumphContract.put(6, 190 + premiaZaKoncowke +premiaZaSzlemika );
        testPointsForNoTrumphContract.put(7, 220 +premiaZaKoncowke + premiaZaSzlema);

        //example of double contract


        testPointsForMajorDoubleContract.put(1, 60 + karaZaKontre + premiaZaCzesciowke);
        testPointsForMajorDoubleContract.put(2, 120 +premiaZaKoncowke + karaZaKontre);
        testPointsForMinorDoubleContract.put(1, 40 + karaZaKontre + premiaZaCzesciowke);
        testPointsForMinorDoubleContract.put(2, 80 + karaZaKontre + premiaZaCzesciowke);
        testPointsForNoTrumphDoubleContract.put(1, 80 + karaZaKontre + premiaZaCzesciowke);
        testPointsForNoTrumphDoubleContract.put(2, 140 +premiaZaKoncowke  + karaZaKontre);
        testPointsForNoTrumphDoubleContract.put(3, 200 +premiaZaKoncowke  + karaZaKontre);
        testPointsForNoTrumphDoubleContract.put(4, 260 +premiaZaKoncowke  + karaZaKontre);

        testNoTrumphContractPointsOneMoreDouble.put(1, 2*40 + nadrobkaZKontra+  karaZaKontre + premiaZaCzesciowke);
        testNoTrumphContractPointsOneMoreDouble.put(2, 140 + nadrobkaZKontra   +premiaZaKoncowke  + karaZaKontre);
        testNoTrumphContractPointsOneMoreDouble.put(3, 200 + nadrobkaZKontra+ premiaZaKoncowke  + karaZaKontre);
        testNoTrumphContractPointsOneMoreDouble.put(4, 260 + nadrobkaZKontra+ premiaZaKoncowke  + karaZaKontre);

        //example of redouble contract

        testPointsForMajorReDoubleContract.put(1, 120 + premiaZaKoncowke + karaZaKontre);
        testPointsForMajorReDoubleContract.put(2, 240 + premiaZaKoncowke  + karaZaKontre);
        testPointsForMinorReDoubleContract.put(1, 80 + karaZaKontre + premiaZaCzesciowke);
        testPointsForMinorReDoubleContract.put(2, 160+  premiaZaKoncowke + karaZaKontre);
        testPointsForNoTrumphReDoubleContract.put(1, 160 + premiaZaKoncowke  + karaZaKontre);
        testPointsForNoTrumphReDoubleContract.put(2, 280 +premiaZaKoncowke + karaZaKontre);



        testPointsForMajorContractOneMore.put(1, 60 + premiaZaCzesciowke);
        testPointsForMajorContractOneMore.put(2, 90 + premiaZaCzesciowke);
        testPointsForMajorContractOneMore.put(3, 120 + premiaZaCzesciowke);
        testPointsForMajorContractOneMore.put(4, 150 + premiaZaKoncowke);
        testPointsForMajorContractOneMore.put(5, 180 + premiaZaKoncowke);
        testPointsForMajorContractOneMore.put(6, 210 +premiaZaKoncowke + premiaZaSzlemika);
    //    testPointsForMajorContractOneMore.put(7, 240 +500); nie ma sensu 7 i jedna lepiej :)


        testPointsForMinorContractOneMoreDouble.put(1, 2*(20)+nadrobkaZKontra + premiaZaCzesciowke  + karaZaKontre); //kontrakt np 1karo + 1 lewa z kontrą (20)*2 +100 za nadróbkę + 50 nieudaną kontrę + 300 premi za końcówkę
        testPointsForMinorContractOneMoreDouble.put(2, 2*(40)+nadrobkaZKontra + premiaZaCzesciowke  + karaZaKontre);
        testPointsForMinorContractOneMoreDouble.put(3, 2*(60)+nadrobkaZKontra +  premiaZaKoncowke  + karaZaKontre);
        testPointsForMinorContractOneMoreDouble.put(4, 2*(80)+nadrobkaZKontra +  premiaZaKoncowke  + karaZaKontre);
        testPointsForMinorContractOneMoreDouble.put(5, 2*(100)+nadrobkaZKontra +  premiaZaKoncowke  + karaZaKontre);
        testPointsForMinorContractOneMoreDouble.put(6, 2*(120)+nadrobkaZKontra +  premiaZaKoncowke  + premiaZaSzlemika  + karaZaKontre); //20*6 =120 *2 = 240 + 100 nadróbka = 340
     //   testPointsForMinorContractOneMoreDouble.put(7, 340+50+500+1500);  nie ma sensu 7 i jedna lepiej :)


        testPointsForMajorContractMinusOne.put(1, -100);
        testPointsForMajorContractMinusOne.put(2, -100);

        testPointsForMajorContractMinusMore.put(1, -100);
        testPointsForMajorContractMinusMore.put(2, -200);
        testPointsForMajorContractMinusMore.put(3, -300);


        testPointsForNoTrumphContractMinusOneDouble.put(1, -200);
        testPointsForNoTrumphContractMinusOneDouble.put(2, -200);

        testPointsForMajorContractMinusMoreDouble.put(1, -200);
        testPointsForMajorContractMinusMoreDouble.put(2, -500);
        testPointsForMajorContractMinusMoreDouble.put(3, -800);
        testPointsForMajorContractMinusMoreDouble.put(4, -1100);

    }


    @Test
    public void testMajorContractPoints() throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForMajorContract.keySet())) {
            Integer res = new PointsForContract(p,p,"s",false,false,beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContract.get(p), res);
        }
    }

    @Test
    public void testMinorContractPoints() throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForMinorContract.keySet())) {
            Integer res = new PointsForContract(p,p,"d",false,false,beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContract.get(p), res);
        }
    }


    @Test
    public void testNoTrumphContractPoints()  throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphContract.keySet())) {
            Integer res = new PointsForContract(p,p,"n",false,false,beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphContract.get(p), res);
        }
    }


    @Test
    public void testMajorDoubleContractPoints()  throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForMajorDoubleContract.keySet())) {
            Integer res = new PointsForContract(p,p,"s",true,false,beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym z kontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorDoubleContract.get(p), res);
        }
    }

    @Test
    public void testMinorDoubleContractPoints()  throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForMinorDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p,"d",true,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z kotrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorDoubleContract.get(p), res);
        }
    }


    @Test
    public void testNoTrumphDoubleContractPoints()  throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p,"n",true,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu z kontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphDoubleContract.get(p), res);
        }
    }

    @Test
    public void testMajorReDoubleContractPoints()  throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForMajorReDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p,"s",false,true, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym z rekontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorReDoubleContract.get(p), res);
        }
    }

    @Test
    public void testMinorReDoubleContractPoints()  throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForMinorReDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p,"d",false,true, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z rekotrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorReDoubleContract.get(p), res);
        }
    }


    @Test
    public void testNoTrumphReDoubleContractPoints()  throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphReDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p,"n",false,true, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu z rekontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphReDoubleContract.get(p), res);
        }
    }



    @Test
    public void testMajorContractOneMore()  throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
            for (int p : new TreeSet<Integer>(testPointsForMajorContractOneMore.keySet())) {
                Integer res = new PointsForContract(p, p + 1, "s", false, false, beforeAfter).getCalculatedPointsForContract();
                logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym  z jedną nadróbką wynik jest: " + res + " punktów.");
                Assert.assertEquals(testPointsForMajorContractOneMore.get(p), res);
         //   }
        }
    }


    @Test
    public void testMinorContractPointsOneMoreDouble()  throws InvalidContractLevelException {
    //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMinorContractOneMoreDouble.keySet())) {
            Integer res = new PointsForContract(p, p+1 ,"d",true,false, beforeAfter).getCalculatedPointsForContract();
                 logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z kotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContractOneMoreDouble.get(p), res);
      //  }
    }}



    @Test
    public void testNoTrumphContractPointsOneMoreDouble()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsOneMoreDouble.keySet())) {
            Integer res = new PointsForContract(p, p+1 ,"nt",true,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu z kotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsOneMoreDouble.get(p), res);
            //  }
        }}

    @Test
    public void testMajorContractMinusOne()  throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajorContractMinusOne.keySet())) {
            Integer res = new PointsForContract(p,  p-1, "s", false, false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości "+ p +" w kolorze starszym  bez  jednej wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContractMinusOne.get(p), res);
            //   }
        }
    }

    @Test
    public void testMajorContractMinusMore()  throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajorContractMinusMore.keySet())) {
            Integer res = new PointsForContract(3, 3 - p, "s", false, false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości 3 w kolorze starszym  bez " + p +" wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContractMinusMore.get(p), res);
            //   }
        }
    }

    @Test
    public void testNoTrumphContractMinusOneDouble()  throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphContractMinusOneDouble.keySet())) {
            Integer res = new PointsForContract(p, p - 1, "n", true, false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu  bez jednej z kontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphContractMinusOneDouble.get(p), res);
            //   }
        }
    }

    @Test
    public void testNoTrumphContractMinusMoreDouble()  throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajorContractMinusMoreDouble.keySet())) {
            Integer res = new PointsForContract(3, 3 - p, "s", true, false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości 3 w starszym  bez  "+ p+ "  z kontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContractMinusMoreDouble.get(p), res);
            //   }
        }
    }
}
