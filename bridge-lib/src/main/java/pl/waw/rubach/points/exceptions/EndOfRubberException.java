package pl.waw.rubach.points.exceptions;

public class EndOfRubberException extends BridgeException {



    private int finalScore;

    /**
     * Exception of end of the rubber (whole game)
     */
    //pyt czy taki wyjątek to dobry pomysł czy próbowąć to zrobić też dla gry na impy?
    public EndOfRubberException(int finalScore, boolean winWe, boolean winThey) {
        super(winWe || winThey ? "Rober jest zakończony z wynikiem: " + finalScore + " dla nas " :"Wyjątek ustawiony w złym przypadku - warunek nie jest spełniony");
        this.finalScore = finalScore;
    }

    public int getContractLevel() {
        return finalScore;
    }
}
