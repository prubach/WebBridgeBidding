package pl.waw.rubach.points;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner2 {
    public static void main(String[] args) {

//=================================Points counting  Package Test
        Result resultCountingPointsTest = JUnitCore.runClasses(CountingPointsForGameTest.class);
        for (Failure failureGameModelCards : resultCountingPointsTest.getFailures()) {
            System.out.println(failureGameModelCards.toString());
        }
        System.out.println("CountingPointsForGameTest test: " + resultCountingPointsTest.wasSuccessful());
//=======================================Basic







}}