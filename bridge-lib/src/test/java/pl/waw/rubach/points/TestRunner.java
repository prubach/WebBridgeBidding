package pl.waw.rubach.points;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner {
    public static void main(String[] args) {

//=================================Points counting first:   Imp and Expected Points test
        Result resultImpExpTest = JUnitCore.runClasses(ImpExpTableTest.class);
        for (Failure failureImpExpTest : resultImpExpTest.getFailures()) {
            System.out.println(failureImpExpTest.toString());
        }
        System.out.println("Imp and ExpectedPoints Test test: " + resultImpExpTest.wasSuccessful());

        //=================================Points counting first:   Imp and Expected Points test
        Result resultCalculatedImpPointsForOneDealBeforeWePlay = JUnitCore.runClasses(CalculatedImpPointsForOneDealBeforeWePlay.class);
        for (Failure failureCalculatedImpPointsForOneDealBeforeWePlay : resultCalculatedImpPointsForOneDealBeforeWePlay.getFailures()) {
            System.out.println(failureCalculatedImpPointsForOneDealBeforeWePlay.toString());
        }
        System.out.println("CalculatedImpPointsForOneDealBeforeWePlay test: " + resultCalculatedImpPointsForOneDealBeforeWePlay.wasSuccessful());


//=======================================Points counting Basic - taking points in both hands and points for contract giving ipm points

        Result resultCountingPointsTest = JUnitCore.runClasses(CalculatedImpPointsForOneDealBeforeWePlay.class);
        for (Failure failureGameModelCards : resultCountingPointsTest.getFailures()) {
            System.out.println(failureGameModelCards.toString());
        }
        System.out.println("CalculatedImpPointsForOneDealBeforeWePlay test: " + resultCountingPointsTest.wasSuccessful());

//=======================================Points counting BothPlay - taking points in both hands and points for contract giving ipm points

        Result resultCountingPointsBothPlayTest = JUnitCore.runClasses(CalculatedImpPointsForOneDealAdvanceTestBefore.class);
        for (Failure failureGameModelCards : resultCountingPointsBothPlayTest.getFailures()) {
            System.out.println(failureGameModelCards.toString());
        }
        System.out.println("CalculatedImpPointsForOneDealAdvanceTestBefore test: " + resultCountingPointsBothPlayTest.wasSuccessful());


//=================================Points for contract  counting not  Vulnerable Package Test
        Result resultCountingPointsTestBefore = JUnitCore.runClasses(DuplicateBridgeScoringTestBefore.class);
        for (Failure failureCountingPointsTestBefore : resultCountingPointsTestBefore.getFailures()) {
            System.out.println(failureCountingPointsTestBefore.toString());
        }
        System.out.println("DuplicateBridgeScoringTestBefore test: " + resultCountingPointsTestBefore.wasSuccessful());


//=================================Points for contract counting Vulnerable Package Test
        Result resultCountingPointsTestAfter = JUnitCore.runClasses(DuplicateBridgeScoringTestAfter.class);
        for (Failure failureCountingPointsTestAfter : resultCountingPointsTestAfter.getFailures()) {
            System.out.println(failureCountingPointsTestAfter.toString());
        }
        System.out.println("DuplicateBridgeScoringTestAfter test: " + resultCountingPointsTestAfter.wasSuccessful());

//=======================================Rubberscoring
        Result resultRubberScoringTest = JUnitCore.runClasses(FourGameImpScorringTest.class);
        for (Failure failureRubberScoringTest : resultRubberScoringTest.getFailures()) {
            System.out.println(failureRubberScoringTest.toString());
        }
        System.out.println("Rubber Scorring  test: " + resultRubberScoringTest.wasSuccessful());


    //=======================================Rubberscoring
    Result resultRubberScoringFirstTest = JUnitCore.runClasses(FourGameImpScorringFirstTest.class);
        for (Failure failureRubberScoringFirstTest : resultRubberScoringFirstTest.getFailures()) {
        System.out.println(failureRubberScoringFirstTest.toString());
    }
        System.out.println("Rubber Scorring  test: " + resultRubberScoringFirstTest.wasSuccessful());

}
}