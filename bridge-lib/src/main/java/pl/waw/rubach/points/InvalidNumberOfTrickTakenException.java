package pl.waw.rubach.points;

public class InvalidNumberOfTrickTakenException extends BridgeException {
    public InvalidNumberOfTrickTakenException(String message) {
        super(message);
    }

    public InvalidNumberOfTrickTakenException(int numberOfTricksTaken) {super("liczba wziętych lew źle podana - spróbuj jeszcze raz");}
    //pyt czy mogłoby tu sprawdzać warunek a nie w kodzie?
    }
