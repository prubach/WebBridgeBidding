package pl.waw.rubach.points.exceptions;

public class InvalidPointToCalculateImpsException extends BridgeException{

  /**
   * Exception of less then zero points difference (to calculate imps), because imp table have only positive values.
   * Not possible according method of scoring - should be error some where ...
   * @param pointDiff - is points difference between expected and real scoring
   *
   */
  public InvalidPointToCalculateImpsException(int pointDiff) {
    super(pointDiff >0 ? WRONG_EXCEPTION_CASE_MESSAGE : "Błąd różnicy punktów - "+ pointDiff +" mniejsza od zera- to jakiś błąd programu");
  }
}
