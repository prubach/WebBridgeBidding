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
 * Exception  of end of the rubber (whole game).
 */
public class EndOfRubberException extends BridgeException {
  /**
   * Parameter describing final scoring before this exception is trigger.
   */
  private final int finalScore;

  /**
   * Exception of end of the rubber (whole game).
   *
   * @param finalScoreParameter describe the score
   * @param winWe               describe if we win
   * @param winThey             describe if they win
   */
  public EndOfRubberException(
      //pyt czy taki wyjątek to dobry pomysł czy próbować to zrobić też dla gry na impy?
      final int finalScoreParameter,
      final boolean winWe, final boolean winThey) {
    super(winWe || winThey
        ? String.format(ExceptionMessages.END_OF_RUBBER_POSITIVE_MESSAGE,
        finalScoreParameter)
        : ExceptionMessages.END_OF_RUBBER_NEGATIVE_MESSAGE);
    this.finalScore = finalScoreParameter;
  }

  /**
   * Getter to have parameter trigger this exception.
   *
   * @return value of final score
   */
  public int getFinalScore() {
    return finalScore;
  }
}
