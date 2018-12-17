package pl.waw.rubach.points;

import org.junit.Before;
//TODO could be not separet class? - :)   test are good I think
public class CalculatedImpPointsForOneDealBeforTheyPlay extends CalculatedImpPointsForOneDealBeforWePLay {

    @Before
    public void fillTestPointsMap() {
        wePlay =false;
        // super.fillTestPointsMap();
    }
}
