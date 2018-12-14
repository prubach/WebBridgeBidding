package pl.waw.rubach.points;

public class InvalidNumberOfGamesInRuber extends BridgeException {

    private int numberGiven;

    public InvalidNumberOfGamesInRuber(String message) {
        super(message);
    }

    public InvalidNumberOfGamesInRuber(int numberGiven) {
        super("Nieprawidłowy numer gry-  podano: " + numberGiven + " a powinna 1, 2, 3 lub 4- spróbuj jeszcze raz!");
         this.numberGiven = numberGiven;
    }

    public int getNumberGiven() {
        return numberGiven;
    }
}
