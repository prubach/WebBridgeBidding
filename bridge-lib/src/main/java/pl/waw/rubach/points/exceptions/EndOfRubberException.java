package pl.waw.rubach.points.exceptions;

/**
 * Exception  of end of the rubber (whole game).
 */
public class EndOfRubberException extends BridgeException {

  private final int finalScore;

  /**
   * Exception of end of the rubber (whole game).
   *
   * @param finalScoreParameter describe the score
   * @param winWe describe if we win
   * @param winThey describe if they win
   * */
  public EndOfRubberException(
      //pyt czy taki wyjątek to dobry pomysł czy próbować to zrobić też dla gry na impy?
      final int finalScoreParameter,
      final boolean winWe, final boolean winThey) {
    super(winWe || winThey
        ? String.format(ExceptionMessages.END_OF_RUBBER_POSITIVE_MESSAGE,
                                                   finalScoreParameter)
        : ExceptionMessages.END_OF_RUBBER_NEGATIVE_MESSAGE);
    this.finalScore = finalScoreParameter;
  }

  public int getFinalScore() {
    return finalScore;
  }
}
