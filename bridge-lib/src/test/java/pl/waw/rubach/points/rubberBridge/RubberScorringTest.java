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

public class RubberScorringTest {


    private static Logger logger = LoggerFactory.getLogger(RubberScorringTest.class);



    private MultiKeyMap<Integer, Integer> testRubberFromPoints = new MultiKeyMap<>();

    private MultiKeyMap<Integer, Integer> testRubberFromPoints1 = new MultiKeyMap<>();

    @Before
    public void fillTestPointsMap()  {

        testRubberFromPoints.put(3, 9, 4,  100+700);
        testRubberFromPoints.put(3, 9, 1,  290+700);

        testRubberFromPoints1.put(3, 9, 4,  150+700);
        testRubberFromPoints1.put(3, 9, 1,  290+700);

    }


    @Test
    public void fillOneContractOnlyWePlay() throws BridgeException {
        for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : testRubberFromPoints.entrySet()) {
            int ph1 = entry.getKey().getKey(0);
            int ph2 = entry.getKey().getKey(1);
            int ph3 = entry.getKey().getKey(2);
         //   int ph4 = entry.getKey().getKey(3);
//            int cS = entry.getKey().getKey(4);

            RubberScorring aa = new RubberScorring((2)); //uwaga istotna jest kolejność wpisów a nie numer!!
            Assert.assertFalse(aa.isAreWeVunerable());
            Assert.assertFalse(aa.isAreTheyVunerable());
            int a1=   aa.fillOneContract(true,ph1,"n",false,false,ph2);
            Integer res1 = aa.getOurWiningScorring();//aa
            Assert.assertTrue(aa.isAreWeVunerable());
            Assert.assertFalse(aa.isAreTheyVunerable());
            int a2=   aa.fillOneContract(true,ph3,"s",false,false,ph2);
            Integer res2 = aa.getOurWiningScorring();//aa

            int a3=   aa.fillOneContract(true,ph1,"n",false,false,ph2);
            Assert.assertTrue(aa.isAreWeVunerable());
            Assert.assertFalse(aa.isAreTheyVunerable());

            Integer res3 = aa.getOurWiningScorring();//aa.getSumm();
            logger.info("Wynik dla robra dla Nas z trzech rozdań (my gramy) (wysokość/liczba lew) (" + ph1 + "/" + ph2 + "," + ph3 + "/" + ph2 + " " + ph1 + "/" + ph2 +") " +
                    "i wyniku rozdania (oni bez partii): " +a1+" " + a2 + "  " +a3 +" "+ +res1 + ", "+ res2+ " ,"+ res3 + " \n");
            Assert.assertEquals(testRubberFromPoints.get(ph1, ph2, ph3), res3);

            RubberScorring bb = new RubberScorring((4));

            int b1 = bb.fillOneContract( true, ph3, "s", false, false, ph2);
            Integer res1b = bb.getOurWiningScorring();//aa
            Assert.assertFalse(bb.isAreWeVunerable());
            Assert.assertFalse(bb.isAreTheyVunerable());

            int b2 = bb.fillOneContract(true, ph1, "n", false, false, ph2);
            Integer res2b = bb.getOurWiningScorring();//aa
            Assert.assertTrue(bb.isAreWeVunerable());
            Assert.assertFalse(bb.isAreTheyVunerable());

            int b3 = bb.fillOneContract(true, ph1, "n", false, false, ph2);
            Assert.assertTrue(bb.isAreWeVunerable());
            Assert.assertFalse(bb.isAreTheyVunerable());


            Integer res3b = bb.getOurWiningScorring();//aa.getSumm();
            logger.info("Wynik2 dla robra dla Nas z trzech rozdań (my gramy) (wysokość/liczba lew) (" + ph3 + "/" + ph2 + "," + ph1 + "/" + ph2 +" "+ ph1 + "/" + ph2 + ") " +
                    "i wyniku rozdania (oni bez partii): " + b1 + " " + b2 + "  " + b3 + " " + +res1b + ", " + res2b + " ," + res3b + " \n");
            Assert.assertEquals(testRubberFromPoints1.get(ph1, ph2, ph3), res3b);


        }}

    @Test
    public void fillOneContractTheyPlay() throws BridgeException {
        for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : testRubberFromPoints.entrySet()) {
            int ph1 = entry.getKey().getKey(0);
            int ph2 = entry.getKey().getKey(1);
            int ph3 = entry.getKey().getKey(2);
       //     int ph4 = entry.getKey().getKey(3);
//            int cS = entry.getKey().getKey(4);

            RubberScorring aa = new RubberScorring((1)); //uwaga istotna jest kolejność wpisów a nie numer!! TODO poprawić?
            Assert.assertFalse(aa.isAreWeVunerable());
            Assert.assertFalse(aa.isAreTheyVunerable());

            int a1=   aa.fillOneContract(false,ph1,"n",false,false,ph2);
            Integer res1 = -aa.getOurWiningScorring();//aa

             Assert.assertFalse(aa.isAreWeVunerable());
            Assert.assertTrue(aa.isAreTheyVunerable());

            int a2=   aa.fillOneContract(false,ph3,"s",false,false,ph2);
            Integer res2 = -aa.getOurWiningScorring();//aa

            int a3=   aa.fillOneContract(false,ph1,"n",false,false,ph2);
            Assert.assertFalse(aa.isAreWeVunerable());
            Assert.assertTrue(aa.isAreTheyVunerable());

            Integer res3 = -aa.getOurWiningScorring();//aa.getSumm();
            logger.info("Wynik dla robra dla Nich z trzech rozdań (oni grają) (wysokość/liczba lew) (" + ph1 + "/" + ph2 + "," + ph3 + "/" + ph2 + "" +  ph1 + "/" + ph2 +  ") " +
                    "i wyniku rozdania dla nich! (my bez partii) : " +a1+" " + a2 + "  " +a3 +" "+ +res1 + ", "+ res2+ " ,"+ res3 + " \n");
            Assert.assertEquals(testRubberFromPoints.get(ph1, ph2, ph3), res3);

            RubberScorring bb = new RubberScorring((3));

            int b1 = bb.fillOneContract( false, ph3, "s", false, false, ph2);
            Integer res1b = -bb.getOurWiningScorring();//aa

            int b2 = bb.fillOneContract( false, ph1, "n", false, false, ph2);
            Integer res2b = -bb.getOurWiningScorring();//aa
            int b3 = bb.fillOneContract( false, ph1, "n", false, false, ph2);
            Integer res3b = -bb.getOurWiningScorring();//aa.getSumm();
            logger.info("Wynik2 dla robra dla Nich z trzech rozdań (oni grają) (wysokość/liczba lew) (" + ph3 + "/" + ph2 + "," + ph1 + "/" + ph2 + " "  + ph1 + "/" + ph2 + ") " +
                    "i wyniku rozdania dla nich (my bez partii): " + b1 + " " + b2 + "  " + b3 + " " + +res1b + ", " + res2b + " ," + res3b + " \n");
            Assert.assertEquals(testRubberFromPoints1.get(ph1, ph2, ph3), res3b);


        }}

    @Test
    public void fillOneContractBothPlay() throws BridgeException {
        for (Map.Entry<MultiKey<? extends Integer>, Integer> entry : testRubberFromPoints.entrySet()) {
            int ph1 = entry.getKey().getKey(0);
            int ph2 = entry.getKey().getKey(1);
            int ph3 = entry.getKey().getKey(2);
            //     int ph4 = entry.getKey().getKey(3);
//            int cS = entry.getKey().getKey(4);

            RubberScorring aa = new RubberScorring((11)); //uwaga istotna jest kolejność wpisów a nie numer!! TODO poprawić?
            Assert.assertFalse(aa.isAreWeVunerable());
            Assert.assertFalse(aa.isAreTheyVunerable());

            int a1=   aa.fillOneContract(true,ph1,"n",false,false,ph2);
            Integer res1 = aa.getOurWiningScorring();//aa

            Assert.assertTrue(aa.isAreWeVunerable());
            Assert.assertFalse(aa.isAreTheyVunerable());

            int a11=   aa.fillOneContract(false,ph1,"n",false,false,ph2);
            Integer res11 = -aa.getOurWiningScorring();//aa

            int a2=   aa.fillOneContract(true,ph3,"s",false,false,ph2);
            Integer res2 = aa.getOurWiningScorring();//aa

            int a3=   aa.fillOneContract(true,ph1,"n",false,false,ph2);
            Assert.assertTrue(aa.isAreWeVunerable());
            Assert.assertTrue(aa.isAreTheyVunerable());

            Integer res3 = aa.getOurWiningScorring()+300;//aa.getSumm();
            logger.info("Wynik dla robra dla Nas (wysokość/liczba lew) (my:" + ph1 + "/" + ph2 + ",(oni)"+ ph1 + "/" + ph2 + "," + ph3 + "/" + ph2 + ") " +
                    "i wyniku rozdania : my:" +a1+" oni:" +a11+" my:"+ a2 + "  my:" +a3 +" "+ res1 + ", "+ res11 + ", "+ res2+ " ,"+ (res3-300) + " \n");
            Assert.assertEquals(testRubberFromPoints.get(ph1, ph2, ph3), res3);
          //  Assert.assertEquals(testRubberFromPoints1.get(ph1, ph2, ph3), res3b);


        }}

}