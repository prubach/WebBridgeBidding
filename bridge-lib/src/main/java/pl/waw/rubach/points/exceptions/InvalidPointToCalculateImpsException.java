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

import pl.waw.rubach.points.duplicateBridgeImps.OneDealImp;


/**
 * Exception of calculation of program.
 */
public class InvalidPointToCalculateImpsException extends BridgeException {

  /**
   * Parameter describing points which are look for in the ImpTable.
   */
  private final int pointDifference;

  /**
   * Exception testing number of points checked in Imp table.
   * It should no be less then zero points difference
   * because imp table have only positive values.
   * It should not be bigger then 10000 because implementation have maximum.
   * Not possible according method of scoring - should be error some where ...
   *
   * @param pointDiff - is points difference between expected and real scoring
   */
  public InvalidPointToCalculateImpsException(final int pointDiff) {
    super(pointDiff > 0
        && pointDiff < OneDealImp.MAX_NUMBER_OF_POINTS_BETWEEN_EXPECTED
        ? ExceptionMessages.WRONG_EXCEPTION_CASE_MESSAGE
        : String.format(ExceptionMessages.INVALID_POINTS_TO_IMPS_MESSAGE,
        pointDiff));
    this.pointDifference = pointDiff;
  }

  /**
   * Getter to have parameter trigger this exception.
   *
   * @return points difference
   */
  public int getPointDifference() {
    return pointDifference;
  }
}
