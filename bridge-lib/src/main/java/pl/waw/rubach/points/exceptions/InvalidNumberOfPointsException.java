package pl.waw.rubach.points.exceptions;

public class InvalidNumberOfPointsException extends BridgeException {

    private float pointsGiven;

   // public InvalidNumberOfPointsException(String message) {
   //     super(message);
   // }

    public InvalidNumberOfPointsException(float pointsGiven) {
        super("Nieprawidłowo podana liczba punktów na obu rękach partnerów-  podano: " + pointsGiven + " a powinna być liczba dodatnia  i nie większa od 40 - spróbuj jeszcze raz!");
         this.pointsGiven = pointsGiven;
    }

    public float getPointsGiven() {
        return pointsGiven;
    }
}
