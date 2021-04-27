package pl.waw.rubach.points.rubberBridge;

import org.junit.Before;

public class CalculatedRubberPointsForOneDealAfter extends CalculatedRubberPointsForOneDealBefore{



    @Before
    public void fillTestPointsMap() {

        wePlay = true;
        assumption[0] = true;
        assumption[1] = true;

        testCountingPointsDeclarerMajor.put(1,7,1,0,30);
        testCountingPointsDeclarerMajor.put(2,7,1,-100,0);
        testCountingPointsDeclarerMajor.put(3,9,1,0,90);
        testCountingPointsDeclarerMajor.put(3,8,1,-100,0);
        testCountingPointsDeclarerMajor.put(3,7,1,-200,0);
        testCountingPointsDeclarerMajor.put(4,10,1,0,120);
        testCountingPointsDeclarerMajor.put(4,12,1,60,120);
        testCountingPointsDeclarerMajor.put(4,12,2,450,240);

        testCountingPointsDeclarerNoTrumph.put(1, 9, 1, 60,40);
        testCountingPointsDeclarerNoTrumph.put(2, 9,  1,30,70);
        testCountingPointsDeclarerNoTrumph.put(3, 9, 1,0,100);
        testCountingPointsDeclarerNoTrumph.put(3, 8,  1,-100,0);
        testCountingPointsDeclarerNoTrumph.put(3, 7,  1,-200,0);
        testCountingPointsDeclarerNoTrumph.put(3, 10, 1,30,100);
        testCountingPointsDeclarerNoTrumph.put(4, 10, 1,0,130);
        testCountingPointsDeclarerNoTrumph.put(5, 11, 1,0,160);
        testCountingPointsDeclarerNoTrumph.put(6, 12, 1,750,190);
        testCountingPointsDeclarerNoTrumph.put(7, 13, 1,1500,220);

        testCountingPointsDeclarerNoTrumph.put(1, 9, 2,450,80);
        testCountingPointsDeclarerNoTrumph.put(2, 9,  2,250,140);
        testCountingPointsDeclarerNoTrumph.put(3, 9, 2,50,200);
        testCountingPointsDeclarerNoTrumph.put(3, 8,  2,-200,0);
         testCountingPointsDeclarerNoTrumph.put(3, 7,  2,-500,0);
        testCountingPointsDeclarerNoTrumph.put(3, 6,  2,-800,0);
        testCountingPointsDeclarerNoTrumph.put(3, 10, 2,250,200);
        testCountingPointsDeclarerNoTrumph.put(4, 10, 2,50,260);
        testCountingPointsDeclarerNoTrumph.put(5, 11, 2,50, 320);
        testCountingPointsDeclarerNoTrumph.put(6, 12, 2,800,380);
        testCountingPointsDeclarerNoTrumph.put(7, 13, 2,1550,440);

        testCountingPointsDeclarerNoTrumph.put(1, 9, 4,900,160);
        testCountingPointsDeclarerNoTrumph.put(2, 9,  4,500,280);
    }





}
