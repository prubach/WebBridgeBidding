package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.OneDeal.NUMBEROFTRICS;

public class InvalidNumberOfTrickTakenException extends BridgeException {

    private final int numberOfTricksTaken;

    public InvalidNumberOfTrickTakenException(int numberOfTricksTaken) {
        super(numberOfTricksTaken < 0 | numberOfTricksTaken > NUMBEROFTRICS ? wrongExceptionCaseMessage
                : "liczba wziętych lew źle podana: podano" + numberOfTricksTaken
                + "a powinno być mniej niż " + NUMBEROFTRICS + " - spróbuj jeszcze raz");

        this.numberOfTricksTaken = numberOfTricksTaken;
    }

    public int getNumberOfTricksTaken() {
        return numberOfTricksTaken;
    }
}
