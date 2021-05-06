package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MAXNUBEROFPOINTS;
import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MINNUMBEROFPOINTS;

public class InvalidNumberOfPointsException extends BridgeException {

    private final float pointsGiven;


    public InvalidNumberOfPointsException(float pointsGiven) {
        super(pointsGiven <= MAXNUBEROFPOINTS && pointsGiven >= MINNUMBEROFPOINTS
            ? WRONG_EXCEPTION_CASE_MESSAGE : "Nieprawidłowo podana liczba punktów na obu rękach partnerów-  podano: " + pointsGiven
                + " a powinna być liczba dodatnia  i nie większa od " + MAXNUBEROFPOINTS + " - spróbuj jeszcze raz!");
        this.pointsGiven = pointsGiven;
    }

    public float getPointsGiven() {
        return pointsGiven;
    }
}
