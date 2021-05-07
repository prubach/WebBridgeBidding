package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.AbstractOneDeal.NUMBEROFTRICS;
/**
 * Exception of invalid number of tricks taken.
 */
public class InvalidNumberOfTrickTakenException extends BridgeException {

  /**
   * Parameter to describe number of tricks taken in game.
   * This should be bigger then 0 (is not possible to take minus tricks)
   * and less then 13 because there are not more cards..
   */
  private final int numberOfTricksTaken;
    /**
     * Exception of invalid number of tricks taken declarer by user.
     *
     * @param tricksTaken which is checked
     */
  public InvalidNumberOfTrickTakenException(final int tricksTaken) {
    super(tricksTaken < 0 | tricksTaken > NUMBEROFTRICS
        ? String.format(
            ExceptionMessages.INVALID_NUMBER_OF_TRICKS_TAKEN_MESSAGE,
            tricksTaken)
        : WRONG_EXCEPTION_CASE_MESSAGE);

    this.numberOfTricksTaken = tricksTaken;
  }
    /**
     * Getter to have parameter trigger this exception.
     *
     * @return number of tricks taken
     */
  public int getNumberOfTricksTaken() {
    return numberOfTricksTaken;
  }
}
