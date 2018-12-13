package pl.waw.rubach.points;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RubberScoringTest {

    private static Logger logger = LoggerFactory.getLogger(RubberScoringTest.class);





    @Test
    public void testRubberScoringTest() throws BridgeException {

        RubberScoring a = new RubberScoring(20,21,22,23,110,110,110,110,false,false,false,false,false,false,false,false);

        //System.out.println("Końcowy wynik jest: "+ a.getSumm(a) +" \n");
        Assert.assertEquals(a.getSumm(), 6);
        RubberScoring a2 = new RubberScoring(20,19,18,17,-110,-110,-110,-110,false,false,false,false,false,false,false,false);
       // System.out.println("Końcowy wynik jest: "+ a.getSumm(a2) +" \n");

        Assert.assertEquals(a2.getSumm(), -6);
    }



    @Test
    public void testRubberScoringTest2() throws BridgeException {

        RubberScoring a = new RubberScoring(1, "nt", 1,"nt", 3,"nt",3,"nt",20,21,22,23,7,6,8,9,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);

        //System.out.println("Końcowy wynik jest: "+ a.getSumm(a) +" \n");
        Assert.assertEquals(a.getSumm(), 6);

        RubberScoring a1 = new RubberScoring(1, "nt", 1,"nt", 6,"nt",6,"nt",12,28,15,35,7,6,1,12,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
        //System.out.println("Końcowy wynik jest: "+ a.getSumm(a) +" \n");
      //  Assert.assertEquals(a1.getSumm(), 0);

        RubberScoring a2 = new RubberScoring(1, "nt", 1,"nt", 1,"nt",1,"nt",20,21,22,23,7,6,8,9,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
        // System.out.println("Końcowy wynik jest: "+ a.getSumm(a2) +" \n");

        Assert.assertEquals(a2.getSumm(), 2);
    }

}
