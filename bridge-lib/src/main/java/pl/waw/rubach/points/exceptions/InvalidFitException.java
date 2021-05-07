package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MEDIUM_NUMBER_OF_POINTS;

/**
 * Exception of not correct given fits for 20 points.
 */
public class InvalidFitException extends BridgeException {
  /**
   * Exception of  invalid  parameters to this method of scoring.
   * is not possible both have fit with 20 points because
   * it should be mark only spades in this case according rules
   *
   * @param numberPoints of pair
   * @param fitWe        if we have 8 card in major suit
   * @param fitThey      if they have 8 cards in major suit
   */
  public InvalidFitException(
      final float numberPoints,
      final boolean fitWe,
      final boolean fitThey) {
    super(!fitWe || !fitThey
        || numberPoints != MEDIUM_NUMBER_OF_POINTS
        ? WRONG_EXCEPTION_CASE_MESSAGE
        : String.format(ExceptionMessages.INVALID_FIT_MESSAGE, numberPoints));

  }

}
