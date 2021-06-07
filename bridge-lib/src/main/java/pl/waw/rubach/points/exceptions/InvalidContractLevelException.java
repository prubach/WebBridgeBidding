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

import static pl.waw.rubach.points.AbstractOneDeal.MAX_CONTRACT_LEVEL;
import static pl.waw.rubach.points.AbstractOneDeal.MIN_CONTRACT_LEVEL;

/**
 * Exception of invalid contract level.
 */
public class InvalidContractLevelException extends BridgeException {

  /**
   * Contract level which trigger this exception.
   */
  private final int contractLevel;

  /**
   * Exception of invalid contract level.
   *
   * @param level is  contract level
   */
  public InvalidContractLevelException(final int level) {
    super(level > MAX_CONTRACT_LEVEL | level < MIN_CONTRACT_LEVEL
        ? String.format(ExceptionMessages.INVALID_CONTRACT_LEVEL_MESSAGE,
        level)
        : ExceptionMessages.WRONG_EXCEPTION_CASE_MESSAGE);
    this.contractLevel = level;
  }

  /**
   * Getter to have parameter trigger this exception.
   *
   * @return contract level
   */
  public int getContractLevel() {
    return contractLevel;
  }
}

