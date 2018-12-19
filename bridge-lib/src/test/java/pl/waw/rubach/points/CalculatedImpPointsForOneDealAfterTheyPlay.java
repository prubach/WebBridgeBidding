package pl.waw.rubach.points;

import org.junit.Before;

public class CalculatedImpPointsForOneDealAfterTheyPlay extends CalculatedImpPointsForOneDealAfterWePlay {

    @Before
    public void fillTestPointsMap() {
        wePlay = false;
        super.fillTestPointsMap();
    }
}
