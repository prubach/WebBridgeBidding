package pl.waw.rubach.points;

import org.junit.Before;

public class CalculatedImpPointsForOneDealAfterTheyPlay extends CalculatedImpPointsForOneDealAfterWePlay {

    @Before
    public void fillTestPointsMap() {
        wePlay = false;
       // a = wePlay ? 1 : -1;
        super.fillTestPointsMap();
    }
}
