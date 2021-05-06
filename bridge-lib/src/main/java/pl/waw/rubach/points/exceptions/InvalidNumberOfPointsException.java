package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MAXNUBEROFPOINTS;
import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MINNUMBEROFPOINTS;

public class InvalidNumberOfPointsException extends BridgeException {
  /**
   * Field to have points making this exception.
   */
  private final float pointsGiven;

  /**
   * Exception to control possible number of points in hands.
   * @param points checked number of points in hands
   */
  public InvalidNumberOfPointsException(final float points) {
    super(points <= MAXNUBEROFPOINTS && points >= MINNUMBEROFPOINTS
        ? WRONG_EXCEPTION_CASE_MESSAGE
        : String.format(ExceptionMessages.INVALID_NUMBER_OF_POINTS_MESSAGE,
        points));
    this.pointsGiven = points;
  }

  /**
   * Getter to see which number of points make this exception.
   * @return points
   * */
  public float getPointsGiven() {
    return pointsGiven;
  }
}
