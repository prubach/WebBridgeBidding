/*
 *
 *  *                     GNU GENERAL PUBLIC LICENSE
 *  *                        Version 0.8 ,  8. 05. 2021
 *  *
 *  *                   "Bridge-lib"  application as help
 *  *                  for bidding, and points counting
 *   *     Copyright (C) {2017}  {Paweł Rubach, Magdalena Wilska}
 *  *
 *  *     This program is free software: you can redistribute it and/or modify
 *  *     it under the terms of the GNU General Public License as published by
 *  *     the Free Software Foundation, either version 3 of the License, or
 *  *      any later version.
 *  *
 *  *     This program is distributed in the hope that it will be useful,
 *  *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *     GNU General Public License for more details.
 *  *
 *  *
 *
 */

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
