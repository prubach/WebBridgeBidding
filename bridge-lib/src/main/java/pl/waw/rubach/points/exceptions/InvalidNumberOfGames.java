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

import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.NUMBER_OF_GAME_IN_RUBBER;
/**
 * Exception of invalid number of contracts in rubber.
 */
public class InvalidNumberOfGames extends BridgeException {

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
  public InvalidNumberOfGames(final int numberOfContract) {
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
