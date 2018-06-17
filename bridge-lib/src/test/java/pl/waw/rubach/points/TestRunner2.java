package pl.waw.rubach.points;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner2 {
    public static void main(String[] args) {

//=================================Points counting not  Vulnerable Package Test
        Result resultCountingPointsTestBefore = JUnitCore.runClasses(CountingPointsForGameTestBefore.class);
        for (Failure failureGameModelCards : resultCountingPointsTestBefore.getFailures()) {
            System.out.println(failureGameModelCards.toString());
        }
        System.out.println("CountingPointsForGameTestBefore test: " + resultCountingPointsTestBefore.wasSuccessful());
//=======================================Basic


//=================================Points counting Vulnerable Package Test
       Result resultCountingPointsTestAfter = JUnitCore.runClasses(CountingPointsForGameTestAfter.class);
        for (Failure failureGameModelCards : resultCountingPointsTestAfter.getFailures()) {
            System.out.println(failureGameModelCards.toString());
        }
        System.out.println("CountingPointsForGameTestAfter test: " + resultCountingPointsTestAfter.wasSuccessful());
//=======================================Basic





}}