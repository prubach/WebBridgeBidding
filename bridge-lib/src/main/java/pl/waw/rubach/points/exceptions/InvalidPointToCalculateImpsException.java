package pl.waw.rubach.points.exceptions;

/**
 * Exception of calculation of program.
 */
public class InvalidPointToCalculateImpsException extends BridgeException {

  /**
   * Exception of less then zero points difference to calculate imps.
   * It is not possible because imp table have only positive values.
   * Not possible according method of scoring - should be error some where ...
   *
   * @param pointDiff - is points difference between expected and real scoring
   */
  public InvalidPointToCalculateImpsException(final int pointDiff) {
    super(pointDiff > 0
        ? WRONG_EXCEPTION_CASE_MESSAGE
        : String.format(ExceptionMessages.INVALID_POINTS_TO_IMPS_MESSAGE,
        pointDiff));
  }
}
