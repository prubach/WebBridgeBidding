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

import static pl.waw.rubach.points.AbstractOneDeal.NUMBER_OF_TRICKS;
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
    super(tricksTaken < 0 | tricksTaken > NUMBER_OF_TRICKS
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
