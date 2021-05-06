package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.AbstractOneDeal.NUMBEROFTRICS;

public class InvalidNumberOfTrickTakenException extends BridgeException {

    private final int numberOfTricksTaken;

    public InvalidNumberOfTrickTakenException(int numberOfTricksTaken) {
        super(numberOfTricksTaken < 0 | numberOfTricksTaken > NUMBEROFTRICS ?
                 "liczba wziętych lew źle podana: podano" + numberOfTricksTaken
                + " a powinno być mniej niż" + NUMBEROFTRICS + " - spróbuj jeszcze raz"
            :WRONG_EXCEPTION_CASE_MESSAGE);

        this.numberOfTricksTaken = numberOfTricksTaken;
    }

    public int getNumberOfTricksTaken() {
        return numberOfTricksTaken;
    }
}
