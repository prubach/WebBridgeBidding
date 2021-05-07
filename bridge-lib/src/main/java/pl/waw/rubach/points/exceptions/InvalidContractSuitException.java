package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.AbstractOneDeal.SUITS;

/**
 * Exception of invalid suits of cards declarer by user.
 */
public class InvalidContractSuitException extends BridgeException {

  /**
   * Suit which is checked in exception.
   */
  private final String contractSuit;

  /**
   * Exception of invalid suits of cards declarer by user.
   *
   * @param suit which is checked
   */
  public InvalidContractSuitException(final String suit) {
    super(SUITS.stream().anyMatch(suit::contains) ? WRONG_EXCEPTION_CASE_MESSAGE
        : String.format(ExceptionMessages.INVALID_CONTRACT_SUIT_MESSAGE, suit));
    this.contractSuit = suit;
  }

  /**
   * Getter to have parameter trigger this exception.
   *
   * @return contract suit
   */
  public String getContractSuit() {
    return contractSuit;
  }
}

