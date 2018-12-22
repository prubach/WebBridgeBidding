package pl.waw.rubach.points.exceptions;

public class InvalidNumberOfTrickTakenException extends BridgeException {
    private int numberOfTricksTaken;

    public InvalidNumberOfTrickTakenException(int numberOfTricksTaken) {super("liczba wziętych lew źle podana: podano" + numberOfTricksTaken +
            "a powinno być mniej niż 13 - spróbuj jeszcze raz");

    this.numberOfTricksTaken=numberOfTricksTaken;}

    public int getNumberOfTricksTaken() {
        return numberOfTricksTaken;
    }
}
