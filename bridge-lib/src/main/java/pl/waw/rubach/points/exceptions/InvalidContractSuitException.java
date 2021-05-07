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

import static pl.waw.rubach.points.AbstractOneDeal.SUITS;

/**
 * Exception of invalid suits of cards declarer by user.
 */
public class InvalidContractSuitException extends BridgeException {

  /**
   * Suit which is checked in exception.
   */
  private final String contractSuit;

  /**
   * Exception of invalid suits of cards declarer by user.
   *
   * @param suit which is checked
   */
  public InvalidContractSuitException(final String suit) {
    super(SUITS.stream().anyMatch(suit::contains) ? WRONG_EXCEPTION_CASE_MESSAGE
        : String.format(ExceptionMessages.INVALID_CONTRACT_SUIT_MESSAGE, suit));
    this.contractSuit = suit;
  }

  /**
   * Getter to have parameter trigger this exception.
   *
   * @return contract suit
   */
  public String getContractSuit() {
    return contractSuit;
  }
}

