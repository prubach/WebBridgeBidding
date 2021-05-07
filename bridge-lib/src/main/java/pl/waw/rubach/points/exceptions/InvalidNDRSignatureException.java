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

import static pl.waw.rubach.points.AbstractOneDeal.IS_DOUBLE;
import static pl.waw.rubach.points.AbstractOneDeal.IS_REDOUBLE;
import static pl.waw.rubach.points.AbstractOneDeal.IS_UNDOUBTED;

/**
 * Exception of invalid undoubted/ double/ redouble signature.
 */
public class InvalidNDRSignatureException extends BridgeException {
  /**
   * Number to have is contract normal/double/redouble.
   * If it is:
   * 1 - is normal
   * 2 - is double
   * 4 - is redouble
   * Other value are not possible.
   */
  private final int nDRSign;

  /**
   * Exception checking if state of game is correct.
   *
   * @param nRDsign parameter to describe
   *                if contract is normal/double/redouble is correct.
   */
  public InvalidNDRSignatureException(final int nRDsign) {
    super((nRDsign == IS_UNDOUBTED
        || nRDsign == IS_DOUBLE
        || nRDsign == IS_REDOUBLE)
        ? WRONG_EXCEPTION_CASE_MESSAGE
        : String.format(ExceptionMessages.INVALID_NDRSIGNATURE_MESSAGE,
        nRDsign));

    this.nDRSign = nRDsign;
  }

  /**
   * Getter to have parameter trigger this exception.
   *
   * @return contract suit
   */
  public int getNDRSignature() {
    return nDRSign;
  }
}
