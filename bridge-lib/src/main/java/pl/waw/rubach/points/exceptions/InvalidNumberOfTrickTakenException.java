package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.AbstractOneDeal.NUMBEROFTRICS;

public class InvalidNumberOfTrickTakenException extends BridgeException {

    private final int numberOfTricksTaken;

    public InvalidNumberOfTrickTakenException(final int tricksTaken) {
        super(tricksTaken < 0 | tricksTaken > NUMBEROFTRICS
                ? String.format(ExceptionMessages.INVALID_NUMBER_OF_TRICKS_TAKEN_MESSAGE,tricksTaken)
                : WRONG_EXCEPTION_CASE_MESSAGE);

        this.numberOfTricksTaken = tricksTaken;
    }

    public int getNumberOfTricksTaken() {
        return numberOfTricksTaken;
    }
}
