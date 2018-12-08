package pl.waw.rubach.points;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RubberScoringTest {

    private static Logger logger = LoggerFactory.getLogger(RubberScoringTest.class);





    @Test
    public void testRubberScoringTest() throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        RubberScoring a = new RubberScoring(20,21,22,23,110,110,110,110,false,false,false,false,false,false,false,false);

        //System.out.println("Końcowy wynik jest: "+ a.getSumm(a) +" \n");
        Assert.assertEquals(RubberScoring.getSumm(a), 6);
        RubberScoring a2 = new RubberScoring(20,19,18,17,-110,-110,-110,-110,false,false,false,false,false,false,false,false);
       // System.out.println("Końcowy wynik jest: "+ a.getSumm(a2) +" \n");

        Assert.assertEquals(RubberScoring.getSumm(a2), -6);
    }



}
