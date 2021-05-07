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

import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MEDIUM_NUMBER_OF_POINTS;

/**
 * Exception of not correct given fits for 20 points.
 */
public class InvalidFitException extends BridgeException {
  /**
   * Exception of  invalid  parameters in special case 20 points.
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
