package pl.waw.rubach.points.bridgeExeption;

public class InvalidNormalDoubleRedoubleSignature extends BridgeException {

    private int numberGiven;

    public InvalidNormalDoubleRedoubleSignature(String message) {
        super(message);
    }

    public InvalidNormalDoubleRedoubleSignature(int numberGiven) {
        super("Nieprawidłowy parametr gry-  podano: " + numberGiven + " a powinna 1, 2, lub 4- spróbuj jeszcze raz!");
        //todo opisać instrukcje co jest co?
        this.numberGiven = numberGiven;
    }

    public int getNumberGiven() {
        return numberGiven;
    }
}