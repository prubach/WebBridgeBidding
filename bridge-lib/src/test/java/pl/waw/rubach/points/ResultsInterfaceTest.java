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

package pl.waw.rubach.points;

import pl.waw.rubach.points.duplicateBridgeImps.CalculatedImpPointsForOneDeal;
import pl.waw.rubach.points.exceptions.BridgeException;

import static org.junit.Assert.*;

public class ResultsInterfaceTest {
  boolean wePlay = true;
  boolean isRedouble = false;
  boolean isDouble = false;
  boolean auctionAssumptionWe = false;
  boolean isAuctionAssumptionThey = false;
  boolean fitWe = false;
  boolean fitThey = false;
  String contractSuit = "N";
  float pointsInBothHandsWe = 23f;
  int contractLevel = 5;
  int numberOfTrickTakenByWe = 9;


  public ResultsInterfaceTest() throws BridgeException {
    CalculatedImpPointsForOneDeal c = new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHandsWe, contractLevel,
        contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe,
        auctionAssumptionWe, isAuctionAssumptionThey, fitWe, fitThey);

    c.getResultsWe(true);
  }
}