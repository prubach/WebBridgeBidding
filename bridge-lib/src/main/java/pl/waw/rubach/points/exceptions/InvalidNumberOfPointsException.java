package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MAX_NUMBER_OF_POINTS;
import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MIN_NUMBER_OF_POINTS;

/**
 * Exception of invalid number of points in hands of player.
 */
public class InvalidNumberOfPointsException extends BridgeException {
  /**
   * Field to have points making this exception.
   */
  private final float pointsGiven;

  /**
   * Exception to control possible number of points in hands.
   *
   * @param points checked number of points in hands
   */
  public InvalidNumberOfPointsException(final float points) {
    super(points <= MAX_NUMBER_OF_POINTS && points >= MIN_NUMBER_OF_POINTS
        ? WRONG_EXCEPTION_CASE_MESSAGE
        : String.format(ExceptionMessages.INVALID_NUMBER_OF_POINTS_MESSAGE,
        points));
    this.pointsGiven = points;
  }

  /**
   * Getter to have parameter trigger this exception.
   *
   * @return points
   */
  public float getPointsGiven() {
    return pointsGiven;
  }
}
