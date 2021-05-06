package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.OneDeal.SUITS;

/**
 * Exception of invalid suits of cards declarer by user.
 */
public class InvalidContractSuitException extends BridgeException {

  private final String contractSuit;

  /**
   * Exception of invalid suits of cards declarer by user.
   */
  public InvalidContractSuitException(String inputValue) {
    super(SUITS.stream().anyMatch(inputValue::contains) ? WRONG_EXCEPTION_CASE_MESSAGE :
        "Nie ma takiego koloru: podałeś " + inputValue + " - wpisz jeszcze raz.");
    this.contractSuit = inputValue;
  }

  public String getContractSuit() {
    return contractSuit;
  }
}

