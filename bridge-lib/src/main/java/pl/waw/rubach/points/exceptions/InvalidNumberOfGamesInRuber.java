package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.NUMBEROFGAMEINRUBBER;

public class InvalidNumberOfGamesInRuber extends BridgeException {

    private int numberOfContract;

    public InvalidNumberOfGamesInRuber(int numberOfContractInRubber) {
        super(numberOfContractInRubber <= NUMBEROFGAMEINRUBBER && numberOfContractInRubber > 0 ?
                "Wyjątek ustawiony w złym przypadku - warunek nie jest spełniony" :
                "Nieprawidłowy numer gry-  podano: " + numberOfContractInRubber + " a powinna 1, 2, 3 lub 4- spróbuj jeszcze raz!");
        this.numberOfContract = numberOfContractInRubber;
    }


    public int getNumberOfContract() {
        return numberOfContract;
    }
}
