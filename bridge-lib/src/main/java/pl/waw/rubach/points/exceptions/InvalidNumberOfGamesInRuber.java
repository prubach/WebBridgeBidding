package pl.waw.rubach.points.exceptions;

public class InvalidNumberOfGamesInRuber extends BridgeException {

    public InvalidNumberOfGamesInRuber(int numberGiven) {
        super("Nieprawidłowy numer gry-  podano: " + numberGiven + " a powinna 1, 2, 3 lub 4- spróbuj jeszcze raz!");
         this.numberGiven = numberGiven;
    }

}
