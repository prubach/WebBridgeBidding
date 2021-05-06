package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.AbstractOneDeal.SUITS;

/**
 * Exception of invalid suits of cards declarer by user.
 */
public class InvalidContractSuitException extends BridgeException {

  private final String contractSuit;

  /**
   * Exception of invalid suits of cards declarer by user.
   */
  public InvalidContractSuitException(String suit) {
    super(SUITS.stream().anyMatch(suit::contains) ? WRONG_EXCEPTION_CASE_MESSAGE :
       String.format(ExceptionMessages.INVALID_CONTRACT_SUIT_MESSAGE,suit));
    this.contractSuit = suit;
  }

  public String getContractSuit() {
    return contractSuit;
  }
}

