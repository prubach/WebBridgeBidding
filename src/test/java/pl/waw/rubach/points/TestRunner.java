package pl.waw.rubach.points;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner {
    public static void main(String[] args) {

//=================================Points counting  Package Test
        Result resultCountingPointsTest = JUnitCore.runClasses(CountingPointsTest.class);
        for (Failure failureGameModelCards : resultCountingPointsTest.getFailures()) {
            System.out.println(failureGameModelCards.toString());
        }
        System.out.println("CountingPointsTest test: " + resultCountingPointsTest.wasSuccessful());
//=======================================Basic







}}