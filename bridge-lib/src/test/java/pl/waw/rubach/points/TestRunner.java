package pl.waw.rubach.points;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner {
    public static void main(String[] args) {

//=================================Points counting first:   Imp and Expected Points test
        Result resultImpExpTest = JUnitCore.runClasses(pl.waw.rubach.points.CountingPointsTest.class);
        for (Failure failureGameModelCards : resultImpExpTest.getFailures()) {
            System.out.println(failureGameModelCards.toString());
        }
        System.out.println("Imp and ExpectedPoints Test test: " + resultImpExpTest.wasSuccessful());

//=======================================Points counting Basic - taking points in both hands and points for contract giving ipm points

        Result resultCountingPointsTest = JUnitCore.runClasses(pl.waw.rubach.points.CountingPointsTest.class);
        for (Failure failureGameModelCards : resultCountingPointsTest.getFailures()) {
            System.out.println(failureGameModelCards.toString());
        }
        System.out.println("CountingPointsTest test: " + resultCountingPointsTest.wasSuccessful());


//=================================Points for contract  counting not  Vulnerable Package Test
        Result resultCountingPointsTestBefore = JUnitCore.runClasses(CountingPointsForGameTestBefore.class);
        for (Failure failureCountingPointsTestBefore : resultCountingPointsTestBefore.getFailures()) {
            System.out.println(failureCountingPointsTestBefore.toString());
        }
        System.out.println("CountingPointsForGameTestBefore test: " + resultCountingPointsTestBefore.wasSuccessful());


//=================================Points for contract counting Vulnerable Package Test
        Result resultCountingPointsTestAfter = JUnitCore.runClasses(CountingPointsForGameTestAfter.class);
        for (Failure failureCountingPointsTestAfter : resultCountingPointsTestAfter.getFailures()) {
            System.out.println(failureCountingPointsTestAfter.toString());
        }
        System.out.println("CountingPointsForGameTestAfter test: " + resultCountingPointsTestAfter.wasSuccessful());
//=======================================Basic


    }
}