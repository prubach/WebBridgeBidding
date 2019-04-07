package pl.waw.rubach.points.exceptions;

import java.util.Optional;

public class EndOfRubberException extends BridgeException {


    private int finalScore;


    public EndOfRubberException(int finalScore) {
        super(Optional.of(finalScore).map(finalScore1 -> "Rober jest zakończony z wynikiem: " + finalScore1 + " dla nas ").orElse("Wyjątek ustawiony w złym przypadku - warunek nie jest spełniony"));
        this.finalScore = finalScore;
    }

    public int getContractLevel() {
        return finalScore;
    }
}
