package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.OneDeal.NUBEROFTRICS;

public class InvalidNumberOfTrickTakenException extends BridgeException {
    private int numberOfTricksTaken;


    public InvalidNumberOfTrickTakenException(int numberOfTricksTaken) {
        super( numberOfTricksTaken <0 | numberOfTricksTaken >NUBEROFTRICS ? "Wyjątek ustawiony w złym przypadku - warunek nie jest spełniony": "liczba wziętych lew źle podana: podano" + numberOfTricksTaken +
            "a powinno być mniej niż "+ NUBEROFTRICS +" - spróbuj jeszcze raz");

    this.numberOfTricksTaken=numberOfTricksTaken;}

    public int getNumberOfTricksTaken() {
        return numberOfTricksTaken;
    }
}
