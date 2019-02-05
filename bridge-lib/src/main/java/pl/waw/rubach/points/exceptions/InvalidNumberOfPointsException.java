package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.DeclarerPointsForOneDeal.MAXNUBEROFPOINTS;

public class InvalidNumberOfPointsException extends BridgeException {

    private float pointsGiven;


    public InvalidNumberOfPointsException(float pointsGiven) {
        super(pointsGiven<MAXNUBEROFPOINTS ?  "Wyjątek ustawiony w złym przypadku - warunek nie jest spełniony":
                "Nieprawidłowo podana liczba punktów na obu rękach partnerów-  podano: " + pointsGiven + " a powinna być liczba dodatnia  i nie większa od "+MAXNUBEROFPOINTS+" - spróbuj jeszcze raz!");
         this.pointsGiven = pointsGiven;
    }

    public float getPointsGiven() {
        return pointsGiven;
    }
}
