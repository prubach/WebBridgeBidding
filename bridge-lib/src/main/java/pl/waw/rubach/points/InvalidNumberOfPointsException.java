package pl.waw.rubach.points;

public class InvalidNumberOfPointsException extends BridgeException {

    int pointsGiven;

    public InvalidNumberOfPointsException(String message) {
        super(message);
    }

    public InvalidNumberOfPointsException(String message, int pointsGiven) {
        super(message);
        this.pointsGiven = pointsGiven;
    }

    public int getPointsGiven() {
        return pointsGiven;
    }
}
