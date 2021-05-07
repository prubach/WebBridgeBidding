package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.NUMBER_OF_GAME_IN_RUBBER;
/**
 * Exception of invalid number of contracts in rubber.
 */
public class InvalidNumberOfGamesInRubber extends BridgeException {

  /**
   * Parameter to describe number of game in rubber.
   * Rubber in duplicated bridge is 4 games -with different assumption
   * so this parameter should not be bigger then 4 and less then 0 of course
   */
  private final int numberOfContractInRubber;
  /**
   * Exception of invalid number of contracts in this game declarer by user.
   *
   * @param numberOfContract which is checked
   */
  public InvalidNumberOfGamesInRubber(final int numberOfContract) {
    super(numberOfContract <= NUMBER_OF_GAME_IN_RUBBER
        && numberOfContract > 0
        ? WRONG_EXCEPTION_CASE_MESSAGE
        : String.format(ExceptionMessages.INVALID_NUMBER_OF_GAME_MESSAGE,
        numberOfContract));
    this.numberOfContractInRubber = numberOfContract;
  }

  /**
   * Getter to have parameter trigger this exception.
   *
   * @return number of contract in this game
   */
  public int getNumberOfContractInRubber() {
    return numberOfContractInRubber;
  }
}
