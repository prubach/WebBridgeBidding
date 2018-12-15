package pl.waw.rubach.points;

import org.junit.Before;

public class CalculatedImpPointsForOneDealBasicTestTheyPlay extends  CalculatedImpPointsForOneDealBasicTestWePLay{

   @Before
    public void fillTestPointsMap() {

        whoPlay=false;
        testCountingPointsBothBeforNoFitMap.put(28f, 990f, -11);
        testCountingPointsBothBeforNoFitMap.put(29f, -50f, 10);
        testCountingPointsBothBeforNoFitMap.put(11f, 50f, -10);

        testCountingPointsBothBeforNoFitMap.put(12f, -990f, 11);
        testCountingPointsBothBeforNoFitMap.put(20f, 400f, -9);
        testCountingPointsBothBeforNoFitMap.put(22f, 400f, -8);
        testCountingPointsBothBeforNoFitMap.put(23f, 400f, -7);
        testCountingPointsBothBeforNoFitMap.put(28f, 400f, 0);

       //Fit only we - they no fit
        testCountingPointsBothAfterFitWeMap.put(20f, 0f, 2);

        testCountingPointsBothAfterFitWeMap.put(24f, 500f, -2);
        testCountingPointsBothAfterFitWeMap.put(24f, 300f, 4);
        testCountingPointsBothAfterFitWeMap.put(24f, 100f, 8);
        testCountingPointsBothAfterFitWeMap.put(24f, -100f, 11);
        testCountingPointsBothAfterFitWeMap.put(24f, -500f, 14);

        testCountingPointsBothAfterFitWeMap.put(30f, 100f, 12);
        testCountingPointsBothAfterFitWeMap.put(12f, -1660f, 14);
        testCountingPointsBothAfterFitWeMap.put(28f, 1660f, -14);

        testCountingPointsBothAfterFitWeMap.put(28f, 1660f, -14);
        testCountingPointsBothAfterFitWeMap.put(12f, -1660f, 14);

        //both fit
      //  testCountingPointsBothAfterFitBoth.put(20f, 0f, 2);  //not such case if both have 20PC and fit in major color mark only spades (one have fit)
        testCountingPointsBothAfterFitBoth.put(30f, 750f, 0);
        testCountingPointsBothAfterFitBoth.put(10f, -750f, 0);
        testCountingPointsBothAfterFitBoth.put(30f, 1250f, -11);
        testCountingPointsBothAfterFitBoth.put(10f, -1250f, 11);

        //both no fit
        testCountingPointsBothAfterNoFitBothMap.put(20f, 0f, 0);

        testCountingPointsBothAfterNoFitBothMap.put(10f, -660f, 0);
        testCountingPointsBothAfterNoFitBothMap.put(10f, -100f, -11);
        testCountingPointsBothAfterNoFitBothMap.put(30f, 660f, 0);
        testCountingPointsBothAfterNoFitBothMap.put(30f, 100f, 11);

        //Fit only they  - we not fit
        testCountingPointsBothAfterFitTheyMap.put(20f, 0f, -2);

        testCountingPointsBothAfterFitTheyMap.put(16f, -500f, 2);
        testCountingPointsBothAfterFitTheyMap.put(16f, -300f, -4);
        testCountingPointsBothAfterFitTheyMap.put(16f, -100f, -8);
        testCountingPointsBothAfterFitTheyMap.put(16f, 100f, -11);
        testCountingPointsBothAfterFitTheyMap.put(16f, 500f, -14);

        testCountingPointsBothAfterFitTheyMap.put(10f, -100f, -12);
        testCountingPointsBothAfterFitTheyMap.put(28f, 1660f, -14);
        testCountingPointsBothAfterFitTheyMap.put(12f, -1660f, 14);

        testCountingPointsBothAfterFitTheyMap.put(28f, -1660f, 20);
        testCountingPointsBothAfterFitTheyMap.put(12f, 1660f, -20);
  }



}
