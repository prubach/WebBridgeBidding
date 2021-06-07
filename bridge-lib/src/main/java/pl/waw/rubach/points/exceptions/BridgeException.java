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

/**
 * Parent class to all bridge exceptions.
 */
public class BridgeException extends Exception {

  /**
   * General constructor to be used by extended class.
   *
   * @param message is string message to print with the exception
   */
  protected BridgeException(final String message) {
    super(message);
  }

  /**
   * General bridge exception. No special cases...
   */
  public BridgeException() {
    super(ExceptionMessages.GENERAL_BRIDGE_EXCEPTION_CASE_MESSAGE);
  }


}
