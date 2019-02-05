package pl.waw.rubach.points;

import org.junit.Before;

public class CalculatedImpPointsForOneDealAfterWePlay extends CalculatedImpPointsForOneDealBeforeWePlay {

   @Before
    public void fillTestPointsMap() {
       wePlay = true;
       assumption[0] = true;
       assumption[1] = true;
      a = wePlay ? 1 : -1;
       //super.fillTestPointsMap(); - is comented because not need to fill with date for we play (extended class) and here are new data:

       //both no fit
       testCountingPointsNoFitBothMap.put(20f, 0f, 0);
       testCountingPointsNoFitBothMap.put(10f, -660f, 0);
       testCountingPointsNoFitBothMap.put(10f, -100f, 11);
       testCountingPointsNoFitBothMap.put(30f, 660f, 0);
       testCountingPointsNoFitBothMap.put(30f, 100f, -11);

       //Fit only we - they no fit
       testCountingPointsFitWeMap.put(20f, 0f, -2);
       testCountingPointsFitWeMap.put(24f, 500f, 2);
       testCountingPointsFitWeMap.put(24f, 300f, -4);
       testCountingPointsFitWeMap.put(24f, 100f, -8);
       testCountingPointsFitWeMap.put(24f, -100f, -11);
       testCountingPointsFitWeMap.put(24f, -500f, -14);
       testCountingPointsFitWeMap.put(30f, -100f, -13);
       testCountingPointsFitWeMap.put(30f, 100f, -12);
       testCountingPointsFitWeMap.put(12f, -1660f, -14);
       testCountingPointsFitWeMap.put(28f, 1660f, 14);
       testCountingPointsFitWeMap.put(28f, 1660f, 14);
       testCountingPointsFitWeMap.put(12f, -1660f, -14);
       testCountingPointsFitWeMap.put(20f, 50f, 0);

       //both fit
       //  testCountingPointsBothAfterFitBothMap.put(20f, 0f, 2);  //not such case if both have 20PC and fit in major color mark only spades (one have fit)
       testCountingPointsFitBothMap.put(24f, -100f, -11);
       testCountingPointsFitBothMap.put(16f, 100f, 11);
       testCountingPointsFitBothMap.put(30f, 750f, 0);
       testCountingPointsFitBothMap.put(10f, -750f, 0);
       testCountingPointsFitBothMap.put(30f, 1250f, 11);
       testCountingPointsFitBothMap.put(10f, -1250f, -11);

       //Fit only they  - we not fit
       testCountingPointsFitTheyMap.put(10f, -100f, 12);
       testCountingPointsFitTheyMap.put(10f, 100f, 13);

       testCountingPointsFitTheyMap.put(12f, -1660f, -14);
       testCountingPointsFitTheyMap.put(12f, 1660f, 20);

       testCountingPointsFitTheyMap.put(20f, -50f, 0);
       testCountingPointsFitTheyMap.put(20f, 0f, 2);
       testCountingPointsFitTheyMap.put(20f,  50f, 3);  //I thout that it should be 2 but it count better - they have fit so they should play 50, We have 50 so we have 100 more

       testCountingPointsFitTheyMap.put(16f, -500f, -2);
       testCountingPointsFitTheyMap.put(16f, -300f, 4);
       testCountingPointsFitTheyMap.put(16f, -100f, 8);
       testCountingPointsFitTheyMap.put(16f, 100f, 11);
       testCountingPointsFitTheyMap.put(16f, 500f, 14);

       testCountingPointsFitTheyMap.put(28f, 1660f, 14);
       testCountingPointsFitTheyMap.put(28f, -1660f, -20);
       testCountingPointsFitTheyMap.put(30f, -100f, -13);
       testCountingPointsFitTheyMap.put(30f, 100f, -11);


   }
}
