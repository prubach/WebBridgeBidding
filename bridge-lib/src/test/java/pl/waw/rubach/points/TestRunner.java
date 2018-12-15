package pl.waw.rubach.points;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner {
    public static void main(String[] args) {

//=================================Points counting first:   Imp and Expected Points test
        Result resultImpExpTest = JUnitCore.runClasses(CalculatedImpPointsForOneDealBasicTest.class);
        for (Failure failureGameModelCards : resultImpExpTest.getFailures()) {
            System.out.println(failureGameModelCards.toString());
        }
        System.out.println("Imp and ExpectedPoints Test test: " + resultImpExpTest.wasSuccessful());

//=======================================Points counting Basic - taking points in both hands and points for contract giving ipm points

        Result resultCountingPointsTest = JUnitCore.runClasses(CalculatedImpPointsForOneDealBasicTest.class);
        for (Failure failureGameModelCards : resultCountingPointsTest.getFailures()) {
            System.out.println(failureGameModelCards.toString());
        }
        System.out.println("CalculatedImpPointsForOneDealBasicTest test: " + resultCountingPointsTest.wasSuccessful());

//=======================================Points counting BothPlay - taking points in both hands and points for contract giving ipm points

        Result resultCountingPointsBothPlayTest = JUnitCore.runClasses(CalculatedImpPointsForOneDealAdvanceTest.class);
        for (Failure failureGameModelCards : resultCountingPointsBothPlayTest.getFailures()) {
            System.out.println(failureGameModelCards.toString());
        }
        System.out.println("CalculatedImpPointsForOneDealAdvanceTest test: " + resultCountingPointsBothPlayTest.wasSuccessful());


//=================================Points for contract  counting not  Vulnerable Package Test
        Result resultCountingPointsTestBefore = JUnitCore.runClasses(PointsForGameTestBefore.class);
        for (Failure failureCountingPointsTestBefore : resultCountingPointsTestBefore.getFailures()) {
            System.out.println(failureCountingPointsTestBefore.toString());
        }
        System.out.println("PointsForGameTestBefore test: " + resultCountingPointsTestBefore.wasSuccessful());


//=================================Points for contract counting Vulnerable Package Test
        Result resultCountingPointsTestAfter = JUnitCore.runClasses(PointsForGameTestAfter.class);
        for (Failure failureCountingPointsTestAfter : resultCountingPointsTestAfter.getFailures()) {
            System.out.println(failureCountingPointsTestAfter.toString());
        }
        System.out.println("PointsForGameTestAfter test: " + resultCountingPointsTestAfter.wasSuccessful());

//=======================================Rubberscoring
        Result resultRubberScoringTest = JUnitCore.runClasses(RubberScoringTest.class);
        for (Failure failureRubberScoringTest : resultRubberScoringTest.getFailures()) {
            System.out.println(failureRubberScoringTest.toString());
        }
        System.out.println("Rubber Scorring  test: " + resultRubberScoringTest.wasSuccessful());

    }
}