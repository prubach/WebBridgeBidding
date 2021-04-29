package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.NUMBEROFGAMEINRUBBER;

public class InvalidNumberOfGamesInRubber extends BridgeException {

  private final int numberOfContract;

  public InvalidNumberOfGamesInRubber(int numberOfContractInRubber) {
    super(numberOfContractInRubber <= NUMBEROFGAMEINRUBBER && numberOfContractInRubber > 0
        ? wrongExceptionCaseMessage
        : "Nieprawidłowy numer gry-  podano: " + numberOfContractInRubber
        + " a powinna 1, 2, 3 lub 4- spróbuj jeszcze raz!");
    this.numberOfContract = numberOfContractInRubber;
  }


  public int getNumberOfContract() {
    return numberOfContract;
  }
}
