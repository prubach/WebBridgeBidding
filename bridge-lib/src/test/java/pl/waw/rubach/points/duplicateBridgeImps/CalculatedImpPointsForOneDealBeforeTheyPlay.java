package pl.waw.rubach.points.duplicateBridgeImps;

import org.junit.Before;

//TODO could be not separet class? - :)   test are good I think
public class CalculatedImpPointsForOneDealBeforeTheyPlay extends CalculatedImpPointsForOneDealBeforeWePlay {

    @Before
    public void fillTestPointsMap() {
        wePlay =false;
        super.fillTestPointsMap();
    }




}
