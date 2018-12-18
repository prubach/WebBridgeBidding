package pl.waw.rubach.points;

import org.junit.Before;
import org.junit.Test;
import pl.waw.rubach.points.exceptions.InvalidNumberOfPointsException;
import pl.waw.rubach.points.exceptions.InvalidParameterException;

//TODO could be not separet class? - :)   test are good I think
public class CalculatedImpPointsForOneDealBeforeTheyPlay extends CalculatedImpPointsForOneDealBeforeWePlay {

    @Before
    public void fillTestPointsMap() {
        wePlay =false;
       // a = wePlay ? 1 : -1;
        //TODO  4 tests failing!
        super.fillTestPointsMap();
    }

    @Test
    public void testCountingPointsRes() throws InvalidNumberOfPointsException, InvalidParameterException {
        testFunction1(testCountingPointsNoFitBothMap, a, wePlay, assumption, false, false);
         //  testFunction2(testCountingPointsNoFitBothMap, a, wePlay, assumption, false, false);
    }

    @Test
    public void testCountingPointsFitWeRes() throws InvalidNumberOfPointsException, InvalidParameterException {
        testFunction1(testCountingPointsFitWeMap, a, wePlay, assumption, true, false);
        //  testFunction2(testCountingPointsFitWeMap, a, wePlay, assumption, true, false);
    }


    @Test
    public void testCountingPointsFitTheyRes() throws InvalidNumberOfPointsException, InvalidParameterException {
        testFunction1(testCountingPointsFitTheyMap, a, wePlay, assumption, false, true);
        //  testFunction2(testCountingPointsFitTheyMap, a, wePlay, assumption, false, true);
    }

    @Test
    public void testCountingPointsBothFitRes() throws InvalidNumberOfPointsException, InvalidParameterException {
        testFunction1(testCountingPointsFitBothMap, a, wePlay, assumption, true, true);
        // testFunction2(testCountingPointsFitBothMap, a, wePlay, assumption, true, true);

    }


}
